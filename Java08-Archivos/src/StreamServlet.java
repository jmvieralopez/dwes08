
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StreamServlet
 */
@WebServlet("/StreamServlet")
public class StreamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StreamServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.setContentType("text/html");

		// INICIALIZAR ARRAY
		ArrayList<String> nombres = new ArrayList<String>();
		nombres.add("Julia");
		nombres.add("Ana");
		nombres.add("Sergio");
		nombres.add("Alberto");

		nombres.stream().filter(s -> s.startsWith("A")).map(String::toUpperCase).sorted().forEach(System.out::println);

		// INICIALIZAR VARIABLES CONTEXTO
		ServletContext contexto = getServletContext();
		PrintWriter out = response.getWriter();

		// LECTURA
		Path path = Paths.get(contexto.getRealPath("/files/modulos.txt"));
		out.println(path);
		/*
		 * try (Stream<String> stream = Files.lines(path)) {
		 * stream.forEach(out::println); } catch (Exception e) {
		 * out.println(e.toString()); } //INICIALIZAR VARIABLES CONTEXTO
		 * 
		 * 
		 * //LECTURA 2 Path path =
		 * Paths.get(contexto.getRealPath("/files/modulos.txt")); try
		 * (Stream<String> stream = Files.lines(path)) { stream.forEach(s -> {
		 * out.println(s); }); } catch (Exception e) {
		 * out.println(e.toString()); }
		 * 
		 * //LECTURA CON SALTO DE LINEA try (Stream<String> stream =
		 * Files.lines(path)) { stream.forEach(s -> { out.println(s + "<br/>");
		 * }); } catch (Exception e) { out.println(e.toString()); }
		 * 
		 * //LECTURA CON FILTRO try (Stream<String> stream = Files.lines(path))
		 * { stream.forEach(s -> { if (s.startsWith("D")) out.println(s +
		 * "<br/>"); }); } catch (Exception e) { out.println(e.toString()); }
		 */
		// LECTURA CON FILTRO Y LAMBDAS
/*		try (Stream<String> stream = Files.lines(path)) {
			stream.filter(s -> s.startsWith("D")).map(String::toUpperCase).sorted()
					.forEach(s -> out.println(s + "<br/>"));
		}
*/		// SOBREESCRITURA
		Path rutaArchivoEscritura = Paths.get(contexto.getRealPath("/files/modulos.txt"));        
/*		try (BufferedWriter writer = Files.newBufferedWriter(rutaArchivoEscritura, StandardCharsets.UTF_8)) {
		  writer.write("Desarrollo web en entorno servidor\n");
		  writer.write("Inglés técnico\n");   
		}
*/		// AÑADIR TEXTO
/*		try (BufferedWriter writer = Files.newBufferedWriter(rutaArchivoEscritura, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			  writer.write("Lenguajes de Marcas\n");
			  writer.write("Entornos de desarrollo\n");
		}
*/		Path rutaArchivoSecreto = Paths.get(contexto.getRealPath("/WEB-INF/secreto.txt"));      
		try (BufferedWriter writer = Files.newBufferedWriter(rutaArchivoSecreto, StandardCharsets.UTF_8)) {
		  writer.write("Información no visible por los usuarios\n");  
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
