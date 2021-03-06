package figuras;

public class Triangulo extends Figura {
	private double base;
	private double altura;

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public Triangulo(double base, double altura) {
		super(null, null);
		this.base = base;
		this.altura = altura;
	}

	public Triangulo(double base, double altura, String titulo, Color color) {
		super(titulo, color);
		this.base = base;
		this.altura = altura;
	}

	@Override
	public double area() {
		return (base * altura) / 2;
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
		double perimetro = Math.hypot(getBase(), getAltura());
		return perimetro;
	}

}
