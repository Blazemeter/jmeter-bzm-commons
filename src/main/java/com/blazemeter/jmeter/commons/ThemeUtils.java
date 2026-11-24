package com.blazemeter.jmeter.commons;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import org.apache.jmeter.gui.action.LookAndFeelCommand;

public class ThemeUtils {

  private static final Pattern DARK_THEME_PATTERN = Pattern
      .compile("Intellij|HighContrastLight|HighContrastDark|Darcula|Motif|OneDark|SolarizedDark");
  private static final Method JMETER_IS_DARK = findJMeterIsDark();

  /**
   * Whether JMeter's active look and feel is dark. JMeter 5.3 and later answer it themselves
   * ({@code LookAndFeelCommand.isDark()}, from the active Darklaf theme), which also classifies
   * light Darklaf themes such as IntelliJ and High Contrast Light correctly; older versions are
   * matched by the name of their configured look and feel.
   */
  public static boolean isDark() {
    if (JMETER_IS_DARK != null) {
      try {
        return (Boolean) JMETER_IS_DARK.invoke(null);
      } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
        // fall back to the look and feel name
      }
    }
    return DARK_THEME_PATTERN.matcher(LookAndFeelCommand.getJMeterLaf()).find();
  }

  private static Method findJMeterIsDark() {
    try {
      return LookAndFeelCommand.class.getMethod("isDark");
    } catch (NoSuchMethodException e) {
      return null;
    }
  }
}
