package com.snack.facade;

import com.snack.applications.ProductApplication;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFacadeTest {
    private ProductFacade productFacade;
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;

    private Product prod1;
    private Product prod2;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication (productRepository, productService);
        productFacade = new ProductFacade( productApplication);
        prod1 = new Product(1, "Churros", 2.5f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\churros.jpg");
        prod2 = new Product(2, "Sonho", 2.5f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\sonho.jpg");

        productFacade.append(prod1);
        productFacade.append(prod2);
    }

    @Test
    public void ListaCompletaProdutos() {
        assertNotNull(productFacade.getAll());
    }

    @Test
    public void ProdutoCorretoPorId() {
        Product prod2 = productFacade.getById(2);
        assertEquals("Sonho", prod2.getDescription());
        assertEquals(2.5f, prod2.getPrice());
        assertEquals("C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\sonho.jpg", prod2.getImage());
    }

    @Test
    public void VIdExistenteFParaIdInexistente(){
        boolean product1 = productApplication.exists(1);
        assertTrue(product1);
        boolean product2 = productApplication.exists(3);
        assertFalse(product2);
    }

    @Test
    public void AdicaoNovoProduto(){
        Product prod3 = new Product(3, "Pastel", 3.4f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\pastel.jpg");
        productFacade.append(prod3);

        assertTrue(productFacade.exists(3));
    }

    @Test
    public void RemocaoProdutoExistentePorId() {
        productFacade.remove(2);
        assertEquals(1, productFacade.getAll().size());
    }
}
