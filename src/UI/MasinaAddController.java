package UI;

import Service.DieselService;
import Service.ElectricaService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MasinaAddController {
    public TextField txtModel;
    public TextField txtPret;
    public TextField txtAutonomie;
    public TextField txtConsum;
    public CheckBox chkDiesel;
    public CheckBox chkElectrica;

    public Button btnAdd;
    public Button btnCancel;

    private DieselService dieselService;
    private ElectricaService electricaService;

//metoda de setare a serviciilor
    public void setService(DieselService dieselService, ElectricaService electricaService) {
        this.dieselService = dieselService;
        this.electricaService = electricaService;
    }


    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick(ActionEvent actionEvent) {

        try {

            String model = txtModel.getText();
            double pret = Double.parseDouble(txtPret.getText());
            double autonomie = Double.parseDouble(txtAutonomie.getText());
            double consum = Double.parseDouble(txtConsum.getText());
            boolean checkDiesel = chkDiesel.isSelected();
            boolean checkElectrica = chkElectrica.isSelected();
            //daca checkboxx Diesel e selectat
            if (checkDiesel == true){
                //adaugam o masina de tip diesel
                dieselService.addOrUpdate ( model,pret,consum );
            }else if(checkElectrica == true){
                //altfel, adaugam masina electrica
                electricaService.addOrUpdate ( model,pret,autonomie );
            }
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

}
