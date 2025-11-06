package entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa una paciente en el sistema.
 * Hereda de Base para obtener id y eliminado.
 *
 * Relación con HistoriaClinica:
 * - Una Persona puede tener 0 o 1 HistoriaClinica
**/

public class Paciente extends Base{
    
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private HistoriaClinica historiaClinica;
    
    /**
     * @param nombre            Nombre del paciente
     * @param apellido          Apellido del paciente
     * @param dni               DNI del paciente
     * @param fechaNacimiento   Fecha de nacimiento
     * @param id                ID del paciente en la BD
     * @param eliminado         Eliminado o no en base de datos
     */
    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, Long id, Boolean eliminado) {
        super(id, eliminado);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Paciente() {}

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    @Override
    public String toString() {
        return "Paciente{" +
               "id=" + getId() +
               "nombre=" + nombre + 
               ", apellido=" + apellido + 
               ", dni=" + dni + 
               ", fechaNacimiento=" + fechaNacimiento + 
               ", historiaClinica=" + historiaClinica + 
               ", eliminado=" + isEliminado() +
               '}';
    }
    
    // Compara dos pacientes por DNI (identificador único).
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(dni, paciente.dni);
    }
    
    // Hash code basado en DNI.
     
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
