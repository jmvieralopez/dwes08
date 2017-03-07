package cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaludoCookies
 */
@WebServlet("/SaludoCookies")
public class SaludoCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaludoCookies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletContext contexto = getServletContext();
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		 
		String errorUsuario = "";
		String usuario = "";
		// procesamiento del formulario 
		if (request.getParameter("enviar") != null) {
		    // validar nombre 
		    usuario = request.getParameter("usuario");
		    if (usuario == "") {
		        errorUsuario = "Debes introducir un nombre";
		    } else {
		        Cookie nuevaCookieUsuario = new Cookie("usuario", usuario);
		        nuevaCookieUsuario.setPath("/Java08-Sesiones");
		        response.addCookie(nuevaCookieUsuario);
		        response.sendRedirect("/Java08-Sesiones/SaludoCookies");
		    }
		}
		if (request.getParameter("eliminarCookie") != null) {
		    Cookie cookieCaducada = new Cookie("usuario","");
		    cookieCaducada.setMaxAge(0);
		    response.addCookie(cookieCaducada);
		    // es necesario refrescar para que se lea la cookie 
		    response.sendRedirect("/Java08-Sesiones/SaludoCookies");
		}
		// se empieza a generar la salida HTML 
		out.println("<html><head><meta charset='UTF-8'/>" + "<style> .error {color: red}</style>" + "</head><body>");
		 
		Cookie cookieUsuario = buscarCookie("usuario", request);
		if (cookieUsuario != null) {
		    out.println("<h2>Bienvenid@, " + cookieUsuario.getValue() + "</h2>");
		} else {
		    out.println("<form action='/Java08-Sesiones/SaludoCookies' method='post'>"
		            + "<label>Introduce tu nombre para dirigirnos a ti:</label>" + "<input type='text' name='usuario'/>"
		            + "<span class='error'>" + errorUsuario + "</span><br/>"
		            + "<input type='submit' name='enviar' value='Enviar'/></form>");
		}
		out.println("<p><a href='/Java08-Sesiones/SaludoCookies'>Enlace a esta misma página</a></p>");
		out.println("<p><a href='/Java08-Sesiones/SaludoCookies?eliminarCookie=true'>Eliminar cookie</a></p>");
		out.println("</body></html>");
	}
	
	private Cookie buscarCookie(String nombreCookie, HttpServletRequest request) {
		Cookie cookieReturn = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
			for(Cookie c : cookies){
		        if(c.getName().equals(nombreCookie)){
		        	cookieReturn = c;
		        }
			}
		}
		return cookieReturn;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
