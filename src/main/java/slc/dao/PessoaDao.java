/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import slc.modelo.Pessoa;
import slc.modelo.Sexo;
import slc.util.ConexaoDB;

/**
 *
 * @author Celina Sebastião
 */
public class PessoaDao {

    private String INSERT = "INSERT INTO `pt_db`.`pessoa`(`pk_pessoa`,`nome_completo`,`data_nascimento`,`endereco`,`foto`,`bi`,`email`,`sexo`)VALUES(?,?,?,?,?,?,?,?)";
    private String EDIT = "UPDATE `pt_db`.`pessoa` SET `pk_pessoa` = ?, `nome_completo` = ?, `data_nascimento` = ?, `endereco` = ?, `foto` = ?, `bi` = ?, `email` = ?, `sexo` = ? WHERE `pk_pessoa` = ? ";
    private String DELETE = "DELETE FROM pessoa WHERE pk_pessoa = ?";
    private String FIND_ALL = "SELECT p.pk_pessoa,p.nome_completo,p.data_nascimento,p.endereco,p.foto,p.bi,p.email,p.sexo FROM pessoa p";
    private String FIND_BY_NAME = "SELECT p.pk_pessoa,p.nome_completo,p.data_nascimento,p.endereco,p.foto,p.bi,p.email,p.sexo FROM pessoa p WHERE p.nome_completo LIKE ? ";
    private final String FIND_BY_ID = "SELECT * FROM pessoa WHERE pk_pessoa = ?";
    private final String LAST_INSERT = "SELECT max(pk_pessoa) FROM pt_db.pessoa";

    ConexaoDB conexao = new ConexaoDB();

    public void insert(Pessoa p) {

        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getNomeCompleto());
            ps.setDate(2, new java.sql.Date(p.getDataNascimento().getTime()));
            ps.setString(3, p.getEndereco());
            ps.setString(4, p.getFoto());
            ps.setString(5, p.getBi());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getSexo().getAbreviatura());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Inserir os dados na Base de Dados: " + e.getLocalizedMessage());
        }
    }

    public int lastInserted() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        int idPessoa = 0;

        try {
            conn = conexao.ligarBB();
            ps = conn.prepareStatement(LAST_INSERT);
            rs = ps.executeQuery();

            if (rs.next()) {
                idPessoa = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return idPessoa;
    }

    public void update(Pessoa p) {

        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(EDIT);
            ps.setString(1, p.getNomeCompleto());
            ps.setDate(2, new java.sql.Date(p.getDataNascimento().getTime()));
            ps.setString(3, p.getEndereco());
            ps.setString(4, p.getFoto());
            ps.setString(5, p.getBi());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getSexo().getAbreviatura());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao actualizar os dados na Base de Dados: " + e.getLocalizedMessage());
        }
    }

    public void delete(Pessoa p) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, p.getPk_pessoa());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());
        } finally {

            ConexaoDB.fecharConexao(conn, ps);
        }

    }

    public List<Pessoa> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            conn = conexao.ligarBB();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setPk_pessoa(rs.getInt("pk_pessoa"));
                pessoa.setNomeCompleto(rs.getString("nome_completo"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setFoto(rs.getString("foto"));
                pessoa.setBi(rs.getString("bi"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSexo(Sexo.getAbreviatura(rs.getString("sexo")));
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return pessoas;
    }

    public List<Pessoa> findByNome(String valor) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            conn = conexao.ligarBB();

            ps = conn.prepareStatement(FIND_BY_NAME);
            ps.setString(1, "%" + valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setPk_pessoa(rs.getInt("pk_pessoa"));
                pessoa.setNomeCompleto(rs.getString("nome_completo"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setFoto(rs.getString("foto"));
                pessoa.setBi(rs.getString("bi"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSexo(Sexo.getAbreviatura(rs.getString("sexo")));
                pessoas.add(pessoa);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return pessoas;
    }

    public List<Pessoa> findById(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            conn = conexao.ligarBB();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setPk_pessoa(rs.getInt("pk_pessoa"));
                pessoa.setNomeCompleto(rs.getString("nome_completo"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setFoto(rs.getString("foto"));
                pessoa.setBi(rs.getString("bi"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSexo(Sexo.getAbreviatura(rs.getString("sexo")));
                pessoas.add(pessoa);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return pessoas;
    }

}
