/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ylanr
 */
public class Servidor {
    
    public static void main(String[] args){
        try {
            ServerSocket servidor = new ServerSocket(4500);
            System.out.println("Servidor conectado");
            
            while(true){
            Socket clienteNuevo = servidor.accept();
           
            ObjectInputStream entrada = new ObjectInputStream(clienteNuevo.getInputStream());
            
       
                String mensaje = (String)entrada.readObject();
                System.out.println(mensaje);
                
                ObjectOutputStream salida = new ObjectOutputStream(clienteNuevo.getOutputStream());
                salida.writeObject("\n TICKET IMPRESO CON !EXITO!");

                clienteNuevo.close();
            }  
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {    
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){

            }  
    }
}

