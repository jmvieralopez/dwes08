import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InventarNumero extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
	    int numeroAleatorio = (int) (Math.random()*25+1);
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>Numero aleatorio</title></head>");
		out.println("<body><h1>Numero</h1></body></html>");
		out.println("<p>"+numeroAleatorio+"</p>");
		out.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doGet(req, res);
	}

	public String getServletInfo() {
		return "Servlet de Ejemplo usando HttpServlet";
	}
}
