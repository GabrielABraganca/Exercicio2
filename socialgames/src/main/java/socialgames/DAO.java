package socialgames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    protected Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "camisasfutebol";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "postgres";
        String password = "1705";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null);
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    // Método para adicionar uma camisa de time de futebol
    public boolean adicionarCamisa(CamisaTimeFutebol camisa) {
        boolean status = false;
        try {
            String sql = "INSERT INTO principal (modelo, cor, tamanho, fabricante) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, camisa.getModelo());
            statement.setString(2, camisa.getCor());
            statement.setString(3, camisa.getTamanho());
            statement.setString(4, camisa.getFabricante());

            int linhasAfetadas = statement.executeUpdate();
            status = linhasAfetadas > 0;

            statement.close();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar camisa de time de futebol: " + e.getMessage());
        }
        return status;
    }

    // Método para remover uma camisa de time de futebol pelo ID
    public boolean removerCamisa(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM principal WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);

            int linhasAfetadas = statement.executeUpdate();
            status = linhasAfetadas > 0;

            statement.close();
        } catch (SQLException e) {
            System.err.println("Erro ao remover camisa de time de futebol: " + e.getMessage());
        }
        return status;
    }

    // Método para listar todas as camisas de time de futebol
    public List<CamisaTimeFutebol> listarCamisas() {
        List<CamisaTimeFutebol> camisas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM principal";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String modelo = resultSet.getString("modelo");
                String cor = resultSet.getString("cor");
                String tamanho = resultSet.getString("tamanho");
                String fabricante = resultSet.getString("fabricante");

                CamisaTimeFutebol camisa = new CamisaTimeFutebol(id, modelo, cor, tamanho, fabricante);
                camisas.add(camisa);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar camisas de time de futebol: " + e.getMessage());
        }
        return camisas;
    }
}
