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

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final StudentService studentService;

    @GetMapping()
    public String index(Model model) {
        //получим всех людей из DAO  и передадим на отображение и представление
        model.addAttribute("books", bookService.getAllBooks());
        return "showBooks";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //получим одного человека по его id из DAO и передадим на отображение и представление
        model.addAttribute("book", bookService.getBookById(id));
        return "infoBook";
    }

    @GetMapping("/infoStudent/{id}")
    public String showForStudent(@PathVariable("id") int id, Model model) {
        //получим одного человека по его id из DAO и передадим на отображение и представление
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

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "updateBook";

        bookService.updateBook(id, book.getTitle(), book.getAuthor(), book.getRating(), book.getPrice());
        return "redirect:/books";
    }
}
