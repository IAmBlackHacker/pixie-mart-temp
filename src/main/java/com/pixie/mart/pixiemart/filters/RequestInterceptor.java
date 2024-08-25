package com.pixie.mart.pixiemart.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        response.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE + ","
                + MediaType.MULTIPART_FORM_DATA_VALUE + "," + MediaType.ALL_VALUE);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
