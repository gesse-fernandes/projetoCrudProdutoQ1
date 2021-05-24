package Controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DAO.ProdutoDao;
import Model.ProdutoModel;

public class ProdutoController {
    ProdutoDao prod;
    public ProdutoController()
    {
        prod = new ProdutoDao();
    }

    public boolean verificaDados(ProdutoModel produto)
    {
        boolean x = true;
        if (produto.getNome().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Preencha o Campo Nome", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            x = false;
        }else if(produto.getNome().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "O Campo Nome não pode Ser Nulo", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            x = false;
        }
        else if(prod.existeProduto(produto))
        {
            JOptionPane.showMessageDialog(null, "Já existe  Esse produto cadastre outro", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            x = false;
        }
        else if(produto.getDescricao().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Preencha o Campo Descrição", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            x = false;
        }else if(produto.getDescricao().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "O Campo Descrição não pode Ser Nulo", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            x = false;
        }else if(produto.getQtd() == 0)
        {
            JOptionPane.showMessageDialog(null, "Preencha o Campo Quantidade", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            x = false;
        }else if(produto.getPreco() == 0)
        {
            JOptionPane.showMessageDialog(null, "Preencha o Campo Preço", "Erro", 0, new ImageIcon(
                    "C:\\Users\\gesse\\OneDrive\\Documentos\\NetBeansProjects\\projetoCrudProdutoQ1\\src\\Imagens\\btn_sair.png"));
            x = false;
        } else {
            x = true;
        }
        if(x == true)
        {
            prod.cadastrar(produto);
            return x;
        }
        return x;
       
    }
    public String proximoProduto()
    {
        return prod.ProximoProduto();
    }
}
