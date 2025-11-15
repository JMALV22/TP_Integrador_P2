package main;

import java.time.LocalDate;
import java.util.Scanner;
import static main.MenuDisplay.mostrarMenuPrincipal;
import service.PacienteServiceImpl;

public class AppMenu {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        PacienteServiceImpl pacienteService = new PacienteServiceImpl();                      
        MenuHandler menuHandler = new MenuHandler(pacienteService, sc);
        
        int opcion = -1;
        
        do {
            try {
               
                mostrarMenuPrincipal();
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> menuHandler.listarPersonas();
                        
                    case 2 -> menuHandler.listarPersonaPorDNI(); 
                      
                    case 3 -> menuHandler.listarPersonaPorID();
                        
                    case 4 -> menuHandler.crearPaciente();
                    
                    case 5 -> menuHandler.actualizarPaciente();
                }
                        
                        
                        
                    
                    
                    /**
                    case 5 -> crearDomicilioIndependiente();
                    case 6 -> listarDomicilios();
                    case 7 -> actualizarDomicilioPorId();
                    case 8 -> eliminarDomicilioPorId();
                    case 9 -> actualizarDomicilioPorPersona();
                    case 10 -> eliminarDomicilioPorPersona();
                    case 0 -> {
                        System.out.println("Saliendo...");
                        running = false;
                    }
                    */
                    
                
   
            } catch (NumberFormatException e ){
                     
            }
    
        } while (true);
           
        // EN CONSTRUCCIONNNNNNNNNNNNNNNNNN

    }
    
    public static LocalDate fechaStringALocalDate(String fechaTexto){
        LocalDate fecha = LocalDate.parse(fechaTexto);
        return fecha;
    }
    
    
}
