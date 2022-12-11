/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;


/**
 *
    ServerSocket server;
 * @author ylanr
 */
public class Cliente {
    
    Socket socket;
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        String Nombre;
        String NombreC;
        int Caja;
        double Precio;
        int Cantidad;
        double SubTotal;
        double Impuesto;
        double Total;
        double IVA = 0.16;
        String nombre2;
        
        SimpleDateFormat fechaA = new SimpleDateFormat("dd/MM/YYYY");
        Date fechaActual = new Date();
        
        
        LocalTime  horaActual = LocalTime.now();
        DateTimeFormatter HoraA = DateTimeFormatter.ofPattern("hh:mm");
        
        
        
        
        try{
            Socket cliente = new Socket("localhost", 4500);
           

            Scanner Prod = new Scanner(System.in);

            Scanner usuario = new Scanner(System.in);
            

            System.out.println("  || Numero de caja ||");
            while(!Prod.hasNextInt()){
            String next3 =Prod.next();
            System.out.println(next3+" No es un valor valido");           
            System.out.println("\n  || Numero de caja || ");
        }
            Caja = Prod.nextInt();
            
            System.out.println("  || Ingrese Nombre y Apellido del Cliente ||");
            while(!ValidarTexto(Prod.next())){
                    String text= Prod.next();
                    System.out.println("Los datos NO son correctos");
                    System.out.println("  || Ingrese Nombre y Apellido del Cliente ||");
                     break;
                } 
                 
            NombreC = Prod.next();
            
            System.out.println("  || ¿Que producto desea comprar? ||");
            while(!ValidarTexto(Prod.next())){
                    String text= Prod.next();
                    System.out.println("Los datos NO son correctos");
                    System.out.println("  || ¿Que producto desea comprar? ||");
                     break;
                }
            Nombre = Prod.next();
            
            
            
            System.out.println("  || ¿Cual es el precio unitario de "+Nombre+"?  ||");
            
            while(!Prod.hasNextDouble()){
            String next =Prod.next();
            System.out.println(Nombre+" No es un valor valido");
            System.out.println("\n  || ¿Cual es el precio unitario de "+Nombre+"?  ||  ");
        }
            Precio = Prod.nextDouble();
            System.out.println("  || ¿Cuantas unidades de "+Nombre+" desea llevar? ||");
            while(!Prod.hasNextInt()){
            String next2 =Prod.next();
            System.out.println(Nombre+" No es un valor valido");
            System.out.println("\n  || ¿Cuantas unidades de "+Nombre+" desea llevar? || ");
        }
            Cantidad = Prod.nextInt();
            SubTotal = Precio*Cantidad;
            Impuesto = SubTotal*IVA;
            Total = SubTotal+Impuesto;
       
            Scanner lectura = new  Scanner(System.in);
            int opcion = 0;            
            System.out.println("\n  Seleccione una opcion."
                            +"\n1. Imprimir factura."
                            +"\n2. Salir sistema. " );
            opcion = lectura.nextInt();
            
            switch(opcion){
                case 1:
            ObjectOutputStream mensaje = new ObjectOutputStream(cliente.getOutputStream());
            mensaje.writeObject("\n        ----------------------------------"
                            +"\n         SUPERMERCADO PROGRAMACION, C.A"
                            +"\n                J-00000001"
                            +"\n        AV -- CALLE -- LOCAL 1, LA GUAIRA"
                            +"\n               ZONA POSTAL 1162"
                            +"\n         Caja: "+Caja
                            +"\n         Cliente: Sr(a). "+NombreC
                            +"\n         Fecha: "+fechaA.format(fechaActual)+"   Hora: "+horaActual.format(HoraA)
                            +"\n\n        -------------FACTURA-------------"
                            
                            +"\n         Producto: "+Nombre
                            +"\n         Precio: "+Precio+"bs"
                            +"\n         Cantidad comprada: "+Cantidad
                            +"\n         SubTotal: "+SubTotal+"bs"
                            +"\n         Impuuesto: "+Impuesto+"bs"
                            +"\n         Total a pagar: "+Total+"bs"
                            +"\n"
                            +"\n                 !MUCHAS GRACIAS!"
                            +"\n        ---------------------------------\n"
                    
                    
            ); 
            
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                String mensaje2 = (String)entrada.readObject();
                System.out.println(mensaje2);
                cliente.close();
                System.out.println("Servidor cerrado");
            
            break;
            
                case 2:
                cliente.close();
                System.out.println("Servicio Cerrado");
                
            break;
            }
            
            
        
        } catch (Exception e){
         
        }     
    }
    public static boolean ValidarTexto(String datos){
        return datos.matches("[A-Za-z]*");
    }
    
}
