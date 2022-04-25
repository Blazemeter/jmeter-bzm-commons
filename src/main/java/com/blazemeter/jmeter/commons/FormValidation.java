package com.blazemeter.jmeter.commons;

import java.util.List;
import javax.swing.JTextField;

public class FormValidation {
  private final List<FieldValidations> fieldValidations;
  private Runnable onValid;
  private Runnable onInvalid;
  private boolean active = true;

  public FormValidation(List<FieldValidations> fieldValidations) {
    this.fieldValidations = fieldValidations;
  }

  public void onSuccess(Runnable runnable) {
    this.onValid = runnable;
  }

  public void onFailure(Runnable runnable) {
    this.onInvalid = runnable;
  }

  public boolean isValid() {
    fieldValidations.forEach(FieldValidations::updateValidationStates);
    return fieldValidations.stream().allMatch(FieldValidations::isValid);
  }

  public void updateFormats(JTextField source) {
    boolean allFieldsValid = true;
    for (FieldValidations fieldValidations : fieldValidations) {
      fieldValidations.updateValidationStates();

      //If at least 1 field is invalid, the form is invalid
      if (!fieldValidations.isValid()) {
        allFieldsValid = false;
      }

      //Apply the format to the field if the triggering field is
      // the same as the field being validated
      if (fieldValidations.getField().equals(source)) {
        fieldValidations.applyFormat();
      }
    }

    if (allFieldsValid) {
      onValid.run();
    } else {
      onInvalid.run();
    }
  }

  public void applyFormats() {
    fieldValidations.forEach(FieldValidations::applyFormat);
  }

  public void validate(JTextField field) {
    updateFormats(field);
  }

  public void validate() {
    if (!active) {
      return;
    }

    if (isValid()) {
      onValid.run();
    } else {
      onInvalid.run();
    }
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
