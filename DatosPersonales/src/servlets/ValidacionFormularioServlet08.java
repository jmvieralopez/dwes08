package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Servlet implementation class ValidacionFormularioServlet08
 */
@WebServlet("/ValidacionFormularioServlet08")
public class ValidacionFormularioServlet08 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidacionFormularioServlet08() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>MostrarVariables</title><meta charset='UTF-8'/></head><body>");
		String pass = request.getParameter("contrasena");
        String fecha = request.getParameter("fecha");
        String numfavorito = request.getParameter("numfavorito");

        //nombres de los campos
		Enumeration <String> campos = request.getParameterNames();
		out.println("<h3>Nombres de los campos</h3><ul>");
		while(campos.hasMoreElements()){
			out.println("<li>"+campos.nextElement()+"</li>");
		}
		out.println("</ul>");

		//pares campo-valor
		Map<String, String[]> campovalor = request.getParameterMap();
		out.println("<h3>Pares campo-valor</h3><ul>");
		campovalor.forEach((parametro, valores)->{
			out.println("<li>"+parametro+"</li>");
			for(String v:valores){
				out.print(" - "+v);
			}
		});
		out.println("</ul>");
		
		//contraseña
		if(pass.isEmpty() || pass == null){
			out.println("<p>No hay contraseña</p>");
		}else{
			boolean tieneMayuscula = false;
			boolean tieneNumero = false;
			boolean tieneCaracterEspecial = false;
			for(int i=0; i<pass.length(); i++){
				char car = pass.charAt(i);
				if(Character.isUpperCase(car)){
					tieneMayuscula = true;
				}
				if(Character.isDigit(car)){
					tieneNumero = true;
				}
				if(!Character.isAlphabetic(car) && !Character.isDigit(car)){
					tieneCaracterEspecial = true;
				}
			}
			out.println("<p>Procesando al Contraseña</p>"+pass);
	
			if(tieneCaracterEspecial && tieneMayuscula && tieneNumero){
				out.println("<p>Contraseña</p>"+pass);
			}else{
				out.println("<p>le falta algún caracter requerido</p>");
			}
		}
		//formateo y comprobacion fecha
		if(fecha.isEmpty() || fecha == null){
			out.println("<p>No hay fecha</p>");
		}else{
			SimpleDateFormat fechaFormulario = new SimpleDateFormat("yyyy-MM-dd");
			try{
				Date fechaDate = fechaFormulario.parse(fecha);
				Date today = Calendar.getInstance().getTime();
				if(fechaDate.before(today) || fechaDate.equals(today)){
					SimpleDateFormat fechaSalida = new SimpleDateFormat("dd/MM/yyyy");
					out.println("<p>Fecha: "+fechaSalida.format(fechaDate)+"</p>");
				}else{
					out.println("<p>La fecha es posterior a la actual</p>");					
				}
			}catch (ParseException | java.text.ParseException e){
				e.printStackTrace();
				out.println("<p>Ha habido un error. Compruebe si ha escrito bien la fecha</p>");				
			}
		}
		
		//comprobacion numero favorito
		if(fecha.isEmpty() || fecha == null){
			out.println("<p>No hay numero favorito</p>");
		}else{
			try{
				int numfav = Integer.parseInt(numfavorito);
			}catch(NumberFormatException e){
				e.printStackTrace();
				out.println("<p>Ha habido un error. Compruebe si ha escrito bien el número</p>");
			}
		}
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
