package figuras;

/**
 * Clase abstracta
 */
public abstract class Figura {

	/**
	 * Nombre de la figura
	 */
	private String titulo;
	
	private Color color;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Figura(String titulo, Color color) {
		this.titulo = titulo;
		this.color = color;
	}

	public abstract double area();
	
	public abstract double perimetro();
}
