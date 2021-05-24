package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.ProdutoModel;
import Util.Conexao;

public class ProdutoDao {
    
    public ProdutoDao()
    {

    }

    public void cadastrar(ProdutoModel prod)
    {
        try {
            String INSERT = "INSERT INTO produtos(nome,descricao,qtd,preco,valorTotal) values(?,?,?,?,?)";
            PreparedStatement st = Conexao.getConnection().prepareStatement(INSERT);
            st.setString(1, prod.getNome());
            st.setString(2, prod.getDescricao());
            st.setInt(3, prod.getQtd());
            st.setDouble(4, prod.getPreco());
            st.setDouble(5, prod.getPreco() * prod.getQtd());
            st.execute();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Registro Salvo com sucesso", "Sucesso", 1, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\ok.png"));
        } catch (Exception e) {
            //TODO: handle exception
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar no banco de dados", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
        }
    }

    public boolean existeProduto(ProdutoModel produto)
    {
        boolean status = false;
        try {
            String SELECT = "SELECT nome FROM produtos where nome = ?";
            PreparedStatement st = Conexao.getConnection().prepareStatement(SELECT);
            st.setString(1, produto.getNome());
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                status = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
            JOptionPane.showMessageDialog(null, "erro ao buscar Email do usuario", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
        }
        return status;
    }

    public String ProximoProduto()
    {
        String SELECT = "SELECT * FROM produtos order by id desc limit 1";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SELECT);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return (Integer.parseInt(rs.getString("id")) + 1) + "";
            } else {
                return "1";
            }
        } catch (Exception e) {
            //TODO: handle exception
            JOptionPane.showMessageDialog(null, "erro ao buscar com o registro", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            return "0";
        }
    }
}
