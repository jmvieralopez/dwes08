package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//ServletContext contexto = getServletContext();
		//out.print("<p>"+contexto.getRealPath("/Java08-Sesiones")+"</p>");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		Statement sentencia = null;
		HttpSession session = request.getSession();
		if(session.isNew()){
			session.setAttribute("usuario", "");
			session.setAttribute("login", 0);
		}
		//session.setMaxInactiveInterval(30);
		String mensajeError = "";
		int contador = 0;
		if(session.getAttribute("login") != null){
			if(session.getAttribute("login").equals(1)){
				response.sendRedirect("/Java08-Sesiones/MostrarContenido");
			}
		}
		if(request.getParameter("enviar") != null){
			mensajeError += "pasa por el if principal";

			boolean iniciado = false;
			String userName = "alumno";
			String password = "alumno";
			//String url = "jdbc:mariadb://localhost:4000/catalogo";
			String url = "jdbc:mariadb://localhost:3306/catalogo";
			try{
				conn = DriverManager.getConnection(url, userName, password);
				//conn = DriverManager.getConnection(url + "?user=" + userName + "&password=" + password);
				sentencia = conn.createStatement();
				String consulta = "SELECT * FROM usuario WHERE login LIKE '" + request.getParameter("user")+"';";
				ResultSet rset = sentencia.executeQuery(consulta);
				if (!rset.isBeforeFirst()) {
					mensajeError += "<h3>No existe el usuario</h3>";
				}
				while(rset.next()){
					mensajeError += "pasa por el while";
					if(rset.getString("login").equals(request.getParameter("user"))){
						if(rset.getString("password").equals(request.getParameter("pass"))){
							session.setAttribute("login", 1);
							session.setAttribute("usuario", request.getParameter("user"));
							response.sendRedirect("/Java08-Sesiones/MostrarContenido");
							//response.sendRedirect("/Java08-Sesiones/InfoSesion2");

						}else{
							mensajeError += "La contraseña está mal";
						}
					}else{
						mensajeError += "El usuario no existe.";
					}
				}
				
				// Paso 6: Desconexión
				if (sentencia != null)
					sentencia.close();
				if (conn != null)
					conn.close();
				if(iniciado){
									}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		out.println(mensajeError);
		out.println("<p>Sesión"+session.getAttribute("login")+" "+session.getAttribute("usuario")+"</p>");

		out.println("<form action='/Java08-Sesiones/Login' method='post'>"
	            + "<label>usuario:</label>" + "<input type='text' name='user'/>"+"<br>"
	            + "<label>password:</label>" + "<input type='password' name='pass'/>"+"<br>"
	            + "<input type='submit' name='enviar' value='Enviar'/></form>");
		out.println("<a href='/Java08-Sesiones/Alta'>¿aún no tienes cuenta? Haz clic aquí para crear una.</a>");
		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
