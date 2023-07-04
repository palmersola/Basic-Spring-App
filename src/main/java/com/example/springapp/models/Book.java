package com.example.springapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Book")
public class Book {
    @Id @GeneratedValue
    private int id;
    private String title;
    @ManyToOne
    @JoinColumn(name ="author_id ")
    private Author author;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
