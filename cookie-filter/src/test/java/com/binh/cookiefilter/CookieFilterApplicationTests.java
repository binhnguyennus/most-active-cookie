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

  /** When file path and date are valid, output the most active cookie strings */
  @Test
  public void filterMostActiveCookies_ValidFilePathAndDate_OutputCookieStrings()
      throws LogParsingException {
    CommandInput commandInput = new CommandInput("src/logs/cookie_log.csv", parse("2018-12-09"));
    cookieFilter.filterMostActiveCookies(commandInput);
    assertThat(byteArrayOutputStream.toString().contains("AtY0laUfhglK3lC7"));
    assertThat(!byteArrayOutputStream.toString().contains("SAZuXPGUrfbcn5UA"));
    assertThat(!byteArrayOutputStream.toString().contains("5UAVanZf6UtGyKVS"));
    assertThat(!byteArrayOutputStream.toString().contains("AtY0laUfhglK3lC7"));
  }

  /** When a specific date has more than one most active cookies, output all of them */
  @Test
  public void filterMostActiveCookies_HasMoreThanOneMostActiveCookies_OutputAllOfThem()
      throws LogParsingException {
    CommandInput commandInput = new CommandInput("src/logs/cookie_log.csv", parse("2018-12-06"));
    cookieFilter.filterMostActiveCookies(commandInput);
    assertThat(byteArrayOutputStream.toString().contains("8xYHIASHaBa79xzf"));
    assertThat(byteArrayOutputStream.toString().contains("1dSLJdsaDJLDsdSd"));
    assertThat(!byteArrayOutputStream.toString().contains("A8SADNasdNadBBda"));
  }

  /** When file path is invalid, throw exception */
  @Test
  public void filterMostActiveCookies_InvalidFilePath_ThrowException() {
    CommandInput commandInput = new CommandInput("/src/logs/dummy.csv", parse("2018-12-09"));
    assertThatThrownBy(() -> cookieFilter.filterMostActiveCookies(commandInput))
        .isInstanceOf(LogParsingException.class);
  }

  /** When date is not exist, output is empty (no cookie) */
  @Test
  public void filterMostActiveCookies_InvalidDate_ThrowException() throws LogParsingException {
    CommandInput commandInput = new CommandInput("src/logs/cookie_log.csv", parse("2021-12-09"));
    cookieFilter.filterMostActiveCookies(commandInput);
    assertThat(byteArrayOutputStream.toString().isEmpty());
  }
}
