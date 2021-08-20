package com.binh.cookiefilter.exception;

/** Custom exception for cookie log parsing */
public class LogParsingException extends Exception {
  public LogParsingException(Throwable error) {
    super(error);
  }
}
