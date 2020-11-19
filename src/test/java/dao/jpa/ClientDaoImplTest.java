package dao.jpa;

import dao.AbstractDaoTest;
import dao.ClientDao;
import modele.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientDaoImplTest extends AbstractDaoTest {

    @Autowired
    ClientDao clientDao;

    @Test
    public void create() {
    }

    @Test
    public void edit() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void findOK() {
        Optional<Client> client = clientDao.find(1003L);
        assertThat(client).isNotEmpty();
        assertThat(client.get().getId()).isEqualTo(1003L);
        assertThat(client.get().getPrenom()).isEqualTo("Paul");
        assertThat(client.get().getComptes()).hasSize(1);
    }

    @Test
    public void findKO() {
        Optional<Client> client = clientDao.find(99999L);
        assertThat(client).isEmpty();
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findRange() {
    }

    @Test
    public void count() {
    }
}