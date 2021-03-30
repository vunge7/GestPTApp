/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slc.mb;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

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
    public PessoaBean() {
    }
    
}
