package my.machine.kasse;

public class GeldbestandFehler extends Exception {

    public GeldbestandFehler() {
    }

    public GeldbestandFehler(String message) {
        super(message);
    }

}