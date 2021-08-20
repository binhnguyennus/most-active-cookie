package com.binh.cookiefilter.parser;

import com.binh.cookiefilter.exception.LogParsingException;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.nio.file.Files.newBufferedReader;
import java.util.List;

import static java.nio.file.Paths.get;
import static java.time.LocalDate.parse;

public class LogParser {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogParser.class);

  /** Parsing CSV log file content */
  public static List<LogEntry> parseLog(String fileName) throws LogParsingException {
    try {
      return new CsvToBeanBuilder<LogEntry>(newBufferedReader(get(fileName)))
          .withType(LogEntry.class)
          .build()
          .parse();
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      throw new LogParsingException(e);
    }
  }

  /** Parsing command line input */
  public static CommandInput parseCommandInput(String[] args) throws LogParsingException {
    CommandInput commandInput = new CommandInput();
    CommandLineParser commandLineParser = new DefaultParser();
    Options options = parseCommandOption();

    try {
      CommandLine commandLine = commandLineParser.parse(options, args);
      commandInput.setFileName(commandLine.getOptionValue("file"));
      commandInput.setSelectedDate(parse(commandLine.getOptionValue("date")));
      return commandInput;
    } catch (ParseException e) {
      LOGGER.error(e.getMessage());
      outputCommandHelp(options);
      throw new LogParsingException(e);
    }
  }

  /** Parsing command line options (file name and selected date) */
  public static Options parseCommandOption() {
    Options commandOptions = new Options();

    // File name of cookie log
    Option fileName = new Option("f", "file", true, "The path of cookie log file");
    fileName.setRequired(true);
    commandOptions.addOption(fileName);

    // Selected date to get the most active cookie
    Option selectedDate =
        new Option("d", "date", true, "The specific date to get most active cookie");
    selectedDate.setRequired(true);
    commandOptions.addOption(selectedDate);

    return commandOptions;
  }

  /** Help message for command line options */
  private static void outputCommandHelp(Options options) {
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp("Cookie Log Filter", options);
  }
}
