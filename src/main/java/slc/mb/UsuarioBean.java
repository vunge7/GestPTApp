/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slc.mb;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import slc.dao.PessoaDao;
import slc.dao.UsuarioDao;
import slc.modelo.Pessoa;
import slc.modelo.Sexo;
import slc.modelo.Usuario;

/**
 *
 * @author Celina Sebastião
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    Pessoa pessoa = new Pessoa();
    Usuario usuario = new Usuario();

    PessoaDao pessoaDao = new PessoaDao();
    UsuarioDao usuarioDao = new UsuarioDao();

    List<Pessoa> listaPessoas = new ArrayList<>();
    List<Usuario> listaUsuarios = new ArrayList<>();

    String letrasNome, letrasUsername;
    Integer idPessoa, idUser;

    /**
     * Creates a new instance of UsuarioBean
     */

    @PostConstruct
    public void inicializar() {

        listaPessoas = pessoaDao.findAll();
        listaUsuarios = usuarioDao.findAll();

        listaPessoas = new ArrayList<>();
        listaUsuarios = new ArrayList();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getLetrasNome() {
        return letrasNome;
    }

    public void setLetrasNome(String letrasNome) {
        this.letrasNome = letrasNome;
    }

    public String getLetrasUsername() {
        return letrasUsername;
    }

    public void setLetrasUsername(String letrasUsername) {
        this.letrasUsername = letrasUsername;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String insert() {
        pessoaDao.insert(pessoa);
        usuarioDao.insert(usuario);
        pessoa = new Pessoa();
        usuario = new Usuario();
        return "usuario-lista?faces-redirect=true";
    }

    public String delete() {
        pessoaDao.delete(pessoa);
        usuarioDao.delete(usuario);
        pessoa = new Pessoa();
        usuario = new Usuario();
        return null;
    }

    public String preperEdit() {
        return "usuario-editar";
    }

    public String edit() {
        pessoaDao.update(pessoa);
        usuarioDao.edit(usuario);
        pessoa = new Pessoa();
        usuario = new Usuario();
        return "usuario-lista?faces-redirect=true";
    }

    public List<SelectItem> getOpSexos() {
        List<SelectItem> list = new ArrayList<>();
        for (Sexo sexo : Sexo.values()) {
            list.add(new SelectItem(sexo, sexo.getExtensao()));
        }
        return list;
    }

}
