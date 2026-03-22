package com.jorge.project.service;


import com.jorge.project.exceptions.ProductException;
import com.jorge.project.model.Category;
import com.jorge.project.model.Product;
import com.jorge.project.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepositoryImpl productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private Product productToUpdate;

    @BeforeEach
    void setUp() {
        product = new Product("Producto1", 100.0, 10, Category.ELECTRONIC);
        productToUpdate = new Product("Producto1", 150.0, 10, Category.ELECTRONIC);
    }

    //Test para create
    @Test
    void shouldThrowExceptionWhenProductIsNull() {
        assertThrows(ProductException.class, () -> {
            productService.create(null);
        });
    }

    @Test
    void shouldThrowExceptionWhenProductAlreadyExists() {
        when(productRepository.findByExactName("Producto1")).thenReturn(List.of(product));
        assertThrows(ProductException.class, () -> {
            productService.create(product);
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
        when(productRepository.findByExactName("Producto1")).thenReturn(List.of());
        productService.create(product);
        verify(productRepository).save(product);
    }


    //Test para delete
    @Test
    void shouldReturnFalseWhenProductNotFound() {
        when(productRepository.findById(1)).thenReturn(null);
        boolean result = productService.delete(1);
        assertFalse(result);
    }


    @Test
    void shouldThrowExceptionWhenProductHasStock() {
        when(productRepository.findById(1)).thenReturn(product);
        assertThrows(ProductException.class, () -> productService.delete(1));
    }

    @Test
    void shouldSuccesfullyDeleteProduct() {
        when(productRepository.findById(1)).thenReturn(new Product("Producto1", 10.0, 0, Category.ELECTRONIC));
        when(productRepository.delete(1)).thenReturn(true);
        boolean result = productService.delete(1);
        assertTrue(result);
    }

    //Test para update
    @Test
    void shouldThrowExceptionWhenIdIsInvalid() {
        assertThrows(ProductException.class, () -> productService.update(0, product));
    }

    @Test
    void shouldThrowExceptionWhenProductToUpdateIsNull() {
        assertThrows(ProductException.class, () -> productService.update(1, null));
    }

    @Test
    void shouldThrowExceptionWhenProductToUpdateNotFound() {
        when(productRepository.findById(1)).thenReturn(null);
        assertThrows(ProductException.class, () -> productService.update(1, productToUpdate));
    }

    @Test
    void shouldThrowExceptionWhenNewPriceExceedsDoubleOfCurrentPrice() {
        when(productRepository.findById(1)).thenReturn(product);
        assertThrows(ProductException.class, () -> productService.update(1, new Product("Producto1", 250.0, 10, Category.ELECTRONIC)));
    }

    @Test
    void shouldThrowExceptionWhenCategoryChangesWithStockAvailable() {
        when(productRepository.findById(1)).thenReturn(product);
        assertThrows(ProductException.class, () -> productService.update(1, new Product("Producto1", 10.0, 10, Category.HOME)));
    }

    @Test
    void shouldSuccesfullyUpdateProduct() {
        when(productRepository.findById(1)).thenReturn(product);
        when(productRepository.update(1, productToUpdate)).thenReturn(true);
        boolean result = productService.update(1, productToUpdate);
        assertTrue(result);
    }


    //Test para findByPriceInRange
    @Test
    void shouldThrowExceptionWhenAnyPriceIsZeroOrNegative() {
        assertAll(
                () -> assertThrows(ProductException.class, () -> productService.findByPriceInRange(0, 100)),
                () -> assertThrows(ProductException.class, () -> productService.findByPriceInRange(-10, 100)),
                () -> assertThrows(ProductException.class, () -> productService.findByPriceInRange(100, 0)),
                () -> assertThrows(ProductException.class, () -> productService.findByPriceInRange(100, -10))
        );
    }

    @Test
    void shouldThrowExceptionWhenMinPriceIsGreaterThanMaxPrice() {
        assertThrows(ProductException.class, () -> productService.findByPriceInRange(100, 50));
    }

    @Test
    void shouldThrowExceptionWhenMaxPriceExceedsLimit() {
        assertThrows(ProductException.class, () -> productService.findByPriceInRange(100, 10001));
    }

    @Test
    void shouldSuccesfullyReturnProductsInPriceRange() {
        when(productRepository.findByPriceInRange(50, 500)).thenReturn(List.of(product));
        List<Product> products = productService.findByPriceInRange(50, 500);
        assertEquals(List.of(product), products);

    }

    //Test para findAll
    @Test
    void shouldReturnEmptyListWhenNoProductsExists() {
        when(productRepository.findAll()).thenReturn(List.of());
        List<Product> products = productService.findAll();
        assertEquals(List.of(), products);
    }

    @Test
    void shouldReturnListWithProductsWhenProductsExists() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<Product> products = productService.findAll();
        assertEquals(List.of(product), products);
    }

    //Test para findById
    @Test
    void shouldReturnNullProductNotFound() {
        when(productRepository.findById(1)).thenReturn(null);
        Product productFound = productService.findById(1);
        assertNull(productFound);
    }

    @Test
    void shouldReturnProductWhenProductFound() {
        when(productRepository.findById(1)).thenReturn(product);
        Product productFound = productService.findById(1);
        assertEquals(product, productFound);
    }


    //Test para findByName
    @Test
    void shouldReturnEmptyListWhenProductNameIsNull() {
        assertEquals(List.of(), productService.findByName(null));
    }

    @Test
    void shouldReturnEmptyListWhenProductNameIsBlank() {
        assertEquals(List.of(), productService.findByName("   "));
    }

    @Test
    void shouldReturnListWithProductsWhenProductNameIsValid() {
        when(productRepository.findByName("Producto1")).thenReturn(List.of(product));
        List<Product> products = productService.findByName("Producto1");
        assertEquals(List.of(product), products);
    }

    //Test para findByExactName
    @Test
    void shouldReturnEmptyListWhenProductExactNameIsNull() {
        assertEquals(List.of(), productService.findByExactName(null));
    }

    @Test
    void shouldReturnEmptyListWhenProductExactNameIsBlank() {
        assertEquals(List.of(), productService.findByExactName("   "));
    }

    @Test
    void shouldReturnListWithProductsWhenProductExactNameIsValid() {
        when(productRepository.findByExactName("Producto1")).thenReturn(List.of(product));
        List<Product> products = productService.findByExactName("Producto1");
        assertEquals(List.of(product), products);
    }


}
