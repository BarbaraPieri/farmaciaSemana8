package service;

import model.Fabricante;
import model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FabricanteService fabricanteService;

    @Test
    public void testCadastrarEListarProdutos() {
        Fabricante novoFabricante = new Fabricante();
        novoFabricante.setNome("Novo Fabricante");
        fabricanteService.cadastrarFabricante(novoFabricante);

        Produto novoProduto = new Produto();
        novoProduto.setNome("Novo Produto");
        novoProduto.setDescricao("Descrição do Novo Produto");
        novoProduto.setPreco(10.99);
        novoProduto.setFabricante(novoFabricante);
        produtoService.cadastrarProduto(novoProduto, novoFabricante.getId());

        List<Produto> produtos = produtoService.listarProdutos();

        assertEquals(1, produtos.size());
        Produto produtoCadastrado = produtos.get(0);
        assertEquals("Novo Produto", produtoCadastrado.getNome());
        assertEquals("Descrição do Novo Produto", produtoCadastrado.getDescricao());
        assertEquals(10.99, produtoCadastrado.getPreco());
        assertEquals(novoFabricante.getId(), produtoCadastrado.getFabricante().getId());
    }
}
