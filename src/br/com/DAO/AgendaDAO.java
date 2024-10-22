package br.com.DAO;

import br.com.DTO.AgendaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {
    
    private Connection conexao;

    public AgendaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Método para inserir um novo evento
    public boolean inserirEvento(AgendaDTO agenda) {
        String sql = "INSERT INTO agenda (descricao, email, nome, data) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, agenda.getDescricao());
            pst.setString(2, agenda.getEmail());
            pst.setString(3, agenda.getNome());
            pst.setString(4, agenda.getData());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os eventos
    public List<AgendaDTO> listarEventos() {
        List<AgendaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM agenda";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AgendaDTO agenda = new AgendaDTO();
                agenda.setId_agenda(rs.getInt("id_agenda"));
                agenda.setDescricao(rs.getString("descricao"));
                agenda.setEmail(rs.getString("email"));
                agenda.setNome(rs.getString("nome"));
                agenda.setData(rs.getString("data"));
                lista.add(agenda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para atualizar um evento
    public boolean atualizarEvento(AgendaDTO agenda) {
        String sql = "UPDATE agenda SET descricao = ?, email = ?, nome = ?, data = ? WHERE id_agenda = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, agenda.getDescricao());
            pst.setString(2, agenda.getEmail());
            pst.setString(3, agenda.getNome());
            pst.setString(4, agenda.getData());
            pst.setInt(5, agenda.getId_agenda());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para excluir um evento
    public boolean excluirEvento(int id_agenda) {
        String sql = "DELETE FROM agenda WHERE id_agenda = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id_agenda);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
