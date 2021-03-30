/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slc.modelo;

import java.util.Objects;

/**
 *
 * @author Celina Sebastião
 */
public class Usuario {

    private Integer pkUsuario;
    private String username;
    private String senha;
    private Pessoa fkPessoa;

    public Usuario() {
    }

    public Integer getPkUsuario() {
        return pkUsuario;
    }

    public void setPkUsuario(Integer pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(Pessoa fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.pkUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.pkUsuario, other.pkUsuario)) {
            return false;
        }
        return true;
    }

}
