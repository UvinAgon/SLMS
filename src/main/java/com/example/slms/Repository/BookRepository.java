package com.example.slms.Repository;

import com.example.slms.Entity.Book;
import com.example.slms.Entity.BookDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookName(String bookName);

    @Query("SELECT b FROM Book b WHERE b.isAvailable = :isAvailable")
    List<Book> findAllAvailableBooks(@Param("isAvailable") boolean isAvailable);

    @Query(value = "SELECT * FROM books WHERE Category = :category", nativeQuery = true)
    List<Book> findAllByCategory(@Param("category") String category);

    @Query(value = "Select Book_ID as bookId, Book_Name as bookName, Category as category, Availability as isAvailable,  Borrow_Date as borrowDate, Return_Date as returnDate from books where Book_ID = :bookId", nativeQuery = true)
    Optional<BookDetailsProjection> customBookDetails(@Param("bookId") long id);
//     user_ID as borrower

//    @Query(value = "SELECT * FROM `books` WHERE Category = :category", nativeQuery = true)
//    List<Book> findAllByCategory(@Param("category") String category);
}
