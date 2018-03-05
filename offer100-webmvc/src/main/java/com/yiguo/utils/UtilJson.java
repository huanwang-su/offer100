/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yiguo.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * 
 * json工具类，使用<a href="http://jackson.codehaus.org/">jackson json</a><br>
 * 所有操作采用原始json语义操作
 * 
 * 不加0表示对日期处理
 * 
 * @author <a href="mailto:1360527082@qq.com">王焕</a> 2017年6月10日
 */
public class UtilJson {
	protected static final Logger logger = (Logger) LoggerFactory.getLogger(UtilJson.class);
	public static final ObjectMapper mapper0 = newObjectMapper0(), mapper = newObjectMapper(), webMapper = mapper;

	private static ObjectMapper newObjectMapper0() {
		ObjectMapper result = new ObjectMapper();
		result.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		result.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		result.setSerializationInclusion(Include.NON_NULL);
		result.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false); // 不输出value=null的属性
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 不知道的属性，不异常
		return result;
	}

	public static ObjectMapper newObjectMapper() {
		ObjectMapper result = newObjectMapper0();
		result.setDateFormat(new SimpleDateFormat(UtilDate.defaultPattern));
		return result;
	}

	/** only {@linkplain #mapper0} */
	public static String writeValueAsString0(Object value) {
		try {
			return mapper0.writeValueAsString(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/** only {@linkplain #mapper0} */
	public static <T> T readValue0(String content, Class<T> type) {
		if (content == null)
			return null;

		try {
			return mapper0.readValue(content, type);
		} catch (IOException e) {
			throw new IllegalArgumentException(content, e);
		}
	}

	public static String writerWithDefaultPrettyPrinter(Object value) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static byte[] writerWithDefaultPrettyPrinterAsBytes(Object value) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static byte[] writeValueAsBytes(Object value) {
		try {
			return mapper.writeValueAsBytes(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String writeValueAsString(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/** 对象转换为map，如果是字符串，先转成json对象再转为map */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(Object value) throws IllegalArgumentException {
		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			try {
				return mapper.readValue((String) value, Map.class);
			} catch (IOException e) {
				throw new IllegalArgumentException((String) value, e);
			}
		}
		return mapper.convertValue(value, Map.class);
	}

	/** 返回结果不为空 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String value) {
		if (StringUtils.isEmpty(value)) {
			return new HashMap<>();
		}
		try {
			return mapper.readValue(value, Map.class);
		} catch (IOException e) {
			throw new IllegalArgumentException(value, e);
		}
	}

	/**
	 * 读取为指定类型
	 * 
	 * @param content
	 *            json字符串（特殊处理：输入null返回null）
	 * @param type
	 *            目标类型
	 * @return
	 * @since 0.1.0
	 */
	public static <T> T readValue(String content, Class<T> type) {
		if (content == null) {
			return null;
		}
		try {
			return mapper.readValue(content, type);
		} catch (IOException e) {
			throw new IllegalArgumentException(content, e);
		}
	}

	/**
	 * 读取为指定类型
	 * 
	 * @param content
	 *            json字符串（特殊处理：输入null返回null）
	 * @param type
	 *            目标类型
	 * @return
	 * @since 0.1.0
	 */
	public static <T> T readValue(String content, JavaType type) {
		if (content == null) {
			return null;
		}
		try {
			return mapper.readValue(content, type);
		} catch (IOException e) {
			throw new IllegalArgumentException(content, e);
		}
	}

	/**
	 * 范型readValue json ==> Pager&lt;MyBean&gt;: readValue(json, Pager.class,
	 * MyBean.class)<br>
	 * json ==> List<Set<Integer>>: readValue(json, List.class, Integer.class)<br>
	 */
	public static <T> T readValue(String json, Class<?> parametrized, Class<?> parametersFor,
			Class<?>... parameterClasses) {
		if (json == null) {
			return null;
		}

		JavaType type;
		if (parameterClasses == null || parameterClasses.length == 0) {
			type = mapper.getTypeFactory().constructParametrizedType(parametrized, parametrized, parametersFor);
		} else {
			type = mapper.getTypeFactory().constructParametrizedType(parametrized, parametersFor, parameterClasses);
		}

		try {
			return mapper.readValue(json, type);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public static <T> T readMap(String content, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
		if (content == null) {
			return null;
		}
		try {
			return mapper.readValue(content, mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> List<T> readList(String content, Class<?> collectionClass, Class<T> elementClass) {
		if (content == null) {
			return null;
		}
		try {
			return mapper.readValue(content, mapper.getTypeFactory()
					.constructCollectionLikeType(collectionClass == null ? List.class : collectionClass, elementClass));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> List<T> readList(String content, Class<T> elementClass) {
		return readList(content, null, elementClass);
	}

	/**
	 * 转换为目标类，如果value是字符串，将被认为是json串<br>
	 * 所以特别注意：'"abc"'是json字符串，目标类型是String时，转换结果为'abc'而不是'"abc"'<br>
	 * 
	 * @param value
	 * @param clazz
	 * @return
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertValue(Object value, Class<T> clazz) throws IllegalArgumentException {
		if (value == null) {
			return null;
		}
		try {
			if (value instanceof String) {
				if (!String.class.equals(clazz) && ((String) value).isEmpty()) {
					return null;
				}
				if (String.class.equals(clazz) && ((String) value).isEmpty()) {
					return (T) value;
				}
				value = mapper.readTree((String) value);
			}
			return mapper.convertValue(value, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static TypeFactory getTypeFactory() {
		return mapper.getTypeFactory();
	}

	public static boolean equals(Object obj1, Object obj2) {
		if (obj1 == obj2)
			return true;
		if (obj1 == null || obj2 == null)
			return false;
		return Objects.equals(writeValueAsString(obj1), writeValueAsString(obj2));
	}

	public static String toString(Object obj) {
		try {
			return UtilJson.writeValueAsString(obj);
		} catch (Exception e) {
			logger.info("[联系开发者]转换为json异常：{}", obj, e);
		}
		return obj == null ? null : obj.toString();
	}
}
