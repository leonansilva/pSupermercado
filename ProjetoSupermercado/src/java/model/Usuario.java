package model;
public class Usuario {
    private int id_user;
    private String nome;
    private String senha;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
//