package my.machine.kasse;

public class Muenze {
    private Muenztyp muenze;


    /* constructor */

    public Muenze(Muenztyp muenze) {
        this.muenze = muenze;
    }


    /* getters and setters */

    public Muenztyp getMuenze() {
        return muenze;
    }

    public void setMuenze(Muenztyp muenze) {
        this.muenze = muenze;
    }


    /* methods */

    public Integer getWertInCent(){
        String methodeName = new Object(){}.getClass().getEnclosingMethod().getName();

        switch (this.muenze) {
            case ZEHN_CENT:
                return 10;
            case ZWANZIG_CENT:
                return 20;
            case FUENZIG_CENT:
                return 50;
            case EIN_EURO:
                return 100;
            case ZWEI_EURO:
                return 200;
            default:
                System.out.println("WARN [" + methodeName + "] Münztyp \"" + this.muenze + "\" existiert nicht (return 0).");
                return 0;
        }
    }
}
