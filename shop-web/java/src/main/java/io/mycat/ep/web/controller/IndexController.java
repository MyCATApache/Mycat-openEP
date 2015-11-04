package io.mycat.ep.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Liwh on 2015/11/4.
 */
@Controller
public class IndexController {


    @RequestMapping("/")
    public String index(){

        return "index";
    }





}
