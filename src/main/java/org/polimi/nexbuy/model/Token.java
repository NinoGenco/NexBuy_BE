package org.polimi.nexbuy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Questa classe rappresenta un token
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String token;

    @ManyToOne
    private User user;

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }
}
