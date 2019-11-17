package bl.Construccion.Juego;

import bl.Construccion.Juego.Turno.Iterador.IIterador;
import bl.Construccion.Jugadores.Jugador;
import bl.Construccion.Tablero.Tablero;

import java.util.ArrayList;

public class Juego implements IJuego{

    private int cantidadJugadores;
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private IIterador iterador;

    public Juego(int cantidadJugadores, Tablero tablero, IIterador iterador) {
        this.cantidadJugadores = cantidadJugadores;
        this.tablero = tablero;
        this.jugadores = new ArrayList<>();
        this.iterador = iterador;
    }

    public Juego(){
        setCantidadJugadores(0);
        setTablero(new Tablero(10,10));
        setJugadores(new ArrayList<>());

    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void agregarJugador(Jugador pJugador){
        this.jugadores.add(pJugador);
    }

    public IIterador getIterador() {
        return iterador;
    }

    public void setIterador(IIterador iterador) {
        this.iterador = iterador;
    }

    @Override
    public void finalizarPartida() {

    }

    @Override
    public void pasarTurno() {

    }
}
