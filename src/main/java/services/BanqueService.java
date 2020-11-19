package services;

import modele.Client;
import modele.Compte;
import modele.Livret;
import services.erreurs.ClientNotFoundException;
import services.erreurs.CompteNotFoundException;

import java.util.Collection;

public interface BanqueService {
    void virement(long idSource, long idDestination, double montant) throws CompteNotFoundException;

    Collection<Client> getAllClients();

    Client getClient(long id) throws ClientNotFoundException;

    Collection<Livret> getAllLivrets();

    Collection<Compte> getAllComptes();

    Collection<Compte> getComptesOfClient(long idClient) throws ClientNotFoundException;

    void saveClients(Client... clients);

    void deleteClient(long id) throws ClientNotFoundException;
}
