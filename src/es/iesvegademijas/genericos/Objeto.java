package es.iesvegademijas.genericos;

public class Objeto<T> {
	
	T objeto;
	int cantidad;
	
	public Objeto(T objeto) {
		this.objeto = objeto;
		this.cantidad = 1;
	}
	
	public Objeto(T objeto, int cantidad) {
		this.objeto = objeto;
		this.cantidad = cantidad;
	}
	
	public T getObjeto() {
		return objeto;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public void aumentarCantidar(int n) {
		cantidad += n;
	}
	
	public void eliminarCantidad(int n) {
		cantidad -= n;
	}

	@Override
	public String toString() {
		return "\n" + objeto.toString() + "	Cantidad: " + cantidad;
	}
}
