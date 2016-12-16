

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class ServletVenta
 */
@WebServlet("/ServletVenta")
public class ServletVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVenta() {
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
		String marca=request.getParameter("marca");
		String modelo=request.getParameter("modelo");
		String fecha=request.getParameter("fecha");
		String color=request.getParameter("color");
		String kmUso=request.getParameter("kmuso");
		String precio=request.getParameter("precio");
		String estado=request.getParameter("estado");

		String usuario=request.getParameter("usuario");
		String pass=request.getParameter("pass");
		String pass2=request.getParameter("pass2");
		String email=request.getParameter("email");
		String usuarios=request.getParameter("usuarios");
		
		boolean marcaok, modelok, fechaok, colorok, kmusook, preciook, usuok, passok, emailok;
		marcaok = modelok = fechaok = colorok = kmusook = preciook = usuok = passok = emailok = false;
		
		out.println("<!DOCTYPE html><html><head><title>Venta Jesus Viera</title></head><body>");
		printTable(out, request);
		
		if(marca.isEmpty()){
			out.println("<p>Tiene que rellenar marca</p>");
		}else{
			marcaok = true;
		}
		
		if(modelo.isEmpty()){
			out.println("<p>Tiene que rellenar modelo</p>");
		}else{
			modelok = true;
		}
		
		if(fecha.isEmpty()){
			out.println("<p>Tiene que rellenar la fecha</p>");
		}else{
			fechaok = true;
			/*try{
				//si quisiéramos usar la fecha deberíamos hacer la conversión
				fechaok = true;
			}catch(Exception e){
				
			}*/
		}
		
		if(color.isEmpty()){
			out.println("<p>Tiene que rellenar color</p>");
		}else{
			colorok = true;
		}

		if(kmUso.isEmpty()){
			out.println("<p>Tiene que rellenar km de uso</p>");
		}else{
			try{
				int kmUsoInt = Integer.valueOf(kmUso);
				kmusook = true;
			}catch(NumberFormatException e){
				e.printStackTrace();
				out.println("<p>no se ha escrito un numero en km de uso</p>");
			}
		}
		
		if(precio.isEmpty()){
			out.println("<p>Tiene que rellenar precio</p>");
		}else{
			try{
				int precioInt = Integer.valueOf(precio);
				preciook = true;
			}catch(NumberFormatException e){
				e.printStackTrace();
				out.println("<p>no se ha escrito un numero en precio</p>");
			}
		}
		
		if(usuario.isEmpty()){
			out.println("<p>Tiene que rellenar el usuario</p>");
		}else{
			boolean yaRegistrado = false;
			String[] nombres = usuarios.split(";"); 
			for (int j=0; j<nombres.length; j++) {
				if(nombres[j].equals(usuario)){
					yaRegistrado = true;
				}
			}
			if(yaRegistrado){
				out.println("<p>usuario ya registrado</p>");
			}else{
				usuok = true;
			}
		}
		
		if(pass.length()>=8){
			if(pass.equals(pass2)){
				passok = true;
			}else{
				out.print("<p>La contraseña no es igual</p>");
			}
		}else{
			out.println("<p>La contraseña requiere 8 caracteres o más</p>");
		}
		
		if(email.isEmpty()){
			out.println("<p>Tiene que rellenar el email</p>");
		}else{
			if(email.contains("@") && email.contains(".")){
				emailok = true;
			}else{
				out.println("<p>E-mail no está bien construido</p>");
			}
		}
		//out.println("<p>Vamos a hacer la comprobación:</p>");
		if(marcaok && modelok && fechaok && colorok && kmusook && preciook && usuok && passok && emailok){
			Coche nuevoCoche = new Coche(marca, modelo, fecha, color, kmUso, precio, estado);
			usuarios = usuarios + usuario + ";";
			out.println("Ha entrado.");
			//printTable(out, request);
			printForm(out, usuarios);
		}else{
			out.println("<p>Algo ha fallado, vea arriba el motivo</p>");
		}
		
		out.println("<body></html>");
	}
	
	void printForm(PrintWriter out, String usuarios){
		out.println("<form action='./ServletVenta' method='post' name='formulario'>");
		out.println("<h3>Datos de un nuevo vehículo</h3><p>Todos los campos son obligatorios excepto estado</p><fieldset>Marca:<input type='text' name='marca'><br>Modelo:<input type='text' name='modelo'><br>Fecha:<input type='date' name='fecha'><br>Color:<input type='text' name='color'><br>Km de uso:<input type='number' name='kmuso'><br>Precio:<input type='number' name='precio'><br>Estado:<textarea cols='6' rows='3' name='estado'></textarea></fieldset>");
		out.println("<h3>Datos del usuario</h3><fieldset>Usuario:<input type='text' name='usuario'><br>Contraseña (min 8 carac.):<input type='password' name='pass'><br>Repita la contraseña:<input type='password' name='pass2'><br>E-mail:<input type='email' name='email'><br></fieldset>");
		out.println("<input type='hidden' name='usuarios' value='"+usuarios+"'/>");
		out.println("<input type='submit' value='Enviar'></form>");
	}

	void printTable(PrintWriter out, HttpServletRequest request){

		Map<String, String[]> campovalor = request.getParameterMap();
		out.println("<h3>Datos ingresados</h3><ul>");
		out.println("<table>");
		campovalor.forEach((parametro, valores)->{
			out.println("<tr><td>"+parametro+"</td>");
			for(String v:valores){
				if(parametro.equals("pass") || parametro.equals("pass2")){
					out.print("<td>");
					for(int i=0; i<v.length(); i++){
						out.print("*");
					}
					out.print("</td>");
				}else{
					out.print("<td>"+v+"</td>");
				}
			}
			out.print("</tr>");
		});
		out.println("</table>");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
