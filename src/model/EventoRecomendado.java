package model;

public class EventoRecomendado {
Evento evento;
int resultado;



public EventoRecomendado(Evento evento, int resultado) {
	this.evento = evento;
	this.resultado = resultado;
}

public Evento getEvento() {
	return evento;
}

public void setEvento(Evento evento) {
	this.evento = evento;
}

public int getResultado() {
	return resultado;
}

public void setResultado(int resultado) {
	this.resultado = resultado;
}



}
