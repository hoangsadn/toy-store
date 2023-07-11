package com.example.toystore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private int id;
    private String name;
    private int qty;
    private String thumbnail;
    private String image;
    private int price;
    private String created;
    private String updated;
    private String status;
    private String note;
    private ProductType type;
    private Source source;
    private int typeId;
    private int sourceId;
}
