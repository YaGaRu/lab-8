package org.edu.lab8.server;

import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;
import org.edu.lab8.client.BookService;
import org.edu.lab8.shared.BookRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Серверная реализация сервиса библиотеки. Данные хранятся в памяти сервера.
 */
public class BookServiceImpl extends RemoteServiceServlet implements BookService {

    /**
     * Возвращает список читателей библиотеки.
     *
     * @return ФИО читателей
     */
    @Override
    public List<String> getReaders() {
        List<String> readers = new ArrayList<>();
        readers.add("Иванов И.И.");
        readers.add("Петрова П.П.");
        readers.add("Сидоров С.С.");
        return readers;
    }

    /**
     * Возвращает список книг, выданных читателю.
     *
     * @param reader ФИО читателя
     * @return список книг; пустой список, если читатель не найден
     */
    @Override
    public List<BookRecord> getBooks(String reader) {
        List<BookRecord> books = new ArrayList<>();
        if ("Иванов И.И.".equals(reader)) {
            books.add(new BookRecord("Булгаков", "Мастер и Маргарита", true));
            books.add(new BookRecord("Достоевский", "Преступление и наказание", true));
            books.add(new BookRecord("Толстой", "Война и мир", false));
        } else if ("Петрова П.П.".equals(reader)) {
            books.add(new BookRecord("Толстой", "Анна Каренина", false));
            books.add(new BookRecord("Достоевский", "Игрок", true));
        } else if ("Сидоров С.С.".equals(reader)) {
            books.add(new BookRecord("Пелевин", "Чапаев и Пустота", false));
        }
        return books;
    }
}
