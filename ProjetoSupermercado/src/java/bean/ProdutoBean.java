
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.projetoLivraria.dao.ProdutoDao;
import br.com.projetoLivraria.model.DadosProduto; 
import br.com.projetoLivraria.util.JSFUtil;
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
      private ArrayList<DadosProduto> itens;
    private DadosProduto produto;

    public ArrayList<DadosProduto> getItens() {
        return itens;
    }

    public void setItens(ArrayList<DadosProduto> itens) {
        this.itens = itens;
    }

    public DadosProduto getProduto() {
        return produto;
    }

    public void setProduto(DadosProduto produto) {
        this.produto = produto;
    }
    @PostConstruct
    public void prepararPesquisa() {
        ProdutoDao dao = new ProdutoDao();
        try {
            itens = dao.lista();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void prepararProduto() {
        produto = new DadosProduto();
    }

    public void novoProduto() throws ClassNotFoundException {
        try {
            ProdutoDao dao = new ProdutoDao();
            dao.salvar(produto);
            itens = dao.lista();
            JSFUtil.addMsgSucesso("produto salvo com sucesso.");
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
            ProdutoDao dao = new ProdutoDao();
            dao.excluir(produto);
            itens = dao.lista();
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
            ProdutoDao dao = new ProdutoDao();
            dao.editar(produto);
            itens = dao.lista();
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
