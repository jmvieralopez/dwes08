package figuras;

public enum Color {
	ROJO("rojo"), AMARILLO("amarillo"), VERDE("verde"), AZUL("azul"), MAGENTA("magenta");

	private String color;

	public String getColor() {
		return color;
	}

	private Color(String color) {
		this.color = color;
	}

}
