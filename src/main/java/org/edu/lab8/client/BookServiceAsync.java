package org.edu.lab8.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.edu.lab8.shared.BookRecord;

import java.util.List;

/**
 * Асинхронный интерфейс сервиса {@link BookService}, используемый клиентским кодом.
 */
public interface BookServiceAsync {
    /**
     * Асинхронно запрашивает список читателей.
     *
     * @param callback обработчик результата
     */
    void getReaders(AsyncCallback<List<String>> callback);

    /**
     * Асинхронно запрашивает список книг читателя.
     *
     * @param reader   ФИО читателя
     * @param callback обработчик результата
     */
    void getBooks(String reader, AsyncCallback<List<BookRecord>> callback);
}
