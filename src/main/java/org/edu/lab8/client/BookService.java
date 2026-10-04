package org.edu.lab8.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.edu.lab8.shared.BookRecord;

import java.util.List;

/**
 * Синхронный интерфейс удалённого сервиса библиотеки (GWT RPC).
 */
@RemoteServiceRelativePath("bookService")
public interface BookService extends RemoteService {
    /**
     * Возвращает список читателей библиотеки.
     *
     * @return ФИО читателей
     */
    List<String> getReaders();

    /**
     * Возвращает список книг, выданных читателю.
     *
     * @param reader ФИО читателя
     * @return список книг
     */
    List<BookRecord> getBooks(String reader);
}
