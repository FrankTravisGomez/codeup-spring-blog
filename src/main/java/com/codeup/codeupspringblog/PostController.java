package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class PostController{
    @GetMapping("/posts")
    @ResponseBody
    public String posts() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "view an individual post" +" " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }
}
