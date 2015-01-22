import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tourismapp.backend.dto.location.SceneryDto;

public class JsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationConfig.Feature.AUTO_DETECT_FIELDS, true)
				.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
				.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false)
				.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.setSerializationConfig(mapper.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL));
	}

	public static JsonNode asJsonNode(String content) {
		try {
			return mapper.readTree(content);
		} catch (Exception e) {
			logger.error("Error when converting Object to Json String", e);
			return null;
		}
	}

	public static <T> T asObject(JsonNode jsonNode, Class<T> valueType) {
		try {
			return mapper.readValue(jsonNode, valueType);
		} catch (Exception e) {
			logger.error("Error when converting Object to Json String", e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T asObject(String content, Class<T> valueType) {
		if (valueType == String.class)
			return (T) content;
		try {
			return mapper.readValue(content, valueType);
		} catch (Exception e) {
			logger.error("Error when converting Object to Json String", e);
			return null;
		}
	}

	public static String asString(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			logger.error("Error when converting Object to Json String", e);
			return null;
		}
	}

	public static void main(String[] args) {
		Map<Integer, List<SceneryDto>> result = new HashMap<Integer, List<SceneryDto>>();
		List<SceneryDto> sceneries = new ArrayList<SceneryDto>();
		SceneryDto s = new SceneryDto();
		s.setId(10);
		s.setName("迪士尼乐园");
		sceneries.add(s);
		s = new SceneryDto();
		s.setId(20);
		s.setName("海洋公园");
		sceneries.add(s);
		result.put(1, sceneries);
		System.out.println(JsonUtil.asString(result));

	}

}