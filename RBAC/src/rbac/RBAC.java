/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rbac;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Queco
 */
public class RBAC {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        Usuario u = new Usuario();
        boolean salir = false;
        ArrayList<Usuario> a = new ArrayList<Usuario>();
        
        do{
            System.out.println("Introduce nombre de usuario:");
            String nom = s.nextLine();
            System.out.println("Introduce el rol del usuario (admin, gest o asist):");
            String rol = s.nextLine();
            u.setNom(nom);
            u.setRol(rol);
        
            String r = DeterminarRol(u);
            a.add(u);
            
            if(null != r)switch (r) {
                case "admin":
                    VistaAdmin(a, u);
                    salir=true;
                    break;
                case "gest":
                    VistaGestor(a, u);
                    salir=true;
                    break;
                case "asist":
                    VistaAsistente(a, u);
                    salir=true;
                    break;
                default:
                    System.out.println("Usuario no valido");
                    break;
            }
        }while(!salir);
    }
    
    public static String DeterminarRol(Usuario usuario){
            
        String rol = usuario.getRol();
        return rol;   
    }
    public static void VistaAdmin(ArrayList<Usuario> a, Usuario u){
        
        Scanner s = new Scanner(System.in);
        Usuario p = new Usuario();
        boolean salir = false;
        int opcion;
        
        do{
            System.out.println("1-Agregar Usuario");
            System.out.println("2-Modificar Usuario");
            System.out.println("3-Eliminar Usuario");
            System.out.println("4-Listar Usuario\n");
            System.out.println("5-Salir");
            /*if(a.size()>1){
                System.out.println("6-Cambiar de Usuario");
            }*/
            System.out.println("Elige la opcion:");
            opcion=s.nextInt();
            String r = DeterminarRol(u);

             switch(opcion){
                   case 1:
                       System.out.println("Has seleccionado la opcion 1");
                       if(!"admin".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                            AgregarUsuario(a, u);
                       }
                       break;
                   case 2:
                       System.out.println("Has seleccionado la opcion 2");
                       if(!"admin".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           
                       }
                       break;
                    case 3:
                       System.out.println("Has seleccionado la opcion 3");
                       if(!"admin".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                  
                       }
                       break;
                    case 4:
                       System.out.println("Has seleccionado la opcion 3");
                       if(!"admin".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           ListarUsuario(a, u);
                       }
                       break;
                    /*case 6:
                       if(a.size()<1){
                           System.out.println("Ningun Usuario a quien cambiar");
                       }else{
                           p=CambiarUsuario(a, u);
                           u=p;
                       }
                       break;*/
                    case 5:
                       salir=true;
                       break;
                    default:
                       System.out.println("Solo n??meros entre 1 y 5");
                       
               }
        }while(!salir);
        
    }
    public static void VistaGestor(ArrayList<Usuario> a, Usuario u){
        Scanner s = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        String r = DeterminarRol(u);
        
        do{
            System.out.println("1-Agregar Usuario");
            System.out.println("2-Modificar Usuario");
            System.out.println("3-Listar Usuario");
            System.out.println("4-Salir");

            System.out.println("Elige la opcion:");
            opcion=s.nextInt();

             switch(opcion){
                   case 1:
                       System.out.println("Has seleccionado la opcion 1");
                       if(!"gest".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           AgregarUsuario(a, u);
                       }
                       break;
                   case 2:
                       System.out.println("Has seleccionado la opcion 2");
                       if(!"gest".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           
                       }
                       break;
                    case 3:
                       System.out.println("Has seleccionado la opcion 3");
                       if(!"gest".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           ListarUsuario(a, u);
                       }
                       break;
                    case 4:
                       salir=true;
                       break;
                    default:
                       System.out.println("Solo n??meros entre 1 y 4");
               }
        }while(!salir);
    }
    public static void VistaAsistente(ArrayList<Usuario> a, Usuario u){
        Scanner s = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        String r = DeterminarRol(u);
        
        do{
            System.out.println("1-Listar Usuarios");
            System.out.println("2-Salir");

            System.out.println("Elige la opcion:");
            opcion=s.nextInt();

             switch(opcion){
                   case 1:
                       System.out.println("Has seleccionado la opcion 1");
                       if(!"asist".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           ListarUsuario(a, u);
                       }
                       break;
                    case 2:
                       salir=true;
                       break;
                    default:
                       System.out.println("Solo n??meros entre 1 y 2");
               }
        }while(!salir);
        
        
    }
    
    public static void AgregarUsuario(ArrayList<Usuario> a, Usuario u){
        Scanner s = new Scanner(System.in);
        Usuario p = new Usuario();
        
        System.out.println("Introduce nombre de usuario:");
        String nom = s.nextLine();
        System.out.println("Introduce el rol del usuario (admin, gest o asist):");
        String rol = s.nextLine();
        p.setNom(nom);
        p.setRol(rol);
        a.add(p);
    }
    public static void ListarUsuario(ArrayList<Usuario> a, Usuario u){
        for (Usuario usuario : a) {
            System.out.println(usuario.nom +" "+ usuario.rol);
        }
        
    }
    
    //public static Usuario CambiarUsuario(ArrayList<Usuario> a, Usuario u){
        
    //}
    
}
