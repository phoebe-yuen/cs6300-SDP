package edu.gatech.seclass.jobcompare6300;

// The "Weight" Java class is used to save comparison settings
public class Weight {

    // Initialize attributes
    private final int ID;
    private final int AYSWeight;
    private final int AYBWeight;
    private final int RBPWeight;
    private final int LTWeight;
    private final int RWTWeight;

    // constructor
    public Weight(int ID, int AYSWeight, int AYBWeight, int RBPWeight, int LTWeight, int RWTWeight) {
        this.ID = ID;
        this.AYSWeight = AYSWeight;
        this.AYBWeight = AYBWeight;
        this.RBPWeight = RBPWeight;
        this.LTWeight = LTWeight;
        this.RWTWeight = RWTWeight;
    }

    // toString for outputting necessary info
    @Override
    public String toString() {
        return "Weight{" +
                "ID=" + ID +
                ", AYSWeight=" + AYSWeight +
                ", AYBWeight=" + AYBWeight +
                ", RBPWeight=" + RBPWeight +
                ", LTWeight=" + LTWeight +
                ", RWTWeight=" + RWTWeight +
                '}';
    }

    // getters functions
    public int getAYSWeight() {
        return AYSWeight;
    }
    public int getAYBWeight() {
        return AYBWeight;
    }
    public int getRBPWeight() {
        return RBPWeight;
    }
    public int getLTWeight() {
        return LTWeight;
    }
    public int getRWTWeight() {
        return RWTWeight;
    }
}
