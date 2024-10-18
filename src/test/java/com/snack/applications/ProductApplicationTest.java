package com.snack.applications;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApplicationTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;

    private Product prod1;
    private Product prod2;

    //C:\Users\rickf\OneDrive\Área de Trabalho\NLayerLanche-def\src\imagem\\churros.jpg
    //C:\Users\rickf\OneDrive\Área de Trabalho\NLayerLanche-def\src\imagem\\sonho.jpg

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        prod1 = new Product(1, "Churros", 5.2f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\churros.jpg");
        prod2 = new Product(2, "Sonho", 2.5f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\sonho.jpg");

        productApplication.append(prod1);
        productApplication.append(prod2);
    }

    @Test
    public void TodosProdutosListados() {
        assertNotNull(productApplication.getAll());
    }

    @Test
    public void ProdutoObtidoPorId(){
        Product prod2 = productApplication.getById(2);
        assertEquals("Sonho", prod2.getDescription());
        assertEquals(2.5f, prod2.getPrice());
        assertEquals("C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\sonho.jpg", prod2.getImage());
    }

    @Test
    public void ProdutoIdInexistente() {

       assertThrows(NoSuchElementException.class, ()-> {
          productApplication.getById(30);
       });
    }


    @Test
    public void ProdutoPorIdValido() {
        boolean prod1 = productApplication.exists(2);
        assertTrue(prod1);
    }

    @Test
    public void ProdutoPorIdInvalido() {
        boolean prod6 = productApplication.exists(6);
        assertFalse(prod6);
    }

    @Test
    public void SalvarProdutoEImagemAdicionado() {
        Product prod3 = new Product(3, "Churros", 5.2f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\churros.jpg");
        productApplication.append(prod3);

        assertEquals("Churros", prod3.getDescription());
        assertEquals("C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\churros.jpg", prod3.getImage());
    }

    @Test
    public void RemocaoProdutoEImagem() {
        Product prod = new Product(7, "Sonho", 2.5f, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\sonho.jpg");
        productApplication.append(prod);
        productApplication.remove(7);
        assertFalse(productApplication.exists(7));
    }

    @Test
    public void SistemaAposRemoverProdutoComIdInexistente() {
        assertThrows(NoSuchElementException.class, ()-> {
            productApplication.remove(6);
        });
    }


    @Test
    public void AtualizacaoDeProdutoComImagem(){
        Product prodNovo = new Product(13, "Pastel", 12, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\pastel.jpg");
        productApplication.append(prodNovo);

        Product prodAlterar = new Product(13, "Pastel", 12, "C:\\Users\\rickf\\OneDrive\\Área de Trabalho\\NLayerLanche-def\\src\\imagem\\pastel.jpg");
        productApplication.update(20, prodAlterar);

        Product prodAlterado = productApplication.getById(20);


        assertEquals(prodNovo, prodAlterado);
    }

}
