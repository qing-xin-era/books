package com.qing.controller;


import com.qing.Dao.UserMapper;
import com.qing.pojo.Books;
import com.qing.pojo.TransactionRecord;
import com.qing.pojo.User;
import com.qing.service.BookService;
import com.qing.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller

public class BookController {

    private  int bookid;
    private  List list;
    BookService BookService;

    @Autowired
    public BookController(BookService bookService) {
        this.BookService = bookService;
    }

    @GetMapping("/Idlist")

    public String queryBookById(Model model) {
        List<Books> list = new ArrayList<Books>();

        Books book = BookService.queryBookById(3);
        list.add(book);
        System.out.println("注册" + list);
        model.addAttribute("list", list);
        return "allBook";
    }
    @GetMapping("/Namelist")
    public String queryBookByName(String name,Model model) {
        List<Books> list = new ArrayList<Books>();

         list = BookService.queryBookByName(name);
        if(list==null) {this.list=BookService.queryAllBook();}
        else this.list=list;


        model.addAttribute("list", this.list);
        return "allBook";
    }

    @GetMapping("/allBook")
    public String list(Model model) {
        List<Books> list = BookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    @GetMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";

    }

    @PostMapping("/addBook")
    public String addBook(Books books) {
        if(books==null){
            return "addBook";
        }
        System.out.println("书本" + books);
        BookService.addBook(books);
        return "redirect:/allBook";
    }

    @GetMapping("/toUpdate")
    public String toUpdatePaper(String id, Model model) {
        Integer i = Integer.valueOf(id);
        this.bookid=i;
        Books book = BookService.queryBookById(i);
        if(book==null){
            return "allBook";
        }
        System.out.println("将要修改" + book);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @PostMapping("/upDatedBook")
    public String upDatedBook(Books books) {
        if(this.bookid==books.getBookID()){
            System.out.println("必定修改而不是添加" + books);
            BookService.updateBook(books);
            return "redirect:/allBook";
        }else return "forward:/addBook";
    }
    @GetMapping("/toDeleteBook")
    public String deleteBookById(String id ) {
        Integer i = Integer.valueOf(id);
        if(i==null){
            return "allBook";
        }
        BookService.deleteBookById(i);
        return "redirect:/allBook";

    }

}