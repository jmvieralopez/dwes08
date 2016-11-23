import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EscribeFecha extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
	    java.util.Date fecha = new Date();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>Fecha de hoy</title></head>");
		out.println("<body><h1>Fecha</h1></body></html>");
		out.println("<p>"+fecha+"</p>");
		out.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doGet(req, res);
	}

	public String getServletInfo() {
		return "Servlet de Ejemplo usando HttpServlet";
	}
}

