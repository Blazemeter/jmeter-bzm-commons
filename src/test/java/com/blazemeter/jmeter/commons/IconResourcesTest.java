package com.blazemeter.jmeter.commons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * Every icon the library's code asks for exists in both themes, in this library's own resources:
 * a plugin that shades the library must not need to bring them.
 */
class IconResourcesTest {

  private static final Pattern ICON = Pattern.compile("\"([\\w.-]+\\.png)\"");

  @Test
  void everyIconUsedExistsInBothThemes() throws IOException {
    Set<String> used = new TreeSet<>();
    try (Stream<Path> sources = Files.walk(Paths.get("src/main/java"))) {
      sources.filter(p -> p.toString().endsWith(".java")).forEach(p -> {
        try {
          Matcher m = ICON.matcher(new String(Files.readAllBytes(p), StandardCharsets.UTF_8));
          while (m.find()) {
            used.add(m.group(1));
          }
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
      });
    }
    assertFalse(used.isEmpty());
    for (String icon : used) {
      for (String theme : new String[] {"/dark-theme/", "/light-theme/"}) {
        assertNotNull(IconResourcesTest.class.getResource(theme + icon), theme + icon);
      }
    }
  }
}
