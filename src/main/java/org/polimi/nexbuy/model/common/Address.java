package org.polimi.nexbuy.model.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode; //CAP

    @Column(nullable = false)
    private String country;

    //Creo una relazione ManyToMany tra user e address
    @ManyToMany(mappedBy = "addresses")
    private Set<User> users;  //Utilizzo il set per evitare duplicati di indirizzi per lo stesso utente
}
