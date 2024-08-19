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
@Table(name = ConstantTable.SERVICE)
public class Service {

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
        return "SV" + uuid.substring(0,5);
    }

    @Column(name = "name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account accountId;

    @OneToMany(mappedBy = "serviceId")
    @JsonBackReference
    private List<ServiceType> serviceTypesId;
}