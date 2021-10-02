import java.util.Scanner;

public class menu{
    public static void main (String[] args){
    Menu();   
    }
    
    public static void Menu(){
        Scanner opcao = new Scanner(System.in);
        int resultado_opcao_menu;
        
        System.out.println("*STANLEY* - Sistema de Controle de Aquisição\n 1 - Entrar ou Alterar usuario\n 2 - Criar um pedido\n 3 - Buscar pedido\n 4 - Painel Admistrador\n 9 - Sair");
        resultado_opcao_menu = opcao.nextInt();
        if(resultado_opcao_menu == 1){
           // login();
        }
        else if(resultado_opcao_menu == 2){
            System.exit(0);
        }
    }}