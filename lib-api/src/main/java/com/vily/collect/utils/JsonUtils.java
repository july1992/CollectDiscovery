package com.vily.collect.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jackson对象
 */
public class JsonUtils {

	/**
	 * 	定义jackson对象
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串
	 * @param data
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param beanType
	 *            对象中的object类型
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * @param jsonData
	 * @param beanType
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * json数据转换成pojo对象map
	 * @param jsonData
	 * @param beanType
	 */
	public static <T> Map<String, Object> jsonToMap(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(HashMap.class, beanType);
		try {
			HashMap<String, Object> map = MAPPER.readValue(jsonData, javaType);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


}
