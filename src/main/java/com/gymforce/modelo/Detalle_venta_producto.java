package com.gymforce.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Detalle_venta_producto{
	private IntegerProperty clv_dvp;
	private Producto clv_producto;
	private Venta clv_venta;
	private IntegerProperty cantidad_dvp;
	private DoubleProperty precio_venta_dvp;

	public Detalle_venta_producto(int clv_dvp, Producto clv_producto, Venta clv_venta, 
int cantidad_dvp, Double precio_venta_dvp) { 
		this.clv_dvp = new SimpleIntegerProperty(clv_dvp);
		this.clv_producto = clv_producto;
		this.clv_venta = clv_venta;
		this.cantidad_dvp = new SimpleIntegerProperty(cantidad_dvp);
		this.precio_venta_dvp = new SimpleDoubleProperty(precio_venta_dvp);
	}

	//Metodos atributo: clv_dvp
	public int getClv_dvp() {
		return clv_dvp.get();
	}
	public void setClv_dvp(int clv_dvp) {
		this.clv_dvp = new SimpleIntegerProperty(clv_dvp);
	}
	public IntegerProperty Clv_dvpProperty() {
		return clv_dvp;
	}
	//Metodos atributo: clv_producto
	public Producto getClv_producto() {
		return clv_producto;
	}
	public void setClv_producto(Producto clv_producto) {
		this.clv_producto = clv_producto;
	}
	//Metodos atributo: clv_venta
	public Venta getClv_venta() {
		return clv_venta;
	}
	public void setClv_venta(Venta clv_venta) {
		this.clv_venta = clv_venta;
	}
	//Metodos atributo: cantidad_dvp
	public int getCantidad_dvp() {
		return cantidad_dvp.get();
	}
	public void setCantidad_dvp(int cantidad_dvp) {
		this.cantidad_dvp = new SimpleIntegerProperty(cantidad_dvp);
	}
	public IntegerProperty Cantidad_dvpProperty() {
		return cantidad_dvp;
	}
	//Metodos atributo: precio_venta_dvp
	public Double getPrecio_venta_dvp() {
		return precio_venta_dvp.get();
	}
	public void setPrecio_venta_dvp(Double precio_venta_dvp) {
		this.precio_venta_dvp = new SimpleDoubleProperty(precio_venta_dvp);
	}
	public DoubleProperty Precio_venta_dvpProperty() {
		return precio_venta_dvp;
	}
	
	
	
	
}
