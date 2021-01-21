package com.example.slms.Entity;

import com.example.slms.Enums.UserRoleEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonNaming( value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table( name = "login" )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_ID")
    private long userID;

    @Column( name = "username" , unique = true)
    private String username;

    @Column( name = "password" )
    private String password;

    @Enumerated(EnumType.STRING)
    @Column( name = "role" )
    private UserRoleEnum role;

    @OneToMany(mappedBy = "borrower")
    private List<Book> bookList;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (!(o instanceof User))
//            return false;
//        User user = (User) o;
//        return Objects.equals(this.userID, user.userID) && Objects.equals(this.username, user.username)
//                && Objects.equals(this.password, user.password);
//    }
//    @Override
//    public int hashCode(){
//        return Objects.hash(this.userID, this.username, this.password);
//    }
//    @Override
//    public String toString() {
//        return "User{" + "user_id=" + this.userID + ", name='" + this.username + '\'' + ", password='" + this.password + '\'' + '}';
//    }
}
