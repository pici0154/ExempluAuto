package Service;

import Domain.Electrica;
import Repository.IRepository;

import java.util.List;

public class ElectricaService {
    private IRepository<Electrica> repository;


    public ElectricaService(IRepository<Electrica> repository) {
        this.repository = repository;
    }


    //face insert sau update
    public void addOrUpdate(String model, double pret, double autonomie) {
        //verifica daca exista masina cu modelul dat
        Electrica existing = repository.findByModel(model);
        //daca exista atunci o sa faca update
        if (existing != null) {
            // keep unchanged fields as they were
            if (model.isEmpty()) {
                model = existing.getModel();
            }
            if (pret == 0 ) {
                pret = existing.getPret();
            }
            if (autonomie == 0) {
                autonomie = existing.getAutonomie();
            }

        }
        Electrica masina = new Electrica(model,pret, autonomie);
        repository.upsert(masina);
    }
    //sterge dupa modelul dat
    public void remove(String model) {
        repository.remove(model);
    }

    //returneaza toate masinile diesel
    public List<Electrica> getAll() {
        return repository.getAll();
    }

}
