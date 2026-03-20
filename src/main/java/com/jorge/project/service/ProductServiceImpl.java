package com.jorge.project.service;


import com.jorge.project.exceptions.ProductException;
import com.jorge.project.model.Product;
import com.jorge.project.repository.IProductRepository;


import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void create(Product product) {
        if (product == null) throw new ProductException("El producto no puede ser nulo");
        if (!findByExactName(product.getName()).isEmpty()) throw new ProductException("El producto ya existe");
        if (product.getStock() < 0) throw new ProductException("El stock debe ser mayor o igual a 0");

        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean update(int id, Product product) {
        if (id <= 0) throw new ProductException("ID debe ser un número positivo");
        if (product == null) throw new ProductException("El producto no puede ser nulo");

        Product productDB = findById(id);
        if (productDB == null) throw new ProductException("El producto no existe");
        if (product.getPrice() > (productDB.getPrice() * 2)) {
            throw new ProductException("El precio no puede ser mayor que 2 veces el precio anterior");
        }
        if (product.getCategory() != productDB.getCategory() && productDB.getStock() > 0) {
            throw new ProductException("No se puede cambiar la categoría de un producto con stock disponible");
        }


        return productRepository.update(id, product);
    }

    @Override
    public boolean delete(int id) {
        Product product = findById(id);
        if (product == null) return false;
        if (product.getStock() > 0) throw new ProductException("No se puede eliminar un producto con stock disponible");
        return productRepository.delete(id);
    }

    @Override
    public List<Product> findByName(String namePart) {
        if (namePart == null || namePart.isBlank()) return new ArrayList<>(); // list empty
        return productRepository.findByName(namePart);
    }

    @Override
    public List<Product> findByExactName(String name) {
        if (name == null || name.isBlank()) return new ArrayList<>(); // list empty
        return productRepository.findByExactName(name);
    }


    @Override
    public List<Product> findByPriceInRange(double min, double max) {
        if (min <= 0 || max <= 0) throw new ProductException("El precio debe ser mayor que cero");
        if (min > max) throw new ProductException("El precio mínimo no puede ser mayor que el máximo");
        if (max > 10000) throw new ProductException("El precio máximo no puede ser mayor que 10,000");

        return productRepository.findByPriceInRange(min, max);
    }


}
