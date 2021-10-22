import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.naming.AuthenticationException;

public class Menu{
    public static void main (String[] args){
        //Chama funcao limpa terminal
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
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{
                System.out.print("\033[H\033[2J");  
                System.out.flush();
            }
        }
        catch (final Exception e )
        {}
    }  

    //menu inicial
    public static Usuario Menu(Usuario usuarioLogado, ListaDepartUsua auxLista, boolean sair){
        Scanner in = new Scanner(System.in);
        String opcao = "";
        LimpaTela();
        System.out.println("Operador do sistema: "+ usuarioLogado.getNome());
        System.out.println("Iniciais: "+ usuarioLogado.getInicialNome() + ". " + usuarioLogado.getInicialSobrenome() + ".");
        System.out.println();
        System.out.println();
        System.out.println("*STANLEY* - Sistema de Controle de Aquisicao");
        System.out.println("1 - Alterar usuario");
        System.out.println("2 - Criar um pedido");
        System.out.println("3 - Buscar meus pedidos");
        System.out.println("4 - Painel Administrador");
        System.out.println("9 - Sair"); 
        System.out.print("Opcao: ");
        opcao = in.nextLine();

        switch(opcao)
        {
            case "1":
                return usuarioLogado = Login(auxLista);

            case "2":
                //Pega um departamento a partir de uma String(nome do departamento) de um usuario
                Departamento departamentoUsu = auxLista.buscaPorDepartamento(usuarioLogado.getDepartamento());
                return criarPedido(usuarioLogado, in, departamentoUsu, auxLista);


            case "3":
                //busca de funcionarios e ADM
                buscaPorFuncionario(usuarioLogado, auxLista, true);
                break;
            
            case "4":
                Usuario aux = usuarioLogado;
                usuarioLogado = ADM(auxLista, usuarioLogado, auxLista);
                if(usuarioLogado==null)
                {
                    LimpaTela();
                    System.out.println("Usuario logado nao tem permissao para acessar este menu.");
                    System.out.print("Digite 0 para voltar para o menu: ");
                    in.nextLine();
                    return aux;
                }
                else
                {
                    return usuarioLogado;
                }
            case "9":
            {
                System.exit(0);
            }
            default:
            {
                LimpaTela();
                System.out.print("Opcao invalida! ");
                in.nextLine();
                break;
            }
        }
        return usuarioLogado;
    }

    //Painel do ADM
    public static void menuADM(Usuario usuarioLogado, ListaDepartUsua auxLista) {
        boolean subLoop = true;
        Scanner in = new Scanner(System.in);
        LimpaTela();

        do {
            System.out.println("Menu Principal do Administrador");
            System.out.println("0- Cadastrar um novo usuario");
            System.out.println("1- Entrar no menu de busca");
            System.out.println("2- Estatistica de pedidos totais");
            System.out.println("3- Numero de pedidos dos ultimos 30 dias e seu valor medio");
            System.out.println("4- Valor total de cada categoria dos ultimos 30 dias");
            System.out.println("5- Detalhes do pedido de maior valor ainda em aberto");
            System.out.println("6- Sair do menu ADM");
            System.out.print("Opcao: ");
            String opcao = in.nextLine();

            switch (opcao) {
                case "0": {
                    LimpaTela();
                    auxLista.cadastraUsuario();
                    subLoop = false;
                    break;
                }
                case "1": {
                    buscaDoADM(usuarioLogado, auxLista, in);
                    subLoop = false;
                    break;
                }
                case "2": {
                    estatisticasPorcentagens(usuarioLogado, auxLista, in);
                    subLoop = false;
                    break;
                }
                case "3": {
                    LimpaTela();
                    System.out.println("Quantidade de pedidos efetuados nos ultimos 30 dias: "+auxLista.ultimos30dias());
                    System.out.println("\nMedia do preco dos pedidos efetuados nos ultimos 30 dias R$ "+auxLista.utlimostrinta()+"\n");

                    System.out.print("Digite 0 para voltar ao menu principal: ");
                    in.next();
                    subLoop = false;
                    break;
                }
                case "4": {

                    LimpaTela();
                    System.out.println("\nValor total de pedidos abertos nos ultimos 30 dias: R$ "+auxLista.ContadorCategoriaAberto());
                    System.out.println("\nValor total de pedidos comprovados nos ultimos 30 dias: R$ "+auxLista.ContadorCategoriaComprovada());
                    System.out.println("\nValor total de pedidos concluidos nos ultimos 30 dias: R$ "+auxLista.ContadorCategoriaConcluido());

                    System.out.print("\n\nDigite 0 para voltar ao menu principal: ");
                    in.next();
                    subLoop = false;
                    break;
                }
                case "5": {
                    if (auxLista.getListaPedidoAquisicaoSize() != 0) {
                        LimpaTela();
                        System.out.print("Maior pedido em valor: ");
                        System.out.println(auxLista.maiorPedidoListado());
                        System.out.print("\n\nDigite 0 para voltar ao menu principal: ");
                        in.next();
                    }
                    else {
                        System.out.println("Nao tem pedidos, visto os requisitos.");
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    subLoop = false;
                    break;
                }
                case "6": {
                    subLoop = false;
                    break;
                }
                default: {
                    System.out.println("\nOpcao invalida.\n");
                    subLoop = true;
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LimpaTela();
                    break;
                }
            }
        }while(subLoop);

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

        LimpaTela();
        ArrayList<Item> listaItens = new ArrayList<>();
        boolean op = false;
        boolean subLoop1 = false;
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
                    LimpaTela();
                    System.out.println("Valor invalido para data, coloque numeros relativos ao calendario.\n");
                    //in.nextLine();
                    subLoop1 = true;
                }

            } else {
                LimpaTela();
                System.out.println("Valor invalido para data, siga o modelo de formato.\n");
                //in.nextLine();
                subLoop1 = true;
            }
        } while (subLoop1);

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataPedido = LocalDate.parse(dataString,formatoData);

        do{
            double valor = 0;
            int quantidade = 0;
            System.out.print("Informe a descricao do item: ");
            String descItem = in.nextLine();

            //Validacao do valor do item
            do {
                try {
                    System.out.print("Informe o valor do item: ");
                    valor = in.nextDouble();
                    subLoop1 = false;
                } catch (InputMismatchException e1) {
                    LimpaTela();
                    System.out.println("Erro: Coloque um valor valido.\n");
                    subLoop1 = true;
                    in.nextLine();

                } catch (Exception e2) {
                    LimpaTela();
                    System.out.println("Erro: " + e2+"\n");
                    subLoop1 = true;
                    //in.nextLine();
                }
            } while(subLoop1);


            //Validacao da quantidade desejada
            do {
                try {
                    System.out.print("Informe a quantidade desejada: ");
                    quantidade = in.nextInt();
                    subLoop1 = false;
                } catch (InputMismatchException e1) {
                    LimpaTela();
                    System.out.println("Erro: Coloque um numero inteiro.\n");
                    subLoop1 = true;
                    in.nextLine();

                } catch (Exception e2) {
                    LimpaTela();
                    System.out.println("Erro: " + e2+"\n");
                    subLoop1 = true;
                    //in.nextLine();
                }
            } while(subLoop1);

            Item item = new Item(descItem, valor, quantidade);
            listaItens.add(item);
            LimpaTela();

            //Validacao de continuacao
            int resposta = -1;
            do {
                try {
                    System.out.println("Deseja adicionar outro item? \n[1] Sim \n[0] Nao");
                    System.out.print("Sua resposta: ");
                    resposta = in.nextInt();
                    subLoop1 = false;
                } catch (InputMismatchException e1) {
                    LimpaTela();
                    System.out.println("Erro: Coloque um numero inteiro.\n");
                    subLoop1 = true;
                    in.nextLine();

                } catch (Exception e2) {
                    LimpaTela();
                    System.out.println("Erro: " + e2+"\n");
                    subLoop1 = true;
                    in.nextLine();
                }

                if (resposta != 0 && resposta != 1) {
                    if (!subLoop1) { //Visando o buffer que vai ser limpo do in.nextLine()
                        LimpaTela();
                        System.out.println("Erro: Selecione um indice valido.\n");
                        subLoop1 = true;
                    }
                }
            } while(subLoop1);

            if (resposta == 0) {
                op = true;
            }

            //Limpa o buffer
            in.nextLine();
            LimpaTela();

        }while(!op);

        PedidoAquisicao pedido = new PedidoAquisicao(logado, departamentoUsu, dataPedido, listaItens);

        //Faz a regra de negocio do limite do pedido
        if(pedido.getValorTotalPedido() > departamentoUsu.getValorLimitePedido()){
            System.out.println("\nPedido de aquisicao invalido: nao respeitou o valor limite.");
        } else{
            System.out.print("Pedido de aquisicao adicionado!  ");
            listaAux.adicionaPedidoAquisicao(pedido);
        }

        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return logado;
    }

    //Utilizado para fazer a validacao se o usuario eh um admin
    public static Usuario ADM(ListaDepartUsua user, Usuario usuarioLogado, ListaDepartUsua auxLista)
    {
        if(usuarioLogado.isAdm())
        {
            menuADM(usuarioLogado, auxLista);
            return usuarioLogado;
        }
        return null;

        
    }

  
    //Utilizado para ambos (admin e usuario), altera o pedido(aprova, deleta, recusa)
    public static void menuAlteracao(Usuario usuarioLogado, Scanner in, int idPedido, ListaDepartUsua auxLista){
        boolean subLoop = true;
        LimpaTela();
        do{
            System.out.println("Deseja fazer algo com este pedido?");
            System.out.println("0 - Nao.");
            System.out.println("1 - Ver mais detalhes");
            System.out.println("2 - Deletar pedido");
            if(usuarioLogado.isAdm()){
                System.out.println("3 - Reprovar pedido");
                System.out.println("4 - Aprovar pedido");
            }
            System.out.print("Opcao: ");
            int opcaoCase = in.nextInt();
            switch(opcaoCase){
                case 0: {
                    subLoop = false;
                    break;
                }
                case 1: {
                    PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
                    System.out.println("\n" + retorno.pedidoToString(idPedido));
                    subLoop = true;
                    break;
                }
                case 2: {
                    deletar(usuarioLogado, in, idPedido, auxLista);
                    subLoop = false;
                    break;
                }
                case 3: {
                    reprovaPedido(usuarioLogado, in, idPedido, auxLista);
                    subLoop = false;
                    break;
                }
                case 4: {
                    aprovaPedido(usuarioLogado, in, idPedido, auxLista);
                    subLoop = false;
                    break;
                }
                default: {
                    System.out.println("\nOpcao invalida.\n");
                    subLoop = true;
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LimpaTela();
                    break;
                }
            }
        }while(subLoop);
    }

    private static void estatisticasPorcentagens(Usuario usuarioLogado, ListaDepartUsua auxLista, Scanner in){

        if(usuarioLogado.isAdm()){

            int qtdAprov = 0;
            for(int i = 0; i < auxLista.getListaPedidoAquisicaoSize(); i++){
                PedidoAquisicao teste = auxLista.getPedidoAquisicao(i);
                if(teste.getStatusDoPedido() == 3) { 
                    qtdAprov++;
                }
            }

            int qtdReprov = 0;
            for(int i = 0; i < auxLista.getListaPedidoAquisicaoSize(); i++){
                PedidoAquisicao teste = auxLista.getPedidoAquisicao(i);
                if(teste.getStatusDoPedido() == 0) { 
                    qtdReprov++;
                }
            }

            int qtdAberto = 0;
            for(int i = 0; i < auxLista.getListaPedidoAquisicaoSize(); i++){
                PedidoAquisicao teste = auxLista.getPedidoAquisicao(i);
                if(teste.getStatusDoPedido() == 1) { 
                    qtdAberto++;
                }
            }

            LimpaTela();
            int total = qtdAprov + qtdReprov + qtdAberto;
            System.out.println("\nHa " + total + " pedido(s) salvos.");
            if(qtdAprov > 0) System.out.println(((qtdAprov*100.0)/total) + "% pedidos que foram aprovados.");
                else System.out.println("0 pedidos que foram aprovados.");
            if(qtdReprov > 0)System.out.println(((qtdReprov*100.0)/total) + "% sao pedidos reprovados.");
                else System.out.println("0 pedidos reprovados.");
            if(qtdAberto > 0) System.out.println(((qtdAberto*100.0)/total) + "% sao pedidos em aberto.\n");
                else System.out.println("0 pedidos abertos.\n");

            boolean subLoopAbert = true;

            do{
                if(qtdAberto > 0 && subLoopAbert) {
                    System.out.println("\nPedidos abertos:");
                    System.out.println(auxLista.getListaPedidosStatus(1));
                    System.out.print("\nInsira o numero de indentificador, ou -1 para ver os pedidos ABERTOS: ");
                    int opcao = in.nextInt();
                    if(opcao >= 0 && opcao <= auxLista.getListaPedidoAquisicaoSize()){
                        menuAlteracao(usuarioLogado, in, opcao, auxLista);
                    } else { subLoopAbert = false; break; }
                } else if (qtdAprov == 0 && subLoopAbert) {
                    subLoopAbert = false;
                    System.out.println("Nao ha pedidos abertos.");
                    break;
                }
            }while(subLoopAbert);  //WTF
        } else {
            System.out.println("Erro: voce nao tem autorizacao para acessar este menu.");
        }

        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   
    //deletar, aprova e reprovar (PEDIDOS)
    private static void deletar(Usuario usuarioLogado, Scanner in, int idPedido, ListaDepartUsua auxLista){
        PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
        if (retorno.getStatusDoPedido() == 1) {
            if (usuarioLogado == retorno.getUsuarioSolicitante()) {
                LimpaTela();
                System.out.println("\nVoce tem certeza que quer deletar este pedido? Esta acao e irreversivel.");
                System.out.println("Os numeros de identificacao dos outros pedidos mudarao dinamicamente.");
                System.out.println("[0] para CANCELAR\n[1] para CONFIRMAR");
                System.out.print("Opcao: ");
                int opcao = in.nextInt();

                if (opcao == 1) {
                    auxLista.getListaPedidoAquisicao().remove(idPedido);
                    LimpaTela();
                    System.out.print("Pedido removido com sucesso. Digite 0 para retornar ao menu: ");
                    in.nextInt();
                } else {
                    LimpaTela();
                    System.out.println("Operacao cancelada.");
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                LimpaTela();
                System.out.println("Desculpe. Voce nao pode executar esta funcao.");
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            LimpaTela();
            System.out.println("Voce so pode excluir um pedido que esta como aberto.");
            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void aprovaPedido(Usuario usuarioLogado, Scanner in, int idPedido, ListaDepartUsua auxLista){
        if(usuarioLogado.isAdm()){
            PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
            if(retorno.getStatusDoPedido() == 1) {
                LimpaTela();
                System.out.print("Informe a data de aprovacao do pedido, seguindo o template dd/MM/yyyy: ");
                String dataAprovadoString = in.next();
                boolean resultado = retorno.setStatusDoPedido(2, dataAprovadoString);

                LimpaTela();
                if (resultado) {
                    System.out.println("Pedido aprovado com sucesso. Voce tem 7 dias ate os itens chegarem, visto a conclusao do pedido.");
                    System.out.print("Digite 0 para voltar ao menu: ");
                }
                else {
                    System.out.println("Nao foi possivel aprovar o pedido. Possivel problema na data passada.");
                    System.out.print("Digite 0 para voltar ao menu: ");
                }
                in.next();
            } else {
                System.out.println("\nNao e possivel modificar o status de um pedido uma vez que este ja foi modificado.");
                try {
                    Thread.sleep(6500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("\nVoce nao tem autorizacao para completar esta acao.");
            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void reprovaPedido(Usuario usuarioLogado, Scanner in, int idPedido, ListaDepartUsua auxLista){
        if(usuarioLogado.isAdm()){
            PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
            if(retorno.getStatusDoPedido() == 1){
                retorno.setStatusDoPedido(0);
                LimpaTela();
                System.out.print("Pedido reprovado com sucesso. Digite 0 para voltar ao menu: ");
                String saida = in.next();
            } else {
                LimpaTela();
                System.out.println("Nao eh possivel modificar o status de um pedido uma vez que este ja foi modificado.");
                try {
                    Thread.sleep(6500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else{
            LimpaTela();
            System.out.println("Voce nao tem autorizacao para completar esta acao.");
            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    

    //inicio da parte de buscas
    public static ArrayList<PedidoAquisicao> BuscarStatus(ListaDepartUsua auxLista){
        ArrayList<PedidoAquisicao> listaEmAberto = new ArrayList<>();

        for(PedidoAquisicao x: auxLista.getListaPedidoAquisicao()){
            if(x.getStatusDoPedido() == 1){
                listaEmAberto.add(x);
            }
        }
        return listaEmAberto;
    }

    public static void BuscaEntreDatas(Usuario usuarioLogado, ListaDepartUsua auxLista){
        Scanner in = new Scanner(System.in);
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ArrayList<PedidoAquisicao> listaDatas = new ArrayList<>();
        boolean test = false;
        if(usuarioLogado.isAdm()){
            if(auxLista.getListaPedidoAquisicaoSize() > 0){
                do{
                    LimpaTela();
                    test = false;
                    try{
                        System.out.print("Digite a data inicial com o template dd/MM/yyyy: ");
                        LocalDate dataInicial = LocalDate.parse(in.next(), formatoData);

                        System.out.println();

                        System.out.print("Digite a data final com o template dd/MM/yyyy: ");
                        LocalDate dataFinal = LocalDate.parse(in.next(), formatoData);


                        for(PedidoAquisicao pedido: BuscarStatus(auxLista)){
                            if((pedido.getDataDoPedido().isAfter(dataInicial) || pedido.getDataDoPedido().isEqual(dataInicial))
                                && (pedido.getDataDoPedido().isBefore(dataFinal) || pedido.getDataDoPedido().isEqual(dataFinal))){
                                listaDatas.add(pedido);
                            }
                        }

                        System.out.println();
                        if (listaDatas.isEmpty()) {
                            LimpaTela();
                            System.out.println("Nao existe nenhum pedido visando esse periodo de datas.");
                        }
                        for(PedidoAquisicao x: listaDatas){
                            System.out.println(x.pedidoToString(x.getIdPedido()));
                            System.out.println();
                        }

                        in.nextLine(); // buffer


                        System.out.print("Digite 0 para voltar ao menu principal: ");
                        in.nextLine();

                    } catch(Exception e){
                        test = true;
                    }
                }while(test);
            }
            else {
                LimpaTela();
                System.out.println("Nao existe pedidos de aquisicao registrados. Com isso nao eh possivel fazer a busca.");
                try {
                    Thread.sleep(6500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else{
            LimpaTela();
            System.out.println("So ADM podem buscar pedidos");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Utilizado para fazer buscas anteriormente
/*//    private static void buscarPorFuncionario(Usuario usuarioLogado, ListaDepartUsua auxLista){
//        ArrayList<PedidoAquisicao> lista = new ArrayList<>();
//
//        if(usuarioLogado.isAdm()){
//            if(auxLista.getListaPedidoAquisicaoSize() > 0){
//                for(PedidoAquisicao x: BuscarStatus(auxLista)){
//                    if(x.getUsuarioSolicitante() == usuarioLogado){
//                        lista.add(x);
//                    }
//                }
//            }
//            System.out.println();
//            if (lista.isEmpty()) {
//                LimpaTela();
//                System.out.println("Nao existe nenhum pedido referente a esse usuario.");
//            }
//            for(PedidoAquisicao x: lista){
//                System.out.println(x.pedidoToString(x.getIdPedido()));
//                System.out.println("\n");
//            }
//        } else{
//            System.out.println("Atraves desse menu, apenas os administradores podem fazer isso.");
//        }
//    }*/

    public static void BuscaPorDescricao(Usuario usuarioLogado, ListaDepartUsua auxLista){
        Scanner in = new Scanner(System.in);
        ArrayList<PedidoAquisicao> buscaDescricao = new ArrayList<>();
        String descricaoItem;
        int aux = -1;

        LimpaTela();
        System.out.print("Digite da Descricao do item que procura: ");
        descricaoItem = in.nextLine();
        System.out.println();

        if(usuarioLogado.isAdm()){
            if(BuscarStatus(auxLista).size() > 0){
                for(PedidoAquisicao x: BuscarStatus(auxLista)){
                    for(Item y: x.getListaItem()){
                        aux = y.getDescricaoItem().indexOf(descricaoItem);
                        if(aux == 0){
                            buscaDescricao.add(x);
                        }
                    }
                }

                System.out.println();
                if (buscaDescricao.isEmpty()) {
                    LimpaTela();
                    System.out.println("Nao existe nenhum pedido visando essa descricao.");
                }
                for(PedidoAquisicao x: buscaDescricao){
                    System.out.println(x.pedidoToString(x.getIdPedido()));
                    System.out.println();
                }

                System.out.print("Digite 0 para voltar ao menu principal: ");
                in.nextLine();
            }
            else {
                LimpaTela();
                System.out.println("Nao tem pedidos classificados abertos.");
                try {
                    Thread.sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else{
            LimpaTela();
            System.out.println("So ADM podem buscar pedidos, nesse menu");
            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void buscaPorStatus(Usuario usuarioLogado, Scanner in, int status, ListaDepartUsua auxLista){

        int qtd = 0;
        for(int i = 0; i < auxLista.getListaPedidoAquisicaoSize(); i++){
            PedidoAquisicao teste = auxLista.getPedidoAquisicao(i);
            if(teste.getStatusDoPedido() == status) { 
                qtd++;
            }
        }

        LimpaTela();

        if(qtd == 0){
            System.out.println("Nao ha pedidos com este salvos status atualmente.");
            System.out.print("Digite 0 coisa para sair para o menu principal: ");
            String sair = in.next();
        } else {
            System.out.println("Ha " + qtd + " pedidos salvos com este status: ");
            System.out.println(auxLista.getListaPedidosStatus(status));
            System.out.print("\nSelecionar algum pedido? Inserir o NUMERO IDENTIFICADOR para selecionar, ou -1 para sair: ");
                int opcao = in.nextInt();
                if(opcao >= 0 && opcao <= auxLista.getListaPedidoAquisicaoSize()){
                menuAlteracao(usuarioLogado, in, opcao, auxLista);
            } else {
                    System.out.println("Direcionando para o menu principal");
                    try {
                        Thread.sleep(4500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
    //fim da busca por ADM

    //Metodo usado antigamente para realizar algumas buscas
    /*// public static void buscaPorID(Usuario usuarioLogado, ListaDepartUsua auxLista){
            
    //     Scanner in = new Scanner(System.in);
    //     boolean subLoop = true;
    //         if(auxLista.getListaPedidoAquisicaoSize() == 0) {
    //             LimpaTela();
    //             System.out.print("\nNão ha pedidos salvos na sua lista.\nDigite qualquer coisa para voltar ao menu principal: ");
    //             String input = in.next();
    //             if(input != null) subLoop = false;
    //         }
    //         else {do{
    //             LimpaTela();
    //             System.out.print("\nDigite o numero de identificacao do pedido que gostaria de ver. Digite -1 para sair: ");
    //             int idPedido = in.nextInt();
    //                 if(idPedido == -1) {subLoop = false; break;}

    //             if(idPedido < auxLista.getListaPedidoAquisicaoSize()) {
    //                 System.out.println("\n---------------------------------------------------------");
    //                 PedidoAquisicao retorno = auxLista.getPedidoAquisicao(idPedido);
    //                 System.out.println(retorno.pedidoToString(idPedido));
    //                 System.out.println("---------------------------------------------------------\n");
    //             }
    //             else {
    //                     LimpaTela(); 
    //                     System.out.print("\nNao ha pedido com este numero de identificacao: ");
    //                     break;
    //                 }

    //             menuAlteracao(usuarioLogado, in, idPedido, auxLista);

    //         }while(subLoop == true);
    //     }
    // }


    // MÉTODO OBSOLETO, MAS DEIXADO AQUI CASO O buscaPorFuncionario QUEBRE
    // private static void buscaMeusPedidos(Usuario usuarioLogado, ListaDepartUsua auxLista){
    //     Scanner in = new Scanner(System.in);
    //     int qtd = 0;
    //     for(int i = 0; i < auxLista.getListaPedidoAquisicaoSize(); i++){
    //         PedidoAquisicao teste = auxLista.getPedidoAquisicao(i);
    //         if(teste.getUsuarioSolicitante() == usuarioLogado) { 
    //             qtd++;
    //         }
    //     }
    //     if(qtd == 0) System.out.println("\nVocê ainda não salvou pedido algum.");
    //     else {
    //         System.out.println("\nVocê salvou " + qtd + " pedidos.");
    //         System.out.println(auxLista.getListaPedidosFuncToString(usuarioLogado));
    //     }
        
    //     System.out.println("Selecionar algum pedido? Inserir o número para selecionar, ou -1 para sair.");
    //     int opcao = in.nextInt();
    //     if(opcao >= 0 && opcao < auxLista.getListaPedidosFunc(usuarioLogado).size()){
    //         menuAlteracao(usuarioLogado, in, opcao, auxLista);
    //     } else System.out.println("Saindo.");

    // }*/

    //busca dos funcionarios e ADM
    private static void buscaPorFuncionario(Usuario usuarioLogado, ListaDepartUsua auxLista, boolean self){
        Scanner in = new Scanner(System.in);
        int qtd = 0;
        String matricula = "";
        if(self == false) {
            LimpaTela();
            System.out.print("Informe a matricula do funcionario cujos pedidos voce quer ver: ");
            matricula = in.next();
        } else matricula = usuarioLogado.getMatricula();
            Usuario pesquisa = auxLista.buscaPorMatricula(matricula);
            for(int i = 0; i < auxLista.getListaPedidoAquisicaoSize(); i++){
                PedidoAquisicao teste = auxLista.getPedidoAquisicao(i);
                Usuario solicitante = teste.getUsuarioSolicitante();
            if(solicitante == pesquisa) { 
                qtd++;
            }
        }
        
        if(qtd == 0 && usuarioLogado != pesquisa) {
            LimpaTela();
            System.out.print("Nao ha pedidos salvos por este funcionario. Digite 0 para retornar ao Menu: ");
            in.nextInt();
        }
        else if (qtd != 0 && usuarioLogado != pesquisa){
            LimpaTela();
            System.out.println("Este usuario salvou " + qtd + " pedido/s.");
            System.out.println(auxLista.getListaPedidosFuncToString(auxLista.buscaPorMatricula(matricula)));
            if(usuarioLogado.isAdm() == false)System.out.println("\nVoce nao pode modificar os pedidos de outros usuarios, apenas pode ve-los. Digite qualquer coisa para sair.");
            else {
                System.out.print("\nDigite o numero do ID do produto ou -1 para retornar: ");
                int opcao = in.nextInt();
                if(opcao >= 0 && opcao <= auxLista.getListaPedidoAquisicaoSize()){
                    menuAlteracao(usuarioLogado, in, opcao, auxLista);
                }
            }
        }
        else if (qtd == 0 && usuarioLogado == pesquisa){ LimpaTela(); System.out.print("\nNenhum pedido cadastrado, digite 0 para retornar: "); in.nextInt();}//VERIFICADO
        else if (qtd != 0 && usuarioLogado == pesquisa){ //verificado para funcionario
            LimpaTela();
            System.out.println("\nVoce salvou " + qtd + " pedido/s.");
            System.out.println(auxLista.getListaPedidosFuncToString(usuarioLogado));
            System.out.print("\nDigite o numero do ID do produto ou -1 para retornar: ");
            int opcao = in.nextInt();
            if(opcao >= 0 && opcao < auxLista.getListaPedidoAquisicaoSize()){
                menuAlteracao(usuarioLogado, in, opcao, auxLista);
            }
        }
    }

    //Menu de busca do ADM
    private static void buscaDoADM(Usuario usuarioLogado, ListaDepartUsua auxLista, Scanner in){
        boolean subLoop = true;
        LimpaTela();

        do {
            System.out.println("Menu de busca do Administrador: ");
            System.out.println("1 - Listar pedidos entre duas datas.");
            System.out.println("2 - Buscar pedidos por funcionario.");
            System.out.println("3 - Visualizar pedidos pela descricao dos itens.");
            System.out.println("4 - Buscar pedidos por Status");
            System.out.println("5 - Voltar para o menu principal.");
            System.out.print("Digite uma opcao: ");
            int opcao = in.nextInt();

            switch (opcao) {
                case 1:
                    BuscaEntreDatas(usuarioLogado, auxLista);
                    subLoop = false;
                    break;

                case 2:
                    buscaPorFuncionario(usuarioLogado, auxLista, false);
                    subLoop = false;
                    break;

                case 3:
                    BuscaPorDescricao(usuarioLogado, auxLista);
                    subLoop = false;
                    break;

                case 4:
                    LimpaTela();
                    System.out.println("Escolha um dos Status");
                    System.out.println("[0] - Pedidos reprovados.");
                    System.out.println("[1] - Pedidos abertos.");
                    System.out.println("[2] - Pedidos Aprovados.");
                    System.out.println("[3] - Pedidos Concluidos.");
                    System.out.print("Opcao escolhida: ");
                    int escolha = in.nextInt();

                    System.out.println();

                    while (escolha > 3 || escolha < 0) {
                        System.out.print("Digite uma opcao valida: ");
                        escolha = in.nextInt();
                        System.out.println();
                    }
                    buscaPorStatus(usuarioLogado, in, escolha, auxLista);
                    subLoop = false;
                    break;
                case 5:

                    subLoop = false;

                    break;
                default:
                    System.out.println("\nOpcao invalida.\n");
                    subLoop = true;
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LimpaTela();
                    break;
            }
        }while(subLoop);

    }
}