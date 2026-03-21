package com.jorge.project.service;


import com.jorge.project.exceptions.ProductException;
import com.jorge.project.model.Category;
import com.jorge.project.model.Product;
import com.jorge.project.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    ProductRepositoryImpl productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void shouldThrowExceptionWhenProductIsNull() {
        assertThrows(ProductException.class, () -> {
            productService.create(null);
        });
    }

    @Test
    void shouldThrowExceptionWhenProductAlreadyExists() {
        when(productRepository.findByExactName("Producto1")).thenReturn((List<Product>) List.of(new Product("Producto1", 100.0, 100, Category.ELECTRONIC)));
        assertThrows(ProductException.class, () -> {
            productService.create(new Product("Producto1", 10.0, 10, Category.ELECTRONIC));
        });
    }

    @Test
    void shouldThrowExceptionWhenStockIsNegative() {
        assertThrows(ProductException.class, () -> {
            productService.create(new Product("Producto1", 10.0, -10, Category.ELECTRONIC));
        });
    }


    @Test
    void shouldSuccessfullyCreateProduct() {
        when(productRepository.findByExactName("Producto1")).thenReturn(new ArrayList<Product>());
        productService.create(new Product("Producto1", 10.0, 10, Category.ELECTRONIC));
        verify(productRepository).save(any());
    }
}
