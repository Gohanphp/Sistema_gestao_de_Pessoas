package br.com.sistema.repository;
import br.com.sistema.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepositoryMemory implements PessoaRepository {
    //autoincremento de id
    private final List<Pessoa> listaPessoas = new ArrayList<>();
    private Integer proximoId = 1;

    @Override
    public void salvar(Pessoa pessoa){
        pessoa.setId(proximoId++);
        listaPessoas.add(pessoa);
    }

    @Override
    public List<Pessoa> listarTodos() {
        return new ArrayList<> (listaPessoas);
    }
    @Override
    public Pessoa buscarPorId(Integer id){
        for (Pessoa p: listaPessoas){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
    @Override
    public void atualizar(Pessoa pessoa) {
        Pessoa pExistente = buscarPorId(pessoa.getId());
        if (pExistente != null) {
            pExistente.setNome(pessoa.getNome());
            pExistente.setCpf(pessoa.getCpf());
            pExistente.setEmail(pessoa.getEmail());
        }
    }
    @Override
    public void deletar(Integer id) {
        Pessoa p = buscarPorId(id);
        if (p != null) {
            listaPessoas.remove(p);
        }
    }
}
