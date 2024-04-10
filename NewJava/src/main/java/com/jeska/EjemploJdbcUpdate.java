package com.jeska;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.jeska.model.Producto;
import com.jeska.repositorio.ProductoRepositorioImple;
import com.jeska.repositorio.Repositorio;
import com.jeska.util.ConexionBaseDatos;

public class EjemploJdbcUpdate {

	public static void main(String[] args) {

		try (Connection conn = ConexionBaseDatos.getInstance()) {

			Repositorio<Producto> repositorio = new ProductoRepositorioImple();
			/*
			System.out.println("============ Listar ============");
			repositorio.listar().forEach(System.out::println);
			System.out.println(" ");
			
			System.out.println("============ Obtener por ID ============");
			System.out.println(repositorio.porId(3L));
			System.out.println(" ");
			*/
			
			System.out.println("============ Editar producto ============");
			Producto producto = new Producto();
			producto.setId(13L);
			producto.setNombre("Mochila");
			producto.setPrecio(900);
			//producto.setFechaRegistro(new Date());
			repositorio.guardar(producto);
			System.out.println("Producto editado con exito");
			repositorio.listar().forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
