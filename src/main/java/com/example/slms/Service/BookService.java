package com.example.slms.Service;

import com.example.slms.Entity.Book;
import com.example.slms.Entity.BookDetailsProjection;
import java.util.List;

public interface BookService {

    BookDetailsProjection customBookDetails(long id);
    List<Book> findAllByCategory(String category);      // Pending

//    Book findByBookName(String bookName);
    Book findById(long id);
    List<Book> findAllBooks();          // Done

    List<Book> findAllAvailableBooks(boolean isAvailable);        // Done
    boolean isAvailable(long id);       // Done

    Book addBook(Book book);
    boolean deleteBook(long id);
    Book updateBook(long id, Book book);

}
