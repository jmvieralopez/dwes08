
public class PartidaHanoi {
	private Collection postes;
	private int numeroDiscos;
	public PartidaHanoi(Collection postes, int numeroDiscos) {
		super();
		this.postes = postes;
		if(numeroDiscos > 1){
			this.numeroDiscos = numeroDiscos;
		}else{
			System.out.println("ERROR: La partida debe tener al menos un disco");
			this.numeroDiscos = 1;
		}
		
		//rellenar de discos el primer poste
	}
	
	boolean haTerminado(){
		
	}
	
	void mover(int posteOrigen, int posteDestino){
		
	}
	
	void dibujar(){
		for(;;){
			
		}
	}
}
