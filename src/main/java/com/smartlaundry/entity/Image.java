package com.smartlaundry.entity;

import com.smartlaundry.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.IMAGE)
public class Image {
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
        return "IMG" + uuid.substring(0,5);
    }


    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "file_id")
    private String fileId;
}
