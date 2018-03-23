package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alper on 23.03.2018.
 */
@RestController
public class WebController {
    private final JdbcTemplate jdbcTemplate;

    public WebController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/users")
    public String getUsersInfo(){
        List userList = this.jdbcTemplate.queryForList("SELECT  * FROM systemUsers").stream().map(Map::values).collect(Collectors.toList());
        StringBuilder strB = new StringBuilder();
        Iterator userIter = userList.iterator();

        while (userIter.hasNext()){
            strB.append(userIter.next() + "<br>");
        }

        strB.append("<br> There is [ " + userList.size() + " ] user in system");
        return strB.toString();
    }

    @GetMapping("/userLists")
    public List getUsers(){
        return this.jdbcTemplate.queryForList("SELECT  * FROM systemUsers").stream().map(Map::values).collect(Collectors.toList());
    }

    @GetMapping("/create-user")
    public String createUser(){
        jdbcTemplate.execute("INSERT INTO systemUsers VALUES ('salih.com','salih','salih.m')");
        return "user created alp.com alp alper";
    }

    @GetMapping("/clearDB")
    public String clearDB(){
        jdbcTemplate.execute("DELETE FROM systemUsers");
        return "systemUsers Removed";
    }

}
