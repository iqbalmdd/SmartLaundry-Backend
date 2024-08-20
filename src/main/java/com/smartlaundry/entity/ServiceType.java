package com.smartlaundry.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smartlaundry.constant.ConstantTable;
import com.smartlaundry.constant.Type;
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
@Table(name = ConstantTable.SERVICETYPE)
public class ServiceType {

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
        return "SVT" + uuid.substring(0,5);
    }

    @Column(name = "name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "serviceType")
    private List<Transaction> transactions;

    @Column(name = "price")
    private Long price;

}
