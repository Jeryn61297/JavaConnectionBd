package com.jeska;

import java.sql.Connection;
import java.sql.SQLException;

import com.jeska.model.Producto;
import com.jeska.repositorio.ProductoRepositorioImple;
import com.jeska.repositorio.Repositorio;
import com.jeska.util.ConexionBaseDatos;

public class EjemploJdbcList {

	public static void main(String[] args) {

		try (Connection conn = ConexionBaseDatos.getInstance()) {

			Repositorio<Producto> repositorio = new ProductoRepositorioImple();
			System.out.println("============ Lista de productos ============");
			repositorio.listar().forEach(System.out::println);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
