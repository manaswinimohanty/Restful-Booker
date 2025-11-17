package models.requestModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BaseModel {
    @JsonIgnore
    protected String ScenerioID;
    @JsonIgnore
    protected String ScenrioDesc;
    @JsonIgnore
    @JsonProperty("Expected StatusCode")
    protected int ExpectedStatusCode;
    @JsonIgnore
    @JsonProperty("Expected ErrorMessage")
    protected String ExpectedErrorMessage;


}
