package com.example.slms.Service;

import com.example.slms.Entity.Book;
import com.example.slms.Entity.BookDetailsProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookService {

    BookDetailsProjection customBookDetails(long id);   // PENDING
    List<Book> findAllByCategory(String category);            // DONE

    Book findByBookName(String bookName);    // DONE
    Book findById(long id);                  // DONE
    List<Book> findAllBooks();               // Done

    List<Book> findAllAvailableBooks(boolean isAvailable);    // Done
    boolean isAvailable(long id);                             // Done

    Book addBook(Book book, long userId);                // NA
    boolean deleteBook(long id);            // NA
    Book updateBook(long id, Book book);    // NA
    Book updateBorrower(long bookId, long userId);            // Done
}
