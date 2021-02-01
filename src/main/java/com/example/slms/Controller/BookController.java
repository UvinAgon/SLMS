package com.example.slms.Controller;

import com.example.slms.Entity.Book;
import com.example.slms.Entity.BookDetailsProjection;
import com.example.slms.Entity.User;
import com.example.slms.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "/slms/books" )
public class BookController {

//  "/slms/books/custom/{id}"            - GET  - customBookDetails  - Works without isAvailable and borrower
//  "/slms/books/custom/{category}"      - GET  - findAllByCategory  - NA
//  "/slms/books/custom/{bookName}"      - GET  - findByBookName     - NA
//  "/slms/books/"                       - POST - addBook            - NA
//                                       - GET  - findAllBooks       - DONE
//                                       - GET  - findAllAvailableBooks - DONE
//  "/slms/books/{id}"                   - GET  - findById           - DONE
//                                       - PUT  - updateBook         - NA
//                                     - DELETE - findById           - NA
//  "/slms/books/{id}/available"         - GET  - isAvailable        - DONE

    @Autowired
    private BookService bookService;

    @RequestMapping( value = "/custom/{id}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BookDetailsProjection> customBookDetails(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.customBookDetails(id));
    }

    @RequestMapping( value = "/custom/{category}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> findAllByCategory(@RequestParam(value = "category", required = true) String category){
        return bookService.findAllByCategory(category);
    }

    @RequestMapping( value = "", method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestBody Book book){
//        Book bo = new Book();
//        bo.setBorrowDate(book.getBorrowDate());
        return ResponseEntity.ok().body(bookService.addBook(book));
    }

    @RequestMapping( value = "", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Book> findById(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.findById(id));
    }

//    @RequestMapping( value = "", method = RequestMethod.GET,
//            produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<Book> findByBookName(@RequestParam(value = "bookName", required = true) String bookName){
//        return ResponseEntity.ok().body(bookService.findByBookName(bookName));
//    }

    @RequestMapping( value = "/", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> findAllAvailableBooks(@RequestParam(value = "isAvailable", required = true) boolean isAvailable){
        return bookService.findAllAvailableBooks(isAvailable);
    }

    @RequestMapping( value = "/{id}/available", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Boolean> isAvailable(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.isAvailable(id));
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable long id,@RequestBody Book book){
        return ResponseEntity.ok().body(bookService.updateBook(id, book));
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteBook(@PathVariable long id){
        boolean isdeleted = bookService.deleteBook(id);
        if (isdeleted == true){
            return ResponseEntity.ok().body(id);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(id);
        }
    }
}
