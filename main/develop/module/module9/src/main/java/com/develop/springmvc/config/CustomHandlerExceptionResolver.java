package com.develop.springmvc.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        logger.error("Unhandled exception caught: ", ex);

        ModelAndView modelAndView = new ModelAndView();
        // Use a plain text view
        modelAndView.setViewName("error/plainTextView");
        modelAndView.addObject("message", ex.getMessage());

        return modelAndView;
    }
}
