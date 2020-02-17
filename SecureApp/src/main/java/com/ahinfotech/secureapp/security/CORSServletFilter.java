package com.ahinfotech.secureapp.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@WebFilter
@Component
public class CORSServletFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CORSServletFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("########## Initiating Custom filter ##########");
	}

	// default filter for every URI hit from client side
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		LOGGER.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());

		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		res.addHeader("Access-Control-Allow-Credentials", "true");
		res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		res.addHeader("Access-Control-Max-Age", "3600");
		// call next filter in filter chain
		filterChain.doFilter(req, res);
		LOGGER.info("Logging Response :{}", res.getContentType());

	}

	@Override
	public void destroy() {
	}

}
