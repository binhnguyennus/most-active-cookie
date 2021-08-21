package com.binh.cookiefilter.executor;

public enum ProcessStatus {
  SUCCESS(0),
  PROGRAM_FAILED(1);

  private final int value;

  ProcessStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
