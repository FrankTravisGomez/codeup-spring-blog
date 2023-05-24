package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//return num1+ "+" +num2"=" (num1+num2)
@Controller
class MathController {

        @GetMapping("/add/{num1}/{num2}")
        @ResponseBody
        public String add(@PathVariable int num1, @PathVariable int num2) {
                int result = num1 + num2;
                return String.valueOf(result);
//                return num1+num2 (num1+num2);
        }

        @GetMapping("/subtract/{num1}/{num2}")
        @ResponseBody
        public String subtract(@PathVariable int num1, @PathVariable int num2) {
                int result = num2 - num1;
                return String.valueOf(result);
        }

        @GetMapping("/multiply/{num1}/{num2}")
        @ResponseBody
        public String multiply(@PathVariable int num1, @PathVariable int num2) {
                int result = num1 * num2;
                return String.valueOf(result);
        }

        @GetMapping("/divide/{num1}/{num2}")
        @ResponseBody
        public String divide(@PathVariable int num1, @PathVariable int num2) {
                if (num2 == 0) {
                        return "zero will not divide";
                }

                int result = num1 / num2;
                return String.valueOf(result);
        }
}