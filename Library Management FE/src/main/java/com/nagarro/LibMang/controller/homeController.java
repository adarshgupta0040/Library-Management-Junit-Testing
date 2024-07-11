package com.nagarro.LibMang.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.BookService;
import com.nagarro.LibMang.service.LoginService;

@Controller
public class homeController {

	@Autowired
	BookService displayBooks;
	@Autowired
	LoginService loginService;


	@RequestMapping(path="/login",method=RequestMethod.POST)
	public ModelAndView checkUser(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		System.out.println("inside controller");
		// getting username and password of the user
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");

		// function to check user exist or not

		if (loginService.checkLogin(username, password)) {
			System.out.println("Successfull login");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			List<Book> books= displayBooks.retrieveBooks();
			session.setAttribute("books", books);
			mv.setViewName("BookListing");

		} else {
			System.out.println("Unsuccessfull login");
			mv.setViewName("login");
		}
		return mv;
	}
	

	@PostMapping("/Logout")
	public ModelAndView check_User(HttpServletRequest request, HttpServletResponse response){

		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		mv.setViewName("login");

		return mv;
	}

}

