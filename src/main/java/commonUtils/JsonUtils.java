package commonUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {
    private static ObjectMapper mapper;
    static Map<String,String>envMap;

    public static String getBaseUri(String envPath){
        mapper=new ObjectMapper();
        try {
           envMap=mapper.readValue(new File(envPath), new TypeReference<Map<String,String>>() {});
            return envMap.get("url");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
