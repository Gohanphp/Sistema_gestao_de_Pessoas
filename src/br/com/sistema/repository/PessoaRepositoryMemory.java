package br.com.sistema.repository;
import br.com.sistemas.model.Pessoa;
import java.util.List;

public class PessoaRepositoryMemory implements PessoaRepository {
    public void salvar(Pessoa pessoa){

    }

    @Override
    public List<Pessoa> listarTodos() {
        return List.of();
    }

    private <Pessoa> PessoaRepositoryMemory(Pessoa pessoa){

    }
    public Pessoa buscarPorId(Integer id){

    }
    public void atualizarPessoa(Pessoa pessoa){

    }

    public void deletar(Integer id){

    }

}
