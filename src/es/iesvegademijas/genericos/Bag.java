package es.iesvegademijas.genericos;

import java.util.ArrayList;
import java.util.Set;

public class Bag<T> {
	
	private ArrayList<Objeto> lista;
	
	public Bag() {
		this.lista = new ArrayList<>();
	}
	
	public Bag(ArrayList lista) {
		this.lista = lista;
	}
	
	public int buscarElemento(T elemento) {
		int encontrado = -1;
		int i = 0;
		if (!lista.isEmpty()) {
			do {
				if (elemento.equals(lista.get(i).getObjeto())) {
					encontrado = i;
				} else {
					i++;
				}
			} while(i < lista.size() && encontrado == -1);
		}
		
		return encontrado;
	}
	
	public void add(T elemento) {
		int pos = buscarElemento(elemento);
		if (pos != -1) {
			lista.get(pos).aumentarCantidar(1);
		} else
			lista.add(new Objeto(elemento));
	}
	
	public void add(T elemento, int n) {
		int pos = buscarElemento(elemento);
		if (pos != -1) {
			lista.get(pos).aumentarCantidar(n);
		} else
			lista.add(new Objeto(elemento, n));
	}
	
	public int getCount(T elemento) {
		int cantidad = -1;
		int pos = buscarElemento(elemento);
		if (pos != -1) {
			cantidad = lista.get(pos).getCantidad();
		}
		if (cantidad == -1)
			cantidad = 0;
		return cantidad;
	}
	
	public void remove(T elemento) {
		int pos = buscarElemento(elemento);
		if (pos != -1) {
			lista.get(pos).eliminarCantidad(1);
			if (lista.get(pos).getCantidad() <= 0)
				lista.remove(pos);
		}
	}
	
	public void remove(T elemento, int cantidad) {
		int pos = buscarElemento(elemento);
		if (pos != -1) {
			lista.get(pos).eliminarCantidad(cantidad);
			if (lista.get(pos).getCantidad() <= 0)
				lista.remove(pos);
		}
	}
	
	public int size() {
		int cantidadTotal = 0;
		if (!lista.isEmpty()) {
			for (int i = 0; i<lista.size(); i++)
				cantidadTotal += lista.get(i).getCantidad();
		}
		return cantidadTotal;
	}
	
	@Override
	public String toString() {
		String devolver = "ELEMENTOS: ";
		for (Objeto ob : lista)
			devolver += ob.toString();
		return devolver;
	}
	
	public static void main(String[] args) {
		 Bag bolsa = new Bag();
		 bolsa.add("Hola");
		 bolsa.add(5, 5);
		 System.out.println(bolsa.toString());
	}
}
