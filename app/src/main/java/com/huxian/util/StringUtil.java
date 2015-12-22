package com.huxian.util;

import java.util.List;

/**
 * @author huxian99
 */
public class StringUtil {

    public static String listInsertComma(List<String> list) {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0, len = list.size(); i < len; i++) {
                stringBuilder.append(list.get(i)).append(",");
            }
            result = stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return result;
    }

}
