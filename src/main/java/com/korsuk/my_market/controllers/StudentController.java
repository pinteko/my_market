package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.StudentDto;
import com.korsuk.my_market.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("students", studentService.getAllStudent());
//        return "showStudents";
//    }

    @GetMapping()
    public List<StudentDto> findAll() {
        return studentService.getAllStudent();
    }


//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("student", studentService.getStudent(id));
//        return "infoStudent";
//    }
//
//    @GetMapping("/{id}/showCart")
//    public String showCart(@PathVariable("id") int id, Model model) {
//        model.addAttribute("student", studentService.getStudent(id));
//        return "showCart";
//    }
//
//
//    @GetMapping("/new")
//    public String newStudent(Model model) {
//        model.addAttribute("student", new Student());
//        return "newStudent";
//    }
//
//    @PostMapping("/new")
//    public String create(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "newStudent";
//        }
//        studentService.addStudent(student.getName());
//        return "redirect:/students";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        studentService.deleteStudent(id);
//        return "redirect:/students";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("student", studentService.getStudent(id));
//        return "updateStudent";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String update(@ModelAttribute("student") @Valid Student student,
//                         BindingResult bindingResult, @PathVariable("id") int id) {
//        if(bindingResult.hasErrors())
//            return "updateStudent";
//
//        studentService.updateStudent(id, student.getName());
//        return "redirect:/students";
//    }
//
//    @GetMapping("/{id}/addBook")
//    public String getBookForCart(Model model, @PathVariable("id") int id) {
//        model.addAttribute("student", studentService.getStudent(id));
//        model.addAttribute("books", bookService.getAllBooks());
//        return "showBooksForStudent";
//    }
//
//    @PostMapping("/{idStudent}/{idBook}")
//    public String addBook(@PathVariable("idStudent") int idSt,
//                          @PathVariable("idBook") int idBk) {
////        studentService.getStudent(idSt).getCart().addBook(bookService.getBookById(idBk));
//        return "redirect:/students/{idStudent}/addBook";
//    }


}
