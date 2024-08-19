package com.smartlaundry.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartlaundry.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.CUSTOMER)
public class Customer {

    @Id
    private String id;
    @PrePersist
    protected void prePersist() {
        if (this.id == null) {
            this.id = generateShortUUID();
        }
    }
    private String generateShortUUID() {
        String uuid = UUID.randomUUID().toString();
        return "CS" + uuid.substring(0,5);
    }

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "customerId")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account accountId;
}
