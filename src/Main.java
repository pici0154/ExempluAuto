import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.*;
import UI.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI/masini.fxml"));
        Parent root = fxmlLoader.load();

        Controller controller =  fxmlLoader.getController();

        IValidator<Diesel> dieselValidator = new DieselValidator();
        IValidator<Electrica> electricaValidator = new ElectricaValidator ();

        IRepository<Diesel> dieselRepository = new InMemoryRepository<>(dieselValidator);
        IRepository<Electrica> electricaRepository = new InMemoryRepository<>(electricaValidator);

        DieselService dieselService = new DieselService (dieselRepository);
        // daca elementul e string atunci pui "", altfel doar cifre
        dieselService.addOrUpdate("BMW", 10, 15);
        dieselService.addOrUpdate("Audi", 10, 5 );
        dieselService.addOrUpdate("VW", 200, 3);

        ElectricaService electricaService = new ElectricaService (electricaRepository);

        controller.setServices(dieselService, electricaService );

        primaryStage.setTitle("Masini manager");
        primaryStage.setScene(new Scene(root, 650, 500));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
