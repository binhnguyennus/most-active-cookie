package com.binh.cookiefilter.parser;

import com.binh.cookiefilter.exception.LogParsingException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogParser {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogParser.class);

  /** Parsing command line options (file name and selected date) using Apache Commons CLI library */
  public static Options parseOptions() {
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
  private static void outputHelp(Options options) {
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp("Cookie Log Filter", options);
  }
}
