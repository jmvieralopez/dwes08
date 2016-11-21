import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EjemploServletGenerico extends GenericServlet {
	private static final long serialVersionUID = 1L;
/*
  public void init() {
    log("Iniciando el servlet genérico");
  }
*/
  public void service(ServletRequest req, ServletResponse res) throws IOException {
    log("Petición recibida desde " + req.getRemoteAddr());
    res.setContentType("text/plain");
    res.getWriter().println("Mensaje desde el servlet genérico de ejemplo");
    res.getWriter().close();
  }
/*
  public void destroy() {
    log("Destruyendo el servlet genérico");
  }

  public String getServletInfo() {
    return "Servlet de Ejemplo usando GenericServlet";
  }*/
}
