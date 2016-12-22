
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletArchivo
 */
@WebServlet("/ServletArchivo")
public class ServletArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletArchivo() {
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
		ServletContext contexto = getServletContext();
		PrintWriter out = response.getWriter();
		// out.println(contexto.getRealPath("/files/modulos.txt"));
		
		// LECTURA DE FICHERO
		String ruta = contexto.getRealPath("/files/modulos.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		String linea;
		while ((linea = br.readLine()) != null)
			out.println(linea);
		br.close();
		
		// ESCRITURA
		// String ruta = contexto.getRealPath("/files/modulos.txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, true), "UTF-8"));
//		bw.write("Lenguajes de marcas\n");
//		bw.write("Entornos de desarrollo\n");
		bw.append("Lenguajes de marcas\n");
		bw.append("Entornos de desarrollo\n");
		bw.write("¿He podido añadir esta línea? Contiene UTF-8\n");
		bw.close();
		
		// NUEVO ARCHIVO
/*		String ruta2 = contexto.getRealPath("/files/nuevo.txt");
		File archivo = new File(ruta2);
		archivo.createNewFile();
	*/	
		// ELIMINAR ARCHIVO
		String ruta2 = contexto.getRealPath("/files/nuevo.txt");
		File archivo = new File(ruta2);
		archivo.delete();
		
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
