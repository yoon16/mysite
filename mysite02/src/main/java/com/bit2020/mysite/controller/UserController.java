package com.bit2020.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2020.mvc.util.MVCUtil;
import com.bit2020.mysite.repository.UserRepository;
import com.bit2020.mysite.vo.UserVo;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");

		if("joinform".equals(action)) {
			MVCUtil.forward("user/joinform", request, response);
		} else if("join".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);
			
			new UserRepository().save(vo);
			
			MVCUtil.redirect("/mysite02/user?a=joinsuccess", request, response);
		} else if("joinsuccess".equals(action)) {
			MVCUtil.forward("user/joinsuccess", request, response);
		} else if("loginform".equals(action)) {
			MVCUtil.forward("user/loginform", request, response);
		} else if("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
	
			UserVo userVo = new UserRepository().findByEmailAndPassword(email, password);
			if(userVo == null) {
				request.setAttribute("result", "fail");
				MVCUtil.forward("user/loginform", request, response);
				return;
			}
			
			/* 인증 처리 */
			HttpSession session = request.getSession(true);
			session.setAttribute("authUser", userVo);
			
			MVCUtil.redirect(request.getContextPath(), request, response);
		} else if("logout".equals(action)) {
			HttpSession session = request.getSession();
			
			if(session != null && session.getAttribute("authUser") != null) {
				/* 로그아웃 처리 */
				session.removeAttribute("authUser");
				session.invalidate();
			}

			MVCUtil.redirect(request.getContextPath(), request, response);
			
		} else {
			MVCUtil.redirect(request.getContextPath(), request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}