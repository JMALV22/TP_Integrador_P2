
package main;

public class MenuDisplay {

    /**
     * Muestra el menú principal con todas las opciones CRUD.
     *
     * Opciones de Paciente (1-6):
     * 1. Mostrar lista de pacientes: Muestra todos los pacientes en la base de datos. 
     * 2. Mostrar paciente por ID.
     * 3. Mostrar paciente por DNI.
     * 4. Crear paciente: Permite crear un paciente nuevo y agregar su Historial clinico.
     * 5. Actualizar paciente: Permite actualizar paciente por DNI, opcionalmente su Historial Clinico.
     * 6. Eliminar pacinete: Se implementa eliminado logico, no lo borra de base de datos.
     * 
     * Opciones de Historial Clinico (7-10):
     * 7. Listar Historiales Clinicos: Lista todos los historiales clinicos en la base de datos.
     * 8. Mostrar Historial Clinico por numero de historial.
     * 9. Mostrar Historial Clinico por DNI del paciente.
     * 10. Actualizar Historial Clinico por DNI: Mediante DNI del paciente, permite actualizar historial clinico
     * 
     * Opción de salida:
     * 0. Salir: Termina la aplicación
     */
    
    public static void mostrarMenuPrincipal() {
        System.out.println("\n========= MENU =========");
        System.out.println("1. Mostrar lista de pacientes");
        System.out.println("2. Mostrar paciente por ID");
        System.out.println("3. Mostrar paciente por DNI");
        System.out.println("4. Crear paciente");
        System.out.println("5. Actualizar paciente");
        System.out.println("6. Eliminar Paciente");
        System.out.println("7. Listar Historiales Clinicos");
        System.out.println("8. Mostrar Historial Clinico por numero de historial");
        System.out.println("9. Mostrar Historial Clinico por DNI del paciente");
        System.out.println("10. Actualizar Historial Clinico por DNI");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opcion: ");
    }
    
}
