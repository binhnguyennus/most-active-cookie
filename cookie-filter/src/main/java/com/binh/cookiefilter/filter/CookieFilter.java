package com.binh.cookiefilter.filter;

import com.binh.cookiefilter.exception.LogParsingException;
import com.binh.cookiefilter.parser.CommandInput;

/** Interface for filtering the most active cookies */
public interface CookieFilter {
  void filterMostActiveCookies(CommandInput commandInput) throws LogParsingException;
}
