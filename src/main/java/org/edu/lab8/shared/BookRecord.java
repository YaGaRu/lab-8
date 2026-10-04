package org.edu.lab8.shared;

import java.io.Serializable;

/**
 * Запись о книге, выданной читателю. Объект передаётся с сервера на клиент по GWT RPC,
 * поэтому класс сериализуем и имеет конструктор без параметров.
 */
public class BookRecord implements Serializable {
    private String author;
    private String title;
    private boolean read;

    /**
     * Конструктор без параметров, необходимый для сериализации GWT.
     */
    public BookRecord() {
    }

    /**
     * Создаёт запись о книге.
     *
     * @param author автор книги
     * @param title  название книги
     * @param read   признак того, что книга прочитана
     */
    public BookRecord(String author, String title, boolean read) {
        this.author = author;
        this.title = title;
        this.read = read;
    }

    /**
     * Возвращает автора книги.
     *
     * @return автор
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Возвращает название книги.
     *
     * @return название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Показывает, прочитана ли книга.
     *
     * @return {@code true}, если книга прочитана
     */
    public boolean isRead() {
        return read;
    }
}
