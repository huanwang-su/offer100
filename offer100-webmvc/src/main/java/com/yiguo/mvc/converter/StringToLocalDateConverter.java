package com.yiguo.mvc.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * 日期转换器
 *
 * @author wanghuan
 * @date 2018-01-06
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		try {
			return LocalDate.parse(source);
		} catch (Exception e) {
			return null;
		}
	}
}
