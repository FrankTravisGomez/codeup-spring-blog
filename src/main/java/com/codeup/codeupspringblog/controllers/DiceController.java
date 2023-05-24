package com.codeup.codeupspringblog.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String dice() {
        return "dice";
    }

    @GetMapping("/roll-dice/{num}")
    public ModelAndView diceRoll(@PathVariable("num") int guess) {
        int roll = (int) (Math.random() * 6) + 1;

        ModelAndView modelAndView = new ModelAndView("dice");
        modelAndView.addObject("guess", guess);
        modelAndView.addObject("roll", roll);

        return modelAndView;
    }
}