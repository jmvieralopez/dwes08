
public class Disco {
	private final int diametro;

	public Disco(int diametro) {
		super();
		if(diametro < 3){
			System.out.println("ADVERTENCIA: diámetro demasiado pequeño, se le asignara un 3");
			this.diametro = 3;
		}else{
			if(diametro % 2 != 0){
				this.diametro = diametro;
			}else{
				System.out.println("ADVERTENCIA: diametro del disco invalido, se le asigna un "+getDiametro());
				this.diametro = diametro + 1;
			}
		}
	}

	public int getDiametro() {
		return diametro;
	}
	
}
