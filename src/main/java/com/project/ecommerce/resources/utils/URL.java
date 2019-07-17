package com.project.ecommerce.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static List<Long> toList(String str){
		List<Long> lst = new ArrayList<Long>();
		String[] lstStr = str.split(",");
		for (String string : lstStr) {
			lst.add(new Long(string));
		}
		return lst;
	}
	
	public static String decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
