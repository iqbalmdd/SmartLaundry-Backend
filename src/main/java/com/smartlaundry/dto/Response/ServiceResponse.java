package com.smartlaundry.dto.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse {
    private String name;
    private String imagePath;
}
