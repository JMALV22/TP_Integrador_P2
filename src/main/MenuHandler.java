
package main;

import entities.GrupoSanguineo;
import entities.HistoriaClinica;
import entities.Paciente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import static main.AppMenu.fechaStringALocalDate;
import service.HistoriaClinicaServiceImpl;
import service.PacienteServiceImpl;

public class MenuHandler {
    
    private final PacienteServiceImpl pacienteService;
    private final HistoriaClinicaServiceImpl historiaClinicaService;
    private final Scanner sc;

    public MenuHandler(PacienteServiceImpl pacienteService, HistoriaClinicaServiceImpl historiaClinicaService, Scanner sc) {
        if (sc == null) {
            throw new IllegalArgumentException("sc no puede ser null");
        }
        if (pacienteService == null) {
            throw new IllegalArgumentException("PersonaService no puede ser null");
        }
        if (historiaClinicaService == null) {
            throw new IllegalArgumentException("HistoriaClinicaService no puede ser null");
        }
        
        this.pacienteService = pacienteService;
        this.historiaClinicaService = historiaClinicaService;
        this.sc = sc;
    }
    
    public void listarPacientes() {
        try {
            List<Paciente> pacientes;
            pacientes = pacienteService.obtenerTodos();
                        
            for (Paciente p : pacientes) {
                System.out.println("ID: " + p.getId() 
                    + "\nNombre: " + p.getNombre() 
                    + "\nApellido: " + p.getApellido() 
                    + "\nDNI: " + p.getDni()
                    + "\nFecha de nacimiento: " + p.getFechaNacimiento());
                if (pacienteService.getHistoriaClinica().obtenerPorId(p.getId())!= null) {
                    HistoriaClinica historiaClinica = pacienteService.getHistoriaClinica().obtenerPorId(p.getId());
                    System.out.println("--- Nro. Historia Clinica: " 
                            + historiaClinica.getNroHistoria()
                            + "\n* Grupo sanguineo: " 
                            + historiaClinica.getGrupoSanguineo().getValor()
                            + "\n* Antecedentes: " 
                            + historiaClinica.getAntecedentes()
                            + "\n* Medicacion actual: "
                            + historiaClinica.getMedicacionActual()
                            + "\n* Observaciones: "
                            + historiaClinica.getObservaciones() + "\n");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar paciente: " + e.getMessage());
        }
    }
    
    /**
     * Opción 2 y 3: Listar paciente por DNI y por ID
     * 
     * Muestra:
     * - ID, Nombre, Apellido, DNI, Fecha de nacimiento
     * - Historia Clinica
     *
     * Manejo de casos especiales:
     * - Si no hay personas: Muestra "Paciente no encontrado"
     * - Si la persona no tiene Historia Clinica: Solo muestra datos de paciente
     *
     * Búsqueda por DNI y ID
     * - Usa pacienteService.buscarPorDni(dni)
     *   y pacienteService.obtenerPorId(id_long)
     */
    public void listarPacientePorDNI() {
        try {
            System.out.println("Ingrese el DNI del paciente a buesca:");
            String dni = sc.nextLine().trim();
                    
            Paciente p = pacienteService.buscarPorDni(dni);
            
            if (p == null) {
                System.out.println("Paciente no encontrado.");
                return;
            }
                        
            System.out.println("ID: " + p.getId() 
                    + "\nNombre: " + p.getNombre() 
                    + "\nApellido: " + p.getApellido() 
                    + "\nDNI: " + p.getDni()
                    + "\nFecha de nacimiento: " + p.getFechaNacimiento());
                if (pacienteService.getHistoriaClinica().obtenerPorId(p.getId())!= null) {
                    HistoriaClinica historiaClinica = pacienteService.getHistoriaClinica().obtenerPorId(p.getId());
                    System.out.println("--- Nro. Historia Clinica: " 
                            + historiaClinica.getNroHistoria()
                            + "\n* Grupo sanguineo: " 
                            + historiaClinica.getGrupoSanguineo().getValor()
                            + "\n* Antecedentes: " 
                            + historiaClinica.getAntecedentes()
                            + "\n* Medicacion actual: "
                            + historiaClinica.getMedicacionActual()
                            + "\n* Observaciones: "
                            + historiaClinica.getObservaciones());
                }
        } catch (Exception e) {
            System.err.println("Error al listar paciente: " + e.getMessage());
        }
    }
    
    public void listarPacientePorID() {
        try {
            System.out.println("Ingrese el ID del paciente a buscar:");
            Long id_long = null;
            try {
                String id_texto = sc.nextLine().trim();
                id_long = Long.parseLong(id_texto);
            } catch (NumberFormatException e) {
                System.out.println("El numero no es valido");
            }
                        
            Paciente p = pacienteService.obtenerPorId(id_long);
            
            if (p == null) {
                System.out.println("Paciente no encontrado.");
                return;
            }
                        
            System.out.println("ID: " + p.getId() 
                    + "\nNombre: " + p.getNombre() 
                    + "\nApellido: " + p.getApellido() 
                    + "\nDNI: " + p.getDni()
                    + "\nFecha de nacimiento: " + p.getFechaNacimiento());
                if (pacienteService.getHistoriaClinica().obtenerPorId(p.getId())!= null) {
                    HistoriaClinica historiaClinica = pacienteService.getHistoriaClinica().obtenerPorId(p.getId());
                    System.out.println("--- Nro. Historia Clinica: " 
                            + historiaClinica.getNroHistoria()
                            + "\n* Grupo sanguineo: " 
                            + historiaClinica.getGrupoSanguineo()
                            + "\n* Antecedentes: " 
                            + historiaClinica.getAntecedentes()
                            + "\n* Medicacion actual: "
                            + historiaClinica.getMedicacionActual()
                            + "\n* Observaciones: "
                            + historiaClinica.getObservaciones());
                }
        } catch (Exception e) {
            System.err.println("Error al listar paciente: " + e.getMessage());
        }
    }
    
    /**
     * Opción 4: Crear nuevo Paciente
     *
     * Flujo:
     * 1. Solicita nombre, apellido, DNI y fecha de nacimiento
     * 2. Al crear un paciente, se crea su Historia Clinica
     * 3. Crea objeto Paciente y objeto Historia Clinica
     * 4. Inserta la Historia Clinica al paciente
     * 5. pacienteService.insertar(p)
     *   
     * Manejo de errores:
     * - Se valida la fecha de nacimiento con el metodo priavdo validarFecha()
     */
    public void crearPaciente() {
        try {
            System.out.print("A continuacion ingrese los datos del paciente nuevo:\n");
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine().trim();
            System.out.print("DNI: ");
            String dni = sc.nextLine().trim();
            
            String fechaNacimiento;
            boolean corte = false;
            do {
                System.out.print("Fecha de nacimiento(formato yyyy-MM-dd): ");
                fechaNacimiento = sc.nextLine().trim();
                corte = validarFecha(fechaNacimiento);
                if(corte == false){
                    System.out.println("Vuelve a insertar...");
                }
            } while (!corte);
         
            System.out.println("\nA continuacion se va a crear su Historia Clinica");
            System.out.print("Nro. de Historia Clinica: ");
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
            pacienteService.insertar(p);
            System.out.println("Paciente creado exitosamente con ID: " + p.getId());
            
        } catch (Exception e) {
            System.err.println("Error al crear un paciente: " + e.getMessage());
        }
    }
    
    /**
     * Opción 5: Actualizar paciente existente.
     *
     * Flujo:
     * 1. Solicita DNI de la persona actualizar
     * 2. Obtiene paciente de la BD
     * 3. Muestra valores actuales y permite actualizar:
     *    - Nombre (Enter para mantener actual)
     *    - Apellido (Enter para mantener actual)
     *    - DNI (Enter para mantener actual)
     *    - Fecha de nacimiento (Enter para mantener actual)
     * 4. Llama a actualizarDomicilioDePersona() para manejar cambios en domicilio
     * 5. Invoca: 
     *    - actualizarHistorialDelPaciente()
     *    - pacienteService.actualizar()
     *
     * Patrón "Enter para mantener":
     * - Lee input con scanner.nextLine().trim()
     * - Si isEmpty() → NO actualiza el campo (mantiene valor actual)
     * - Si tiene valor → Actualiza el campo
     */
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
           
            String fechaNacimiento;
            boolean corte = false;
            do {
                System.out.print("Nueva fecha de nacimiento(formato yyyy-MM-dd) (actual: "
                + p.getFechaNacimiento()+ ", Enter para mantener): ");
                fechaNacimiento = sc.nextLine().trim();
                corte = validarFecha(fechaNacimiento);
                if(corte == false){
                    System.out.println("Vuelve a insertar...");
                }
            } while (!corte);
            LocalDate fechaNacimientoLocal = fechaStringALocalDate(fechaNacimiento);
            if (!fechaNacimiento.isEmpty()) {
                p.setFechaNacimiento(fechaNacimientoLocal);
            }

            actualizarHistorialDelPaciente(p);
            pacienteService.actualizar(p);
            System.out.println("Paciente actualizado exitosamente.");
  
        } catch (Exception e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
        }
    }
    
