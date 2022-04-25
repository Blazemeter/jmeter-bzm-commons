package com.blazemeter.jmeter.commons;

public class StringUtils {

  public static String capitalize(String text) {
    return text != null && !text.isEmpty() ? text.substring(0, 1).toUpperCase() + text.substring(1)
        : text;
  }

}
