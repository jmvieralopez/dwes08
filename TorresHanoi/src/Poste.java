import java.util.ArrayList;

public class Poste {
	private ArrayList<Disco> discos;
	
	boolean esVacio(){
		if(discos.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	int obtenerDiametroDiscoCima(){
		if(discos.size() == 0){
			return 0;
		}else{
			int tamaño = discos.get(discos.size() - 1).getDiametro();
			return tamaño;
		}
	}
	
	void insertarDisco(Disco d){
		discos.add(d);
	}
	
	Disco extraerDisco(){
		if(discos.size() == 0){
			return null;
		}else{
			return discos.get(discos.size() - 1);
		}
	}
	
	void dibujar(){
		
	}
}
