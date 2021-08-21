package com.binh.cookiefilter;

import com.binh.cookiefilter.exception.LogParsingException;
import com.binh.cookiefilter.filter.CookieFilterImpl;
import com.binh.cookiefilter.parser.CommandInput;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
class CookieFilterApplicationTests {

  private CookieFilterImpl cookieFilter;
  private PrintStream stdout = System.out;
  private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    cookieFilter = new CookieFilterImpl();
    PrintStream output = new PrintStream(byteArrayOutputStream);
    System.setOut(output);
  }

  @AfterEach
  public void tearDown() {
    System.setOut(stdout);
  }

  /** When file path and date are valid, print cookie strings */
  @Test
  public void filterMostActiveCookies_ValidFilePathAndDate_OutputCookieStrings()
      throws LogParsingException {
    CommandInput commandInput = new CommandInput("src/logs/cookie_log.csv", parse("2018-12-09"));
    cookieFilter.filterMostActiveCookies(commandInput);
    assertThat(byteArrayOutputStream.toString().contains("AtY0laUfhglK3lC7"));
  }

  /** When file path is invalid, throw exception */
  @Test
  public void filterMostActiveCookies_InvalidFilePath_ThrowException() {
    CommandInput commandInput = new CommandInput("/src/logs/dummy.csv", parse("2018-12-09"));
    assertThatThrownBy(() -> cookieFilter.filterMostActiveCookies(commandInput))
        .isInstanceOf(LogParsingException.class);
  }

  /** When date is not exist, output should be empty */
  @Test
  public void filterMostActiveCookies_InvalidDate_ThrowException() throws LogParsingException {
    CommandInput commandInput = new CommandInput("src/logs/cookie_log.csv", parse("2021-12-09"));
    cookieFilter.filterMostActiveCookies(commandInput);
    assertThat(byteArrayOutputStream.toString().isEmpty());
  }
}
