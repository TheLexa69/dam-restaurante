package edu.badpals.damrestaurante.entities;

public enum Turno {
    MANANA("Mañana"),
    TARDE("Tarde"),
    NOCHE("Noche");

    private final String displayName;

    Turno(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Turno fromDisplayName(String displayName) {
        for (Turno turno : Turno.values()) {
            if (turno.getDisplayName().equalsIgnoreCase(displayName)) {
                return turno;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + displayName);
    }
}