import java.util.ArrayList;

public class ListaUsuarios {
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public void preencheUsuarios(){
        Usuario user0 = new Usuario("Gustavo", "Melo", "0", true, "RH");
        listaUsuarios.add(user0);
        Usuario user1 = new Usuario("Mateus", "Souza", "1", false, "Producao");
        listaUsuarios.add(user1);
        Usuario user2 = new Usuario("Leonardo", "Santos", "2", true, "TI");
        listaUsuarios.add(user2);
        Usuario user3 = new Usuario("Angelo", "Escolto", "3", false, "TI");
        listaUsuarios.add(user3);
        Usuario user4 = new Usuario("Silvio", "Luiz", "4", false, "Manutencao");
        listaUsuarios.add(user4);
        Usuario user5 = new Usuario("Alberto", "Carvalho", "5", false, "Engenharia");
        listaUsuarios.add(user5);
        Usuario user6 = new Usuario("Maria", "Silva", "6", false, "Producao");
        listaUsuarios.add(user6);
        Usuario user7 = new Usuario("Carla", "Perez", "7", false, "Producao");
        listaUsuarios.add(user7);
        Usuario user8 = new Usuario("Julio", "Andrade", "8", false, "TI");
        listaUsuarios.add(user8);
        Usuario user9 = new Usuario("Lucas", "Silveira", "9", false, "RH");
        listaUsuarios.add(user9);
        Usuario user10 = new Usuario("Marcos", "Pereira", "10", false, "Engenharia");
        listaUsuarios.add(user10);
        Usuario user11 = new Usuario("Fabiola", "Borba", "11", false, "Producao");
        listaUsuarios.add(user11);
        Usuario user12 = new Usuario("Jefersson", "Castro", "12", false, "Manutencao");
        listaUsuarios.add(user12);
        Usuario user13 = new Usuario("Nilda", "Silveira", "13", false, "RH");
        listaUsuarios.add(user13);
        Usuario user14 = new Usuario("Jairo", "Bastos", "14", false, "Producao");
        listaUsuarios.add(user14);
    }


    public void getUsuarios(){
        int qtd_users = listaUsuarios.size();
        Usuario user = null;
        for(int i = 0; i < qtd_users;i++){
            user = listaUsuarios.get(i);
            
        }
    }

    public Usuario buscaPorMatricula(String matricula)
    {
        int qtd_users = listaUsuarios.size();
        Usuario user = null;
        for(int i = 0; i < qtd_users; i++){
            user = listaUsuarios.get(i);
            if(user.getMatricula().equals(matricula))
            {
                System.out.println(user.toString());
                return user;
            }
        }
        return null;
    }

    //Adcionada visando funcao em departamento
    public ArrayList<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }
}

    
