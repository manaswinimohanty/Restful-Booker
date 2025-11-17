package models.requestModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

//@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class CreateTokenPojoUsingDDT extends BaseModel{

    private String username;
    private String password;




}
