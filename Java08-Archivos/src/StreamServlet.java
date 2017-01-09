

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//INICIALIZAR ARRAY
		ArrayList<String> nombres = new ArrayList<String>();
		nombres.add("Julia");
		nombres.add("Ana");
		nombres.add("Sergio");
		nombres.add("Alberto");

		nombres.stream()
		  .filter(s -> s.startsWith("A"))
		  .map(String::toUpperCase)
		  .sorted()
		  .forEach(System.out::println);
		
		//INICIALIZAR VARIABLES CONTEXTO
		ServletContext contexto = getServletContext();
		PrintWriter out = response.getWriter();

		//LECTURA
		Path path = Paths.get(contexto.getRealPath("/files/modulos.txt"));
/*		try (Stream<String> stream = Files.lines(path)) {
				stream.forEach(out::println);
		} catch (Exception e) {
				out.println(e.toString());
		}
		*/
		//LECTURA 2
/*		Path path = Paths.get(contexto.getRealPath("/files/modulos.txt"));
		try (Stream<String> stream = Files.lines(path)) {
		  stream.forEach(s -> {
		    out.println(s);
		  });
		} catch (Exception e) {
		  out.println(e.toString());
		}
*/
		//LECTURA CON SALTO DE LINEA
/*		try (Stream<String> stream = Files.lines(path)) {
			  stream.forEach(s -> {
					out.println(s + "<br/>");
			});
			} catch (Exception e) {
				out.println(e.toString());
			}
		*/
		//LECTURA CON FILTRO
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(s -> {
			  if (s.startsWith("D"))
			    out.println(s + "<br/>");
			});
		} catch (Exception e) {
			out.println(e.toString());
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
