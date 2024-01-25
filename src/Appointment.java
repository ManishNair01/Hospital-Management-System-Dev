import java.util.HashMap;

public class Appointment {
    private Patient _patient;
    private Staff _doctor;
    private int _tokenNumber;
    private static HashMap<Staff, Integer> _currentTokens = new HashMap<Staff, Integer>();

    public Appointment(Patient patient, Staff doctor) {
        this._patient = patient;
        this._doctor = doctor;
        try {
            int _currentToken = _currentTokens.get(doctor);
            this._tokenNumber = _currentToken;
        } catch (NullPointerException e) {
            _currentTokens.put(doctor, 0);
            this._tokenNumber = 1;
        } finally {
            _currentTokens.put(doctor, _currentTokens.get(doctor) + 1);
        }

    }

    public Staff getDoctor() {
        return this._doctor;
    }

    public int getTokenNumber() {
        return this._tokenNumber;
    }

    public Patient getPatient() {
        return this._patient;
    }
}