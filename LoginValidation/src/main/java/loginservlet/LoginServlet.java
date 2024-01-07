package loginservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Server-side validation
		if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			out.print("<p class=\"error-message\">Please enter both username and password.</p>");
			request.getRequestDispatcher("login.jsp").include(request, response);
		} else {
			if ("admin".equals(username) && "admin123".equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				response.sendRedirect("welcome.html");
			} else {
				request.getRequestDispatcher("loginagain.html").include(request, response);
			}
		}

		out.close();
	}
}
