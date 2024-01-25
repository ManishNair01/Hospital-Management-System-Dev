
public class Ward {
    private int wardNumber;
    private boolean occupied;
    private Patient _patient;
    private static int occupiedWards = 1;

    Ward(Patient patient) {
        this._patient = patient;
        this.wardNumber = occupiedWards;
        this.occupied = true;
        occupiedWards++;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getWardNumber() {
        return wardNumber;
    }

    public Patient getPatient() {
        return this._patient;
    }
}
