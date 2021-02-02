package com.example.slms.Entity;

import java.util.Date;

public interface BookDetailsProjection {

    long getBookId();
    String getBookName();
    String getCategory();
//    boolean isAvailable();
//    User getBorrower();
    Date getBorrowDate();
    Date getReturnDate();

}
