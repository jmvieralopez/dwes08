package figuras;

import java.util.ArrayList;

public class GestorFiguras {
	private ArrayList<Figura> arrayFiguras;

	public GestorFiguras() {
		this.arrayFiguras = new ArrayList<Figura>();
	}

	public void añadirFigura(Figura f) {
		boolean noExiste = true;
		for (Figura e : arrayFiguras) {
			if (e.getTitulo().equals(f.getTitulo())) {
				noExiste = false;
			}
		}
		if (noExiste) {
			this.arrayFiguras.add(f);
		}
	}

	public void eliminarFigura(String titulo) {
		Figura eliminar = null;
		for (Figura e : arrayFiguras) {
			if (e.getTitulo().equals(titulo)) {
				eliminar = e;
			}
		}
		this.arrayFiguras.remove(eliminar);
	}

	public void mostrarFiguras() {
		for (Figura e : arrayFiguras) {
			System.out.println(e.getTitulo() + " " + e.getColor() + " " + e.getClass().getName()+" area:"+e.area());
		}
	}
	
	public double sumaAreas(){
		double suma = 0.0;
		for (Figura e : arrayFiguras) {
			suma = suma + e.area();
		}
		return suma;
	}
}
