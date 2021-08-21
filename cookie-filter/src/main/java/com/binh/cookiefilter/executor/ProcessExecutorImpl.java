package com.binh.cookiefilter.executor;

import com.binh.cookiefilter.exception.LogParsingException;
import com.binh.cookiefilter.filter.CookieFilter;
import com.binh.cookiefilter.parser.CommandInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.binh.cookiefilter.parser.LogParser.parseCommandInput;
import static com.binh.cookiefilter.executor.ProcessStatus.SUCCESS;
import static com.binh.cookiefilter.executor.ProcessStatus.PROGRAM_FAILED;

/** Implementation for the interface ProcessExecutor */
public class ProcessExecutorImpl implements ProcessExecutor {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExecutorImpl.class);
  private CookieFilter cookieFilter;

  public ProcessExecutorImpl(CookieFilter cookieFilter) {
    this.cookieFilter = cookieFilter;
  }

  @Override
  public int executeProcess(String[] args) {
    try {
      CommandInput commandInput = parseCommandInput(args);
      cookieFilter.filterMostActiveCookies(commandInput);
      return SUCCESS.getValue();
    } catch (LogParsingException | RuntimeException e) {
      LOGGER.error("Program failed!", e);
    }
    return PROGRAM_FAILED.getValue();
  }
}
