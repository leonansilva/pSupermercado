
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.ProdutoDAO;
import model.Produto; 
import util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
    private ArrayList<Produto> itens;
    private Produto produto;

    public ArrayList<Produto> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Produto> itens) {
        this.itens = itens;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    @PostConstruct
    public void prepararPesquisa() {
        ProdutoDAO dao = new ProdutoDAO();
        try {
            itens = dao.listaProduto();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void prepararProduto() {
        produto = new Produto();
    }

    public void novoProduto() throws ClassNotFoundException {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            dao.salvarProduto(produto);
            itens = dao.listaProduto();
            JSFUtil.addMsgSucesso("Produto salvo com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        }

    }
    //metodo excluir produto

    public void excluirProduto() throws ClassNotFoundException {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            dao.excluirProduto(produto);
            itens = dao.listaProduto();
            JSFUtil.addMsgSucesso("Produto excluido com sucesso.");
        } catch (SQLException e) {
           e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        }
        }
    //metodo editar produto
    public void editarProduto() throws ClassNotFoundException {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            dao.editarProduto(produto);
            itens = dao.listaProduto();
            JSFUtil.addMsgSucesso("Produto editado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        }
        }
}
