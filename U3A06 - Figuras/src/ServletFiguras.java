
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import figuras.Circunferencia;
import figuras.Color;
import figuras.Cuadrado;
import figuras.Elipse;
import figuras.Figura;
import figuras.Rectangulo;

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
		out.print("<html><body><h1>Respuesta</h1>");
		try{
			//evaluar si request son campos vacíos antes de convertirlos a double
			boolean lxCon, lyCon, rxCon, ryCon;
			lxCon = lyCon = rxCon = ryCon = false;
			Double ladoX = null, ladoY = null, radioX = null, radioY = null;
			String lx = request.getParameter("ladox");
			String ly = request.getParameter("ladoy");
			String rx = request.getParameter("radiox");
			String ry = request.getParameter("radioy");
			String color = request.getParameter("color");
			String borde = request.getParameter("borde");
			
			//recorrer campos
			Map<String, String[]> campovalor = request.getParameterMap();
			out.println("<h3>Pares campo-valor</h3><ul>");
			campovalor.forEach((parametro, valores)->{
				out.println("<li>"+parametro+"</li>");
				for(String v:valores){
					out.print(" - "+v);
				}
			});
			out.println("</ul>");
			
			//comprobar valores			
			out.println("<h3>Asignacion de valores</h3>");
			out.println("<ul>");
			if(!lx.isEmpty()){
				ladoX = Double.valueOf(lx);
				lxCon = true;
				out.println("<li>"+ladoX+"</li>");
			}
			if(!ly.isEmpty()){
				ladoY = Double.valueOf(ly);
				lyCon = true;
				out.println("<li>"+ladoY+"</li>");

			}
			if(!rx.isEmpty()){
				radioX = Double.valueOf(rx);
				rxCon = true;
				out.println("<li>"+radioX+"</li>");

			}
			if(!ry.isEmpty()){
				radioY = Double.valueOf(ry);
				ryCon = true;
				out.println("<li>"+radioY+"</li>");

			}
			
			Color colorElegido = null;
			switch(color){
				case "red": colorElegido = Color.ROJO;break;
				case "yellow": colorElegido = Color.AMARILLO;break;
				case "green": colorElegido = Color.VERDE;break;
				case "blue": colorElegido = Color.AZUL;break;
				case "magenta": colorElegido = Color.MAGENTA;break;
				default: out.println("<p>No hay color</p>");
			}
			
			Boolean hayBorde = null;
			if(borde != null){
				if(borde.equals("si")){
					hayBorde = true;
				}else if(borde.equals("no")){
					hayBorde = false;
				}else{
					out.println("<p>No has puesto si necesita borde, o ha habido un error.</p>");
				}
			}else{
				out.println("<p>No has puesto si necesita borde, o ha habido un error.</p>");
			}
			out.println("</ul>");

			//creacion objeto
			//para no perder mucho tiempo en modificar el código de las figuras, borde se ha puesto como una propiedad booleana pública.
			Figura fig = null;
			if(lxCon){
				out.println("<p>hay un lx</p>");
				if(!lyCon){
					out.print("<p>Creando cuadrado...</p>");
					fig = new Cuadrado(ladoX, "Cuadrado", colorElegido);
					out.print("<p>Cuadrado creado");
				}else{
					out.print("<p>Creando rectangulo...</p>");
					fig = new Rectangulo(ladoX, ladoY, "Rectangulo", colorElegido);
					out.print("<p>Rectangulo creado");
				}
				fig.borde = hayBorde;
				print(fig, out);
			}else if(rxCon && !lxCon && !lyCon){
				out.println("<p>hay un rx</p>");
				if(!ryCon){
					out.print("<p>Creando circulo...</p>");
					fig = new Circunferencia(radioX, "Circulo", colorElegido);
					out.print("<p>Circulo creada");
				}else{
					out.print("<p>Creando elipse...</p>");					
					fig = new Elipse(radioX, radioY, "Elipse", colorElegido);
					out.print("<p>Elipse creada");
				}
				fig.borde = hayBorde;
				print(fig, out);
			}else if(!lxCon && !lyCon && !rxCon && !ryCon){
				out.println("<p>No se ha rellenado ningun campo</p>");
			}else{
				out.println("<p>No se han rellenado bien los campos</p>");
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch(NumberFormatException e){
			e.printStackTrace();
		}finally{
			
		}
		out.println("</body></html>");
	}

	void print(Figura fig, PrintWriter out){
		out.println("<p>titulo: "+fig.getTitulo()+"</p>");
		out.println("<p>color "+fig.getColor()+"</p>");
		out.println("<p>area: "+fig.area()+"</p>");
		out.println("<p>perimetro: "+fig.perimetro()+"</p>");
		generarSvg(fig, out);
	}
	
	void generarSvg(Figura fig, PrintWriter out){
		out.println("<svg");
		if (fig instanceof Cuadrado) {
			Cuadrado cuad = (Cuadrado) fig;
			int lado = (int) (cuad.getLado() * 50);
			int margen = lado +1;
			out.println(" height='"+margen+"' width='"+margen+"'>");
			out.println("<rect width='"+lado+"' height='"+lado+"' fill='"+cuad.getColor().getColor()+"' "+dibujarBorde(fig, out)+"'/>");
		}
		if (fig instanceof Rectangulo) {
			Rectangulo rect = (Rectangulo) fig;
			int base = (int) (rect.getBase() * 50);
			int altura = (int) (rect.getAltura() * 50);
			int margenb = base +1;
			int margena = altura +1;
			out.println(" height='"+margenb+"' width='"+margena+"'>");
			out.println("<rect width='"+base+"' height='"+altura+"' fill='"+rect.getColor().getColor()+"' "+dibujarBorde(fig, out)+"'/>");
		}
		if (fig instanceof Circunferencia) {
			Circunferencia circ = (Circunferencia) fig;
			int radio = (int) (circ.getRadio() * 50);
			int margen = radio * 2 + 1;
			out.println(" height='"+margen+"' width='"+margen+"'>");
			out.println("<circle cx='"+margen/2+"' cy='"+margen/2+"' r='"+radio+"' fill='"+circ.getColor().getColor()+"' "+dibujarBorde(fig, out)+"'/>");
		}
		if (fig instanceof Elipse) {
			Elipse elip = (Elipse) fig;
			int ejex = (int) (elip.getRadioX() * 50);
			int ejey = (int) (elip.getRadioY() * 50);
			int margenx = ejex * 2 + 1;
			int margeny = ejey * 2 + 1;
			out.println(" height='"+margenx+"' width='"+margeny+"'>");
			out.println("<ellipse cx='"+margenx/2+"' cy='"+margeny/2+"' rx='"+ejex+"' ry='"+ejey+"' fill='"+fig.getColor().getColor()+"' "+dibujarBorde(fig, out)+"'/>");
		}
		
		
		out.println("</svg>");
	}
	
	String dibujarBorde(Figura fig, PrintWriter out){
		if(fig.borde){
			return "stroke='black' stroke-width='3'";
			//return ";stroke:black;stroke-width:2";
		}else{
			return "";
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
