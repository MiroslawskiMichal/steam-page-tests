package utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtilities {

    public static List<String> jsonFileToStringList(File file) throws IOException {
        ObjectMapper mapperJson = new ObjectMapper(new JsonFactory());
        List<String> jsonStringList = mapperJson.readValue(file, new TypeReference<List<String>>() {
        });
        return jsonStringList;
    }

}
