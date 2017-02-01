

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibroVisitas
 */
@WebServlet("/LibroVisitas")
public class LibroVisitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibroVisitas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
/*		Comentario c = new Comentario("toma", "esa");
		out.println(c.getDate());*/
		String autor = request.getParameter("autor");
		String comentario = request.getParameter("comentario");
		Comentario com = null;
		ServletContext contexto = getServletContext();
		//out.println(contexto.getRealPath("/files/comentarios.txt"));
		response.setContentType("text/html");
		out.println("<body style='text-align: center; background: lightblue;'");
		out.println("<h2>Libro de visitas</h2>");
		String ruta = contexto.getRealPath("/files/comentarios.txt");
		// out.println(ruta);
		// lectura de fichero para detectar repetidos
		BufferedReader brp = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		String linea;

		if(autor.length() > 15 || autor.startsWith("0") || autor.startsWith("1") || autor.startsWith("2") || autor.startsWith("3") || autor.startsWith("4") || autor.startsWith("5") || autor.startsWith("6") || autor.startsWith("7") || autor.startsWith("8") || autor.startsWith("9")){
			out.println("No se admiten numeros al principio del autor ni más de 15 caracteres en el mismo");
		}else{
			boolean repetido = false;
			while ((linea = brp.readLine()) != null){
				String comment[] = {"", "", ""};
				comment = linea.split("\\*");
/*				for (String str : linea.split("\\*")) {
					out.println(str);
				}*/
				if(comment[1].equals(autor)){
					repetido = true;
				}
			}
			brp.close();
			if(repetido){
				out.println("No se pueden poner dos notas del mismo autor.");
			}else{
				//ESCRITURA
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, true), "UTF-8"));
//				Cuando append esta en true, write y append son iguales.
				com = new Comentario(autor, comentario);
				//formateamos el string para quitar los caracteres de retorno por un br de HTML
				String comentarioFormateado = com.getComentario().replace("\r", "</br>").replace("\n", "</br>");
				bw.write(com.getDate()+"*"+com.getAutor()+"*"+comentarioFormateado);
				bw.newLine();
				bw.close();
			}
		}
		// LECTURA DE FICHERO
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		while ((linea = br.readLine()) != null){
			String comment[] = linea.split("\\*");
			out.println("<p>On "+comment[0]+" </br>"+comment[1]+" wrote:</br>"+comment[2]+"</p>");
		}
		br.close();
		
		//CARGAR FORMULARIO
		out.print("<form action='/Visitas-Jesus-Viera/LibroVisitas' method='post'>Autor: <input type='text' name='autor'></br>Comentario: <br><textarea cols='20' rows='10' name='comentario'></textarea></br><input type='submit' name='enviar' value='Enviar comentario'></form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
