package com.api.models.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
	
	@JsonIgnore
    private String scenerioID;
    @JsonIgnore
    private String scenrioDesc;
    @JsonIgnore
    private int expectedStatusCode;
    @JsonIgnore
    private String expectedErrorMessage;

}
