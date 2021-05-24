package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.ProdutoModel;
import Util.Conexao;
import View.JFrameProdutos;

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
            st.setDouble(5, prod.getValorTotal());
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

    public void buscarProduto(String pesquisa, DefaultTableModel modelo) {
        try {
            String SELECT = "SELECT * from produtos where nome like '%" + pesquisa + "%'";
            PreparedStatement st = Conexao.getConnection().prepareStatement(SELECT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                        rs.getInt("qtd"), rs.getDouble("preco"), rs.getDouble("valorTotal") });
            }
        } catch (Exception e) {
            //TODO: handle exception
            JOptionPane.showMessageDialog(null, "erro ao buscar Produto\n"+e.getMessage(), "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
        }

    }
    
    public ProdutoModel preenche(int id) {
        ProdutoModel produto = new ProdutoModel();
        try {
            String SELECT = "SELECT * FROM produtos where id = ?";
            PreparedStatement st = Conexao.getConnection().prepareStatement(SELECT);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setValorTotal(rs.getDouble("valorTotal"));
            }
        } catch (Exception e) {
            //TODO: handle exception
            JOptionPane.showMessageDialog(null, "erro ao buscar produto", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\SofyDay\\src\\Imagens\\btn_sair.png"));
        }
        return produto;
    }
    
    public void editar(ProdutoModel prod) {
        try {
            String UPDATE = "UPDATE produtos set nome=?,descricao=?,qtd=?,preco=?,valorTotal=? where id = ?";
            PreparedStatement st = Conexao.getConnection().prepareStatement(UPDATE);
            st.setString(1, prod.getNome());
            st.setString(2, prod.getDescricao());
            st.setInt(3, prod.getQtd());
            st.setDouble(4, prod.getPreco());
            st.setDouble(5, prod.getValorTotal());
            st.setInt(6, prod.getId());
            st.execute();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Registro Editado com sucesso", "Sucesso", 1, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\ok.png"));
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Erro ao Editar no banco de dados", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
        }
    }

    public void deletar(ProdutoModel produto)
    {
        int delete = JOptionPane.showConfirmDialog(null, "Tem certeza que quer excluir?");
        if(delete == JOptionPane.YES_OPTION)
        {
            String DELETE = "DELETE FROM produtos where id  = ?";
            try {
                PreparedStatement st = Conexao.getConnection().prepareStatement(DELETE);
                st.setInt(1, produto.getId());
                st.executeUpdate();
                Conexao.getConnection().commit();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso", "Sucesso", 1, new ImageIcon(
                        "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\ok.png"));
            } catch (Exception e) {
                //TODO: handle exception
                JOptionPane.showMessageDialog(null, "Erro ao Editar no banco de dados" + e.getMessage(), "Erro", 0,
                        new ImageIcon(
                                "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            }
        }
    }
}
