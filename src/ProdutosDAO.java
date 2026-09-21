import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) {

        if (produto.getNome() == null
                || produto.getNome().trim().isEmpty()
                || produto.getValor() <= 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "Preencha corretamente os campos."
            );

            return false;
        }

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            prep.close();
            conn.close();

            JOptionPane.showMessageDialog(
                    null,
                    "Produto cadastrado com sucesso!"
            );

            return true;

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao cadastrar produto: " + erro.getMessage()
            );

            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }
}