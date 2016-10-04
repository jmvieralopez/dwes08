package figuras;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Circunferencia circ = new Circunferencia(3.0, "circ", Color.AMARILLO);
		System.out.println("Radio: " + circ.getRadio() + ", nombre: " + circ.getTitulo() + ", Color: "
				+ circ.getColor().getColor());
		Cuadrado cuad = new Cuadrado(4.0, "cuad", Color.AZUL);
		System.out.println(
				"lado: " + cuad.getLado() + ", nombre: " + cuad.getTitulo() + ", Color: " + cuad.getColor().getColor());
		Triangulo tri = new Triangulo(5.0, 6.0, "tri", Color.VERDE);
		System.out.println("base: " + tri.getBase() + ", altura: " + tri.getAltura() + ", nombre: " + tri.getTitulo()
				+ ", Color: " + tri.getColor().getColor());
		System.out.println("AÑADIENDO FIGURAS");
		GestorFiguras gf = new GestorFiguras();
		gf.añadirFigura(circ);
		gf.añadirFigura(cuad);
		gf.añadirFigura(tri);
		gf.mostrarFiguras();
		System.out.println("suma de areas "+gf.sumaAreas());
		System.out.println("ELIMINANDO UNA DE LAS FIGURAS");
		gf.eliminarFigura(cuad.getTitulo());
		gf.mostrarFiguras();
		System.out.println("suma de areas "+gf.sumaAreas());
		
		/*
		Problema i = new Problema();
		i.mainProblema(args);
		*/
	}

}
