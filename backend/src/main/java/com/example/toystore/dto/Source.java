package com.example.toystore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Source {
    private int id;
    private String name;
    private String status;
    private String created;
    private String updated;
    private String address;
    private String phone;
    private String note;
}
