package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.login.LoginService;
import com.example.member.MemberVO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/login/")
@Controller
public class LoginController {
	LoginController() {
		System.out.println("===> LoginController");
	}
	
	@Autowired
	private LoginService service;
	
	@GetMapping("login")
	public String login() {
		System.out.println("===> login");
		return "/login/login";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		System.out.println("===> logout");
		session.invalidate();
		return "/login/logout";
	}
	
	@GetMapping("loginOK")
	public String loginOK(MemberVO vo) {
		System.out.println("===> loginOK");
		System.out.println(vo.getUsername() + ":" + vo.getPassword());
		MemberVO m = service.getLogin(vo);
		if (m != null) {
			if (m.getPassword().equals(vo.getPassword())) {
				return "redirect:/login/loginSuccess";
			} else {
				return "redirect:/login/loginFail";
			}
		} else {
			return "redirect:/login/loginFail";
		}
	}
	
	@GetMapping("loginSuccess")
	public String loginSuccess(HttpSession session) {
		System.out.println("===> loginSuccess");
		return "/security/loginSuccess";
	}
	
	@GetMapping("loginFail")
	public String loginFail() {
		System.out.println("===> fail");
		return "/security/loginFail";
	}
	
	@GetMapping("accessDenied")
	public String accessDenied() {
		System.out.println("===> accessDenied");
		return "/security/accessDenied";
	}
}
