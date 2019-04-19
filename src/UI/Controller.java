package UI;

import Domain.Diesel;
import Domain.Electrica;
import Service.DieselService;
import Service.ElectricaService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    @FXML

    public TableView tableViewDiesel;
    public TableColumn tableColumnModel;
    public TableColumn tableColumnPret;
    public TableColumn tableColumnConsum;

    public TableView tableViewElectrica;
    public TableColumn tableColumnModelE;
    public TableColumn tableColumnPretE;
    public TableColumn tableColumnAutonomieE;

    public TextField txtGetByPrice;
    public Button btnMasinaAdd;
    public Button btnDieselDelete;
    public Button btnElectricaDelete;

    private ObservableList<Diesel> diesels = FXCollections.observableArrayList();
    private ObservableList<Electrica> electrics = FXCollections.observableArrayList();

    private DieselService dieselService;
    private ElectricaService electricaService;

    public void setServices(DieselService dieselService, ElectricaService electricaService) {
        this.dieselService = dieselService;
        this.electricaService = electricaService;
    }

    // pentru a initializa fereastra masini.fxml
    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            // initializam tabelul pentru masini diesel
            diesels.addAll(dieselService.getAll());
            tableViewDiesel.setItems(diesels);

            // initializam tabelul pentru masini electrice
            electrics.addAll(electricaService.getAll());  // in electrics pune toate masinile electrice
            tableViewElectrica.setItems(electrics);  // seteaza masinile in tabel
        });
    }

    public void btnMasinaAddClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addMasina.fxml"));
//initializarea fereastra de adaugare
            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle("Masina add");
            stage.setScene(scene);
            stage.initModality( Modality.APPLICATION_MODAL);


            MasinaAddController controller =  fxmlLoader.getController();
            controller.setService(dieselService, electricaService);
            stage.showAndWait();
            // dupa adaugarea unei masini reinitializam tabelul de  masini Diesel
            diesels.clear();
            diesels.addAll(dieselService.getAll());
// dupa adaugarea unei masini reinitializam tabelul de masini Electrice
            electrics.clear();
            electrics.addAll(electricaService.getAll());


        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log( Level.SEVERE, "Failed to create new Window: Cake add.", e);
        }
    }

    public void btnDieselDeleteClick(ActionEvent actionEvent) {
        //returneaza linia pe care o selectam in Masini Diesel
        Diesel selected = (Diesel)tableViewDiesel.getSelectionModel().getSelectedItem();
        //daca linia selectata nu e initiala, atunci sterge linia selectata
        if (selected != null) {
            try {
                dieselService.remove(selected.getModel ());
                diesels.clear();
                diesels.addAll(dieselService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    public void btnElectricaDeleteClick(ActionEvent actionEvent) {
        //returneaza linia selectata din tabelul de masini electrice
        Electrica selected = (Electrica) tableViewElectrica.getSelectionModel().getSelectedItem();
        //daca linia selectata nu e initiala, atunci sterge linia selectata
        if (selected != null) {
            try {
                electricaService.remove(selected.getModel ());
                electrics.clear();
                electrics.addAll(electricaService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

// cauta doar masinile diesel dupa pret
    public void btnGetByPriceClick(ActionEvent actionEvent) {
        double price = Double.parseDouble ( txtGetByPrice.getText () );

        if (price != 0){
            diesels.clear();
            // setam ca in tabel sa apara doar masinile cu pretul cautat
            diesels.addAll(dieselService.searchDieselByPrice(price));
        }
    }

}
