package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MostrarContenido
 */
@WebServlet("/MostrarContenido")
public class MostrarContenido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarContenido() {
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
		String mensajeError = "";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null || !session.getAttribute("login").equals(1)){
			response.sendRedirect("/Java08-Sesiones/Login");
		}
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();

		try {
			Connection conn = null;
			Statement sentencia = null;

			// Paso 1: Cargar el driver JDBC.
			Class.forName("org.mariadb.jdbc.Driver").newInstance();

			// Paso 2: Conectarse a la Base de Datos utilizando la clase
			// Connection
			String userName = "alumno";
			String password = "alumno";
			//String url = "jdbc:mariadb://localhost:4000/catalogo";
			String url = "jdbc:mariadb://localhost:3306/catalogo";
			conn = DriverManager.getConnection(url, userName, password);

			// Paso 3: Crear sentencias SQL, utilizando objetos de tipo
			// Statement
			sentencia = conn.createStatement();
			
			//COMPROBAR USUARIO
			String consultaUser = "SELECT * FROM usuario WHERE login LIKE '" + session.getAttribute("usuario")+"';";
			ResultSet rsetUser = sentencia.executeQuery(consultaUser);
			if (!rsetUser.isBeforeFirst()) {
				mensajeError += "<h3>No existe el usuario</h3>";
			}
			boolean noExiste = true;
			while(rsetUser.next()){
				if(rsetUser.getString("login").equals(session.getAttribute("usuario"))){
					out.println("<h2>Bienvenido al catálogo de "+rsetUser.getString("nombre")+"</h2>");
					noExiste = false;
				}
			}
			if(noExiste){
				mensajeError += "El usuario no existe.";
				response.sendRedirect("/Java08-Sesiones/Logout");
			}
			
			// GENERAR HTML
			out.println("<html><head><meta charset='UTF-8'/>  <meta name='viewport' content='width=device-width, initial-scale=1'>  <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>  <script src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>  <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script></head><body><div class='container-fluid'");
			out.println("<p>Sesión"+session.getAttribute("login")+" "+session.getAttribute("usuario")+"</p>");
			out.println("<h3>Busqueda por titulo</h3>");
			out.println("<form action='?' method='get'>");
			out.println("<input type='text' name='titulo'>");
			out.println("<input type='submit' value='Buscar'>");
			out.println("</form>");

			// Paso 4: Ejecutar la sentencia SQL a través de los objetos
			String consulta = "SELECT artistas.nombre AS nomartista, canciones.* FROM artistas, canciones WHERE (artistas.id = canciones.idArtista)";
			Map<String, String[]> param = request.getParameterMap();
			if(param.containsKey("artista")){
				String artista = request.getParameter("artista");
				consulta += " AND (artistas.nombre = '" + artista + "')";
			}
			
			if(param.containsKey("titulo")){
				String titulo = request.getParameter("titulo");
				consulta += " AND (artistas.nombre = '" + titulo + "')";
			}
			
			if (param.containsKey("campo") && param.containsKey("orden")) {
				String campo = request.getParameter("campo");
				String orden = request.getParameter("orden");
				if(campo.equals("artista")){
					if(orden.equals("a")){
						consulta += " ORDER BY nomartista";
					}else if(orden.equals("d")){
						consulta += " ORDER BY nomartista DESC";
					}
				}else if(campo.equals("cancion")){
					if(orden.equals("a")){
						consulta += " ORDER BY cancion";
					}else if(orden.equals("d")){
						consulta += " ORDER BY cancion DESC";
					}
				}
			} else if(!param.isEmpty() && !param.containsKey("artista") && !param.containsKey("titulo")){
				out.println("<p>No se han puesto bien los parámetros</p>");
			}
			
			ResultSet rset = sentencia.executeQuery(consulta);

			if (!rset.isBeforeFirst()) {
				out.println("<h3>No hay resultados 2</p>");
			}

			// Paso 5: Mostrar resultados
			out.println(
					"<div class='table-responsive'><table class='table'><tr><td>artista <a href='?campo=artista&orden=a'>&#9650;</a> <a href='?campo=artista&orden=d'>&#9660;</a></td>"
							+ "<td>cancion <a href='?campo=cancion&orden=a'>&#9650;</a> <a href='?campo=cancion&orden=d'>&#9660;</a></td></tr>");
			while (rset.next()) {
				Cancion c = new Cancion(rset.getInt("id"), rset.getInt("idArtista"), rset.getString("cancion"),
						rset.getString("nomartista"), rset.getString("album"), rset.getString("caratula"));

				out.println("<tr><td><a href='?artista=" + c.getArtista()+ "'>" + c.getArtista() + "</a></td><td><a href='MostrarObra?id="+ c.getId() +"'>" + c.getCancion() + "</a></td></tr>");
			}
			out.println("</table></div>");

			// Paso 6: Desconexión
			if (sentencia != null)
				sentencia.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<a href='?'>Eliminar filtros (volver)</a>");
		out.println("<a href='/Java08-Sesiones/Logout'>Cerrar sesión</a>");
		out.println("<a href='/Java08-Sesiones/Baja'>Darse de baja</a>");
		out.println("</div></body></html>");
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
