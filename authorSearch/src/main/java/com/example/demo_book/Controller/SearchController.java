package com.example.demo_book.Controller;

import com.example.demo_book.DTO.ResultDTO;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class SearchController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/authorSearch")
    public String checkAuth(@RequestParam("bookName") String bookName){
        log.info("authorSearch for bookName: {}", bookName);
        String authorName;
        try {
            String sql="SELECT author FROM book WHERE name = '"+bookName+"'";
            authorName=jdbcTemplate.queryForObject(sql,String.class);

        }catch (Exception e){
            log.info("authorSearch failed!");
            authorName=null;
        }
        log.info(authorName);
        return authorName;
    }
}
