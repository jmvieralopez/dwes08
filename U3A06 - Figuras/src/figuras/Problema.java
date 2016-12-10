package figuras;

public class Problema {

	public static void mainProblema(String[] args) {
		// TODO Auto-generated method stub
		Circunferencia cir1 = new Circunferencia(4.8);
		Circunferencia cir2 = new Circunferencia(1.5);
		Cuadrado cua1 = new Cuadrado(4.2);
		Triangulo tri1 = new Triangulo(8, 15);
		double area = (cir1.area() / 2) + (cir2.area() * (3.0 / 4.0)) + cua1.area() + tri1.area();
		System.out.println("Area: " + area);
	}

}
