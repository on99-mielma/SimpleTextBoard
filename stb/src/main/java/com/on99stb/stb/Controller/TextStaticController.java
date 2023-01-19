package com.on99stb.stb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/textstatic")
public class TextStaticController {
    @RequestMapping("/1")
    public String toIndex(){
        return "redirect:/text.html";
    }
}
