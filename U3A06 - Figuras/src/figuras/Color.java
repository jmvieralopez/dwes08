package figuras;

public enum Color {
	ROJO("red"), AMARILLO("yellow"), VERDE("green"), AZUL("blue"), MAGENTA("magenta");

	private String color;

	public String getColor() {
		return color;
	}

	private Color(String color) {
		this.color = color;
	}

}
