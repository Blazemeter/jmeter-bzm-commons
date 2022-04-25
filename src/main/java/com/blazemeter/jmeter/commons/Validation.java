package com.blazemeter.jmeter.commons;

import java.util.function.Predicate;

public class Validation {

  private final Predicate<String> condition;
  private final String errorMessage;
  private boolean valid;

  public Validation(Predicate<String> condition, String errorMessage) {
    this.condition = condition;
    this.errorMessage = errorMessage;
  }

  public void updateState(String value) {
    this.valid = condition.test(value);
  }

  public boolean isValid() {
    return valid;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
