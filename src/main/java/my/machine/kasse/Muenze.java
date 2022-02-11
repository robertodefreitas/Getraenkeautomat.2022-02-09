package my.machine.kasse;

public class Muenze {
    private Muenztyp muenze;


    /* constructor */

    public Muenze(Muenztyp muenze) {
        this.muenze = muenze;
    }

    public Muenze(Integer cents) {
        this.muenze = bekommenMuenztypVonCents(cents).getMuenze();
    }


    /* getters and setters */

    public Muenztyp getMuenze() {
        return muenze;
    }

    public void setMuenze(Muenztyp muenze) {
        this.muenze = muenze;
    }


    /* methods */

    public Integer bekommenCents(){
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

    public Muenze bekommenMuenztypVonCents(Integer cents){
        String methodeName = new Object(){}.getClass().getEnclosingMethod().getName();

        switch (cents) {
            case 10:
                return new Muenze(Muenztyp.ZEHN_CENT);
            case 20:
                return new Muenze(Muenztyp.ZWANZIG_CENT);
            case 50:
                return new Muenze(Muenztyp.FUENZIG_CENT);
            case 100:
                return new Muenze(Muenztyp.EIN_EURO);
            case 200:
                return new Muenze(Muenztyp.ZWEI_EURO);
            default:
                System.out.println("WARN [" + methodeName + "] Der Wert \"" + cents + "\" existiert nicht (return 0).");
                return null;
        }
    }
}
