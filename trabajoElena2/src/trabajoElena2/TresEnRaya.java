package trabajoElena2;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class TresEnRaya {
	
	public static int juegoTerminado = 0;
	public static Scanner sn = new Scanner (System.in);
	public static String rutaLog = "log.txt";
	
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
		String fecha = new SimpleDateFormat("ddMMyyyy-HHmm").format(new Date());
		rutaLog = ("log\\" + fecha + "-" + "log.txt");
		File f = new File(rutaLog);
		f.getParentFile().mkdirs();
		FileWriter fw;
		PrintWriter pw = null ;
		try {
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
		//Generar numero resultado del dado aleatorio
		while(aleatorio1 == aleatorio2) {
			aleatorio1 = (int) (Math.random()*6+1);
			System.out.println("Tira el dado, " + jugador1 + ":"+ aleatorio1);
			log(rutaLog, " Tira el dado, " + jugador1 + " resultado : " + aleatorio1);
			aleatorio2 = (int) (Math.random()*6+1); 
			System.out.println("Tira el dado, " + jugador2 + " resultado : " + aleatorio2);
			log(rutaLog, " Tira el dado, " + jugador2 + " resultado : " + aleatorio2);
			if(aleatorio2 < aleatorio1) {
				System.out.println(jugador1 + " Pone ficha primero");
				log(rutaLog, " Pone ficha primero " + jugador1);
			}
			else if(aleatorio2 > aleatorio1) {
				System.out.println(jugador2 + " Pone ficha primero");
				log(rutaLog, " Pone ficha primero " + jugador2);

				jugadorPrimero = 2;
			}
			else {
				System.out.println("Oh no habeis sacado el mismo numero teneis que volver a tirar los dados");
				log(rutaLog, " Los dos jugadores han sacado el mismo numero deben tirar de nuevo los dados");
			}
		}
		tablero = inicializarTablero(tablero);
		imprimirTablero(tablero);
		if(jugadorPrimero == 2) { //Significa que le toca primero al jugador 2
			System.out.println("Es el turno de " + jugador2);
			log(rutaLog, " Es el turno de " + jugador2);
			tablero = jugando("O", tablero);
			imprimirTablero(tablero);
		}
		while(juegoTerminado == 0) { // Cero significa que aun no ha terminado el juego
			//Metodo para comprobar que el tablero esta lleno
			if(tableroLleno(tablero) == false) {
				System.out.println("Es el turno de " + jugador1);
				log(rutaLog, " Es el turno de " + jugador1);
				tablero = jugando("X", tablero);
				imprimirTablero(tablero);
				if(haGanado(tablero, jugador1) == true) {
					juegoTerminado = 1;
					log(rutaLog, " El jugador " + jugador1 + " ha ganado");
					log(rutaLog, " El juego ha terminado ");


				}else if(tableroLleno(tablero) == false) {
					
					//Metodo para comprobar que el tablero esta lleno
					System.out.println("Es el turno de " + jugador2);
					log(rutaLog, " Es el turno de " + jugador2);
					tablero = jugando("O", tablero);
					imprimirTablero(tablero);
					if(haGanado(tablero, jugador2) == true) {
						juegoTerminado = 1;
						log(rutaLog, " El jugador " + jugador2 + " ha ganado");
						log(rutaLog, " El juego ha terminado");
					}
					
				}else {
					juegoTerminado = 1;
				}
			}else {
				juegoTerminado = 1;
			}
		}
	}
	/**
	 * Metodo para pedir al usuario que meta una ficha en una posicion
	 * @param  ficha puede ser X/O, es la ficha que meto
	 * @param  tablero es el tablero de juego
	 * */
	public static String[][] jugando(String ficha, String[][]tablero ) {
		String posString = "";
		int posX;
		int posY;
		Boolean bError = true;
		do {
			try {
				do{
					System.out.println("Introduce una cordenada valida Ejemplo B2");
					posString = sn.next();
					posX = convertidorANumero(Character.toString(posString.charAt(0)).toUpperCase());
					posY = Character.getNumericValue(posString.charAt(1))-1;
					if(tablero[posX][posY].equals("X") || tablero[posX][posY].equals("O")) {
						System.out.println("Debes introducir una cordenada vacia, intentalo de nuevo");
						log(rutaLog, " El jugador ha introducido una crodenada que esta ya ocupada, debe ingresar una de nuevo ");
					}
					if(posString.length()>2) {
						System.out.println("Para una cordenada solo 1 valor entre A-B-C y un valor entre 1-2-3, intentalo de nuevo");
						log(rutaLog, " El jugador ha introducido una cordenada no valida, debera intentarlo de nuevo ");
					}
					bError = false;
				}while((posX >= tablero.length || posY >=  tablero[0].length || tablero[posX][posY].equals("X") || tablero[posX][posY].equals("O") || posString.length()>2)); 
				//esto sirve para comprobar si las cordenadas que introduce el usuario estan dentro de la longitud/tamaño de la matriz. Tambien para comprobar si esta ocupada esa posicion
				tablero[posX][posY] = ficha;
			}catch(ArrayIndexOutOfBoundsException e) {//Trata si meto valores fuera del tamaño de la matriz tablero o fuera de ABC
				System.out.println("MENSAJE DE ERROR!!!");
				System.out.println("Introduce valores que esten permitidos en el rango del tablero y correctamente ordenados [A-B-C 1-2-3]");
				log(rutaLog, " El jugador ha introducido valores fuera del rango del tablero, debe volver a intentarlo");
			}catch(StringIndexOutOfBoundsException e) {//Trata si en vez de meter los dos valores de una cordenada meto solo 1
				System.out.println("MENSAJE DE ERROR!!!");
				System.out.println("Introducir los dos valores de la coordenada, no solo uno");
				log(rutaLog, " El jugador solo ha metido 1 de los dos valores para indicar una cordenada debe volver a intentarlo ");
			}
		}while (bError);
		return tablero;
	}
	/**
	 * Metodo para convertir el string de la cordenada x en un entero
	 * @param  posXString Letra introducida por el usuario
	 * */
	public static int convertidorANumero(String posXString) {
		int posX = -1;
		if(posXString.equals("A")) {
			posX = 0;
		}else if(posXString.equals("B")) {
			posX = 1;
		}else if(posXString.equals("C")) {
			posX = 2;
		}
		return posX;
	}
	/**
	 * Metodo para inicializar tablero
	 * @param  tablero aqui le damos el tablero a inicializar
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
	/**
	 * Metodo para imprimir tablero
	 * @param  tablero aqui le damos el tablero a imprimir
	 * */
	
	public static void imprimirTablero(String[][] tablero) {
		String stringAImprimir;
		System.out.println("\n");
		System.out.println("    1   2   3  ");
		for(int i=0; i< tablero.length; i++) {
			System.out.print(convertidorAString(i));
			for(int j = 0; j < tablero[0].length; j++) {
				stringAImprimir = tablero[i][j];
				if(stringAImprimir.equals("")) {
					stringAImprimir = "_";
				}
				System.out.print(" | " + stringAImprimir);
			}
			System.out.print(" | " +"\n");
		}
		System.out.println("\n");
	}
	/**
	 * Metodo para convertir de un numero a un string
	 * @param  numero a convertir 
	 * */
	public static String convertidorAString(int numero) {
		if(numero == 0) {
			return "A";
		}else if(numero == 1) {
			return "B";
		}else {
			return "C";
		}
	}
	/**
	 * Metodo comprobar si el tablero ya esta lleno o esta vacio 
	 * @param  tablero tablero a compronbar
	 * */
	public static Boolean tableroLleno(String[][] tablero) {
		for(int i = 0; i < tablero.length; i++) {
			for(int j = 0; j < tablero[0].length; j++) {
				if(tablero[i][j].equals("")) {
					return false;
				}
			}
		}
		System.out.println("Habeis acabado en un empate!!!!!!");
		log(rutaLog, " Los jugadores terminaron en empate ");
		return true;
	}
	/**
	 * Metodo comprobar si hay ganador
	 * @param  tablero tablero a comprobar 
	 * @param ganador nombre del ganador
	 * */
	public static Boolean haGanado(String[][] tablero, String ganador) {
		if(tablero[0][0] == tablero[0][1] && tablero[0][1] == tablero[0][2] && tablero[0][0] != "") { //horizontal
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		} else if (tablero[1][0] == tablero[1][1] && tablero[1][1] == tablero[1][2] && tablero[1][0] != "") { //horizontal
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		} else if (tablero[2][0] == tablero[2][1] && tablero[2][1] == tablero[2][2] && tablero[2][0] != "") { //horizontal
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		} else if (tablero[0][0] == tablero[1][0] && tablero[1][0] == tablero[2][0] && tablero[0][0] != "") { //vertical
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		} else if (tablero[0][1] == tablero[1][1] && tablero[1][1] == tablero[2][1] && tablero[0][1] != "") { //vertical
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		} else if (tablero[0][2] == tablero[1][2] && tablero[1][2] == tablero[2][2] && tablero[0][2] != "") { //vertical
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		} else if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != "") { //diagonal principal
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		} else if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != "") {  //diagonal secundaria
			System.out.println(ganador + " ha ganado esta partida");
			return true;
		}
		return false;
	}
	/**
	 * Metodo crear los logs
	 * @param  rutaArchivo
	 * @param mensaje mensaje que tendra el log
	 * */
	public static void log(String rutaArchivo, String mensaje) {
		
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter(rutaArchivo, true);
			pw = new PrintWriter(fw);
			pw.println(new Date() + mensaje);
			//pw.append(mensaje);
			//fr.read();
			//br.readLine();
		}catch (IOException e) {
            e.printStackTrace();
        }
		//skip();
		pw.close();
	}
}
