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
        ArrayList<PedidoAquisicao> pedidos;

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
        System.out.println("1 - Alterar usuário");
        System.out.println("2 - Criar um pedido");
        System.out.println("3 - Buscar pedido");
        System.out.println("4 - Painel Admistrador");
        System.out.println("9 - Sair"); 
        System.out.print("Opção: ");
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
                //if(criarPedido(usuarioLogado, in, departamentoUsu, auxLista) == usuarioLogado) System.out.println("Pedido adicionado com sucesso.");
                return criarPedido(usuarioLogado, in, departamentoUsu, auxLista);
            }
            case "3":
            {
                busca(usuarioLogado, auxLista);
            }
            case "4":
            {
                Usuario aux = usuarioLogado;
                usuarioLogado = ADM(auxLista, usuarioLogado);
                if(usuarioLogado==null)
                {
                    System.out.println("Usuário logado nao tem permissão para acessar este menu");
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
                System.out.println("Opção inválida\n");
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
            System.out.println("Insira sua matrícula para entrar no sistema: ");
            System.out.print("Matrícula: ");
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
                    System.out.println("Valor inválido para data, coloque numeros relativos ao calendário.\n");
                    subLoop1 = true;
                }

            } else {
                System.out.println("Valor inválido para data, siga o modelo de formato.\n");
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
                    System.out.println("Erro: Coloque um numero válido.\n");
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
                    System.out.println("Erro: Coloque um número inteiro.\n");
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
                    System.out.println("Erro: Coloque um número inteiro.\n");
                    subLoop1 = true;
                    in.nextLine();

                } catch (Exception e2) {
                    System.out.println("Erro: " + e2+"\n");
                    subLoop1 = true;
                    in.nextLine();
                }

                if (resposta != 0 && resposta != 1) {
                    if (!subLoop1) { //Visando o buffer que vai ser limpo do in.nextLine()
                        System.out.println("Erro: Selecione um índice válido.\n");
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
            listaAux.adicionaPedidoAquisicao(pedido);
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

    public static void menuADM() {
            boolean sair = false;
            while(sair==false)
            {
                System.out.println("Menu Admimistrador");
                System.out.println("1- Avaliar pedido em aberto");
                System.out.println("2- Estatística de pedidos totais");
                System.out.println("3- Número de pedidos dos últimos 30 dias e seu valor médio");            
                System.out.println("4- Valor total de cada categoria dos últimos 30 dias");            
                System.out.println("5- Detalhes do pedido de maior valor ainda em aberto");            
                System.out.println("6- Sair do menu ADM");            
                System.out.print("Opção: ");
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
                    System.out.println("Opção inválida");
                }
            }
    }

    public static void busca(Usuario usuarioLogado, ListaDepartUsua auxLista){
        Scanner in = new Scanner(System.in);
        boolean subLoop = true;
            if(auxLista.getListaPedidoAquisicaoSize() == 0) {
                System.out.println("\nNão há pedidos salvos na sua lista.\nDigite qualquer coisa para voltar ao menu principal.");
                String input = in.next();
                if(input != null) subLoop = false;
            }
            else {do{
                System.out.println("\nDigite o número de identificação do pedido que gostaria de ver. Digite -1 para sair.");
                int idPedido = in.nextInt();
                    if(idPedido == -1) {subLoop = false; break;}

                if(idPedido < auxLista.getListaPedidoAquisicaoSize()) {
                    System.out.println("\n---------------------------------------------------------");
                    PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
                    System.out.println(retorno.pedidoToString(idPedido));
                    System.out.println("---------------------------------------------------------\n");
                }
                else {System.out.println("\nNão há pedido com este número de identificação."); break;}

                System.out.println("\nDeseja fazer algo com este pedido?");
                System.out.println("0 - Não.");
                System.out.println("1 - Deletar pedido");
                System.out.println("2 - Editar pedido");
                System.out.println("3 - Marcar como concluído");
                int opcaoCase = in.nextInt();
                switch(opcaoCase){
                    case 0: {
                        break;
                    }
                    case 1: {
                        deletar(usuarioLogado, in, idPedido, auxLista);
                        break;
                    }
                    case 2: {
                        editar(usuarioLogado, in, idPedido, auxLista);
                        break;
                    }
                    case 3: {
                        PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
                        statusChanger(usuarioLogado, in, retorno, 3, idPedido);
                        break;
                    }
                }
        }while(subLoop == true);}
    }
    
    private static void statusChanger(Usuario usuarioLogado, Scanner in, PedidoAquisicao retorno, int status, int idPedido){
        if(usuarioLogado.isAdm() == false) {
            if(retorno.getStatusDoPedido() == status) System.out.println("Este pedido já " + retorno.getStatusString() + ".");
            else {
            System.out.println("ATENÇÃO: Uma vez que um produto não está mais em aberto, ele não pode ser reaberto, apenas visto.\nDeseja continuar?");
            int yesNo;
            System.out.println("Digite 0 para CANCELAR.\nDigite 1 para CONTINUAR.");
            yesNo = in.nextInt();
                if(yesNo == 1) { retorno.setStatusDoPedido(status); System.out.println("Status modificado com sucesso. O pedido " + retorno.getStatusString() + "."); }
                else if (yesNo == 0) System.out.println("Operação cancelada.");
                else System.out.println("Opção inválida.");
            }
        } 
        
        else {
            retorno.setStatusDoPedido(status);
            System.out.println("Status modificado com sucesso. O pedido " + retorno.getStatusString() + ".");
        }   
    }

    private static void deletar(Usuario usuarioLogado, Scanner in, int idPedido, ListaDepartUsua auxLista){
        PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
        if(usuarioLogado == retorno.getUsuarioSolicitante()){
            System.out.println("Você tem certeza que quer deletar este pedido? Esta ação é irreversível.");
            System.out.println("Não se preocupe. Os números de identificação dos outros pedidos continuarão os mesmos.");
            System.out.println("[0] para CANCELAR\n[1] para CONFIRMAR");
            int opcao = in.nextInt();
            if(opcao == 1){
                // TO-DO
            }
            else System.out.println("Operação cancelada.");
        }
        else System.out.println("Desculpe. Você não pode executar esta função.");
    }

    private static void editar(Usuario usuarioLogado, Scanner in, int idPedido, ListaDepartUsua auxLista){
        PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
        if(retorno.getStatusDoPedido() != 1) System.out.println("Desculpe, mas este pedido não pode ser editado.");
        else {
            System.out.println("O que gostaria de modificar?\nDigite:");
            System.out.println("[0] Cancelar\n[1] Status do pedido\n[2] Itens do pedido");
            int opcao = in.nextInt();
            switch(opcao){
                case 1: {
                    if(usuarioLogado.isAdm()) {
                        System.out.println("Qual status gostaria de atribuir ao pedido?");
                        System.out.println("[1] Reprovado\n[2] Aprovado\n[3] Concluído");
                        int status = in.nextInt();
                        statusChanger(usuarioLogado, in, retorno, status, idPedido);
                    }
                    else statusChanger(usuarioLogado, in, retorno, 1, idPedido);
                }
                case 2: {
                    retorno.getItensString();
                    System.out.println("Selecione um item.");
                    // TO-DO
                }
                default: {
                    break;
                }
            }
        }

    }
}