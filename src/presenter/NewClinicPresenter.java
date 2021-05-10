package presenter;

import java.sql.Date;

import model.DatabaseConnection;
import view.NewClinicFrame;

public class NewClinicPresenter {

    private String clinicName;
    private Date creationDate;
    private boolean cnamState;
    private float consultationPrice;
    private NewClinicFrame newClinicFrame;

    public NewClinicPresenter(String clinicName, Date creationDate, boolean cnamState, float consultationPrice) {
        this.clinicName = clinicName;
        this.creationDate = (Date) creationDate.clone();
        this.cnamState = cnamState;
        this.consultationPrice = consultationPrice;
    }

    public void setClinicFrame(NewClinicFrame newClinicFrame) {
        this.newClinicFrame = newClinicFrame;
    }

    public void addNewClinic() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        boolean connectionFeedback = databaseConnection.connect();
        if (!connectionFeedback)
            newClinicFrame.connectionFailed();
        else {
            String insertFeedback = databaseConnection.insert(this.clinicName, this.creationDate, this.cnamState,
                    this.consultationPrice);
            newClinicFrame.displayMessage(insertFeedback);
        }

    }
}
