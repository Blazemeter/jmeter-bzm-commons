package com.blazemeter.jmeter.commons;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;

public class SwingBuilders {

  public static class ComponentBuilder {

    private String name;
    private String tooltip;
    private String text;
    private String title;
    private String placeholder;
    private int fontType;
    private boolean visible = true;
    private Dimension preferredSize;
    private JComponent component;

    public ComponentBuilder() {
    }

    public ComponentBuilder notVisible() {
      return isVisible(false);
    }

    public ComponentBuilder isVisible(boolean visible) {
      this.visible = visible;
      return this;
    }

    public ComponentBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public ComponentBuilder withPreferredSize(Dimension preferredSize) {
      this.preferredSize = preferredSize;
      return this;
    }

    public ComponentBuilder forComponent(JComponent component) {
      this.component = component;
      return this;
    }

    public ComponentBuilder withToolTip(String toolTip) {
      this.tooltip = toolTip;
      return this;
    }

    public ComponentBuilder withItalicFont() {
      this.fontType = Font.ITALIC;
      return this;
    }

    public ComponentBuilder withText(String text) {
      this.text = text;
      return this;
    }

    public ComponentBuilder withTitle(String title) {
      this.title = title;
      return this;
    }

    public ComponentBuilder withPlaceholder(String placeholder) {
      this.placeholder = placeholder;
      return this;
    }

    public JComponent build() {
      component.setName(name);
      if (!visible) {
        component.setVisible(false);
      }

      if (preferredSize != null) {
        component.setPreferredSize(preferredSize);
      }

      if (tooltip != null) {
        component.setToolTipText(tooltip);
      }

      if (fontType != 0) {
        component.setFont(component.getFont().deriveFont(fontType));
      }

      if (placeholder != null) {
        if (component instanceof PlaceHolderPassword) {
          ((PlaceHolderPassword) component).setPlaceHolder(placeholder);
        } else if (component instanceof PlaceHolderTextField) {
          ((PlaceHolderTextField) component).setPlaceHolder(placeholder);
        }
      }

      if (text != null) {
        if (component instanceof JTextField) {
          ((JTextField) component).setText(text);
        } else if (component instanceof JLabel) {
          ((JLabel) component).setText(text);
        }
      }

      if (title != null && component instanceof JPanel) {
        component.setBorder(BorderFactory.createTitledBorder(title));
      }

      return component;
    }

    public PlaceHolderPassword buildPlaceHolderPassword() {
      return (PlaceHolderPassword) this.build();
    }

    public PlaceHolderTextField buildPlaceHolderTextField() {
      return (PlaceHolderTextField) this.build();
    }

    public JLabel buildLabel() {
      component = new JLabel();
      return (JLabel) this.build();
    }

    public JTextField buildJTextField() {
      component = new JTextField();
      return (JTextField) this.build();
    }

    public JPanel buildPanel() {
      this.component = new JPanel();
      return (JPanel) this.build();
    }
  }
}
