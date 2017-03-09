package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InfoSesion
 */
@WebServlet("/InfoSesion2")
public class InfoSesion2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoSesion2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// Crear una sesión o recuperar la existente si se encuentra la cookie de sesión 
		HttpSession session = request.getSession();
		//session.setMaxInactiveInterval(30);
		int contador = (int) session.getAttribute("login");
		  String usuario = session.getAttribute("usuario").toString();
		  PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='UTF-8'/>" + "<style> .error {color: red}</style>"
		    + "<title>Sesiones en JavaEE</title></head><body>");
		response.setContentType("text/html;UTF-8");
		out.println("<h2>Información sobre la sesión</h2>" +
		            "<ul>" +
		            "<li> Identificador: " + session.getId() + "</li>\n" +
		            "<li> Usuario: " + contador + "</li>\n" +
		            "<li> Usuario: " + usuario + "</li>\n" +
		            "</ul>" +
		        "<p><a href='" + request.getRequestURI() + "'>Refrescar</a></p>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
