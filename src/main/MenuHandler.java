
package main;

import entities.GrupoSanguineo;
import entities.HistoriaClinica;
import entities.Paciente;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import static main.AppMenu.fechaStringALocalDate;
import service.PacienteServiceImpl;

public class MenuHandler {
    
    private final PacienteServiceImpl pacienteService;
    
    private final Scanner sc;
    
    public MenuHandler(service.PacienteServiceImpl pacienteService, Scanner sc) {
        if (sc == null) {
            throw new IllegalArgumentException("sc no puede ser null");
        }
        if (pacienteService == null) {
            throw new IllegalArgumentException("PersonaService no puede ser null");
        }
        this.sc = sc;
        this.pacienteService = pacienteService;
        
    }

    public void listarPersonas() {
        try {
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
        } catch (Exception e) {
            System.err.println("Error al listar paciente: " + e.getMessage());
        }
    }
    
    public void listarPersonaPorDNI() {
        try {
            System.out.println("Ingrese el ID del paciente a buesca:");
            String id = sc.nextLine().trim();
                        
            Paciente p = pacienteService.buscarPorID(id);
                        
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
        } catch (Exception e) {
            System.err.println("Error al listar paciente: " + e.getMessage());
        }
    }
    
    public void listarPersonaPorID() {
        try {
            System.out.println("Ingrese el ID del paciente a buesca:");
            String id = sc.nextLine().trim();
                        
            Paciente p = pacienteService.buscarPorID(id);
                        
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
        } catch (Exception e) {
            System.err.println("Error al listar paciente: " + e.getMessage());
        }
    }
    
    public void crearPaciente() {
        try {
            System.out.print("A continuacion ingrese los datos del paciente nuevo:\n");
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine().trim();
            System.out.print("DNI: ");
            String dni = sc.nextLine().trim();
            System.out.print("Fecha de nacimiento(formato yyyy-MM-dd): ");
            String fechaNacimiento = sc.nextLine().trim();
                        
            System.out.println("\nA continuacion se va a crear su historia clinica");
            System.out.print("N° de historia clinica: ");
            String nroHistoria = sc.nextLine().trim();
            System.out.print("Grupo sanguineo: ");
            System.out.print("Debe ingresarse: A+ / A- / B+ / B- / AB+ / AB- / O+ / O-\n");
            String grupoSanguineo = sc.nextLine().trim();
            System.out.print("Antecedentes: ");
            String antecedentes = sc.nextLine().trim();
            System.out.print("Medicacion actual: ");
            String medicacionActual = sc.nextLine().trim();
            System.out.print("Observaciones: ");
            String Observaciones = sc.nextLine().trim();
                        
            LocalDate fechaNacimientoLocal = fechaStringALocalDate(fechaNacimiento);
            GrupoSanguineo grupoSang = GrupoSanguineo.convertir(grupoSanguineo);
                        
            Paciente p = new Paciente(nombre, apellido, dni, fechaNacimientoLocal, Long.MIN_VALUE, Boolean.TRUE);
            HistoriaClinica h = new HistoriaClinica(nroHistoria, grupoSang, antecedentes, 
                                                               medicacionActual, Observaciones, Long.MIN_VALUE, Boolean.TRUE);
            p.setHistoriaClinica(h);      
            pacienteService.insertarPaciente(p);
            System.out.println("Paciente creado exitosamente con ID: " + p.getId());
            
        } catch (Exception e) {
            System.err.println("Error al crear un paciente: " + e.getMessage());
        }
    }
    
    public void actualizarPaciente() {
        try {
            System.out.print("DNI del paciente a actualizar: ");
            String dni = sc.nextLine().trim();
            Paciente p = pacienteService.buscarPorDni(dni);

            if (p == null) {
                System.out.println("Paciente no encontrado.");
                return;
            }

            System.out.print("Nuevo nombre (actual: " + p.getNombre() + ", Enter para mantener): ");
            String nombre = sc.nextLine().trim();
            if (!nombre.isEmpty()) {
                p.setNombre(nombre);
            }

            System.out.print("Nuevo apellido (actual: " + p.getApellido() + ", Enter para mantener): ");
            String apellido = sc.nextLine().trim();
            if (!apellido.isEmpty()) {
                p.setApellido(apellido);
            }

            System.out.print("Nuevo DNI (actual: " + p.getDni() + ", Enter para mantener): ");
            String dni_nuevo = sc.nextLine().trim();
            if (!dni_nuevo.isEmpty()) {
                p.setDni(dni_nuevo);
            }

            actualizarHistorialDelPaciente(p);
            pacienteService.actualizarPaciente(p);
            System.out.println("Persona actualizada exitosamente.");
  
        } catch (Exception e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    private void actualizarHistorialDelPaciente(Paciente p) throws Exception {
        if (p.getHistoriaClinica()!= null) {
            System.out.print("¿Desea actualizar el Historial del paciente? (s/n): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                
                System.out.print("Nuevo Grupo Sanguineo (" + p.getHistoriaClinica().getGrupoSanguineo() + "): ");
                System.out.print("Debe ingresarse: A+ / A- / B+ / B- / AB+ / AB- / O+ / O-\n");
                String grupoSanguineo = sc.nextLine().trim();
                if (!grupoSanguineo.isEmpty()) {
                    GrupoSanguineo grupoSang = GrupoSanguineo.convertir(grupoSanguineo);
                    p.getHistoriaClinica().setGrupoSanguineo(grupoSang);
                }
                System.out.print("Nuevo antecedente (" + p.getHistoriaClinica().getAntecedentes()+ "): ");
                String antecedente = sc.nextLine().trim();
                if (!antecedente.isEmpty()) {
                    p.getHistoriaClinica().setAntecedentes(antecedente);
                }
                System.out.print("Nueva Medicacion actual: (" + p.getHistoriaClinica().getMedicacionActual()+ "): ");
                String medicacionActual = sc.nextLine().trim();
                if (!medicacionActual.isEmpty()) {
                    p.getHistoriaClinica().setMedicacionActual(medicacionActual);
                }
                System.out.print("Nueva observacion: (" + p.getHistoriaClinica().getObservaciones()+ "): ");
                String observacion = sc.nextLine().trim();
                if (!observacion.isEmpty()) {
                    p.getHistoriaClinica().setObservaciones(observacion);
                }
              
                pacienteService.getHistoriaClinicaServer().actualizar(p.getHistoriaClinica());
            }
        } else {
            System.out.print("La persona no tiene domicilio. ¿Desea agregar uno? (s/n): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                //HistoriaClinica nuevaHistoria = crearHistoriaClinica();
                //pacienteService.getHistoriaClinicaServer().insertar(nuevaHistoria);
                //p.setHistoriaClinica(nuevaHistoria);
            }
        }
    }
    

}
