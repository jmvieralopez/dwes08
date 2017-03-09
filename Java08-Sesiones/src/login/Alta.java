package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Alta
 */
@WebServlet("/Alta")
public class Alta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Alta() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		ServletContext contexto = getServletContext();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		Statement sentencia = null;

		//HttpSession session = request.getSession();
		// session.setMaxInactiveInterval(30);
		String mensajeError = "";
		int contador = 0;
		/*if (session.getAttribute("login").equals(1)) {
			response.sendRedirect("/Java08-Sesiones/MostrarContenido");
		}*/
		String userName = "alumno";
		String password = "alumno";
		//String url = "jdbc:mariadb://localhost:4000/catalogo";
		String url = "jdbc:mariadb://localhost:3306/catalogo";
		
		if (request.getParameter("enviar2") != null) {
			if (request.getParameter("user") != null) {
				if (request.getParameter("pass") != null) {
					try {
						conn = DriverManager.getConnection(url, userName, password);
						sentencia = conn.createStatement();
						String consulta = "SELECT * FROM usuario WHERE login LIKE '" + request.getParameter("usuario")+"';";
						ResultSet rset = sentencia.executeQuery(consulta);
						
						if (!rset.isBeforeFirst()) {
							String insert = "INSERT INTO usuario VALUES ('" + request.getParameter("user")
										+ "', '" + request.getParameter("pass") + "', '"
										+ request.getParameter("nombre") + "', '" + request.getParameter("admin")
										+ "', '" + request.getParameter("descripcion") + "');";
								sentencia.executeUpdate(insert);
								mensajeError += "EXITO, loguéese ";
								response.sendRedirect("/Java08-Sesiones/Login");
							
						} else {
							mensajeError += "El usuario ya existe. ";
						}

						// Paso 6: Desconexión
						if (sentencia != null)
							sentencia.close();
						if (conn != null)
							conn.close();

					} catch (Exception e) {
						mensajeError +="Error en el registro";
						e.printStackTrace();
					}
				} else {
					mensajeError += "La contraseña está vacía. ";
				}
			} else {
				mensajeError += "El usuario está vacío. ";
			}
		}
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		out.print(mensajeError);
		out.println("<form action='/Java08-Sesiones/Alta' method='post'>" + "<label>usuario:</label>"
				+ "<input type='text' name='user'/>"+"<br>" + "<label>password:</label>"
				+ "<input type='password' name='pass'/>" +"<br>"+ "<label>nombre:</label>"
				+ "<input type='text' name='nombre'/>"+"<br>" 
				+ "Admin?: <input type='radio' name='admin' value='1'>Si<input type='radio' name='admin' value='0'>No<br>"
				+ "Descripción: <input type='text' name='descripcion'><br>"
				+ "<input type='submit' name='enviar2' value='Enviar'/></form>");
		out.println("<a href='/Java08-Sesiones/Login'>¿tienes cuenta? Haz clic aquí para iniciar sesión.</a>");
		out.println("</body></html>");

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
