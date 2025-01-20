package com.develop.springboot.resolver;


import io.micrometer.core.annotation.Counted;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class handles unhandled exceptions.
 * It is used to return a plain text error message to the client.
 * In case of an unhandled exception during any endpoint call, the client receives a plain text error message.
 */
@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

    /**
     * This method resolves unhandled exceptions.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler, or {@code null} if none chosen at the
     *                 time of the exception (for example, if multipart resolution failed)
     * @param ex       the exception that got thrown during handler execution
     * @return a corresponding {@link ModelAndView} to forward to, or {@code null}
     */
    @Counted(value = "exception.count", description = "Counting how many times the exception has been caught")
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
