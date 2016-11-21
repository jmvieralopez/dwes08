import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EjemploServlet implements Servlet {
	private ServletConfig config;
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig cfg) throws ServletException {
		config = cfg;
		config.getServletContext().log("Iniciando el servlet");
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		config.getServletContext().log("Petición recibida desde " + req.getRemoteAddr());
		res.setContentType("text/plain");
		res.getWriter().println("Mensaje desde el servlet de ejemplo");
		res.getWriter().close();
	}

	public void destroy() {
		config.getServletContext().log("Destruyendo el servlet");
	}

	public String getServletInfo() {
		return "Servlet de Ejemplo";
	}

	public ServletConfig getServletConfig() {
		return config;
	}
}