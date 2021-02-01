package com.example.slms.Service.Implementation;

import com.example.slms.Entity.Book;
import com.example.slms.Entity.BookDetailsProjection;
import com.example.slms.Entity.User;
import com.example.slms.Exceptions.CustomException;
import com.example.slms.Repository.BookRepository;
import com.example.slms.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllByCategory(String category){
//        List<Book> books = new ArrayList<>();
//        bookRepository.findAllByCategory(category)
//                .forEach(book -> {
//                    book.setBorrower();
//                });
        return bookRepository.findAllByCategory(category);
    };

    public BookDetailsProjection customBookDetails(long id){
        Optional<BookDetailsProjection> optionalBook = bookRepository.customBookDetails(id);
        if(optionalBook.isPresent()){
            return optionalBook.get();
        }else{
            throw new CustomException(400, "User not found");
        }
    }

    public Book addBook(Book book){
        Optional<Book> optionalBook = bookRepository.findByBookName(book.getBookName());
        if(optionalBook.isPresent()){
            throw new CustomException(400, "Book already exist");
        }else return bookRepository.save(book);
    }

    @Transactional
    public List<Book> findAllBooks(){
        List<Book> books = new ArrayList<>();
        bookRepository.findAll()
                .forEach(book -> {
                    User user = new User();
                    user.setUserID(book.getBorrower().getUserID());
                    user.setUsername(book.getBorrower().getUsername());
                    user.setRole(book.getBorrower().getRole());
                    book.setBorrower(user);
                    books.add(book);
                });
        return books;
    }

    public Book findById(long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            User user = new User();
            user.setUserID(bookOptional.get().getBorrower().getUserID());
            user.setUsername(bookOptional.get().getBorrower().getUsername());
            user.setRole(bookOptional.get().getBorrower().getRole());
            user.setBookList(null);
            bookOptional.get().setBorrower(user);
            return bookOptional.get();
        }else throw new CustomException(400, "Book not found");
//        return bookOptional
//                .orElseThrow(() -> new CustomException(400, "Book not found"));
    }

    public Book findByBookName(String bookName){
        Optional<Book> bookOptional = bookRepository.findByBookName(bookName);
        if (bookOptional.isPresent()){
            return bookOptional.get();
        }else throw new CustomException(400, "Book not found");
    }

    @Transactional
    public List<Book> findAllAvailableBooks(boolean isAvailable){
        List<Book> books = new ArrayList<>();
        bookRepository.findAllAvailableBooks(isAvailable)
                .forEach(book -> {
                    User user = new User();
                    user.setUsername(book.getBorrower().getUsername());
                    user.setUserID(book.getBorrower().getUserID());
                    user.setRole(book.getBorrower().getRole());
                    book.setBorrower(user);
                    books.add(book);
                });
        return books;
    }

    public boolean isAvailable(long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            return optionalBook.get().isAvailable();
        }else throw new CustomException(400, "Book not found");
    }

    public boolean deleteBook(long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            bookRepository.deleteById(id);
            return true;
        }else return false;
    }

    public Book updateBook(long id, Book book){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            return bookRepository.save(book);
        }else throw new CustomException(400, "Book not found");
    }
}
