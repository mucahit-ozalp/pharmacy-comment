package com.app.pharmacy.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PharmacyFilter implements Filter {

  private final static Logger logger = LoggerFactory.getLogger(PharmacyFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    logger.info("Initializing filter :{}", this);
  }

  @Override
  public void doFilter( ServletRequest request,  ServletResponse response,
      final FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    logger.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
    logger.info("Hello from: " + request.getLocalAddr());
    chain.doFilter(request, response);
    logger.info("Logging Response :{}", res.getContentType());

  }

  @Override
  public void destroy() {
    logger.warn("Destructing filter :{}", this);

  }
}
