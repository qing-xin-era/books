package com.qing.Dao;

import com.qing.pojo.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface BookMapper {
    //增加一本书
    int addBook(Books books);
    //删除一本书
    int deleteBookById(@Param("bookID") int id);
    //更新一本书
    int updateBook(Books books );
    //查询一本书
    Books queryBookById(@Param("bookID") int id);
    List<Books> queryBookByName(@Param("bookName") String bookName);
    //查询全部的书
    List<Books> queryAllBook();

}
