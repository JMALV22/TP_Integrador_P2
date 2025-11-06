package entities;

public abstract class Base {
    private Long id;             // Identificador único
    private Boolean eliminado;   // Baja lógica

    public Base() {
        this.eliminado = false;  // por defecto, no eliminado
    }

    public Base(Long id, Boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isEliminado() {
        return eliminado;
    }
    public Boolean getEliminado() { return eliminado; }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    // Métodos útiles
    public void eliminar() {
        this.eliminado = true;
    }

    public void recuperar() {
        this.eliminado = false;
    }
}

