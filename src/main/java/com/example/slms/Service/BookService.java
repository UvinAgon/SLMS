package com.example.slms.Service;

import com.example.slms.Entity.Book;
import com.example.slms.Entity.BookDetailsProjection;
import java.util.List;

public interface BookService {

    BookDetailsProjection customBookDetails(long id);   // PENDING
    List<Book> findAllByCategory(String category);            // DONE

    Book findByBookName(String bookName);    // DONE
    Book findById(long id);                  // DONE
    List<Book> findAllBooks();               // Done

    List<Book> findAllAvailableBooks(boolean isAvailable);    // Done
    boolean isAvailable(long id);                             // Done

    Book addBook(Book book);                // NA
    boolean deleteBook(long id);            // NA
    Book updateBook(long id, Book book);    // NA

}
