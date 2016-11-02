import java.util.Collection;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("***********************");
		System.out.println("* LAS TORRES DE HANOI *");
		System.out.println("***********************");
		int opcion;
		do{
			System.out.println("Menú principal:");
			System.out.println("1- Nueva partida");
			System.out.println("2- Salir del programa");
			System.out.println();
			System.out.print("Escoge una opción: ");
			opcion = s.nextInt();
			if(opcion == 1){
				System.out.print("Introduce el número de discos: ");
				int numeroDiscos = s.nextInt();
				PartidaHanoi p = new PartidaHanoi(new Collection, numeroDiscos);
				int opcionPartida;
				do{
					p.dibujar();
					System.out.println("¿Qué deseas hacer?");
					System.out.println("1- Realizar un movimiento");
					System.out.println("2- Abandonar la partida");
					opcionPartida = s.nextInt();
					int posteOrigen, posteDestino;
					if(opcionPartida == 1){
						System.out.print("Número de poste de origen");
						posteOrigen = s.nextInt();
						System.out.print("Número de poste de destino");
						posteDestino = s.nextInt();
					}
				}while(opcionPartida != 2);
			}
		}while(opcion != 2);
	}
	
}
