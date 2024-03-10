package service;

import model.Produto;
import model.Fabricante;
import repository.ProdutoRepository;
import repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, FabricanteRepository fabricanteRepository) {
        this.produtoRepository = produtoRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto cadastrarProduto(Produto produto, Long fabricanteId) {
        Fabricante fabricante = fabricanteRepository.findById(fabricanteId)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado com o ID fornecido: " + fabricanteId));
        produto.setFabricante(fabricante);
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto editarDescricaoDoProduto(Long produtoId, String novaDescricao) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID fornecido: " + produtoId));
        produto.setDescricao(novaDescricao);
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deletarProduto(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }
}
