package com.greedy.section01.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showErrorPage")
public class ExceptionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 참조, 어떤 데이터를 참조할 수 있는가
		Enumeration<String> attrNames = request.getAttributeNames();
		while(attrNames.hasMoreElements()) {
			String attrName =  attrNames.nextElement();
			// key : 값
			System.out.println(attrName+" : "+request.getAttribute(attrName));
		}
		
		// getAttribute 는 반환값이 Object이기 때문에 다운 캐스팅을 해야한다. 
		Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
		String errorCode = (String)request.getAttribute("javax.servlet.error.messge");
		String errorMessage = (String)request.getAttribute("javax.servlet.error.message");
		
		StringBuilder errorPage = new StringBuilder();
		errorPage.append("<!doctype html>\n")
				.append("<html>\n")
				.append("<head>\n")
				.append("</head>\n")
				.append("<body>\n")
				.append("<h1 align='center'>")
				.append(statusCode)
				.append(" - ")
				.append(errorMessage)
				.append("</h1>\n")
				.append("</body>\n")
				.append("</html>");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(errorPage.toString());
		out.flush();
		out.close();
		
		
				
	}

}
