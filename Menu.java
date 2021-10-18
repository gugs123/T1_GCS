import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu{
    public static void main (String[] args){
        //Chama função limpa terminal
        LimpaTela();
        //Classe que contem todos os dados (usuarios, departamentos e pedidos)
        ListaDepartUsua auxLista = new ListaDepartUsua();
        boolean sair = false;
        Usuario usuarioLogado = null;
        usuarioLogado = Login(auxLista);

        do{
            usuarioLogado = Menu(usuarioLogado, auxLista, sair);
        }while(true);
    }
    
    public static void LimpaTela(){

        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static Usuario Menu(Usuario usuarioLogado, ListaDepartUsua auxLista, boolean sair){
        Scanner in = new Scanner(System.in);
        String opcao = "";
        LimpaTela();
        System.out.println("Operador do sistema: "+ usuarioLogado.getNome());
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
            LimpaTela();
            Scanner in = new Scanner(System.in);
            System.out.println("Insira sua matricula para entrar no sistema: ");
            System.out.print("Matricula: ");
            String matricula = in.nextLine();
            logado = users.buscaPorMatricula(matricula);
        }while(logado == null);
        return logado;
        
        
    }

    public static Usuario criarPedido(Usuario logado, Scanner in, Departamento departamentoUsu, ListaDepartUsua listaAux){

        ArrayList<Item> listaItens = new ArrayList<>();
        boolean op = false;
        boolean subLoop1 = false;
        LimpaTela();
        String dataString = "";
        do {
            System.out.print("Informe a data do pedido, com formato dd/MM/yyyy: ");
            dataString = in.nextLine();

            if (dataString.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                int dias = Integer.parseInt(dataString.substring(0,2));
                int mes = Integer.parseInt(dataString.substring(3,5));

                if ((dias >= 1 && dias <= 31) && (mes >= 1 && mes <= 12)) {
                    subLoop1 = false;
                }
                else {
                    System.out.println("Valor invalido para data, coloque numeros relativos ao calendario.\n");
                    subLoop1 = true;
                }

            } else {
                System.out.println("Valor invalido para data, siga o modelo de formato.\n");
                subLoop1 = true;
            }
        } while (subLoop1);

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataPedido = LocalDate.parse(dataString,formatoData);

        do{
            double valor = 0;
            int quantidade = 0;
            LimpaTela();
            System.out.print("Informe a descrição do item: ");
            String descItem = in.nextLine();

            //Validacao do valor do item
            do {
                try {

                    System.out.print("Informe o valor do item: ");
                    valor = in.nextDouble();
                    subLoop1 = false;
                } catch (InputMismatchException e1) {
                    System.out.println("Erro: Coloque um numero valido.\n");
                    subLoop1 = true;
                    in.nextLine();

                } catch (Exception e2) {
                    System.out.println("Erro: " + e2+"\n");
                    subLoop1 = true;
                    in.nextLine();
                }
            } while(subLoop1);


            //Validacao da quantidade desejada
            do {
                try {

                    System.out.print("Informe a quantidade desejada: ");
                    quantidade = in.nextInt();
                    subLoop1 = false;
                } catch (InputMismatchException e1) {
                    System.out.println("Erro: Coloque um numero inteiro.\n");
                    subLoop1 = true;
                    in.nextLine();

                } catch (Exception e2) {
                    System.out.println("Erro: " + e2+"\n");
                    subLoop1 = true;
                    in.nextLine();
                }
            } while(subLoop1);

            Item item = new Item(descItem, valor, quantidade);
            listaItens.add(item);
            System.out.println();

            //Validacao de continuacao
            int resposta = -1;
            do {
                try {
                    System.out.println("Deseja adicionar outro item? \n[1] sim \n[0] não");
                    System.out.print("Sua resposta: ");
                    resposta = in.nextInt();
                    subLoop1 = false;
                } catch (InputMismatchException e1) {
                    System.out.println("Erro: Coloque um numero inteiro.\n");
                    subLoop1 = true;
                    in.nextLine();

                } catch (Exception e2) {
                    System.out.println("Erro: " + e2+"\n");
                    subLoop1 = true;
                    in.nextLine();
                }

                if (resposta != 0 && resposta != 1) {
                    if (!subLoop1) { //Visando o buffer que vai ser limpo do in.nextLine()
                        System.out.println("Erro: Selecione um indice valido.\n");
                        subLoop1 = true;
                        in.nextLine();
                    }
                }
            } while(subLoop1);

            if (resposta == 0) {
                op = true;
            }
            //Limpa o buffer
            in.nextLine();

        }while(!op);

        PedidoAquisicao pedido = new PedidoAquisicao(logado, departamentoUsu, dataPedido, listaItens);

        if(pedido.getValorTotalPedido() > departamentoUsu.getValorLimitePedido()){
            System.out.println("\nPedido de aquisição inválido: não respeitou o valor limite.");
        } else{
            listaAux.adcionaPedidoAquisicao(pedido);
            System.out.println("\nPedido de aquisição adicionado.");
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