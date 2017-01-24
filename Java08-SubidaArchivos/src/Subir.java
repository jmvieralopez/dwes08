
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Subir
 */
@WebServlet("/Subir")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 10 MB
/*
 * En caso de hacerlo por descriptor de despliegue, añadir en <servlet></..>
 * 
 * <multipart-config> <max-file-size>1024</max-file-size> </multipart-config>
 */
public class Subir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subir() {
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
		out.println("<html><head><meta charset='UTF-8'/></head><body>");

		String ruta = contexto.getRealPath("/upload");

		// Crea el directorio files si no existe
		File directorioDestino = new File(ruta);
		if (!directorioDestino.exists()) {
			directorioDestino.mkdirs();
		}

		// Localizar la parte correspondiente al archivo y escribir el archivo
		try {
			for (Part part : request.getParts()) {
				String nombreArchivo = getFileName(part);
				if (nombreArchivo.length() > 0) {
					System.out.println("Escribiendo " + nombreArchivo);
					part.write(ruta + File.separator + nombreArchivo);
				}
			}
			out.println("<h3>Archivo subido correctamente</h3>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h3>No se pudo subir el archivo</h3>");
		}
		out.println("</body></html>");

	}

	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("La cabecera content-disposition es: " + contentDisp);
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		// si hemos llegado hasta aquí, no se encontró 'filename' en este
		// elemento de la cabecera
		return "";
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
