import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> telefonos = new HashMap<String, Integer>();
		telefonos.put("fulanito", 600);
		telefonos.put("menganito", 611);
		telefonos.put("zutano", 622);
		telefonos.put("apachita", 680);
		System.out.println(telefonos.get("fulanito"));
		telefonos.forEach((clave, valor) -> System.out.println("el tlf de "+clave+" es "+valor));
		telefonos.remove("menganito");
		System.out.println("LISTA NUEVA");
		telefonos.forEach((clave, valor) -> {
			if(clave.equalsIgnoreCase("FULANITO")){
				System.out.println("el tlf de "+clave+" es "+valor);
			}
		});
		
	}

}
 