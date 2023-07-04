package com.example.springapp.controllers;

import com.example.springapp.models.Author;
import com.example.springapp.models.Book;
import com.example.springapp.repositiories.AuthorRepository;
import com.example.springapp.repositiories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public String findByName(int id) {
        List<Author> authors = authorRepository.findAll();
        for(Author author: authors)
            if(author.getId() == id)
                return author.getName();
        return null;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("author", authorRepository.findAll());
        return "book/list";
    }

    @GetMapping("/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("author", authors);
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") Book book) {
        System.out.println(book.toString());
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        model.addAttribute("book", book);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("author", authors);
        return "book/edit";
    }

    @PostMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
