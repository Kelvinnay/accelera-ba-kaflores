package com.enyoi.orders.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "client_id")
    private String clientId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    // ADD CascadeType.PERSIST or CascadeType.ALL here
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> productOrderList;
    //cascade = CascadeType.ALL: This tells JPA to cascade all operations
    // (persist, merge, remove, refresh, detach) from the Order entity to its associated ProductOrder entities.
    // For saving, CascadeType.PERSIST would also suffice.

    //orphanRemoval = true: This is highly recommended for OneToMany relationships.
    // If a ProductOrder is removed from the productOrderList of an Order and the Order is then saved,
    // orphanRemoval = true will ensure that the ProductOrder is also deleted from the database. This prevents orphaned records.


}
