package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MostrarCuidador
 */
@WebServlet("/MostrarCuidador")
public class MostrarCuidador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarCuidador() {
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
		int idCuidador = 0;
		boolean errorIdCuidadorAusente = false;
		boolean errorIdCuidadorFormato = false;
		boolean errorIdCuidadorInexistente = false;
		String idCuidadorParametro = request.getParameter("idCuidador");
		if (idCuidadorParametro == null)
			errorIdCuidadorAusente = true;
		else {
			try {
				idCuidador = Integer.parseInt(idCuidadorParametro);
			} catch (Exception e) {
				errorIdCuidadorFormato = true;
			}
		}

		if (errorIdCuidadorAusente) {
			out.println("<h3>Error: falta identificador de cuidador</h3>");
		} else if (errorIdCuidadorFormato) {
			out.println("<h3>Error: el identificador de cuidador debe ser numérico</h3>");
		} else {
			Connection conn = null;
			Statement sentencia = null;
			try {
				// Paso 1: Cargar el driver JDBC.
				Class.forName("org.mariadb.jdbc.Driver").newInstance();

				// Paso 2: Conectarse a la Base de Datos utilizando la clase
				// Connection
				String userName = "alumno";
				String password = "alumno";
				String url = "jdbc:mariadb://localhost:3306/animales";
				conn = DriverManager.getConnection(url, userName, password);

				// Paso 3: Crear la sentencia SQL
				sentencia = conn.createStatement();

				// Paso 4: Ejecutar la sentencia SQL a través de los objetos
				// Statement
				String consulta = "SELECT nombre from cuidador WHERE idCuidador=" + idCuidador;
				ResultSet rset = sentencia.executeQuery(consulta);

				// Paso 5: Mostrar resultados
				if (!rset.isBeforeFirst()) {
					out.println("<h3>Error: identificador de cuidador no válido</h3>");
				} else {
					rset.next();
					out.println("<p>Animales cuidados por " + rset.getString("nombre") + ":</p>");
					// TO-DO: código para mostrar los animales
					
				}

				// Paso 6: Desconexión
				if (sentencia != null)
					sentencia.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
