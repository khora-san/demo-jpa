package fr.diginamic;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "EMPRUNT")
@Getter
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "DATE_DEBUT")
    private String dateDebut;
    @Column(name = "DATE_FIN")
    private String dateFin;
    @Column(name = "DELAI")
    private Integer delai;

    @ManyToOne
    @JoinColumn(name="ID_CLIENT")
    private Client client;

}
