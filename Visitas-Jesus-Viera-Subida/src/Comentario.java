import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class Comentario {
	private String autor;
	private String comentario;
	private String date;
	public String getAutor() {
		return autor;
	}
	public String getComentario() {
		return comentario;
	}
	public String getDate(){
		return date;
	}
	public Comentario(String autor, String comentario) {
		super();
		this.autor = autor;
		this.comentario = comentario;
		Date d = Calendar.getInstance().getTime();
		SimpleDateFormat fechaSalida = new SimpleDateFormat("MMMM d, yyyy, hh:mm");
		this.date = fechaSalida.format(d);
	}
}
