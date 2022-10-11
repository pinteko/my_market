package com.korsuk.my_market.controllers;

import com.korsuk.my_market.products.Book;
import com.korsuk.my_market.services.BookService;
import com.korsuk.my_market.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final StudentService studentService;

    @GetMapping()
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/change_rating")
    public void changeRating(@RequestParam Integer bookId, @RequestParam Double delta){
        bookService.changeScore(bookId, delta);
    }

    @DeleteMapping("/delete_book")
    public void deleteBook(@RequestParam Integer bookId){
        bookService.deleteBook(bookId);
    }

    @GetMapping("/new_book")
    public String newBook(){
        return "/newBook.html";
    }

    @PostMapping("/form_book")
    public Book formBook(@RequestParam(name = "title") String title, @RequestParam(name = "author") String author,
                           @RequestParam(name = "rating") Double rating, @RequestParam(name = "price") Double price){
       return bookService.addBook(title, author, rating, price);
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "infoBook";
    }

    @GetMapping("/infoStudent/{id}")
    public String showForStudent(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "infoBookForStudent";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "newBook";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newBook";
        }
        bookService.addBook(book.getTitle(), book.getAuthor(), book.getRating(), book.getPrice());
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.getBookById(id));
        return "updateBook";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "updateBook";

        bookService.updateBook(id, book.getTitle(), book.getAuthor(), book.getRating(), book.getPrice());
        return "redirect:/books";
    }
}
