package bean;

import model.fornecedor;
import dao.FornecedorDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.JSFUtil;


@ManagedBean(name = "MBFornecedor")
// escopo: session(criado quando o tomcat é iniciadp e finalizando quando o tomcat)
// escopo: request (a cada clique é instanciado)
@ViewScoped
public class FornecedorBean{
    private ArrayList<fornecedor> itens;
    private fornecedor fornecedor;

        public ArrayList<fornecedor> getItens() {
            return itens;
        }

        public void setItens(ArrayList<fornecedor> itens) {
            this.itens = itens;
        }

        public fornecedor getFornecedor() {
            return fornecedor;
        }

        public void setFornecedor(fornecedor fornecedor) {
            this.fornecedor = fornecedor;
        }

   

    
    
    @PostConstruct //método chamado antes da tag desenhada
    public void prepararPesquisa(){
        try {
            FornecedorDAO dao = new FornecedorDAO();
            try {
                itens = dao.listaFornecedor();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(ClassCastException ex){
            ex.printStackTrace();
        }
    } 
        public  void prepararFornecedor(){
        fornecedor = new fornecedor();
    }

    public void novoFornecedor(){
        try {
            FornecedorDAO dao = new FornecedorDAO();
            dao.salvarFornecedor(fornecedor);
            itens = dao.listaFornecedor();
            JSFUtil.addMsgSucesso("Fornecedor salvo com sucesso.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }
}
    public void excluiFornecedor(){
        FornecedorDAO dao = new FornecedorDAO();
        try {
            dao.excluirFornecedor(fornecedor);
            itens = dao.listaFornecedor();
            JSFUtil.addMsgSucesso("Fornecedor excluído com sucesso.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }
    }
    public void alteraFornecedor(){
        FornecedorDAO dao = new FornecedorDAO();
        try {
            dao.alterarFornecedor(fornecedor);
            itens = dao.listaFornecedor();
            JSFUtil.addMsgSucesso("Fornecedor alterado com sucesso.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
           JSFUtil.addMsgErro(ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }
    }
    }