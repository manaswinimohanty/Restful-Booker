package commonUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter


public class AssertionKeys {
    private String jsonPath;
    private Object expectedValue;
    private Object actualValue;
    private String result;

}
