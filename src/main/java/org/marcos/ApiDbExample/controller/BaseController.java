package org.marcos.ApiDbExample.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
public class BaseController {
    
    @GetMapping("/")
    public String getMethodName() {
        return "Esta vivo";
    }
    
}
