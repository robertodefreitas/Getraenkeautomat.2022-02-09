package my.machine.dto;

public class Getraenkewunsch {
    private Integer fachNummer;

    /* constructor */

    public Getraenkewunsch(Integer fachNummer) {
        this.fachNummer = fachNummer;
    }

    /* getters and setters */

    public Integer getFachNummer() {
        return fachNummer;
    }

    public void setFachNummer(Integer fachNummer) {
        this.fachNummer = fachNummer;
    }

    /* methods */
}
