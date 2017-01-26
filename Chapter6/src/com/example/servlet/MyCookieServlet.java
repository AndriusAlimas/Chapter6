package com.example.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyCookieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException,IOException{
		
	}
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException,IOException{
		// just get from parameter form details and check if you enter details, if not use HttpServletResponse
		// interface method sendRedirect() works at client side, it can be used within and outside the server.
		// you always must use absolute path for response.
	      if (request.getParameter("fName") == null || request.getParameter("fName").equals("") 
	    		  || request.getParameter("lName") == null || request.getParameter("lName").equals("")) {
	            response.sendRedirect("index.html"); // absolute path, No relative path
	        }
	      // Create cookies object which will be name saved objects, this object we will got from form:
	      Cookie cookie = new Cookie("first_name", request.getParameter("fName"));
	      Cookie cookie2 = new Cookie("last_name", request.getParameter("lName"));
	      // set the maximum age a time in seconds:
	      cookie.setMaxAge(86400);
	      cookie2.setMaxAge(86400);
	      
	      // using HttpServletResponse method addCookie(Cookie cookie); we simple add a cookie
	      response.addCookie(cookie);
	      response.addCookie(cookie2);
	      // using ServletResponse we set content type using in argument MIME TYPE:
	      response.setContentType("text/html");
	      PrintWriter pw = response.getWriter();
	      
	     String title = "Setting Cookies Example";
	     String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
	     // Simple print writer out:
	     pw.println(docType + "<html>\n<head><title>" + title + "</title>"
	     		+ "</head>\n<body bgcolor=\"#afaaaa\">\n<h1 align=\"center\">" + title + "</h1>\n<ul>\n  <li>"
	     		+ "<b>First Name</b>: " + request.getParameter("fName") + "\n  <li><b>Last Name</b>: "
	     		+ request.getParameter("lName") + "\n</ul>\n</body></html>");
	}
}