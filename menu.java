import java.util.Scanner;
class Pessoa{
    private int matricula;
    private String nome;
    private String categoria;

         //METODOS SET
        public void setmatricula(int matricula) 
        {
            this.matricula = matricula;
        }

        public void setnome(String nome) 
        {
            this.nome = nome;
        }

        public void setcategoria(String categoria) 
        {
            this.categoria = categoria;
        }

        //METODOS GET
        public int getmatricula() {
            return matricula;
        }

        public String getnome(){
            return nome;
        }

        public String categoria(){
            return categoria;
        }
}


public class login{
    public static void main (String[] args){
       // Menu();

        Pessoa[] pessoas = new Pessoa[5];
        pessoas[0] = new Pessoa();
        pessoas[0].setmatricula = 1;
        
        
    }

    //Classe Pessoas destinada a armanezenar dados dos Pessoas.
    
    

    public static void Menu(){
        Scanner opcao = new Scanner(System.in);
        int resultado_opcao_menu;

        

        System.out.println("*STANLEY* - Sistema de Controle de Aquisição\n 1 - Entrar com um usuario\n 2 - Alterar o usuario \n 3- Sair");
        resultado_opcao_menu = opcao.nextInt();
        if(resultado_opcao_menu == 1){
            login();
        }
        else if(resultado_opcao_menu == 3){
            System.exit(0);
        }
    }

    public static void login(){
        Scanner opcao_usuario = new Scanner(System.in);
        
        //variaveis
        int resultado_opcao_menu_usuario;
        int matricula = 1;
        String nome = "Silvio Tramonti Costa";
        String categoria = "Admistrador";

          System.out.println("Matricula: " +matricula);
          System.out.println("Nome: " +nome);
          System.out.println("Categoria: " +categoria);             
          System.out.println("----------------");
          System.out.println("Digite o numero da matricula ou VOLTAR para retornar ao menu:");
          resultado_opcao_menu_usuario = opcao_usuario.nextInt();
        
          if(resultado_opcao_menu_usuario == 0) {
              Menu();
          }

    }

}