package com.binh.cookiefilter.parser;

import java.time.LocalDate;

/** Class to handle command input, which is f: filename, and d: selected date */
public class CommandInput {
  private String fileName;
  private LocalDate selectedDate;

  // All arguments constructor
  public CommandInput(String fileName, LocalDate selectedDate) {
    this.fileName = fileName;
    this.selectedDate = selectedDate;
  }

  // No argument constructor
  public CommandInput() {}

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public LocalDate getSelectedDate() {
    return selectedDate;
  }

  public void setSelectedDate(LocalDate selectedDate) {
    this.selectedDate = selectedDate;
  }
}
