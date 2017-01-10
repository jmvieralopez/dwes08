

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Problema13
 */
@WebServlet("/Problema13")
public class Problema13 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Problema13() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String num = request.getParameter("num");
		int n = Integer.parseInt(num);
		int cnt = 0;
		out.println("<table>");
		for(int i = 1; i <= n; i++){
			out.println("<tr>");
			if(i % 2 == 0){
				cnt += n;
				out.print("n = "+n);
				for (int j = n; j > 0; j--) {
					out.println("<td>"+cnt+"</td>");
					cnt--;
				}
			}else{
				if(i != 1){
					cnt += n;
				}
				for (int j = 0; j < n; j++) {
					cnt++;
					out.println("<td>"+cnt+"</td>");
				}
			}
			out.println("</tr>");
		}
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
