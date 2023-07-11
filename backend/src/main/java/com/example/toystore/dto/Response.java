package com.example.toystore.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Data
@Setter(AccessLevel.NONE)
@Jacksonized
public class Response<T> extends BaseResponse {
    private T data;
}
