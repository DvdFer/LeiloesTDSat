import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private JTable tabelaVendas;
    private DefaultTableModel modeloTabela;
    private JButton btnVoltar;

    public vendasVIEW() {

        setTitle("Vendas");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Produtos Vendidos");
        titulo.setFont(new Font("Lucida Fax", Font.PLAIN, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Valor", "Status"}, 0
        );

        tabelaVendas = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabelaVendas);

        btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
        });

        JPanel painelBotao = new JPanel();
        painelBotao.add(btnVoltar);

        setLayout(new BorderLayout());

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);

        listarVendas();
    }

    private void listarVendas() {

        modeloTabela.setRowCount(0);

        ProdutosDAO produtosdao = new ProdutosDAO();

        ArrayList<ProdutosDTO> vendidos =
                produtosdao.listarProdutosVendidos();

        for (int i = 0; i < vendidos.size(); i++) {

            modeloTabela.addRow(new Object[]{
                vendidos.get(i).getId(),
                vendidos.get(i).getNome(),
                vendidos.get(i).getValor(),
                vendidos.get(i).getStatus()
            });
        }
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new vendasVIEW().setVisible(true);
        });
    }
}