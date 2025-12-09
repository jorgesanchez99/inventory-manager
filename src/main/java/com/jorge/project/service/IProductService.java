package com.jorge.project.service;

import com.jorge.project.model.Product;

import java.util.List;

public interface IProductService {
    /**
     * Crea un nuevo producto
     * El servicio se encarga de asignar el ID automáticamente
     *
     * @param product: producto a registrar
     */
    void create(Product product);

    /**
     * Devuelve todos los productos
     *
     * @return Lista de productos
     */
    List<Product> findAll();


    /**
     * Busca un producto por su ID
     *
     * @param id del producto
     * @return Producto encontrado o null si no existe
     */
    Product findById(int id);


    /**
     * Actualiza un producto existente
     *
     * @param id      identificador del producto a actualizar
     * @param product con los nuevos datos
     * @return true si se actualizó correctamente, false si no se encontró el producto
     */
    boolean update(int id, Product product);

    /**
     * Elimina un producto por su ID
     *
     * @param id identificador del producto a eliminar
     * @return true si se eliminó correctamente, false si no se encontró el producto
     */
    boolean delete(int id);

    /**
     * Busca productos cuyo nombre contenga la parte especificada
     *
     * @param namePart texto parcial para buscar por nombre
     * @return Lista de productos que coinciden con la búsqueda
     */
    List<Product> findByName(String namePart);


    /**
     * Busca productos dentro de un rango de precios
     *
     * @param min precio mínimo
     * @param max precio máximo
     * @return Lista de productos dentro del rango de precios
     */
    List<Product> findByPriceInRange(double min, double max);


}
