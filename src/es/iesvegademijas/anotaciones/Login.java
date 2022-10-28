package es.iesvegademijas.anotaciones;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


@UsuarioAnotado(usuario = "usuario1", hashPasswd = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")
@UsuarioAnotado(usuario = "usuario2", hashPasswd = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270")
public class Login {
	
	ArrayList<Usuario> listaUsuarios;
	
	public Login() {
		this.listaUsuarios = new ArrayList<>();
	}
	
	public String login() throws NoSuchAlgorithmException {
		Scanner sc = new Scanner(System.in);
		String usuario;
		String contraseña;
		String contraseñaCifrada;
		String devolver = "ACCESO DENEGADO";
		boolean encontrado = false;
		int i = 0;
		
		System.out.print("Introduzca su usuario: ");
		usuario = sc.nextLine();
		System.out.print("Introduzca su contraseña: ");
		contraseña = sc.nextLine();
		contraseñaCifrada = hashPassword(contraseña);
		
		do {
			Usuario usuarioABuscar = listaUsuarios.get(i);
			if (usuarioABuscar.getUsuario().equals(usuario) && usuarioABuscar.getContraseña().equals(contraseñaCifrada)) {
				devolver = "ACCESO CONCEDIDO";
				encontrado = true;
			} else {
				i++;
			}
		} while(i < listaUsuarios.size() && !encontrado);
		
		return devolver;
	}
	
	public static Login cargadorContexto() {
		
		Login login = new Login();
		
		UsuarioAnotado[] lista = Login.class.getAnnotationsByType(UsuarioAnotado.class);
		
		for (UsuarioAnotado usuarioAAñadir : lista) {
			Usuario usuario = new Usuario();
			
			usuario.setUsuario(usuarioAAñadir.usuario());
			usuario.setContraseña(usuarioAAñadir.hashPasswd());
			login.listaUsuarios.add(usuario);
		}
		
		return login;
	}
	
	/**
	 * Método que obtiene el hash de una password, por ejemplo, dado password = admin, devuelve:          
8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
	 * @param password
	 * @return hash de encriptación de la password
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String password ) throws NoSuchAlgorithmException {
		MessageDigest digest;
		
		digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
				password.getBytes(StandardCharsets.UTF_8));
		
		return bytesToHex(encodedhash);					
		
	}
	
	private static String bytesToHex(byte[] byteHash) {
		
	    StringBuilder hexString = new StringBuilder(2 * byteHash.length);	  	
	    for (int i = 0; i < byteHash.length; i++) {
	        String hex = Integer.toHexString(0xff & byteHash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    
	    return hexString.toString();
	    
	}
	
	@Override
	public String toString() {
		String devolver = "";
		for (Usuario u : listaUsuarios)
			devolver += "\nUsuario: " + u.getUsuario() + ", Contraseña: " + u.getContraseña();
		return devolver;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		Login login = Login.cargadorContexto();
		
		System.out.println(login.login());
		System.out.println(login.toString());
		
	}
}
