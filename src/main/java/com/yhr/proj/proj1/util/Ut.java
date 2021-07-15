package com.yhr.proj.proj1.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhr.proj.proj1.dto.Member;

public class Ut {

	public static String f(String string, Object... args) {
		return String.format(string, args);
	}

	public static Map<String, Object> mapOf(Object... args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("인자를 짝수개 입력해주세요.");
        }

        int size = args.length / 2;

        Map<String, Object> map = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            int keyIndex = i * 2;
            int valueIndex = keyIndex + 1;

            String key;
            Object value;

            try {
                key = (String) args[keyIndex];
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("키는 String으로 입력해야 합니다. " + e.getMessage());
            }

            value = args[valueIndex];

            map.put(key, value);
        }

        return map;
    }
	
	
    // 객체를 Mapping해서 Json형태로 바꿔서 리턴
	public static String toJson(Object obj) {
		ObjectMapper om = new ObjectMapper();
		
		try {
			return om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
	// Json을 객체로 만들어서 리턴
	public static <T> T toObjFromJson(String jsonStr, TypeReference<T> typeReference) {
        ObjectMapper om = new ObjectMapper();

        try {
            return (T) om.readValue(jsonStr, typeReference);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

	// 오버라이딩  ->  ex) Member.class
    public static <T> T toObjFromJson(String jsonStr, Class<T> cls) {
        ObjectMapper om = new ObjectMapper();

        try {
            return (T) om.readValue(jsonStr, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }


}
