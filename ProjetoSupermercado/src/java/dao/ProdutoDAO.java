package dao;

import br.com.projetoLivraria.factory.ConnectionFactory;
import br.com.projetoLivraria.model.DadosProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {

   

        public void salvar(DadosProduto d)
                throws ClassNotFoundException, SQLException {
            String sql = "INSERT INTO produto (nome,quantidade,preco,id_produto) VALUES(?,?,?,?)";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getQuantidade());
            stmt.setFloat(3, d.getPreco());
            stmt.setInt(4, d.getId_produto());
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

       public void excluir(DadosProduto d)
                throws ClassNotFoundException, SQLException {
            String sql = "DELETE FROM produto WHERE id_produto = ?";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, d.getId_produto());
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

        public void editar(DadosProduto d) throws ClassNotFoundException, SQLException {
            String sql = "UPDATE produto SET nome = ?, quantidade = ?, preco = ?,id_produto=? WHERE id_produto = ?";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getQuantidade());
            stmt.setFloat(3, d.getPreco());
            stmt.setInt(4, d.getCod_fornecedor());
            stmt.setInt(5, d.getId_produto());
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
        public ArrayList<DadosProduto> lista() throws SQLException, ClassNotFoundException {
            String sql = "select produto.id_produto, produto.nome, produto.quantidade, produto.preco, editora.razao_social from produto inner join editora on editora.id_editora = produto.cod_editora";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            ArrayList<DadosProduto> lista = new ArrayList<DadosProduto>();

            while (rs.next()) {
                DadosProduto e = new DadosProduto();
                e.setId_produto(rs.getInt("id_produto"));
                e.setNome(rs.getString("nome"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setPreco(rs.getFloat("preco"));
                e.setFornecedor(rs.getString("razao_social"));
                lista.add(e);
            }
            return lista;
        }
    }
