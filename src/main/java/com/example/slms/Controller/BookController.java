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

//  "/slms/books/custom/{id}"    - GET  - customBookDetails  - isAvailable and borrower is na in Query
//  "/slms/books/"               - GET  - findAllByCategory  - DONE
//                               - GET  - findByBookName     - DONE
//                               - GET  - findAllAvailableBooks - DONE
//                               - PUT  - updateBorrower     - DONE
//  "/slms/books"                - POST - addBook            - NA
//                               - GET  - findAllBooks       - DONE
//  "/slms/books/{id}"           - GET  - findById           - DONE
//                               - PUT  - updateBook         - NA
//                               - DELETE - findById         - NA
//  "/slms/books/{id}/available" - GET  - isAvailable        - DONE

    @Autowired
    private BookService bookService;

    @RequestMapping( value = "/custom/{id}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BookDetailsProjection> customBookDetails(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.customBookDetails(id));
    }

    @RequestMapping( value = "/", params = "category", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> findAllByCategory(@RequestParam String category){
        return bookService.findAllByCategory(category);
    }

    @RequestMapping( value = "", method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestBody Book book, @RequestParam(required = false) long userId){
        return ResponseEntity.ok().body(bookService.addBook(book,userId));
    }

    @RequestMapping( value = "", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBorrower(@RequestParam long bookId, @RequestParam long userId){
        return ResponseEntity.ok().body(bookService.updateBorrower(bookId,userId));
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

    @RequestMapping( value = "/", params = "bookName", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Book> findByBookName(@RequestParam String bookName){
        return ResponseEntity.ok().body(bookService.findByBookName(bookName));
    }

    @RequestMapping( value = "/", params = "isAvailable", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Book> findAllAvailableBooks(@RequestParam boolean isAvailable){
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
