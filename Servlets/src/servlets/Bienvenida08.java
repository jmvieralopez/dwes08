package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Bienvenida08
 */
// @WebServlet("/Bienvenida08")
/* value="/Bienvenida", */

@WebServlet(
		urlPatterns = { "/Bienvenida", "/bienvenida" },
		name = "BienvenidaServlet",
		loadOnStartup = 1,
		initParams = {
				@WebInitParam(name = "usuarioRemoto", value = "aruiz"),
				@WebInitParam(name = "log", value = "1") })
public class Bienvenida08 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bienvenida08() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.println();
		out.println(this.getServletName());

		ServletContext contexto = getServletContext();
		// context-params principales
		out.println("<h3>Context-params de la aplicación</h3>");
		Enumeration<String> parametros = contexto.getInitParameterNames();
		while (parametros.hasMoreElements()) {
			String actual = parametros.nextElement();
			out.println("<p>" + actual + ": " + contexto.getInitParameter(actual) + "</p>");
		}

		// context-params servlet
		out.println("<h3>Init-params del servlet</h3>");
		Enumeration<String> parametrosServlet = getInitParameterNames();
		while (parametrosServlet.hasMoreElements()) {
			String actual = parametrosServlet.nextElement();
			out.println("<p>" + actual + ": " + getInitParameter(actual) + "</p>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
