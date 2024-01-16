package org.marcos.ApiDbExample.tools;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import lombok.val;

public class Utils {
    
    @Autowired
    private Environment env;

    public static String formatProperty(String originalValue){
        String value;


		try {
			value = new String(originalValue.getBytes("ISO-8859-1"), Charset.forName("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			value = originalValue;
			e.printStackTrace();
		}
		return value;
    }
}
