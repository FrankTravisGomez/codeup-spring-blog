//
//package com.codeup.codeupspringblog.controllers;
//
//import com.codeup.codeupspringblog.models.Post;
//import com.codeup.codeupspringblog.models.User;
//import com.codeup.codeupspringblog.repositories.PostRepository;
//import com.codeup.codeupspringblog.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//public class PostController {
//
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//
//    @Autowired
//    public PostController(PostRepository postRepository, UserRepository userRepository) {
//        this.userDao = userRepository;
//        this.postDao = postRepository;
//    }
//
//    @GetMapping("/posts")
//    public String getAllPosts(Model model) {
//        Iterable<Post> posts = postDao.findAll();
//
//        model.addAttribute("posts", posts);
//
//        return "posts/index";
//    }
//
//    @GetMapping("/posts/{id}")
//    public String getPostById(Model model, @PathVariable("id") Long id) {
//        Optional<Post> optionalPost = postDao.findById(id);
//
//        if (optionalPost.isEmpty()) {
//
//            return "error-page";
//        }
//
//        Post post = optionalPost.get();
//
//        model.addAttribute("post", post);
//
//        return "posts/show";
//    }
//
//    @GetMapping("/posts/create")
//    public String showCreateForm() {
//        return "posts/create";
//    }
//
//    @PostMapping("/posts/create")
//    public String createPost(@RequestParam String title, @RequestParam String body) {
//        User user = userDao.getReferenceById(1L);
//        Post post = new Post(title, body, user);
//        postDao.save(post);
//
//        return "redirect:/posts";
//    }
//}




package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.EmailService;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailDao;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository, EmailService emailService) {
        this.userDao = userRepository;
        this.postDao = postRepository;
        this.emailDao = emailService;
    }

    //new code until stop
//    @GetMapping("/posts")
//    public String index(Model model){
//        model.addAttribute("post", postDao.findAll());
//        return "posts/index";
//    }
//
//    @GetMapping("/posts/{id}")
//    public String singlePost(@PathVariable long id, Model model){
//        model.addAttribute("posts", postDao.findById(id).get());
//        return "posts/show";
//    }
//
//    @GetMapping("/posts/create")
//    public String createForm(Model model){
//        model.addAttribute("posts",new Post());
//        return "posts/create";
//    }

//    @PostMapping("/posts/create")
//    public String createPost(@ModelAttribute Post post){
//        post.setUser(userDao)
//    }


    //new code stop
    @GetMapping
    public String getAllPosts(Model model) {
        Iterable<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(Model model, @PathVariable("id") Long id) {
        Post post = postDao.findById(id).orElse(null);
        if (post == null) {
            return "error-page";
        }
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute("post") Post post, @RequestParam("body") String body) {
        User user = userDao.getReferenceById(1L);
        post.setUser(user);
        post.setBody(body);
        postDao.save(post);
        emailDao.sendPostCreatedEmail(user);

//        String subject = "New Post Created";
//        String message = "Your post has been created successfully!";
//        emailService.sendEmailToUser(user, subject, message);

        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Post post = postDao.findById(id).orElse(null);
        if (post == null) {
            return "error-page";
        }
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PutMapping("/posts/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute("post") Post updatedPost) {
        Post post = postDao.findById(id).orElse(null);
        if (post == null) {
            return "error-page";
        }
        post.setTitle(updatedPost.getTitle());
        post.setBody(updatedPost.getBody());
        postDao.save(post);
        return "redirect:/posts";
    }
}