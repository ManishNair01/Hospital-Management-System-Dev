import java.util.HashMap;

///Stores the appointment details.
///Appoints a patient to a doctor on the basis of their token number.
///@param patient Takes patient name.
///@param doctor Takes the name of the doctor assigned to the patient.
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
        /// @return Returns the doctor assigned.
        return this._doctor;
    }

    public int getTokenNumber() {
        /// @return Returns the token number.
        return this._tokenNumber;
    }

    public Patient getPatient() {
        /// @return Returns the patient.
        return this._patient;
    }
}