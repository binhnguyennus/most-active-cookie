package com.binh.cookiefilter.parser;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class LogEntry {
  @CsvBindByPosition(position = 0) // first column, i.e cookie
  private String cookie;

  @CsvBindByPosition(position = 1) // second column, i.e timestamp
  @CsvDate(value = "yyyy-MM-dd'T'HH:mm:ss'00+00:00'")
  private LocalDateTime timestamp;

  /** getter, setter, not allow to use Lombok or any additional libraries here */
  public String getCookie() {
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  /** Cookie log entry as a string */
  @Override
  public String toString() {
    return "Cookie Log Entry: cookie=" + cookie + ", timestamp=" + timestamp;
  }
}
