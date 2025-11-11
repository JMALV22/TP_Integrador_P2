package entities;

public enum GrupoSanguineo {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private final String valor;

    GrupoSanguineo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    // metodo para convertir texto que viene de la base de datos a su enum correspondiente
    // mira todos los grupos sanguíneos y devuelve el que coincide con el texto que vino de la base

    public static GrupoSanguineo convertir(String valorBD) {
        for (GrupoSanguineo g : values()) {
            if (g.valor.equalsIgnoreCase(valorBD)) return g;
        }
        throw new IllegalArgumentException("Valor de grupo sanguineo invalido: " + valorBD);
    }
}