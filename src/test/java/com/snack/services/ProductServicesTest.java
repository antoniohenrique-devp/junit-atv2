package com.snack.services;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServicesTest {
    private static ProductService productService;

    private Product prod1;
    private Product prod2;


    @BeforeEach
    public void setup() {
        productService = new ProductService();
        prod1 = new Product(1, "Churros", 2.5f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\churros.jpg");
        prod2 = new Product(2, "Sonho", 2.5f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\sonho.jpg");

        productService.save(prod1);
        productService.save(prod2);
    }

    @Test
    public void ProdutoSalvoComImagem() {
        Product prod5 = new Product(5, "Churros", 2.5f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\churros.jpg");

        assertEquals(true, productService.save(prod5));
    }

    @Test
    public void ProdutoSalvoSemImagem(){
        Product product6 = new Product(6, "Sorvete", 5.0f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\sorvete.jpg");
        boolean isSaved = productService.save(product6);
        assertFalse(isSaved);
    }

    @Test
    public void RemoverProdutoExistente() {
        Path path = Paths.get(productService.getImagePathById(prod2.getId()));
        productService.remove(prod2.getId());
        assertFalse(Files.exists(path));
    }

    @Test
    public void Atualizarproduto() {
        prod1.setDescription("Coca");
        productService.update(prod1);
        assertEquals("Coca",prod1.getDescription());
    }

    @Test
    public void ObterCaminhoImgPorId() {
        Path path = Paths.get(productService.getImagePathById(1));
        assertTrue(Files.exists(path));
    }
}
