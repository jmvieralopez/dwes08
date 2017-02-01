

import java.io.*;

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
 * En caso de hacerlo por descriptor de despliegue, a�adir en <servlet></..>
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

		String ruta = contexto.getRealPath("/files");

		// Crea el directorio files si no existe
		File directorioDestino = new File(ruta);
		if (!directorioDestino.exists()) {
			directorioDestino.mkdirs();
		}

		// Localizar la parte correspondiente al archivo y escribir el archivo
		try {
			boolean nombreCorrecto = false;
			for (Part part : request.getParts()) {
				String nombreArchivo = getFileName(part);
				if (nombreArchivo.length() > 0 && nombreArchivo.equals("comentarios.txt")) {
					System.out.println("Escribiendo " + nombreArchivo);
					part.write(ruta + File.separator + nombreArchivo);
					nombreCorrecto = true;
				}
			}
			if(nombreCorrecto){
				out.println("<h3>Archivo subido correctamente</h3>");
			}else{
				out.println("<h3>No tiene el formato correcto, 'comentarios.txt'</h3>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h3>No se pudo subir el archivo</h3>");
		}
		out.println("<a href='javascript:history.back()'>Volver</a>");
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
		// si hemos llegado hasta aqu�, no se encontr� 'filename' en este
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
