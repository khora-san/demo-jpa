package fr.diginamic;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Livre")
@Getter
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TITRE")
    private String titre;
    @Column(name = "AUTEUR")
    private String auteur;
}
