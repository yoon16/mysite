package com.bit2020.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2020.mvc.util.MVCUtil;
import com.bit2020.mysite.repository.GuestbookRepository;
import com.bit2020.mysite.repository.UserRepository;
import com.bit2020.mysite.vo.GuestbookVo;
import com.bit2020.mysite.vo.UserVo;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("a");

		if ("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String message = request.getParameter("content");

			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);

			new GuestbookRepository().insert(vo);

			MVCUtil.redirect(request.getContextPath() + "/guestbook", request, response);
		} else if ("deleteform".equals(action)) {

			MVCUtil.forward("guestbook/deleteform", request, response);

		} else if ("delete".equals(action)) {
			String no = request.getParameter("no");
			String password = request.getParameter("password");

			new GuestbookRepository().delete(Long.parseLong(no), password);
			MVCUtil.redirect(request.getContextPath() + "/guestbook", request, response);

		} else {
			/* list */
			List<GuestbookVo> list = new GuestbookRepository().findAll();

			request.setAttribute("list", list);

			MVCUtil.forward("guestbook/list", request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}