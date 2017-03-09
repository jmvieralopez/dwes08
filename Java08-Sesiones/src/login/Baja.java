package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class Baja
 */
@WebServlet("/Baja")
public class Baja extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Baja() {
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
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		Statement sentencia = null;

		HttpSession session = request.getSession();
		// session.setMaxInactiveInterval(30);
		String mensajeError = "";
		int contador = 0;
		if (!session.getAttribute("login").equals(1)) {
			response.sendRedirect("/Java08-Sesiones/Login");
		}
		String userName = "alumno";
		String password = "alumno";
		// String url = "jdbc:mariadb://localhost:4000/catalogo";
		String url = "jdbc:mariadb://localhost:3306/catalogo";

		if (request.getParameter("enviar") != null) {
			if (request.getParameter("password") != null) {
				try {
					conn = DriverManager.getConnection(url, userName, password);
					sentencia = conn.createStatement();
					String consulta = "SELECT * FROM usuario WHERE login LIKE '" + session.getAttribute("usuario") + "';";
					ResultSet rset = sentencia.executeQuery(consulta);
					if (!rset.isBeforeFirst()) {
						mensajeError = "<h3>No existe el usuario</h3>";
					}
					String user = "";
					String pass = "";
					while (rset.next()) {
						if (rset.getString("login").equals(session.getAttribute("usuario"))) {
							user = rset.getString("login");
							pass = rset.getString("password");
						}
					}
					//rset.beforeFirst();
					rset.close();
					sentencia.close();
					if (user.length() > 0 && pass.equals(request.getParameter("password"))) {
						mensajeError += "<h3>Borrando</h3>";
						String insert = "DELETE FROM usuario WHERE login LIKE '" + user + "';";
						Statement sentencia2 = conn.createStatement();
						sentencia2.executeUpdate(insert);
						sentencia2.close();
						response.sendRedirect("/Java08-Sesiones/Logout");
					}

					// Paso 6: Desconexión
					if (sentencia != null)
						sentencia.close();
					if (conn != null)
						conn.close();

				} catch (Exception e) {
					out.println("<h3>Error en el borrado</h3>");
					e.printStackTrace();
				}
			} else {
				mensajeError += "La contraseña está vacía. ";
			}
			out.print(mensajeError);
		}
		out.println("<form action='/Java08-Sesiones/Baja' method='post'>" + "<label>contraseña:</label>"
				+ "<input type='password' name='password'/>"
				+ "<input type='submit' name='enviar' value='Enviar'/></form>");
		out.println("<a href='/Java08-Sesiones/MostrarContenido'>Cancelar</a>");
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
