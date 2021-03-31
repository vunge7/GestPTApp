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
import slc.modelo.Pessoa;
import slc.modelo.Sexo;

/**
 *
 * @author Celina Sebastião
 */
@Named(value = "pessoaBean")
@SessionScoped
public class PessoaBean implements Serializable {

    /**
     * Creates a new instance of PessoaBean
     */
    Pessoa pessoa = new Pessoa();
    PessoaDao pessoaDao = new PessoaDao();
    List<Pessoa> listaPessoas = new ArrayList<>();

    String letrasNome;
    Integer id;

    @PostConstruct
    public void inicializar() {

        listaPessoas = pessoaDao.findAll();

        listaPessoas = new ArrayList<>();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    public String insert() {
        pessoaDao.insert(pessoa);
        pessoa = new Pessoa();
        return "usuario-lista?faces-redirect=true";
    }

    public String delete() {
        pessoaDao.delete(pessoa);
        pessoa = new Pessoa();
        return null;
    }

    public String preperEdit() {
        return "usuario-editar";
    }

    public String edit() {
        pessoaDao.update(pessoa);
        pessoa = new Pessoa();
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
