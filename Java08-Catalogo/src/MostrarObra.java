

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

/**
 * Servlet implementation class MostrarObra
 */
@WebServlet("/MostrarObra")
public class MostrarObra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarObra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='UTF-8'/></head><body>");
		ServletContext contexto = getServletContext();
		int idCancion = 0;
		boolean errorIdCancionAusente = false;
		boolean errorIdCancionFormato = false;
		boolean errorIdCancionInexistente = false;
		String idCancionParametro = request.getParameter("id");
		if (idCancionParametro == null)
			errorIdCancionAusente = true;
		else {
			try {
				idCancion = Integer.parseInt(idCancionParametro);
			} catch (Exception e) {
				errorIdCancionFormato = true;
			}
		}

		if (errorIdCancionAusente) {
			out.println("<h3>Error: falta identificador de cuidador</h3>");
		} else if (errorIdCancionFormato) {
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
				String url = "jdbc:mariadb://localhost:4000/catalogo";
				conn = DriverManager.getConnection(url, userName, password);

				// Paso 3: Crear la sentencia SQL
				sentencia = conn.createStatement();

				// Paso 4: Ejecutar la sentencia SQL a través de los objetos Statement
				String consulta = "SELECT artistas.nombre AS nomartista, canciones.* FROM artistas, canciones WHERE (artistas.id = canciones.idArtista) AND (canciones.id = " + idCancion + ")";
				ResultSet rset = sentencia.executeQuery(consulta);

				// Paso 5: Mostrar resultados
				if (!rset.isBeforeFirst()) {
					out.println("<h3>Error: identificador de canción no válido</h3>");
				} 

					out.println("<table><tr><td>artista</td><td>cancion</td><td>album</td><td>caratula</td></tr>");
					while (rset.next()) {
						Cancion c = new Cancion(rset.getInt("id"), rset.getInt("idArtista"), rset.getString("cancion"), rset.getString("nomartista"), rset.getString("album"), rset.getString("caratula"));

						out.println("<tr><td>" + c.getArtista() + "</td><td>" + c.getCancion() + "</td><td>" + c.getAlbum() + "</td><td><img width='100' height='100' src='./img/" + c.getCaratula() + "'></td></tr>");
					}
					out.println("</table>");
				

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
