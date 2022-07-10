package com.qing.service;

import com.qing.pojo.Books;
import com.qing.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {
   //增加一本书
   int addBook(Books books);
   //删除一本书
   int deleteBookById(int id);
   //更新一本书
   int updateBook(Books books );
   //查询一本书
   List<Books> queryBookByName(@Param("bookName") String bookName);
   Books queryBookById(int id);
   //查询全部的书
   List<Books> queryAllBook();


}
