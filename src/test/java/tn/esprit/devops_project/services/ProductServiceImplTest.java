package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testAddProduct() {
        Stock stock = new Stock(1L, "Test Stock", null);
        Product product = new Product(1L, "Test Product", 10.0, 5, ProductCategory.ELECTRONICS, stock);

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.addProduct(product, 1L);

        verify(stockRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);

        assertNotNull(result);
        assertEquals(product.getTitle(), result.getTitle());
    }

    @Test
    void testRetrieveProduct() {
        Product product = new Product(1L, "Test Product", 10.0, 5, ProductCategory.ELECTRONICS, new Stock());

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.retrieveProduct(1L);

        verify(productRepository, times(1)).findById(1L);

        assertNotNull(result);
        assertEquals(product.getTitle(), result.getTitle());
    }

    @Test
    void testRetreiveAllProduct() {
        List<Product> productList = List.of(
                new Product(1L, "Product 1", 10.0, 5, ProductCategory.ELECTRONICS, new Stock()),
                new Product(2L, "Product 2", 20.0, 10, ProductCategory.BOOKS, new Stock())
        );

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.retreiveAllProduct();

        verify(productRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList.get(0).getTitle(), result.get(0).getTitle());
        assertEquals(productList.get(1).getTitle(), result.get(1).getTitle());
    }

    @Test
    void testRetrieveProductByCategory() {
        ProductCategory category = ProductCategory.ELECTRONICS;

        List<Product> productList = List.of(
                new Product(1L, "Product 1", 10.0, 5, category, new Stock()),
                new Product(2L, "Product 2", 20.0, 10, category, new Stock())
        );

        when(productRepository.findByCategory(category)).thenReturn(productList);

        List<Product> result = productService.retrieveProductByCategory(category);

        verify(productRepository, times(1)).findByCategory(category);

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList.get(0).getTitle(), result.get(0).getTitle());
        assertEquals(productList.get(1).getTitle(), result.get(1).getTitle());
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRetreiveProductStock() {
        List<Product> productList = List.of(
                new Product(1L, "Product 1", 10.0, 5, ProductCategory.ELECTRONICS, new Stock(1L, "Stock 1", null)),
                new Product(2L, "Product 2", 20.0, 10, ProductCategory.BOOKS, new Stock(2L, "Stock 2", null))
        );

        when(productRepository.findByStockIdStock(1L)).thenReturn(productList);

        List<Product> result = productService.retreiveProductStock(1L);

        verify(productRepository, times(1)).findByStockIdStock(1L);

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList.get(0).getTitle(), result.get(0).getTitle());
        assertEquals(productList.get(1).getTitle(), result.get(1).getTitle());
    }
}
