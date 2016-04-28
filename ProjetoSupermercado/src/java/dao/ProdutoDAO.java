package dao;

import factory.ConnectionFactory;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {

   

        public void salvar(Produto d)
                throws ClassNotFoundException, SQLException {
            String sql = "INSERT INTO produto (nome,quantidade,preco,id_fornecedor) VALUES(?,?,?,?)";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getQtd());
            stmt.setDouble(3, d.getPreco());
            stmt.setInt(4, d.getId_for());
            stmt.execute();
            stmt.close();

        }
       /* public static void main(String [] args)
         throws ClassNotFoundException, SQLException{
         DadosProduto f1 = new DadosProduto();
         f1.setNome("Produto 1");
         f1.setQuantidade(1);
         f1.setPreco((float) 30.50);
         DadosProduto f2 = new DadosProduto();
         f2.setNome("Produto 1");
         f2.setQuantidade(2);
         f2.setPreco((float) 31.50);
        
        
         ProdutoDAO fdao = new ProdutoDAO();
         try{
         fdao.salvar(f1);
         fdao.salvar(f2);
         System.out.println("Produto cadastrado com sucesso!");
         }catch(SQLException e){
         System.out.println("Erro ao tentar salvar o Produto");
         e.printStackTrace();
         }
         */

       public void excluir(Produto d)
                throws ClassNotFoundException, SQLException {
            String sql = "DELETE FROM produto WHERE id_produto = ?";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, d.getId_pro());
            stmt.execute();
            stmt.close();
        }
        /* public static void main(String [] args)throws ClassNotFoundException, SQLException{
         DadosProduto f1 = new DadosProduto();
         f1.setId_produto(1);
        
         ProdutoDao fdao = new ProdutoDao();
         try{
         fdao.excluir(f1);
         }catch(SQLException e){
         System.out.println("Erro ao excluir o produto.");
         e.printStackTrace();
         }
        
         }*/

        public void editar(Produto d) throws ClassNotFoundException, SQLException {
            String sql = "UPDATE produto SET nome = ?, quantidade = ?, preco = ?,id_fornecedor=? WHERE id_produto = ?";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getQtd());
            stmt.setDouble(3, d.getPreco());
            stmt.setInt(4, d.getId_for());
            stmt.setInt(5, d.getId_pro());
            stmt.execute();
            stmt.close();
        }

        /*public static void main(String [] args)throws ClassNotFoundException, SQLException{
         DadosProduto f1 = new DadosProduto();
         f1.setId_produto(2);
         f1.setNome("Produto 2 editado");
       
         ProdutoDao fdao = new ProdutoDao();
        
         try{
         fdao.editar(f1);
         System.out.println("Produto alterado com sucesso!");
         }catch(SQLException e){
         System.out.println("Erro ao editar o produto!");
         e.printStackTrace();
         }
         }*/
        
        /* public Produto pesquisarProduto(Produto p) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM produto WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, p.getId());

        ResultSet rs = stmt.executeQuery();

        Produto retorno = null;

        if (rs.next()) {
            retorno = new Produto();
            retorno.setId(rs.getInt("id"));
            retorno.setRazaoSocial(rs.getString("razao_social"));

        }
        return retorno;
    }
         */
        public ArrayList<Produto> lista() throws SQLException, ClassNotFoundException {
            String sql = "select produto.id_produto, produto.nome, produto.qtd, produto.preco"
                    + ", fonecedor.nome from produto inner join fornecedor on fornecedor.id_fornecedor = produto.id_fornecedor";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Produto> lista = new ArrayList<Produto>();

            while (rs.next()) {
                Produto e = new Produto();
                e.setId_pro(rs.getInt("id_produto"));
                e.setNome(rs.getString("nome"));
                e.setQtd(rs.getInt("qtd"));
                e.setPreco(rs.getDouble("preco"));
                e.setNome_for((rs.getString("nome")));
                lista.add(e);
            }
            return lista;
        }
    }
