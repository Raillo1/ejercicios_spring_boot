package com.ejercicio.estructuras.controlador;

import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorEjercicios {

	@PostMapping("/parImpar")
	public String ejercicio1(@RequestParam int numero) {
		String ret = "";
		if (numero % 2 == 0) {
			ret = "Es par el número " + numero;
		} else {
			ret = "No es par el número: " + numero;
		}
		return "<h1> " + ret + "</h1>";
	}

	@PostMapping("/tablaMultiplicar")
	public String ejercicio2(@RequestParam int numero) {
		String tabla = "";
		for (int i = 1; i <= 10; i++) {
			tabla += "" + (numero * i) + "<br>";
		}
		return tabla;
	}

	@PostMapping("/promedioCalificaciones")
	@ResponseBody
	public String ejercicio3(@RequestParam(required = false, defaultValue = "") String calificaciones) {

		if (calificaciones.isEmpty()) {
			return "El parámetro está vacío o no se ha enviado.";
		}

		String[] alumnos = calificaciones.split(",");
		int suma = 0;

		StringBuilder valores = new StringBuilder("<p>");
		try {
			for (int i = 0; i < alumnos.length; i++) {
				try {
					int valor = Integer.parseInt(alumnos[i].trim());
					suma += valor;

					if (valor >= 5) {
						valores.append("El alumno ").append(i).append(" ha aprobado con ").append(valor).append("<br>");
					} else {
						valores.append("El alumno ").append(i).append(" no ha aprobado con ").append(valor)
								.append("<br>");
					}
				} catch (NumberFormatException e) {
					return "Error: uno de los valores no es un número válido (" + alumnos[i].trim() + ").";
				}
			}

			double promedio = (double) suma / alumnos.length;

			valores.append("<br> El promedio de la clase es: ").append(promedio).append("</p>");
		} catch (Exception e) {
			return "Error: Ocurrió un problema procesando las calificaciones.";
		}

		return valores.toString();
	}

	@PostMapping("/calcularIMC")
	public String ejercicio4(@RequestParam double peso, @RequestParam double altura) {
		try {
			double imc = peso / (altura * altura);
			String resultado = "";
			if (imc < 18.5) {
				resultado = "bajo peso";
			} else if (imc <= 24.9) {
				resultado = "peso normal";
			} else if (imc <= 29.9) {
				resultado = "sobrepeso";
			} else if (imc > 40) {
				resultado = "obesidad grado 3";
			}
			return "<h1>El Indice de Masa Corporal es de " + String.format("%.2f", imc) + " lo que significa que tiene "
					+ resultado + "</h1>";
		} catch (Exception e) {
			return "Ha habido un problemas con los parámetros";
		}
	}

	@PostMapping("/encuesta")
	public String ejercicio5(@RequestParam int satisfaccion) {
		return "<h1>Su valoración es de " + satisfaccion + "</h1>" + "Muchas gracias por su valoración";
	}

	@PostMapping("/generarContrasena")
	public String ejercicio6(@RequestParam String longitud) {
		int longitudContrasena = Integer.parseInt(longitud);
		String contrasena = "";
		Random rnd = new Random();
		try {
			for (int i = 0; i < longitudContrasena; i++) {
				int tipoCaracter = rnd.nextInt(3);

				if (tipoCaracter == 0) {
					contrasena += (char) (rnd.nextInt(26) + 65);
				} else if (tipoCaracter == 1) {
					contrasena += (char) (rnd.nextInt(26) + 97);
				} else {
					contrasena += (char) (rnd.nextInt(10) + 48);
				}
			}
			return contrasena;
		} catch (Exception e) {
			return "Ha habido un error";
		}
	}

	@PostMapping("/sumatoria")
	public String ejercicio7(@RequestParam int numero){
		int resultado = 0;
		for (int i = 1; i <= numero; i++) {
			resultado+= i;
		}
		return "<h1>El resultado es " + resultado + "<h1>";
	}

	@PostMapping("/factorial")
	public String ejercicio8(@RequestParam int numero){
		int resultado = 1;
		for (int i = 1; i <= numero; i++) {
			resultado = resultado*i;
		}
		return "<h1>El resultado es " + resultado + "<h1>";
	}
}
