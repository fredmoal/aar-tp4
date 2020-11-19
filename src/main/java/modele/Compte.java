package modele;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Compte {
    @Id
    private long id;
    @ManyToOne
    private Client titulaire;
    private double solde;
    private Date dateOuverture;

    public Compte() {
    }

    public Compte(long id, Client titulaire, double solde, Date dateOuverture) {
        this.id = id;
        this.titulaire = titulaire;
        this.solde = solde;
        this.dateOuverture = dateOuverture;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", titulaire=" + titulaire.getId() +
                ", solde=" + solde +
                ", dateOuverture=" + dateOuverture +
                '}';
    }

    public long getId() {
        return id;
    }

    public Client getTitulaire() {
        return titulaire;
    }

    public double getSolde() {
        return solde;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setTitulaire(Client titulaire) {
        this.titulaire = titulaire;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }
}