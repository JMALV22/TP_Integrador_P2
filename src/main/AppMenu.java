package main;

import entities.Paciente;
import java.util.List;
import java.util.Scanner;
import static main.MenuDisplay.mostrarMenuPrincipal;
import service.HistoriaClinicaService;
import service.PacienteService;

public class AppMenu {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        PacienteService pacienteService = new PacienteService();                      
        HistoriaClinicaService historialClinicoService = new HistoriaClinicaService();
        
        int opcion = -1;
        
        do {
            try {
                
                mostrarMenuPrincipal();
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> {
                        
                        List<Paciente> pacientes;
                        pacientes = pacienteService.getAll();
                        System.out.println(pacientes); //print solo para prueba
                        
                        for (Paciente p : pacientes) {
                            System.out.println("ID: " + p.getId() 
                                + "\nNombre: " + p.getNombre() 
                                + "\nApellido: " + p.getApellido() 
                                + "\nDNI: " + p.getDni());
                            if (p.getHistoriaClinica()!= null) {
                                System.out.println("\n --- N° Historial Clinico: " 
                                        + p.getHistoriaClinica().getNroHistoria()
                                        + "\n* Grupo sanguineo: " 
                                        + p.getHistoriaClinica().getGrupoSanguineo()
                                        + "\n* Antecedentes: " 
                                        + p.getHistoriaClinica().getAntecedentes()
                                        + "\n* Medicacion actual: "
                                        + p.getHistoriaClinica().getMedicacionActual()
                                        + "\n* Observaciones: "
                                        + p.getHistoriaClinica().getObservaciones());
                            }
                        }
                    }
                    case 2 -> {
                        
                        System.out.println("Ingrese el DNI del paciente a buesca:");
                        String dni = sc.nextLine().trim();
                        
                        Paciente p = pacienteService.buscarPorDni(dni);
                        
                        System.out.println("ID: " + p.getId() 
                                + "\nNombre: " + p.getNombre() 
                                + "\nApellido: " + p.getApellido() 
                                + "\nDNI: " + p.getDni());
                            if (p.getHistoriaClinica()!= null) {
                                System.out.println("\n --- N° Historial Clinico: " 
                                        + p.getHistoriaClinica().getNroHistoria()
                                        + "\n* Grupo sanguineo: " 
                                        + p.getHistoriaClinica().getGrupoSanguineo()
                                        + "\n* Antecedentes: " 
                                        + p.getHistoriaClinica().getAntecedentes()
                                        + "\n* Medicacion actual: "
                                        + p.getHistoriaClinica().getMedicacionActual()
                                        + "\n* Observaciones: "
                                        + p.getHistoriaClinica().getObservaciones());
                    }
                
                    /**
                    case 3 -> actualizarPersona();
                    case 4 -> eliminarPersona();
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
                    }
                }
   
            } catch (NumberFormatException e ){
                     
            }
    
        } while (true);
           
        // EN CONSTRUCCIONNNNNNNNNNNNNNNNNN

    }
    
    
}
