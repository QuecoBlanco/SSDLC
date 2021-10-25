/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rbac;

/**
 *
 * @author Queco
 */
public class Usuario {
    String nom;
    String rol;

    public Usuario(String nom, String rol) {
        this.nom = nom;
        this.rol = rol;
    }

    public Usuario(){
        
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
