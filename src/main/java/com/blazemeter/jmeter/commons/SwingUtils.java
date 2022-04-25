package com.blazemeter.jmeter.commons;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import org.apache.jmeter.util.JMeterUtils;

public class SwingUtils {
  public static <T extends JComponent> T createComponent(String name, T component) {
    component.setName(name);
    return component;
  }

  public static final class ButtonBuilder {

    private ActionListener actionListener;
    private String action;
    private String name;
    private String iconName = "";
    private ImageIcon icon;
    private boolean enabled = true;
    private boolean hasText = true;

    public ButtonBuilder() {

    }

    public ButtonBuilder withActionListener(ActionListener actionListener) {
      this.actionListener = actionListener;
      return this;
    }

    public ButtonBuilder withAction(String action) {
      this.action = action;
      return this;
    }

    public ButtonBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public ButtonBuilder withIcon(String iconName) {
      this.iconName = iconName;
      return this;
    }

    public ButtonBuilder withIcon(ImageIcon icon) {
      this.icon = icon;
      return this;
    }

    public ButtonBuilder isEnabled(boolean enabled) {
      this.enabled = enabled;
      return this;
    }

    public ButtonBuilder hasText(boolean hasText) {
      this.hasText = hasText;
      return this;
    }

    public JButton build() {
      String parsedName = JMeterUtils.getResString(name);

      JButton button = createComponent(name + "Button", new JButton());

      button.setActionCommand(action);
      button.addActionListener(actionListener);
      button.setEnabled(enabled);
      button.setIcon(iconName.isEmpty() ? icon : ThemedIcon.fromResourceName(iconName));
      button.setText(!hasText ? ""
          : parsedName.contains("res_key") ? StringUtils.capitalize(name) : parsedName);
      return button;
    }
  }

}
