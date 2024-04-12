package com.jeska;

import com.jeska.model.Categoria;
import com.jeska.model.Producto;
import com.jeska.repositorio.ProductoRepositorioImple;
import com.jeska.repositorio.Repositorio;
import com.jeska.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcUpdate {

	public static void main(String[] args) {

		try (Connection conn = ConexionBaseDatos.getInstance()) {

			Repositorio<Producto> repositorio = new ProductoRepositorioImple();
			/*
			 * System.out.println("============ Listar ============");
			 * repositorio.listar().forEach(System.out::println); System.out.println(" ");
			 * 
			 * System.out.println("============ Obtener por ID ============");
			 * System.out.println(repositorio.porId(3L)); System.out.println(" ");
			 */
			
			
			System.out.println("============ Editar producto ============");
			Producto producto = new Producto();
			producto.setId(14L);
			producto.setNombre("Teclado Gaming");
			producto.setPrecio(699);
			Categoria categoria = new Categoria();
			categoria.setId(2L);
			producto.setCategoria(categoria);
			repositorio.guardar(producto);
			System.out.println("Producto editado con éxito");
			repositorio.listar().forEach(System.out::println);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
