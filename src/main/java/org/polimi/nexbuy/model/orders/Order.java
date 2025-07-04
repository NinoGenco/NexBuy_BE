package org.polimi.nexbuy.model.orders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polimi.nexbuy.model.common.Address;
import org.polimi.nexbuy.model.common.User;
import org.polimi.nexbuy.model.enums.OrderStatus;
import org.polimi.nexbuy.model.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Data dell'acquisto
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    // Stato dell'ordine (IN_ATTESA, PAGATO, SPEDITO, ANNULLATO, ecc.)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    // Totale dell'ordine
    @Column(name = "order_total", nullable = false)
    private Double total;

    // Tipo di pagamento (es. CARTA, BONIFICO, PAYPAL)
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    // Email del cliente (se non vuoi mappare un'entità utente)
    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    // Indirizzo di spedizione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    // Indirizzo di fatturazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;

    // Prodotti ordinati (relazione OneToMany)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;;

}
