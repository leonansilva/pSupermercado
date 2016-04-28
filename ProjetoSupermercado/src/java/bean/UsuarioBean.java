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
     
    private String email;
     
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
         
        if(email != null && email.equals(email) && senha != null && senha.equals(senha)) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("editora.xhtml");
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", email);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }   
}