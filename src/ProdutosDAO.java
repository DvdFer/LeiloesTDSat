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

        listagem.clear();

        String sql = "SELECT id, nome, valor, status FROM produtos";

        try {
            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

            resultset.close();
            prep.close();
            conn.close();

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao listar produtos: " + erro.getMessage()
            );
        }

        return listagem;
    }
}