package com.example.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyCookieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException,IOException{
		// Sets the status code for this response. This method is used to set the return status code when there
		// is no error (for example, for the status codes SC_OK or SC_MOVED_TEMPORARILY). If there is an error,
		// and the caller wishes to invoke an error-page defined in the web application, the sendError method 
		// should be used instead:
		response.setStatus(404);
        Cookie cookie = null;
        Cookie[] arrcookie = null;
        // using HttpServletRequest we get all cookies:
        arrcookie = request.getCookies();
        
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String title = "Reading Cookies Example";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
        String color = "#CBfCf0";
        pw.println(docType + "<html>\n<head><title>" + title + "</title></head>\n<body bgcolor=\""+color+"\">\n");
        // this code simple retrieve all cookie names and values:
        if (arrcookie != null) {
            pw.println("<h2> Found Cookies Name and Value</h2>");
            for (int i = 0; i < arrcookie.length; ++i) {
                cookie = arrcookie[i];
                pw.print("Name : " + cookie.getName() + ",  ");
                pw.print("Value: " + cookie.getValue() + " <br/>");
            }
        } else {
            pw.println("<h2>No cookies founds</h2>");
        }
        pw.println("</body>");
        pw.println("</html>");
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