/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package auditoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 *
 * @author Queco
 */
public class Auditoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        Usuario u = new Usuario();
        boolean salir = false;
        int i;
        ArrayList<Usuario> a = new ArrayList<Usuario>();
        ArrayList<Usuario> arrayUsu = new ArrayList<Usuario>();
        ArrayList<String> arrayHash = new ArrayList<String>();
        ArrayList<String> arrayHash2 = new ArrayList<String>();
        
        String line = "";
        String separador = ",";
        Usuario t = null;
            
        ComprobarIntegridad(arrayHash, u);
        /*for(String st : arrayHash){
            System.out.println(st);
        }*/
        
        BufferedReader bufferLectura = null;
        try {
            bufferLectura = new BufferedReader(new FileReader("C:\\Users\\Queco\\Desktop\\csv.txt"));
            String linea = bufferLectura.readLine();
            
            while (linea != null) {
                String[] campos = linea.split(separador); 
                t = new Usuario();
                linea = bufferLectura.readLine();
                //System.out.println(campos[0]);
                t.setNom(campos[0]);
                t.setRol(campos[1]);
                //System.out.println(t.nom +","+ t.rol);   
                arrayUsu.add(t);
                //System.out.println(t.nom +","+ t.rol);   
            }
        } 
           catch (IOException e) {
                e.printStackTrace();
           }
            finally {
                if (bufferLectura != null) {
            try {
                bufferLectura.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
                }
            }
        //LeerFichero(arrayUsu, u);  
        EscribirUsuario(arrayUsu, u);
        //System.out.println("\n");
        HacerHash(arrayUsu, arrayHash2);
        Comparar(arrayHash, arrayHash2);
        /*for (Usuario usuario : arrayUsu) {
            System.out.println(usuario.nom.toString() +"/"+ usuario.rol.toString());
        }*/
        
        do{
            System.out.println("Introduce nombre de usuario:");
            String nom = s.nextLine();
            System.out.println("Introduce el rol del usuario (admin, gest o asist):");
            String rol = s.nextLine();
            u.setNom(nom);
            u.setRol(rol);
            EscribirUsuario(arrayUsu, u);  
        
            String r = DeterminarRol(u);
            a.add(u);
                 
            if(null != r)switch (r) {
                case "admin":
                    VistaAdmin(arrayUsu, u);
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
    public static void VistaAdmin(ArrayList<Usuario> a, Usuario u) throws IOException{
        
        Scanner s = new Scanner(System.in);
        Usuario p = new Usuario();
        boolean salir = false;
        int opcion;
        
        do{
            System.out.println("1-Agregar Usuario");
            System.out.println("2-Modificar Usuario");
            System.out.println("3-Eliminar Usuario");
            System.out.println("4-Listar Usuarios\n");
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
                            Auditar("Agregar Usuario");
                       }
                       break;
                   case 2:
                       System.out.println("Has seleccionado la opcion 2");
                       if(!"admin".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           Auditar("Modificar Usuario");
                       }
                       break;
                    case 3:
                       System.out.println("Has seleccionado la opcion 3");
                       if(!"admin".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           Auditar("Eliminar Usuario");
                       }
                       break;
                    case 4:
                       System.out.println("Has seleccionado la opcion 3");
                       if(!"admin".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           ListarUsuario(a, u);
                           LeerFichero(a, u);
                           Auditar("Listar Usuarios");
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
                       System.out.println("Solo números entre 1 y 5");
                       
               }
        }while(!salir);
        
    }
    public static void VistaGestor(ArrayList<Usuario> a, Usuario u) throws IOException{
        Scanner s = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        String r = DeterminarRol(u);
        
        do{
            System.out.println("1-Agregar Usuario");
            System.out.println("2-Modificar Usuario");
            System.out.println("3-Listar Usuarios");
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
                           Auditar("Agregar Usuario");
                       }
                       break;
                   case 2:
                       System.out.println("Has seleccionado la opcion 2");
                       if(!"gest".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           Auditar("Modificar Usuario Usuario");
                       }
                       break;
                    case 3:
                       System.out.println("Has seleccionado la opcion 3");
                       if(!"gest".equals(r)){
                           System.out.println("Usuario No Autorizado");
                       }else{
                           ListarUsuario(a, u);
                           Auditar("Listar Usuarios");
                       }
                       break;
                    case 4:
                       salir=true;
                       break;
                    default:
                       System.out.println("Solo números entre 1 y 4");
               }
        }while(!salir);
    }
    public static void VistaAsistente(ArrayList<Usuario> a, Usuario u) throws IOException{
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
                           Auditar("Listar Usuarios");
                       }
                       break;
                    case 2:
                       salir=true;
                       break;
                    default:
                       System.out.println("Solo números entre 1 y 2");
               }
        }while(!salir);
    }
    
    public static void AgregarUsuario(ArrayList<Usuario> a, Usuario u) throws IOException {
        Scanner s = new Scanner(System.in);
        Usuario p = new Usuario();
        
        System.out.println("Introduce nombre de usuario:");
        String nom = s.nextLine();
        System.out.println("Introduce el rol del usuario (admin, gest o asist):");
        String rol = s.nextLine();
        p.setNom(nom);
        p.setRol(rol);
        a.add(p);
        EscribirUsuario(a, p);      
    }
    
    public static void ListarUsuario(ArrayList<Usuario> a, Usuario u){
        for (Usuario usuario : a) {
            System.out.println(usuario.nom +", "+ usuario.rol);
        }
    }
  
    public static void EscribirUsuario(ArrayList<Usuario> a, Usuario u) throws IOException{
        String csvFile = "C:\\Users\\Queco\\Desktop\\csv.txt";
        BufferedReader br = null;
        String line = "";
        String separador = ",";
        
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(csvFile));
        
        //bw.write("");
        for(Usuario s : a){     
            bw.write(s.getNom());
            bw.write(",");
            bw.write(s.getRol());
            bw.write("\n");
        }
        bw.close();
        
        
    }
    
    public static void EscribirMD5(String s) throws IOException{
        String csvFile = "C:\\Users\\Queco\\Desktop\\md5.txt";
        BufferedReader br = null;
        String line = "";
        String separador = ",";
        
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(csvFile));
        bw.write(s);
        bw.close();
    }
    
    public static void HacerHash(ArrayList<Usuario> arrayUsu, ArrayList<String> arrayHash2) throws NoSuchAlgorithmException, IOException{
        
        String csvFile = "C:\\Users\\Queco\\Desktop\\md5.txt";
        BufferedReader br = null;
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(csvFile));
        int j = 0;
        
        //System.out.println(cadena);
        for (Usuario usuario : arrayUsu) {
            //System.out.println(usuario.nom.toString() +"/"+ usuario.rol.toString());
        
            String cadena = usuario.getString();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String cadenaEntrada = cadena;
            md5.update(cadenaEntrada.getBytes());
            byte resultado[] = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < resultado.length; i++){
                sb.append(String.format("%02x", resultado[i]));
            }
            //System.out.println(sb.toString());
            arrayHash2.add(sb.toString());
            bw.write(sb.toString());
            bw.write("\n");
            
        }
        bw.close();
        
    }
    public static void LeerFichero(ArrayList<Usuario> arrayUsu, Usuario u) throws IOException{
        String line = "";
        String separador = ",";
        Usuario t = null;
        int i=0;
        
        BufferedReader bufferLectura = null;
        try {
            bufferLectura = new BufferedReader(new FileReader("C:\\Users\\Queco\\Desktop\\csv.txt"));
            String linea = bufferLectura.readLine();
            while (linea != null) {
                String[] campos = linea.split(separador); 
                t = new Usuario();
                linea = bufferLectura.readLine();
                //System.out.println(campos[0]);
                t.setNom(campos[0]);
                t.setRol(campos[1]);
                //System.out.println(t.nom +","+ t.rol);
               
                //arrayUsu.add(t);
                //System.out.println(t.nom +","+ t.rol);   
                /*for(i=0 ; i<arrayUsu.size(); i++){
                    System.out.println(arrayUsu.get(i).nom);
                    arrayUsu.add(i, t);
                }  */
            }
        } 
           catch (IOException e) {
                e.printStackTrace();
           }
            finally {
                if (bufferLectura != null) {
            try {
                bufferLectura.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
                }
            }
        /*for (Usuario usuario : arrayUsu) {
            System.out.println(usuario.nom +" "+ usuario.rol);
        }*/
    }
    //public static Usuario CambiarUsuario(ArrayList<Usuario> a, Usuario u){
        
    //}
    
    public static void ComprobarIntegridad(ArrayList<String> a, Usuario u) throws FileNotFoundException, IOException{
        String line = "";
        Usuario t = new Usuario();
        int i=0;
        
        BufferedReader bufferLectura = null;
        bufferLectura = new BufferedReader(new FileReader("C:\\Users\\Queco\\Desktop\\md5.txt"));
            String linea = bufferLectura.readLine();
            //System.out.println(linea);
            a.add(linea);
            while (linea != null) {
                linea = bufferLectura.readLine();
                //System.out.println(linea);
                if(linea != null){
                    a.add(linea);
                }
                
        }
    }
    public static void Comparar(ArrayList<String> a, ArrayList<String> b) throws FileNotFoundException, IOException{
        if (a.equals(b)){
            System.out.println("Se ha confirmado la integridad del fichero");
        }else{
            System.out.println("Se ha producido un fallo de integridad en el fichero");
        }
        System.out.println();
    }
    
     public static void Auditar(String s) throws FileNotFoundException, IOException{
        String csvFile = "C:\\Users\\Queco\\Desktop\\auditoria.txt";
        BufferedReader br = null;
        String line = "";
        String separador = ",";
        java.util.Date fecha = new Date();
        
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(csvFile));
        bw.write(fecha.toString() + " " + s);
        bw.close();
     }
    
}
