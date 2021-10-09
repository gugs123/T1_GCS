import java.util.Scanner;

public class Menu{
    public static void main (String[] args){
        ListaDepartUsua auxLista = new ListaDepartUsua();
        boolean sair = false;
        Usuario usuarioLogado = null;
        usuarioLogado = Login(auxLista, usuarioLogado);
        do{
            usuarioLogado = Menu(usuarioLogado, auxLista, sair);
        }while(true);
    }
    
    public static Usuario Menu(Usuario usuarioLogado, ListaDepartUsua auxLista, boolean sair){
        Scanner in = new Scanner(System.in);
        String opcao = "";
        System.out.println("\nOperador do sistema: "+ usuarioLogado.getNome());
        System.out.println("Iniciais: "+ usuarioLogado.getInicialNome() + ". " + usuarioLogado.getInicialSobrenome() + ".");
        System.out.println();
        System.out.println();
        System.out.println("*STANLEY* - Sistema de Controle de Aquisição");
        System.out.println("1 - Alterar usuario");
        System.out.println("2 - Criar um pedido");
        System.out.println("3 - Buscar pedido");
        System.out.println("4 - Painel Admistrador");
        System.out.println("9 - Sair"); 
        System.out.print("Opcao: ");
        opcao = in.nextLine();
        switch(opcao)
        {
            case "1":
            {
                return usuarioLogado = Login(auxLista, usuarioLogado);
            }
            case "9":
            {
                System.exit(0);
            }
            default:
            {
                System.out.println("Opcao invalida\n");
                break;
            }
        }
        return usuarioLogado;
    }

    public static Usuario Login(ListaDepartUsua users, Usuario usuarioLogado)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Insira sua matricula para entrar no sistema: ");
        System.out.print("Matricula: ");
        String matricula = in.nextLine();
        
        return users.buscaPorMatricula(matricula);
        
    }
}