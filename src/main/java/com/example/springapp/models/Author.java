package com.example.springapp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
public class Author {
   @Id @GeneratedValue
   private int id;
   private String name;
   @OneToMany(
           mappedBy = "author",
           cascade = CascadeType.ALL,
           orphanRemoval = true
   )
   private List<Book> books = new ArrayList<>();


    public Author(String name) {
        this.name = name;
    }

    public Author() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
