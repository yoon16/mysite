package com.bit2020.mvc.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MVCUtil {

	public static void forward(
		String viewName, 
		HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
		rd.forward(request, response);
	}

	public static void redirect(
		String url,
		HttpServletRequest request,
		HttpServletResponse response) throws IOException {
		response.sendRedirect(url);
	}

}