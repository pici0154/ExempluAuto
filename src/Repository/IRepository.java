package Repository;

import Domain.Masina;

import java.util.ArrayList;
import java.util.List;

public interface IRepository<T extends Masina> {

    //metoda de findById sau findByModel - verifica daca exista obiectul in lista
    // cu ajutorul ei verici ca id sa fie unic

    T findByModel (String model);
    //T findById (String id);

    // faci insert sau update
    void upsert(T masina);

    // stergi dupa model/ id
    void remove(String model);

    //returneaza toate obiectele din lista(MAsini de tip diesel & electrice)
    List<T> getAll();
}