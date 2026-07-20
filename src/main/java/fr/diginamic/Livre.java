package fr.diginamic;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "livres")
    private List<Emprunt> emprunts = new ArrayList<>();

//    //Version @JoinTable pour utiliser la méthode testDesyncManyToMany()
//    @ManyToMany
//    @JoinTable(name = "COMPO",
//            joinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID"),
//            inverseJoinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID")
//    )
//    private List<Emprunt> emprunts = new ArrayList<>();

}
