import figuras.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletFiguras
 */
@WebServlet("/ServletFiguras")
public class ServletFiguras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFiguras() {
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
		try{
			//evaluar si request son campos vacíos antes de convertirlos a double
			Double ladoX = Double.valueOf(request.getParameter("ladox"));
			Double ladoY = Double.valueOf(request.getParameter("ladoy"));
			Double radioX = Double.valueOf(request.getParameter("radiox"));
			Double radioY = Double.valueOf(request.getParameter("radioy"));

			Figura fig = null;
			if(ladoX == null){
				if(ladoY == null){
					fig = new Cuadrado(ladoX, "Cuadrado", Color.AMARILLO);
				}else{
					fig = new Rectangulo(ladoX, ladoY, "Rectangulo", Color.AZUL);
				}
			}else if(radioX == null){
				if(radioY == null){
					fig = new Circunferencia(radioX, "Circulo", Color.MAGENTA);
				}else{
					fig = new Elipse(radioX, radioY, "Elipse", Color.ROJO);
				}

			}else{
				out.println("¿No había ningún campo relleno?");
			}
			out.println("titulo: "+fig.getTitulo());
			out.println("color "+fig.getColor());
		}catch(NullPointerException e){
			
		}catch(NumberFormatException e){
			e.printStackTrace();
		}finally{
			
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
