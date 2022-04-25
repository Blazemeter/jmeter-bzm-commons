package com.blazemeter.jmeter.commons;

import java.util.regex.Pattern;
import org.apache.jmeter.gui.action.LookAndFeelCommand;

public class ThemeUtils {

  private static final Pattern DARK_THEME_PATTERN = Pattern
      .compile("Intellij|HighContrastLight|HighContrastDark|Darcula|Motif|OneDark|SolarizedDark");

  public static boolean isDark() {
    return DARK_THEME_PATTERN.matcher(LookAndFeelCommand.getJMeterLaf()).find();
  }
}
