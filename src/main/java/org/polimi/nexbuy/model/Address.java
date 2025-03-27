package org.polimi.nexbuy.model;

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
    private String street;
    private String city;
    private String state;
    private String zipCode; //CAP
    private String country;

    //Creo una relazione ManyToMany tra user e address
    @ManyToMany(mappedBy = "addresses")
    private Set<User> users;  //Utilizzo il set per evitare duplicati di indirizzi per lo stesso utente
}
