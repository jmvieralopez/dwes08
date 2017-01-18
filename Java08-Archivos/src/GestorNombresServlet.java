

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestorNombresServlet
 */
@WebServlet("/GestorNombresServlet")
public class GestorNombresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestorNombresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		ServletContext contexto = getServletContext();
		response.setContentType("text/html");
		String ruta = contexto.getRealPath("/files/nombres.txt");
		//out.print(contexto.getRealPath("/files/nombres.txt"));
		String nombre = request.getParameter("nombre");
		if(request.getParameter("enviar") != null) {
			writeNewName(ruta, nombre);
		}else if(request.getParameter("borrar") != null) {
			deleteName(ruta, nombre);
		}
		readFile(ruta, out);
		out.println("<h3>Añada más nombres</h3>");
		out.println("<form action='./GestorNombresServlet' method='post'><input type='text' name='nombre'><input type='submit' name='enviar' value='enviar'><input type='submit' name='borrar' value='borrar'></form>");
	}
	
	// LECTURA DE FICHERO
	public void readFile(String ruta, PrintWriter out) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		String linea;
		while ((linea = br.readLine()) != null)
			out.println(linea);
		br.close();
	}
	
	// ESCRITURA
	public void writeNewName(String ruta, String newName) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, true), "UTF-8"));
		bw.append(newName+"\n");
		bw.close();
	}
	
	public void deleteName(String ruta, String nameToDelete) throws IOException{
		ArrayList lineas = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		String linea;
		while ((linea = br.readLine()) != null){
			if(!linea.equals(nameToDelete)){
				lineas.add(linea);
			}
		}
			//out.println(linea);
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, false), "UTF-8"));
		Iterator<String> it = lineas.iterator();
		while(it.hasNext()) {
			String nombre = it.next();
			bw.write(nombre+"\n");
		}
		bw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
