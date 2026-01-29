package com.example.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ルートパスコントローラ
 * ルートパス（/）へのアクセスを社員一覧にリダイレクト
 */
@Controller
public class RootController {

    /**
     * ルートパスから社員一覧へリダイレクト
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/employees";
    }
}
