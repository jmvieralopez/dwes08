package servlets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComprobarNumero
 */
@WebServlet("/ComprobarNumero")
public class ComprobarNumero extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComprobarNumero() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext contexto = getServletContext();
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='UTF-8'/>" + "<style>	* {margin:5px; }" + ".error {color: red}"
				+ ".premio {color: green}" + "</style></head><body>");

		String errorNumero = "", errorCantidad = "";
		boolean error = false;
		int numero = 0, cantidad = 0;
		if (request.getParameter("enviar") != null) {
			// validar número
			try {
				numero = Integer.parseInt(request.getParameter("numero"));
			} catch (NumberFormatException e) {
				error = true;
				errorNumero = "Debes introducir un valor numérico";
			}
			if (((Integer) numero).toString().length() != 5) {
				error = true;
				errorNumero = "El número debe tener cinco dígitos";
			}
			// validar cantidad
			try {
				cantidad = Integer.parseInt(request.getParameter("cantidad"));
			} catch (NumberFormatException e) {
				error = true;
				errorCantidad = "Debes introducir un valor numérico";
			}

			// comprobamos el número si los datos del formulario están bien
			if (!error) {
				// Almacenamos los pares número-premio en un array asociativo
				// (Map)
				HashMap<Integer, Integer> premios = new HashMap<Integer, Integer>();
				String ruta = contexto.getRealPath("/files/loteria.txt");
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
				String linea;
				while ((linea = br.readLine()) != null) {
					String[] par = linea.split(" ");
					int numeroLeido = Integer.parseInt(par[0]);
					int premioLeido = Integer.parseInt(par[1]);
					premios.put(numeroLeido, premioLeido);
				}
				br.close();
				// Comprobamos el número
				if (premios.containsKey(numero)) {
					int dinero = premios.get(numero) * cantidad;
					out.println("<h3 class='premio'>¡¡ Te han tocado " + dinero + " &euro; !!</h3>");
				} else {
					out.println("<h3>No te ha tocado nada, sigue participando</h3>");
				}
			}
		}

		// Mostramos el formulario
		out.println("<form action='/Java-Loteria/ComprobarNumero' method='post'>"
				+ "<label>¿Qué número has jugado?</label>" + "<input type='text' name='numero'/><span class='error'>"
				+ errorNumero + "</span><br/>" + "<label>¿Cuántos billetes has comprado?</label>"
				+ "<input type='text' name='cantidad'><span class='error'>" + errorCantidad + "</span><br/>"
				+ "<input type='submit' name='enviar' value='Comprobar número'/></form>");

		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
