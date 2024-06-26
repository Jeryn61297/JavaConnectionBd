package com.jeska;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.jeska.model.Categoria;
import com.jeska.model.Producto;
import com.jeska.repositorio.ProductoRepositorioImple;
import com.jeska.repositorio.Repositorio;
import com.jeska.util.ConexionBaseDatos;

public class EjemploJdbcInsert {

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

			System.out.println("============ Insertar nuevo producto ============");
			Producto producto = new Producto();
			producto.setNombre("Laptop HP");
			producto.setPrecio(19999);
			producto.setFechaRegistro(new Date());
			Categoria categoria = new Categoria();
			categoria.setId(3L);
			producto.setCategoria(categoria);
			repositorio.guardar(producto);
			
			System.out.println("Producto guardado con exito");
			repositorio.listar().forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
