package figuras;

public class Cuadrado extends Figura {
	private double lado;

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	public Cuadrado(double lado) {
		super(null, null);
		this.lado = lado;
	}

	public Cuadrado(double lado, String titulo, Color color) {
		super(titulo, color);
		this.lado = lado;
	}

	@Override
	public double area() {
		return lado * lado;
	}

	@Override
	public String getTitulo() {
		// TODO Auto-generated method stub
		return super.getTitulo();
	}

	@Override
	public void setTitulo(String titulo) {
		// TODO Auto-generated method stub
		super.setTitulo(titulo);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return super.getColor();
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		super.setColor(color);
	}

	@Override
	public double perimetro() {
		double perimetro = 4 * getLado();
		return perimetro;
	}
}
