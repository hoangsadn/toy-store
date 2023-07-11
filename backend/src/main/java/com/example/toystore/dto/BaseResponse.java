package com.example.toystore.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@Setter(AccessLevel.NONE)
public abstract class BaseResponse {
    private int code;
    private String message;
}

