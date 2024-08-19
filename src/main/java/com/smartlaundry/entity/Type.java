package com.smartlaundry.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartlaundry.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.TYPE)
public class Type {
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
        return "T" + uuid.substring(0,5);
    }

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "typeId")
    @JsonBackReference
    private List<ServiceType> serviceTypes;
}
