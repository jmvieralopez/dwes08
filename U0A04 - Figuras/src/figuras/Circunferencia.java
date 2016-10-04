package figuras;

public class Circunferencia extends Figura {
	private double radio;

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

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public Circunferencia(double radio) {
		super(null, null);
		this.radio = radio;
	}
	

	public Circunferencia(double radio, String titulo, Color color) {
		super(titulo, color);
		this.radio = radio;
	}
	
	@Override
	public double area(){
		double area = Math.PI * getRadio() * getRadio();
		return area;
	}
	
	@Override
	public double perimetro() {
		double perimetro = 2 * Math.PI * getRadio();
		return perimetro;
	}
}
