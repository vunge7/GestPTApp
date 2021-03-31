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
import slc.modelo.Usuario;
import slc.util.ConexaoDB;

/**
 *
 * @author Celina Sebastião
 */
public class UsuarioDao {

    private final String LOGIN = "SELECT * FROM usuario WHERE user_name = ? and senha = ?";
    private String INSERT = "INSERT INTO `pt_db`.`usuario` (`pk_usuario`, `user_name`, `senha`, `fk_pessoa`) VALUES (?,?,?,?)";
    private String EDIT = "UPDATE `pt_db`.`usuario` SET `pk_usuario` = ?, `user_name` = ?, `senha` = ?,`fk_pessoa` = ? WHERE `pk_usuario` = ?";
    private String DELETE = "DELETE FROM `pt_db`.`usuario` WHERE `pk_usuario` = ?";
    private String FIND_ALL = "SELECT p.pk_pessoa,p.nome_completo,p.data_nascimento,p.endereco,p.foto,p.bi,p.email,p.sexo,u.pk_usuario,u.username, u.senha FROM usuario u INNER JOIN pessoa p m ON u.pk_usuario = p.pk_pessoa";
    private String FIND_BY_USER = " SELECT p.pk_pessoa,p.nome_completo,p.data_nascimento,p.endereco,p.foto,p.bi,p.email,p.sexo,u.pk_usuario,u.username, u.senha FROM usuario u INNER JOIN pessoa p m ON u.pk_usuario = p.pk_pessoa WHERE u.username LIKE ?";
    private String FIND_BY_ID = " SELECT p.pk_pessoa,p.nome_completo,p.data_nascimento,p.endereco,p.foto,p.bi,p.email,p.sexo,u.pk_usuario,u.username, u.senha FROM usuario u INNER JOIN pessoa p m ON u.pk_usuario = p.pk_pessoa WHERE u.pk_usuario = ?";

    ConexaoDB conexao = new ConexaoDB();

    public void insert(Usuario u) {

        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(INSERT);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getSenha());
            ps.setInt(3, u.getFkPessoa().getPk_pessoa());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Inserir os dados na Base de Dados: " + e.getLocalizedMessage());
        }
    }

    public void edit(Usuario u) {

        PreparedStatement ps;
        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(EDIT);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getSenha());
            ps.setInt(3, u.getFkPessoa().getPk_pessoa());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao Inserir os dados na Base de Dados: " + e.getLocalizedMessage());
        }
    }

    public void delete(Usuario u) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            Connection con = conexao.ligarBB();
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, u.getPkUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());
        } finally {

            ConexaoDB.fecharConexao(conn, ps);
        }

    }

    public List<Usuario> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();

        try {
            conn = conexao.ligarBB();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {

                usuario.setPkUsuario(rs.getInt("fk_usuario"));
                usuario.setUsername(rs.getString("user_name"));
                usuario.setSenha(rs.getString("senha"));

                Pessoa pessoa = new Pessoa();
                pessoa.setNomeCompleto(rs.getString("nome_completo"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setFoto(rs.getString("foto"));
                pessoa.setBi(rs.getString("bi"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSexo(Sexo.getAbreviatura(rs.getString("sexo")));

                usuario.setFkPessoa(pessoa);

            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return usuarios;
    }

    public List<Usuario> findByUser(String valor) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        try {
            conn = conexao.ligarBB();

            ps = conn.prepareStatement(FIND_BY_USER);
            ps.setString(1, "%" + valor + "%");
            rs = ps.executeQuery();

            while (rs.next()) {

                usuario.setPkUsuario(rs.getInt("fk_usuario"));
                usuario.setUsername(rs.getString("user_name"));
                usuario.setSenha(rs.getString("senha"));

                Pessoa pessoa = new Pessoa();
                pessoa.setNomeCompleto(rs.getString("nome_completo"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setFoto(rs.getString("foto"));
                pessoa.setBi(rs.getString("bi"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSexo(Sexo.getAbreviatura(rs.getString("sexo")));

                usuario.setFkPessoa(pessoa);
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return usuarios;
    }

    public List<Usuario> findById(Integer valor) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        try {
            conn = conexao.ligarBB();
            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, valor);
            rs = ps.executeQuery();

            while (rs.next()) {

                usuario.setPkUsuario(rs.getInt("fk_usuario"));
                usuario.setUsername(rs.getString("user_name"));
                usuario.setSenha(rs.getString("senha"));

                Pessoa pessoa = new Pessoa();
                pessoa.setNomeCompleto(rs.getString("nome_completo"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setFoto(rs.getString("foto"));
                pessoa.setBi(rs.getString("bi"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSexo(Sexo.getAbreviatura(rs.getString("sexo")));

                usuario.setFkPessoa(pessoa);
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConexaoDB.fecharConexao(conn);
        }
        return usuarios;
    }

}
