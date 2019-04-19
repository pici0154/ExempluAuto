package Service;

import Domain.Diesel;
import Repository.IRepository;

import java.util.*;

public class DieselService {
    private IRepository<Diesel> repository;

    public DieselService(IRepository<Diesel> repository) {
        this.repository = repository;
    }
//face insert sau update
    public void addOrUpdate(String model, double pret, double consum) {
      //verifica daca exista masina cu modelul dat
        Diesel existing = repository.findByModel(model);
        //daca exista atunci o sa faca update
        if (existing != null) {
            // keep unchanged fields as they were
            if (model.isEmpty()) {
                model = existing.getModel();
            }
            if (pret == 0 ) {
                pret = existing.getPret();
            }
            if (consum == 0) {
                consum = existing.getConsum();
            }

        }
        Diesel masina = new Diesel(model,pret, consum);
        repository.upsert(masina);
    }
//sterge dupa modelul dat
    public void remove(String model) {
        repository.remove(model);
    }

//returneaza toate masinile diesel
    public List<Diesel> getAll() {
        return repository.getAll();
    }

    // returneaza masinile dupa pret
    public List<Diesel> searchDieselByPrice(double price) {
        List<Diesel> masini_diesel = new ArrayList<Diesel> ();

        for(Diesel d: getAll ()){
            // verificam ca fiecare masina din lista de masini diesel sa aiba pretul dupa care cautam
            // daca are pretul, o adaugam in masini_diesel
            if(d.getPret () == price ) {
                masini_diesel.add ( d );
            }
        }
// returnam doar masinile care au pretul cautat
        return  masini_diesel;
    }
}

