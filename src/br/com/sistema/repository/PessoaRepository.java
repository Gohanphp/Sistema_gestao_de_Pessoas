package br.com.sistema.repository;
import br.com.sistema.model.Pessoa;
import java.util.List;

public interface PessoaRepository {
        void salvar(Pessoa pessoa);

        List<Pessoa> listarTodos();

        Pessoa buscarPorId(Integer id);

        void atualizar(Pessoa pessoa);

        void deletar(Integer id);

}
