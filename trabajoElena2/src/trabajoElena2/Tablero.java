package trabajoElena2;

public class Tablero {

	//Atributos
	String[][] tablero = new String[2][2];
	String jugador1;
	String jugador2;
	
	
	//Constructor
	Tablero(){
		
	}
	Tablero(String jugador1, String jugador2){ //Tablero tableroPrueba = new Tablero("Juan", "Pedro");
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}
	
	//Getters y setters
	public String[][] getTablero() {
		return tablero;
	}
	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}
	public String getJugador1() {
		return jugador1;
	}
	public void setJugador1(String jugador1) {
		this.jugador1 = jugador1;
	}
	public String getJugador2() {
		return jugador2;
	}
	public void setJugador2(String jugador2) {
		this.jugador2 = jugador2;
	}
	
	//Metodos
	public void introducirPosicion(int posX, int posY, String ficha) { //ficha tiene que ser X o O
		this.tablero[posX][posY] = ficha;
	}

	
}
