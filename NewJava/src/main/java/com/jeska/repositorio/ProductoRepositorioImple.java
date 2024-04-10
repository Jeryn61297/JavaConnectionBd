package com.jeska.repositorio;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jeska.model.Producto;
import com.jeska.util.ConexionBaseDatos;

public class ProductoRepositorioImple implements Repositorio<Producto> {

	private Connection getConnection() throws SQLException {
		return ConexionBaseDatos.getInstance();
	}

	@Override
	public List<Producto> listar() {
		List<Producto> productos = new ArrayList<>();

		try (Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {

			while (rs.next()) {
				Producto p = crearProducto(rs);
				productos.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

	@Override
	public Producto porId(Long id) {
		Producto producto = null;

		try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM productos WHERE id = ?")) {
			stmt.setLong(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					producto = crearProducto(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}

	@Override
	public void guardar(Producto producto) {
		String sql;
		if (producto.getId() != null && producto.getId() > 0) {
			sql = "UPDATE productos SET nombre=?, precio=? WHERE id=?";
		} else {
			sql = "INSERT INTO productos(nombre, precio, fecha_registro) VALUES(?,?,?)";

		}
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, producto.getNombre());
			stmt.setLong(2, producto.getPrecio());

			if (producto.getId() != null && producto.getId() > 0) {
				stmt.setLong(3, producto.getId());
			} else {
				stmt.setDate(3, new Date(producto.getFechaRegistro().getTime()));
			}

			stmt.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void eliminar(Long id) {
		try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")) {
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private Producto crearProducto(ResultSet rs) throws SQLException {
		Producto p = new Producto();
		p.setId(rs.getLong("Id"));
		p.setNombre(rs.getString("nombre"));
		p.setPrecio(rs.getInt("precio"));
		p.setFechaRegistro(rs.getDate("fecha_registro"));
		return p;
	}
}