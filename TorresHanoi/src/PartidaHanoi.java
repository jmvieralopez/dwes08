import java.util.Collection;

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
		if(postes.get(0).isEmpty() && postes.get(1).isEmpty() && postes.get(2).contains()){
			return true;
		}else{
			return false;
		}
	}
	
	void mover(int posteOrigen, int posteDestino){
		
		//Si el n�mero de poste origen o destino es inv�lido (no es 0, 1 � 2).
		
		//Si el poste origen est� vac�o y por tanto no es posible extraer ning�n disco
		
		//Si  el  tama�o  del  disco  que  est�  en  la  cima  del  poste  origen  es  mayor  que  el 
		//tama�o  del  disco  que  est�  en  la  cima  del  poste  destino.
		//�Vigila  el  caso particular  en  el  que  el  poste  de  destino  est�  vac�o!  En  ese  caso  siempre  se 
		//puede insertar.
	}
	
	void dibujar(){
		for(;;){
			
		}
	}
}
