package services;

import dao.ClientDao;
import dao.CompteDao;
import dao.LivretDao;
import modele.Client;
import modele.Compte;
import modele.Livret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import services.erreurs.ClientNotFoundException;
import services.erreurs.CompteNotFoundException;

import java.util.Collection;
import java.util.Optional;

public class BanqueServiceImpl implements BanqueService {

    private ClientDao clientDao;
    private CompteDao compteDao;
    private LivretDao livretDao;

    @Autowired
    public BanqueServiceImpl(ClientDao clientDao, CompteDao compteDao, LivretDao livretDao) {
        this.clientDao = clientDao;
        this.compteDao = compteDao;
        this.livretDao = livretDao;
    }

    @Override
    @Transactional
    public void virement(long idSource, long idDestination, double montant) throws CompteNotFoundException {
        Optional<Compte> osource = compteDao.find(idSource);
        Optional<Compte> odest   = compteDao.find(idDestination);

        Compte source = osource.orElseThrow( () -> new CompteNotFoundException("id "+idSource+" not found"));
        Compte dest = odest.orElseThrow(() -> new CompteNotFoundException("id "+idDestination+" not found"));
        source.setSolde(source.getSolde()-montant);
        dest.setSolde(dest.getSolde()+montant);

        compteDao.edit(source);
        compteDao.edit(dest);
    }

    @Override
    public Collection<Client> getAllClients() {
        return clientDao.findAll();
    }

    @Override
    public Client getClient(long id) throws ClientNotFoundException {
        return clientDao.find(id)
                .orElseThrow( ()->new ClientNotFoundException("id "+id +" not found") );
    }

    @Override
    public Collection<Livret> getAllLivrets() {
        return livretDao.findAll();
    }

    @Override
    public Collection<Compte> getAllComptes() {
        return compteDao.findAll();
    }

    @Override
    public Collection<Compte> getComptesOfClient(long idClient) throws ClientNotFoundException {
        Client client = clientDao.find(idClient).orElseThrow(()->new ClientNotFoundException("id "+idClient +" not found"));
        return client.getComptes();
    }

    @Override
    @Transactional
    public void saveClients(Client... clients) {
        for(Client c:clients) {
            clientDao.create(c);
        }
    }

    @Override
    @Transactional
    public void deleteClient(long id) throws ClientNotFoundException {
        Client client = clientDao.find(id).orElseThrow(()->new ClientNotFoundException("id "+id +" not found"));
        clientDao.remove(client);
    }
}
