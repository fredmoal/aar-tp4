package services;

import dao.ClientDao;
import dao.CompteDao;
import dao.LivretDao;
import modele.Client;
import modele.Compte;
import org.junit.Before;
import org.junit.Test;
import services.erreurs.ClientNotFoundException;
import services.erreurs.CompteNotFoundException;

import java.util.Date;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class BanqueServiceImplTest {

    ClientDao clientDao = null;
    CompteDao compteDao = null;
    LivretDao livretDao = null;

    BanqueService service = null;

    @Before
    public void setUp() {
        clientDao = createMock(ClientDao.class);
        compteDao = createMock(CompteDao.class);
        livretDao = createMock(LivretDao.class);
        service = new BanqueServiceImpl(clientDao,compteDao,livretDao);
    }

    @Test
    public void findClient() throws ClientNotFoundException {
        Client c = new Client("Moal","Fred","Orleans");

        expect(clientDao.find(308L)).andReturn(Optional.of(c));

        replay(clientDao);

        // test du getClient
        Client cret = service.getClient(308L);

        assertTrue(c.equals(cret));
        verify(clientDao);
    }











    @Test(expected = ClientNotFoundException.class)
    public void findClientNotFound() throws ClientNotFoundException {
        expect(clientDao.find(308L)).andStubReturn(Optional.empty());
        replay(clientDao);

        // test du getClient
        Client c = service.getClient(308L);
        verify(clientDao);
    }

    @Test
    public void virement() {
        // configuration des Mocks de Dao
        Compte c1 = new Compte(105L,null,500.00,new Date());
        Compte c2 = new Compte(334L,null,2500.00,new Date());

        expect(compteDao.find(105L)).andStubReturn(Optional.of(c1));
        expect(compteDao.find(334L)).andStubReturn(Optional.of(c2));
        compteDao.edit(c1);
        compteDao.edit(c2);

        replay(compteDao);

        // test du virement
        try {
            service.virement(105L, 334L, 250.00);
        } catch (CompteNotFoundException e) {
            fail();
        }

        assertEquals(250.00,  c1.getSolde(), 0.0);
        assertEquals(2750.00, c2.getSolde(), 0.0);

        verify(compteDao);
    }
}
