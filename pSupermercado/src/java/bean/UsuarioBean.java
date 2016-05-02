package org.primefaces.showcase.view.overlay;
 
import model.Usuario;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandlink.CommandLink;
 
import org.primefaces.context.RequestContext;
 
@ManagedBean (name = "MBUsuario")
public class UsuarioBean {
     
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

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public void login(ActionEvent event) throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(nome != null && nome.equals(nome) && senha != null && senha.equals(senha)) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("principal_funcionario.xhtml");
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo ", nome);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }   
}