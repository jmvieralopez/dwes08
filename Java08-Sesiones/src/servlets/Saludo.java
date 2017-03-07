package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Saludo
 */
@WebServlet("/Saludo")
public class Saludo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Saludo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletContext contexto = getServletContext();
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		//session.setMaxInactiveInterval(30);
		String error = "";
		int contador = 0;
		
		if(request.getParameter("cerrarSesion")!=null){
			//session.removeAttribute("contador");
			session.invalidate();
			session = request.getSession();
			//session.setMaxInactiveInterval(30);
			session.setAttribute("usuario", "");
		}
		
		  if(request.getParameter("nombre") != null){
			session.setAttribute("usuario", request.getParameter("nombre"));			  
		  }		
		// Comprobar si es la primera vez 
		
		// Obtener datos sobre la sesión 
		Date fechaInicioSesion = new Date(session.getCreationTime());
		// Get last access time of this web page. 
		Date fechaUltimoAcceso = new Date(session.getLastAccessedTime());
		 
		// Comienza la salida 
		out.println("<html><head><meta charset='UTF-8'/>" + "<style> .error {color: red}</style>"
		    + "<title>Sesiones en JavaEE</title></head><body>");
		response.setContentType("text/html;UTF-8");
		out.println(error);
		if (session.isNew()) {
			out.println("<form action='/Java08-Sesiones/Saludo' method='post'>"
		            + "<label>Introduce tu nombre para dirigirnos a ti:</label>" + "<input type='text' name='nombre'/>"
		            + "<input type='submit' name='enviar' value='Enviar'/></form>");
		}else if(!session.isNew() && session.getAttribute("usuario") != null) {
			out.println("<h2>Bienvenid@, " + session.getAttribute("usuario") + "</h2>");
		}
		out.println("<p><a href='" + request.getRequestURI() + "?cerrarSesion=true'>Borrar la sesión</a></p>");
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
