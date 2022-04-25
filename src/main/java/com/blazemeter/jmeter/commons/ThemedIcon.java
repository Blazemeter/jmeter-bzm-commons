package com.blazemeter.jmeter.commons;

import java.util.Map;
import java.util.WeakHashMap;
import javax.swing.ImageIcon;

public class ThemedIcon {

  private static final Map<String, ImageIcon> CACHED_ICONS = new WeakHashMap<>();
  private static final String RESOURCE_SEPARATOR = "/";

  public static ImageIcon fromResourceName(String resourceName) {
    String resourcePath = getThemePath() + RESOURCE_SEPARATOR + resourceName;
    return CACHED_ICONS
        .computeIfAbsent(resourcePath, p -> new ImageIcon(ThemedIcon.class.getResource(p)));
  }

  private static String getThemePath() {
    return ThemeUtils.isDark() ? "/dark-theme" : "/light-theme";
  }

}