    /**
     * Opción 6: Eliminar paciente.
     *
     * Flujo:
     * 1. Solicita DNI del paciente, busca por medio de buscarPorDni()
     * 2. pacienteService.eliminar(p.getId()) que:
     *    - Marca paciente.eliminado = TRUE
     */
    public void eliminarPaciente() {
        try {
            System.out.print("DNI de del paciente a eliminar: ");
            String dni = sc.nextLine().trim();
            
            Paciente p = pacienteService.buscarPorDni(dni);
            pacienteService.eliminar(p.getId());
            
            System.out.println("Paciente (DNI: " + p.getDni() + " eliminado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el paciente: " + e.getMessage());
        }
    }
    
    /**
     * Opción 6: Listar Historias clinicas.
     *
     * Muestra: 
     *  - Nro. Historia Clinica, Grupo sanguineo, Antecedentes, Medicacion actual y Observaciones
     *
     */
    public void listarHistoriaClinica() {
        try {
            List<HistoriaClinica> historiaClinica;
            historiaClinica = historiaClinicaService.obtenerTodos();
            
            for (HistoriaClinica h : historiaClinica) {
             
                System.out.println("\n --- Nro. Historia Clinica: " 
                            + h.getNroHistoria()
                            + "\n* Grupo sanguineo: " 
                            + h.getGrupoSanguineo()
                            + "\n* Antecedentes: " 
                            + h.getAntecedentes()
                            + "\n* Medicacion actual: "
                            + h.getMedicacionActual()
                            + "\n* Observaciones: "
                            + h.getObservaciones());
            }
  
        } catch (Exception e) {
            System.err.println("Error al listar Historia Clinica: " + e.getMessage());
        }
    }
    
