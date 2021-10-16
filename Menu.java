import java.util.ArrayList;
import java.util.Scanner;

public class Menu{
    public static void main (String[] args){
        ListaDepartUsua auxLista = new ListaDepartUsua();
        boolean sair = false;
        Usuario usuarioLogado = null;
        usuarioLogado = Login(auxLista);
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
                return usuarioLogado = Login(auxLista);
            }
            case "2":
            {
                //Pega um departamento a partir de uma String(nome do departamento) de um usuário
                Departamento departamentoUsu = auxLista.buscaPorDepartamento(usuarioLogado.getDepartamento());
                return criarPedido(usuarioLogado, in, departamentoUsu, auxLista);
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

    public static Usuario Login(ListaDepartUsua users)
    {
        Usuario logado = null;
        do{   
            
            Scanner in = new Scanner(System.in);
            System.out.println("Insira sua matricula para entrar no sistema: ");
            System.out.print("Matricula: ");
            String matricula = in.nextLine();
            logado = users.buscaPorMatricula(matricula);
        }while(logado == null);
        return logado;
        
        
    }

    public static Usuario criarPedido(Usuario logado, Scanner in, Departamento departamentoUsu, ListaDepartUsua listaAux){
        System.out.print("\nInforme a data do pedido:");
        String dataDoPedido = in.nextLine();
        boolean op = false;
        ArrayList<Item> listaItens = new ArrayList<>();
        do{
            System.out.print("\nInforme a descrição do item: ");
            String descItem = in.nextLine();
            System.out.print("\nInforme o valor do item: ");
            double valor = in.nextDouble();
            System.out.print("\nInforme a quantidade desejada: ");
            int quantidade = in.nextInt();
            Item item = new Item(descItem, valor, quantidade);
            listaItens.add(item);
            System.out.print("\nDeseja adicionar outro item? \n[1] sim \n[0] não");
            int resposta = in.nextInt();
            in.nextLine();
            if(resposta == 0){
                op = true;
            }
        }while(!op);

        PedidoAquisicao pedido = new PedidoAquisicao(logado, departamentoUsu, dataDoPedido, listaItens);

        if(pedido.getValorTotalPedido() > departamentoUsu.getValorLimitePedido()){
            System.out.println("Pedido de aquisição inválido: não respeitou o valor limite.");
        } else{
            listaAux.adcionaPedidoAquisicao(pedido);
            System.out.println("Pedido de aquisição adicionado.");
        }
        return logado;
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
            while(sair==false)
            {
                System.out.println("Menu Admimistrador");
                System.out.println("1- Avaliar pedido em aberto");
                System.out.println("2- Estatistica de pedidos totais");
                System.out.println("3- Numero de pedidos dos ultimos 30 dias e seu valor medio");            
                System.out.println("4- Valor total de cada categoria dos ultimos 30 dias");            
                System.out.println("5- Detalhes do pedido de maior valor ainda em aberto");            
                System.out.println("6- Sair do menu ADM");            
                System.out.print("Opcao: ");
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