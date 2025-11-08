package entities;

import java.util.Objects;

/**
 * Entidad que representa la Historia Clinica de un paciente en el sistema.
 * Hereda de Base para obtener id y eliminado.

 * Relación con HistoriaClinica Asociacion
 * - Una Persona puede tener 0 o 1 HistoriaClinica
**/

public class HistoriaClinica extends Base{

    private String nroHistoria;
    private GrupoSanguineo grupoSanguineo;
    private String antecedentes;
    private String medicacionActual;
    private String observaciones;

    /*
     * @param nroHistoria       Numero asignado a la historia clinica
     * @param grupoSanguineo    Grupo Sanguinio del paciente
     * @param antecedentes      Antecedentes clinicos
     * @param medicacionActual  Medicacion que este tomando actualmente
     * @param observaciones     Observaciones
     * @param id                ID del paciente en la BD
     * @param eliminado         Eliminado o no en base de datos (Eliminado Logico)
     */
    public HistoriaClinica(String nroHistoria, GrupoSanguineo grupoSanguineo, String antecedentes,
                           String medicacionActual, String observaciones, Long id, Boolean eliminado) {
        super(id, eliminado);
        this.nroHistoria = nroHistoria;
        this.grupoSanguineo = grupoSanguineo;
        this.antecedentes = antecedentes;
        this.medicacionActual = medicacionActual;
        this.observaciones = observaciones;
    }

    public HistoriaClinica() {
    }

    public String getNroHistoria() {
        return nroHistoria;
    }

    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public String getMedicacionActual() {
        return medicacionActual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setMedicacionActual(String medicacionActual) {
        this.medicacionActual = medicacionActual;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" +
                "id=" + getId() +
                "nroHistoria=" + nroHistoria +
                ", grupoSanguineo=" + grupoSanguineo +
                ", Antecedentes=" + antecedentes +
                ", medicacionActual=" + medicacionActual +
                ", observaciones=" + observaciones +
                ", eliminado=" + isEliminado() +
                '}';
    }

    // Compara dos Historias Clinicas por nroHistoria (identificador único).

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoriaClinica historiaClinica = (HistoriaClinica) o;
        return Objects.equals(nroHistoria, historiaClinica.nroHistoria);
    }

    // Hash code basado en nroHistorial.

    @Override
    public int hashCode() {
        return Objects.hash(nroHistoria);
    }
}
