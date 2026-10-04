package org.edu.lab8.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.edu.lab8.shared.BookRecord;

import java.util.List;

/**
 * Точка входа GWT-приложения «Книги читателей»: выбор читателя и показ списка его книг.
 */
public class BookModule implements EntryPoint {
    private final BookServiceAsync service = GWT.create(BookService.class);
    private final ListBox readers = new ListBox();

    /**
     * Строит пользовательский интерфейс и загружает список читателей с сервера.
     */
    @Override
    public void onModuleLoad() {
        Button show = new Button("Получить список книг");
        show.addClickHandler(event -> loadBooks(readers.getSelectedValue()));

        HorizontalPanel row = new HorizontalPanel();
        row.setSpacing(6);
        row.add(readers);
        row.add(show);

        VerticalPanel main = new VerticalPanel();
        main.add(new Label("Выберите читателя:"));
        main.add(row);
        RootPanel.get("container").add(main);

        service.getReaders(new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Не удалось получить список читателей: " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<String> result) {
                for (String name : result) {
                    readers.addItem(name);
                }
            }
        });
    }

    /**
     * Запрашивает у сервера книги читателя и показывает их в диалоговом окне.
     *
     * @param reader ФИО читателя
     */
    private void loadBooks(String reader) {
        service.getBooks(reader, new AsyncCallback<List<BookRecord>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка вызова сервиса: " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<BookRecord> books) {
                showDialog(reader, books);
            }
        });
    }

    private void showDialog(String reader, List<BookRecord> books) {
        final DialogBox dialog = new DialogBox();
        dialog.setText("Список книг читателя " + reader);
        dialog.setGlassEnabled(false);

        FlexTable table = new FlexTable();
        table.setCellPadding(6);
        table.setText(0, 0, "Автор книги");
        table.setText(0, 1, "Название книги");
        table.setText(0, 2, "Прочитал");
        int row = 1;
        for (BookRecord b : books) {
            table.setText(row, 0, b.getAuthor());
            table.setText(row, 1, b.getTitle());
            table.setText(row, 2, b.isRead() ? "Да" : "Нет");
            row++;
        }

        Button close = new Button("Закрыть");
        close.addClickHandler(event -> dialog.hide());

        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(8);
        panel.add(table);
        panel.add(close);
        dialog.setWidget(panel);
        dialog.center();
    }
}
