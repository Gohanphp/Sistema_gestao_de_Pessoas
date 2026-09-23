package br.com.sistema.app;
import br.com.sistema.model.Pessoa;
import br.com.sistema.repository.PessoaRepository;
import br.com.sistema.repository.PessoaRepositoryMemory;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // instanciando a interface
    private static final PessoaRepository repo = new PessoaRepositoryMemory();

    public static void main(String[] args) {
        int opcao = -1;

        //Menu
        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> cadastrarPessoa();
                    case 2 -> listarPessoas();
                    case 3 -> buscarPorId();
                    case 4 -> atualizarPessoa();
                    case 5 -> excluirPessoa();
                    case 0 -> System.out.println("\nSistema encerrado. Até logo!");
                    default -> System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                // trata letras e caracteres
                System.out.println("\nErro: Entrada inválida! Digite apenas números inteiros.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                // retorna erro se for uma resposta vazia
                System.out.println("\nErro de Validação: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA DE GESTÃO DE PESSOAS =====");
        System.out.println("1 - Cadastrar Pessoa");
        System.out.println("2 - Listar Todas as Pessoas");
        System.out.println("3 - Buscar por ID");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Excluir");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarPessoa() {
        System.out.println("\n--- Cadastrar Pessoa ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Pessoa novaPessoa = new Pessoa(null, nome, cpf, email);
        repo.salvar(novaPessoa);
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private static void listarPessoas() {
        System.out.println("\n--- Listando Pessoas ---");
        List<Pessoa> lista = repo.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma pessoa encontrada.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void buscarPorId() {
        System.out.println("\n--- Buscar por ID ---");
        System.out.print("Digite o ID desejado: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        Pessoa p = repo.buscarPorId(id);
        if (p != null) {
            System.out.println("Registro encontrado: " + p);
        } else {
            System.out.println("Pessoa não encontrada para o ID " + id);
        }
    }

    private static void atualizarPessoa() {
        System.out.println("\n--- Atualizar Pessoa ---");
        System.out.print("Digite o ID do registro que deseja atualizar: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        Pessoa pExistente = repo.buscarPorId(id);
        if (pExistente == null) {
            System.out.println("Erro: ID não encontrado no sistema.");
            return;
        }

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();

        Pessoa pessoaAtualizada = new Pessoa(id, nome, cpf, email);
        repo.atualizar(pessoaAtualizada);
        System.out.println("Dados atualizados com sucesso!");
    }

    private static void excluirPessoa() {
        System.out.println("\n--- Excluir Pessoa ---");
        System.out.print("Digite o ID a ser excluído: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        Pessoa p = repo.buscarPorId(id);
        if (p != null) {
            repo.deletar(id);
            System.out.println("Pessoa removida com sucesso!");
        } else {
            System.out.println("Erro: Não foi possível excluir. ID não encontrado.");
        }
    }
}