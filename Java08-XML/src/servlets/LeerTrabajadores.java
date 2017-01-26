package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.UtilXML;

/**
 * Servlet implementation class LeerTrabajadores
 */
@WebServlet("/LeerTrabajadores")
public class LeerTrabajadores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeerTrabajadores() {
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
		PrintWriter out = response.getWriter();

		String ruta = contexto.getRealPath("/files/trabajadores.xml");
		Document documentoXML = UtilXML.abrirDocumentoXML(ruta);
		if (documentoXML == null) {
			out.println("<h3>Error al procesar el archivo XML</h3>");
			return;
		}
		
		Element raiz = documentoXML.getDocumentElement();
		out.println("<h4>El elemento raíz es " + raiz.getTagName() + "</h4>");

		NodeList nodos = raiz.getChildNodes();
		for (int i = 0; i < nodos.getLength(); i++) {
			Node n = nodos.item(i);
			if (n instanceof Element) {
				Element hijo = (Element) n;
				out.println("<h4>"+hijo.getTagName() + " de tipo " + hijo.getAttribute("categoria") + "</h4>");
			}
		}

		// Salida más detallada:
		out.println("<h2>Plantilla</h2>");

		// para cada elemento trabajador...
		for (int i = 0; i < nodos.getLength(); i++) {
			Node n = nodos.item(i);
			if (n instanceof Element) {
				Element trabajador = (Element) n;
				out.println("<ul>");
				// Nombre
				Node nodoNombre = trabajador.getElementsByTagName("nombre").item(0);
				if (nodoNombre instanceof Element) {
					Element nombre = (Element) nodoNombre;
					Node contenidoNombre = nombre.getFirstChild();
					out.println("<li>Nombre: " + contenidoNombre.getNodeValue() + "</li>");
				}
				// Categoria
				out.println("<li>Categoria: " + trabajador.getAttribute("categoria") + "</li>");
				// Direccion
				Node nodoDir = trabajador.getElementsByTagName("direccion").item(0);
				if (nodoDir instanceof Element) {
					Element dir = (Element) nodoDir;
					Node contenidoDir = dir.getFirstChild();
					out.println("<li>Dirección: " + contenidoDir.getNodeValue() + "</li>");
				}
				// Teléfonos
				out.println("<li>Teléfonos</li>");
				Node nodoTel = trabajador.getElementsByTagName("telefonos").item(0);
				if (nodoTel instanceof Element) {
					Element tel = (Element) nodoTel;
					NodeList numeros = tel.getChildNodes();
					out.println("<ul>");
					for (int j = 0; j < numeros.getLength(); j++) {
						Node t = numeros.item(j);
						if (t instanceof Element) {
							Element numero = (Element) t;
							Node contenidoNum = numero.getFirstChild();
							out.println("<li>Tel: " + contenidoNum.getNodeValue() + "</li>");
						}
					}
					out.println("</ul>");
				}
				out.println("</ul><hr/>\n");

			}
		}
		out.println("</body></html>");
	} // doGet

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
