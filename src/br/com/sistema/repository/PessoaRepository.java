package br.com.sistema.repository;
import br.com.sistemas.model.Pessoa;
import java.util.List;

public interface PessoaRepository {

    void salvar(Pessoa pessoa);

    List<Pessoa> listarTodos();

    Pessoa buscarPorId(Integer id);

    void atualizarPessoa(Pessoa pessoa);

    void deletar(Integer id);

}
