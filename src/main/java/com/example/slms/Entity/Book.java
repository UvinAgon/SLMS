package com.example.slms.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@JsonNaming( value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Book_ID")
    private long bookId;

    @Column(name = "Book_Name")
    private String bookName;

    @Column(name = "Category")
    private String category;

    @Column(name = "Availability")
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_ID")
    private User borrower;

    @Column(name = "Borrow_Date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date borrowDate;

    @Column(name = "Return_Date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date returnDate;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        if (!borrowDate.equals(null)){
            this.borrowDate = borrowDate;
        }else this.borrowDate = java.util.Calendar.getInstance().getTime();
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    //    public void setBorrowDate(Date borrowDate) {
//        if(borrowDate.equals(null)){
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
////        LocalDateTime now = LocalDateTime.now();
////        System.out.println(dtf.format(now));
////        this.borrowDate = dtf.format(now);
////            Date date=java.util.Calendar.getInstance().getTime();
//            this.borrowDate = java.util.Calendar.getInstance().getTime();
//        }
//        else this.borrowDate = borrowDate;
//    }

}
