package trabajoElena2;
import java.util.Scanner;
public class TresEnRaya {
	
	public static int juegoTerminado = 0;
	//juegoTerminado = 0 -> Aun no ha terminado
	//juegoTerminado = 1 -> Ha terminado. 
	
	
	public static Scanner sn = new Scanner (System.in);
	
	public static void main(String[] args) {
		
		String[][] tablero = new String[3][3];
		String jugador1;
		String jugador2;
		int jugadorPrimero = 1; //Cuando es 1, primero juega el jug1, caundo es 2 primero juega jug2
		int aleatorio1 = 0;
		int aleatorio2 = 0;
		

		
		System.out.println("Bienvenido al mejor juego al TRES EN RAYA DELUXE");
		System.out.println("Las Normas son las siguientes: ");
		System.out.println("1º El primer jugador en poner ficha es quien saque el numero mas alto tirando un dado");
		System.out.println("2º Cada jugador solo debe colocar su simnolo una vez por turno");
		System.out.println("4º En caso de empate se tendra la opcion de desempatar con una nueva partida");
		System.out.println("5º Sera ganador quien consiga una linea recta o diagonal de 3 de sus fichas");
		System.out.println("6ª El primer jugador por defecto siempre sera la X\n\n\n");
		System.out.println("Introduzca el nombre del jugador numero 1");
		jugador1 = sn.next();
		System.out.println("Introduzca el nombre del jugador numero 2");
		jugador2 = sn.next();
		
		
		
		//Generar numero resultado del dado aleatorio
		while(aleatorio1 == aleatorio2) {
			aleatorio1 = (int) (Math.random()*6+1);
			System.out.println("Tira el dado, " + jugador1 + ":"+ aleatorio1);
			aleatorio2 = (int) (Math.random()*6+1); 
			System.out.println("Tira el dado, " + jugador2 + ":"+ aleatorio2);

			if(aleatorio2 < aleatorio1) {
				System.out.println(jugador1 + " Pone ficha primero");
			}
			else if(aleatorio2 > aleatorio1) {
				System.out.println(jugador2 + " Pone ficha primero");
				jugadorPrimero = 2;
			}
			else {
				System.out.println("Oh no habeis sacado el mismo numero teneis que volver a tirar los dados");
			}
		}
		
		tablero = inicializarTablero(tablero);
		imprimirTablero(tablero);
		
		if(jugadorPrimero == 2) { //Significa que le toca primero al jugador 2
			System.out.println("va a jugar el " + jugador2);
			tablero = jugando("O", tablero);
			imprimirTablero(tablero);


		}
		while(juegoTerminado == 0) { // Cero significa que aun no ha terminado el juego
			System.out.println("va a jugar el " + jugador1);

			tablero = jugando("X", tablero);
			/*
			 * if(juegoTerminado == 1){
			 * 	//Ha ganado el jugador 1
			 *  //exit
			 * }
			 * 
			 */
			imprimirTablero(tablero);
			System.out.println("va a jugar el " + jugador2);
			tablero = jugando("O", tablero);
			/*
			 * if(juegoTerminado == 1){
			 * 	//Ha ganado el jugador 1
			 * }
			 * 
			 */
			imprimirTablero(tablero);

			

		}
		
	}
	/*
	 * Metodo para pedir al usuario que meta una ficha en una posicion
	 * @param  ficha puede ser X/O, es la ficha que meto
	 * @param  tablero es el tablero de juego
	 * */
	public static String[][] jugando(String ficha, String[][]tablero) {
		int posX;
		int posY;
		do {
			System.out.println("Introduce Posicion X:  ");
			posX = sn.nextInt();
			System.out.println("Introduce Posicion Y:  ");
			posY = sn.nextInt();
		}while((posX >= tablero.length || posY >=  tablero[0].length || tablero[posX][posY].equals("X") || tablero[posX][posY].equals("O"))); 
		//esto sirve para comprobar si las cordenadas que introduce el usuario estan dentro de la longitud/tamaño de la matriz. Tambien para comprobar si esta ocupada esa posicion
		//PREGUNTAR A ELENA COMO PUEDO SEPARAR LO DE ARRIBA EN DOS PARTES Y PONER UN MENSAJE QUE ME INDIQUE EL ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		tablero[posX][posY] = ficha;
		
		//LLamar a un metodo que compruebe si ya se ha ganado
		return tablero;
	}
	//----------------------------------------------------------------------------------------
	/*
	 * Metodo para inicializar matriz
	 * @param  m1 aqui le damos la matriz a inicializar
	 * */
	public static String[][] inicializarTablero(String[][] tablero) {
		//Inicializar el tablero
		for(int i=0; i< tablero.length; i++) {
			for(int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = "";
			}
		}
		return tablero;
	}
	
	//----------------------------------------------------------------------------------------
	/*
	 * Metodo para imprimir matriz
	 * @param  m1 aqui le damos la matriz a imprimir
	 * */
	
	public static void imprimirTablero(String[][] tablero) {
	
		for(int i=0; i< tablero.length; i++) {
			System.out.println("\n");
			for(int j = 0; j < tablero[0].length; j++) {
				System.out.print("|  " + tablero[i][j]+"  |" + "\t");
			}
		}
		System.out.println("\n");
	}
	public static Boolean tableroLLeno(String[][] tablero) {
		
		
	}
}
