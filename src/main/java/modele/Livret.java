package modele;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Livret extends Compte {
    private double tauxInteret;

    public Livret() {
        super();
    }

    public Livret(long id, Client titulaire, double solde, Date dateOuverture, double tauxInteret) {
        super(id, titulaire, solde, dateOuverture);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public String toString() {
        return "Livret{" + super.toString() +
                " ,tauxInteret=" + tauxInteret +
                '}';
    }
}
