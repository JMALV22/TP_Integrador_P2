package main;

import java.time.LocalDate;
import java.util.Scanner;
import static main.MenuDisplay.mostrarMenuPrincipal;
import service.HistoriaClinicaServiceImpl;
import service.PacienteServiceImpl;

public class AppMenu {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        PacienteServiceImpl pacienteService = new PacienteServiceImpl();
        HistoriaClinicaServiceImpl historiaClinicaService = new HistoriaClinicaServiceImpl();
        MenuHandler menuHandler = new MenuHandler(pacienteService, historiaClinicaService, sc);
        
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
                    
                    case 6 -> menuHandler.eliminarPaciente();
                    
                    case 7 -> menuHandler.listarHistoriaClinica();
                    
                    case 8 -> menuHandler.listarHistoriaPorDNI();
                    
                    case 9 -> menuHandler.listarPorIDHistorial();
                    
                    case 10 -> menuHandler.actualizarHistoriaPorDNIPaciente();
                    
                    case 0 -> {
                        System.out.println("Saliendo...");
                        opcion = 0;
                    }
                }
     
            } catch (NumberFormatException e ){
                System.out.println("Opcion no valida.");
            }
    
        } while (opcion != 0);
  
    }
    
    public static LocalDate fechaStringALocalDate(String fechaTexto){
        LocalDate fecha = LocalDate.parse(fechaTexto);
        return fecha;
    }
      
}
