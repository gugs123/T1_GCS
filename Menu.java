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
            case "4":
            {
                Usuario aux = usuarioLogado;
                usuarioLogado = ADM(auxLista, usuarioLogado);
                if(usuarioLogado==null)
                {
                    System.out.println("Usuario logado nao tem permissao para acessar este menu");
                    return aux;
                }
                else
                {
                    return usuarioLogado;
                }
            }
            case "9":
            {
                System.exit(0);
            }

            case "5":
            {
                
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

    public static Usuario ADM(ListaDepartUsua user, Usuario usuarioLogado)
    {
        if(usuarioLogado.isAdm())
        {
            menuADM();
            return usuarioLogado;
        }
        return null;

        
    }

    public static void menuADM()
        {
        boolean sair = false;
        while(sair==false){
            System.out.println("Menu Admimistrador");
            System.out.println("1- Avaliar pedido em aberto");
            System.out.println("2- Estatistica de pedidos totais");
            System.out.println("3- Numero de pedidos dos ultimos 30 dias e seu valor medio");            
            System.out.println("4- Valor total de cada categoria dos ultimos 30 dias");            
            System.out.println("5- Detalhes do pedido de maior valor ainda em aberto");            
            System.out.println("6- Sair do menu ADM");            
            System.out.println("Opcao: ");
            Scanner in = new Scanner(System.in);
            String opcao = in.nextLine();
            switch(opcao)
            {
                case "1":
                //TODO
                break;
                case "2":
                //TODO
                break;
                case "3":
                //TODO
                break;
                case "4":
                //TODO
                break;
                case "5":
                //TODO
                break;
                case "6":
                sair = true;
                break;
                default:
                System.out.println("Opcao invalida");
            }
        }
        }
}