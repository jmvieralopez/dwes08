

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
 * Servlet implementation class Problema23
 */
@WebServlet("/Problema23")
public class Problema23 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Problema23() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = "<form action='/RepasoJava/Problema23' method='post'>Texto: <br><input type='text' name='texto' /><input type='submit' value='enviar'>";
		PrintWriter out = response.getWriter();
		String texto = request.getParameter("texto");
		ServletContext contexto = getServletContext();
		response.setContentType("text/html");
		// ESCRITURA
		String ruta = contexto.getRealPath("/files/texto.txt");
		// out.println(ruta);
		// lectura de fichero para detectar repetidos
		BufferedReader brp = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		// evaluar línea mas larga
		String linea;

		if(texto.length() > 50){
			out.println("No se admiten más de 50 caracteres");
		}else{
			boolean repetido = false;
			while ((linea = brp.readLine()) != null){
				if(linea.equals(texto)){
					repetido = true;
				}
			}
			brp.close();
			if(repetido){
				out.println("Esta nota ya existe.");
			}else{
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, true), "UTF-8"));
//				Cuando append esta en true, write y append son iguales.
				bw.write(texto);
				bw.newLine();
				bw.close();
			}
		}
		// LECTURA DE FICHERO
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		// evaluar línea mas larga
		int ancho = 0;
		while ((linea = br.readLine()) != null){
			if(ancho < linea.length()){
				ancho = linea.length();
			}
		}
		br.close();
		// escribir en pantalla
		out.println("<pre>");
		// barra arriba y margen superior
		out.print(" ");
		for(int i = 0; i < ancho + 4; i++){
			out.print("_");
		}
		out.println(" ");
		out.print("/");
		for(int i = 0; i < ancho + 4; i++){
			out.print(" ");
		}
		out.println("\\");
		// texto
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		while ((linea = br2.readLine()) != null){
			out.print("|  "+linea);
			for(int j = 0; j < (ancho + 2 - linea.length()); j++){
				out.print(" ");
			}
			out.println("|");
		}
		br2.close();
		// barra abajo
		out.print("\\");
		for(int i = 0; i < ancho + 4; i++){
			out.print("_");
		}
		out.println("/");
		out.println("</pre>");

		out.println(form);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
