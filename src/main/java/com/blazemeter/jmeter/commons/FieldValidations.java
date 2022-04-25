package com.blazemeter.jmeter.commons;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FieldValidations {
  private final Border defaultBorder = new JTextField().getBorder();
  private final Border invalidBorder = BorderFactory.createLineBorder(Color.red);

  private JTextField field;
  private final JLabel error;
  private List<Validation> validations = new ArrayList<>();

  public FieldValidations(JTextField field, JLabel error) {
    this.field = field;
    this.error = error;
  }

  public void setField(JTextField field) {
    this.field = field;
  }

  public JTextField getField() {
    return field;
  }

  public void addValidations(Validation... validation) {
    validations = Arrays.asList(validation);
  }

  public List<Validation> getValidations() {
    return validations;
  }

  public void applyFormat() {
    updateValidationStates();
    boolean valid = isValid();
    field.setBorder(valid ? defaultBorder : invalidBorder);
    error.setVisible(!valid);
    error.setText(valid ? "" : validations.stream()
        .filter(validation -> !validation.isValid())
        .map(Validation::getErrorMessage)
        .collect(Collectors.joining(". ")));
  }

  public void updateValidationStates() {
    validations.forEach(validation -> validation.updateState(field.getText()));
  }

  public boolean isValid() {
    return validations.stream().allMatch(Validation::isValid);
  }
}
