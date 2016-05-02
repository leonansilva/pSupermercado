package dao;

import factory.ConnectionFactory;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
        public void salvarProduto(Produto d)
                throws ClassNotFoundException, SQLException {
            String sql = "INSERT INTO produto (nome,qtd,preco,id_fornecedor) VALUES(?,?,?,?)";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, d.getNome_pro());
            stmt.setInt(2, d.getQtd());
            stmt.setDouble(3, d.getPreco());
            stmt.setInt(4, d.getId_for());
            stmt.execute();
            stmt.close();

        }  
        public void excluirProduto(Produto d)
                throws ClassNotFoundException, SQLException {
            String sql = "DELETE FROM produto WHERE id_produto = ?";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, d.getId_pro());
            stmt.execute();
            stmt.close();
        }
        public void editarProduto(Produto d) throws ClassNotFoundException, SQLException {
            String sql = "UPDATE produto SET nome = ?, qtd = ?, preco = ?,id_fornecedor=? WHERE id_produto = ?";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, d.getNome_pro());
            stmt.setInt(2, d.getQtd());
            stmt.setDouble(3, d.getPreco());
            stmt.setInt(4, d.getId_for());
            stmt.setInt(5, d.getId_pro());
            stmt.execute();
            stmt.close();
        }

        
        public ArrayList<Produto> listaProduto() throws SQLException, ClassNotFoundException {
            String sql = "select produto.id_produto, produto.nome, produto.qtd,produto.preco,\n" +
                "supermercado.fornecedor.nome_fornecedor\n" +
                "from produto inner join fornecedor\n" +
                "on fornecedor.id_fornecedor = produto.id_fornecedor";

            Connection connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Produto> lista = new ArrayList<Produto>();

            while (rs.next()) {
                Produto e = new Produto();
                e.setId_pro(rs.getInt("id_produto"));
                e.setNome_pro(rs.getString("nome"));
                e.setQtd(rs.getInt("qtd"));
                e.setPreco(rs.getDouble("preco"));
                e.setNome_for((rs.getString("nome_fornecedor")));
                lista.add(e);
            }
            return lista;
        }
    }
