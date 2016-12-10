package figuras;

public class Elipse extends Figura {
	private double radioX;
	private double radioY;

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

	public double getRadioX() {
		return radioX;
	}

	public void setRadioX(double radioX) {
		this.radioX = radioX;
	}
	
	public double getRadioY() {
		return radioY;
	}

	public void setRadioY(double radioY) {
		this.radioY = radioY;
	}

	public Elipse(double radioX, double radioY) {
		super(null, null);
		this.radioX = radioX;
		this.radioY = radioY;
	}
	

	public Elipse(double radioX, double radioY, String titulo, Color color) {
		super(titulo, color);
		this.radioX = radioX;
		this.radioY = radioY;
	}
	
	@Override
	public double area(){
		double area = getRadioX() * getRadioY();
		return area;
	}
	
	@Override
	public double perimetro() {
		//Formula falsa
		double perimetro = 2 * Math.PI * getRadioX() * getRadioY();
		return perimetro;
	}
}
