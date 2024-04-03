package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.dashboardtests.model.PersonModelData;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {
    public static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> valueType) {
        try (InputStream stream = ResourceLoader.getResources(path)) {
             return mapper.readValue(stream, valueType);

        } catch (IOException e) {
            log.error("unable to read test data {}", path, e);

        }
        return null;

    }


}
