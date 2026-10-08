package com.blazemeter.jmeter.commons;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.ImageIcon;

public class ThemedIcon {

  /*
   A strong, thread-safe cache: a WeakHashMap keyed by paths built on each call dropped its
   entries at the next garbage collection, and icons were loaded again.
   */
  private static final Map<String, ImageIcon> CACHED_ICONS = new ConcurrentHashMap<>();
  private static final String RESOURCE_SEPARATOR = "/";

  public static ImageIcon fromResourceName(String resourceName) {
    String resourcePath = getThemePath() + RESOURCE_SEPARATOR + resourceName;
    return CACHED_ICONS.computeIfAbsent(resourcePath, ThemedIcon::load);
  }

  private static ImageIcon load(String resourcePath) {
    URL url = ThemedIcon.class.getResource(resourcePath);
    if (url == null) {
      throw new IllegalArgumentException("No icon resource " + resourcePath);
    }
    return new ImageIcon(url);
  }

  private static String getThemePath() {
    return ThemeUtils.isDark() ? "/dark-theme" : "/light-theme";
  }

}