    public void listarHistoriaPorDNI() {
        try {
            System.out.println("Ingrese el DNI del paciente a buesca su Historia Clinica:");
            String dni = sc.nextLine().trim();
                    
            Paciente p = pacienteService.buscarPorDni(dni);
                        
            System.out.println("Nombre y apellido: " + 
                    p.getNombre() + " " + p.getApellido());
                    
                if (p.getHistoriaClinica()!= null) {
                    System.out.println("\n --- Nro. Historia Clinica: " 
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
    
    public void listarPorIDHistorial() {
        try {
            System.out.println("Ingrese el ID de la Historia Clinica :");
            Long id_long = null;
            try {
                String id_texto = sc.nextLine().trim();
                id_long = Long.parseLong(id_texto);
            } catch (NumberFormatException e) {
                System.out.println("El numero no es válido");
            }
                    
            HistoriaClinica h = historiaClinicaService.obtenerPorId(id_long);
                        
            System.out.println("\n --- Nro. Historia Clinica: " 
                    + h.getNroHistoria()
                    + "\n* Grupo sanguineo: " 
                    + h.getGrupoSanguineo()
                    + "\n* Antecedentes: " 
                    + h.getAntecedentes()
                    + "\n* Medicacion actual: "
                    + h.getMedicacionActual()
                    + "\n* Observaciones: "
                    + h.getObservaciones());
            
        } catch (Exception e) {
            System.err.println("Error al listar paciente: " + e.getMessage());
        }
    }
    
    public void actualizarHistoriaPorDNIPaciente() {
        try {
            System.out.print("DNI del paciente a actualizar su historia clinica: ");
            String dni = sc.nextLine().trim();
            Paciente p = pacienteService.buscarPorDni(dni);

            if (p == null) {
                System.out.println("Paciente no encontrado.");
                return;
            }
            
            actualizarHistorialDelPaciente(p);

            System.out.println("Historial actualizado exitosamente.");
  
        } catch (Exception e) {
            System.err.println("Error al actualizar Historia Clinica: " + e.getMessage());
        }
    }
    
    /**
     * METODO PRIVADO:
     * actualizarHistorialDelPaciente()
     * crearHistoriaClinica()
     * 
     */
    private void actualizarHistorialDelPaciente(Paciente p) throws Exception {
        if (pacienteService.getHistoriaClinica().obtenerPorId(p.getId())!= null) {
            System.out.print("¿Desea actualizar la Historia Clinica del paciente? (s/n): ");
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
              
                pacienteService.getHistoriaClinica().actualizar(p.getHistoriaClinica());
            }
        } else {
            System.out.print("El paciente no tiene Historia Clinica. ¿Desea agregar uno? (s/n): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                HistoriaClinica nuevaHistoria = crearHistoriaClinica();
                pacienteService.getHistoriaClinica().actualizar(nuevaHistoria);
                p.setHistoriaClinica(nuevaHistoria);
            }
        }
    }
    
    private HistoriaClinica crearHistoriaClinica(){
        System.out.println("\nA continuacion se va a crear su Historia Clinica");
        System.out.print("Nro. de historia clinica: ");
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
                        
        GrupoSanguineo grupoSang = GrupoSanguineo.convertir(grupoSanguineo);
                        
        HistoriaClinica h = new HistoriaClinica(nroHistoria, grupoSang, antecedentes, 
                                medicacionActual, Observaciones, Long.MIN_VALUE, Boolean.TRUE);
        
        return h;
    }
    
    public static boolean validarFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
        try {
            LocalDate.parse(fecha, formatter);
            return true;  // formato válido
        } catch (Exception e) {
            return false; // formato inválido
        }
    }
}