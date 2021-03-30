/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slc.dao;

/**
 *
 * @author Celina Sebastião
 */
public class UsuarioDao {
    
    private final String LOGIN = "SELECT * FROM usuario WHERE user_name = ? and senha = ?";
    
}
