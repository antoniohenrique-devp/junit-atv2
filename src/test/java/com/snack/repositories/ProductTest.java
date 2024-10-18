package test.repositories;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private ProductRepository reposi;
    private Product product;


    @BeforeEach
    public void set() {
        reposi = new ProductRepository();
        product = new Product(1,"Josefina", 27.5f, "");
        reposi.append(product);
    }

    //1. Verificar se um produto é adicionado corretamente ao repositório (List)
    @Test
    public void VerificaSeFoiSalvo() {
        Product teste = new Product(2,"Carne do sol", 43.6f, "");
        reposi.append(teste);
        List<Product> list = reposi.getAll();
        assertEquals(list.size(), 2);
    }

    //2. Verificar se é possível recuperar um produto usando seu ID
    @Test
    public void BuscaPorId() {
        reposi.append(product);
        Product product1 = reposi.getById(1);
        assertEquals(product1.getId(), 1);
    }

    // 3. Confirmar se um produto existe no repositório (List)
    @Test
    public void ConfirmaProdutoNaLista(){
        assertTrue(reposi.exists(1));
    }

    //4. Testar se um produto é removido corretamente do repositório (List)
    @Test
    public void RemoverCorretamente(){
        reposi.remove(1);
        assertFalse(reposi.exists(1));
    }

    //5. Verificar se um produto é atualizado corretamente
    @Test
    public void TestaAtualizacaoCorreta(){
        product.setDescription("Carne");
        reposi.update(1, product);
        assertEquals("Carne", reposi.getById(1).getDescription());
    }

    //6. Testar se todos os produtos armazenados são recuperados corretamente
    @Test
    public void TodosArmazenados(){
        Product teste = new Product(1, "Carne", 15.5f, "");
        reposi.append(teste);
        assertEquals(2, reposi.getAll().size());
    }

    //7. Verificar o comportamento ao tentar remover um produto que não existe
    @Test
    public void RemoverProdutoInexistente(){
        reposi.remove(3);
        assertEquals(1, reposi.getAll().size());
    }

    //8. Testar o que acontece ao tentar atualizar um produto que não está no repositório (List)
    @Test
    public void AtualizarProdutoInexistente(){
        Product product1 = reposi.getById(1);
        product.setDescription("Carne");
        assertThrows(NoSuchElementException.class, ()->{
            reposi.update(3, product);
        });
    }

    //9. Verificar se o repositório aceita a adição de produtos com IDs duplicados (espera-se que não)
    @Test
    public void RepositoriocomIdDuplicado(){
        Product teste = new Product(1,"Carne", 15.5f, "");
        reposi.append(teste);
        assertNotEquals("Carne", reposi.getById(1).getDescription());
    }

    //10. Confirmar que o repositório retorna uma lista vazia ao ser inicializado (List)
    @Test
    public void ListaInicializadaVazia(){
        ProductRepository novo = new ProductRepository();
        assertEquals(0,novo.getAll().size());
    }


}