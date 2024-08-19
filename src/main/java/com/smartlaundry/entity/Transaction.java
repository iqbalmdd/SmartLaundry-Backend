package com.smartlaundry.entity;

import com.smartlaundry.constant.ConstantTable;
import com.smartlaundry.constant.PaymentMethod;
import com.smartlaundry.constant.PaymentStatus;
import com.smartlaundry.constant.LaundryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.TRANSACTION)
public class Transaction {

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
        return uuid.substring(0,5);
    }

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceTypeId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LaundryStatus laundryStatus;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


}
