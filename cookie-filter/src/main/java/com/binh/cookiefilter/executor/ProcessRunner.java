package com.binh.cookiefilter.executor;

import static java.lang.System.exit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class ProcessRunner implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessRunner.class);
  private ApplicationContext applicationContext;
  private ProcessExecutor processExecutor;

  public ProcessRunner(ApplicationContext applicationContext, ProcessExecutor processExecutor) {
    this.applicationContext = applicationContext;
    this.processExecutor = processExecutor;
  }

  @Override
  public void run(String[] args) {
    LOGGER.info("Program started!");
    terminate(() -> processExecutor.executeProcess(args));
  }

  private void terminate(ExitCodeGenerator exitCodeGenerator) {
    exit(SpringApplication.exit(applicationContext, exitCodeGenerator));
  }
}
