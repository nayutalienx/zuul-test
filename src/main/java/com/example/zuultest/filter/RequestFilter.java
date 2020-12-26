package com.example.zuultest.filter;

import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Date timestamp = new Date();

        String requestURI = request.getRequestURI();

        Map<String, String> requestHeaders = RequestContext.getCurrentContext()
                .getZuulRequestHeaders();

        RequestContext.getCurrentContext().addZuulRequestHeader(
                "Custom-Header-thread-id-" + Thread.currentThread().getId(),
                "Timestamp:" + timestamp.toString());

        chain.doFilter(request, response);
    }

}