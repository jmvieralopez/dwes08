import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EjemploServletHttp extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>EjemploServletHttp</title></head>");
		out.println("<body><h1>Mensaje desde el servlet HTTP de ejemplo</h1></body></html>");
		out.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doGet(req, res);
	}

	public String getServletInfo() {
		return "Servlet de Ejemplo usando HttpServlet";
	}
}
