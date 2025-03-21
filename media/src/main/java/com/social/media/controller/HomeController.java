package com.social.media.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController




public class HomeController {

   @GetMapping("/new")
   public String newController(){
    return "wowwy";
 }



 @GetMapping("/old")
 public String oldHomeController(){
    return "old controller";
 }
   }
   
        




