package com.yiguo.mvc.converter;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 *
 * @author wanghuan
 * @date 2018-01-06
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String source) {
		return LocalDateTime.parse(source);
	}
}
