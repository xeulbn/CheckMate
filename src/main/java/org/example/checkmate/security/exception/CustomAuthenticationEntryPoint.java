package org.example.checkmate.security.exception;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");

        if(isRestRequest(request)){
            handleRestResponse(request,response,exception);
        }else{
            handlePageResponse(request,response,exception);
        }
    }

    private void handlePageResponse(HttpServletRequest request, HttpServletResponse response, String exception) throws IOException {
        log.error("Page Request - Commence Get Exception : {}", exception);

        if (exception != null) {
            // 추가적인 페이지 요청에 대한 예외 처리 로직을 여기에 추가할 수 있습니다.
        }

        response.sendRedirect("/loginform");
    }

    private boolean isRestRequest(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWithHeader)|| request.getRequestURI().startsWith("/api/");
    }

    private void handleRestResponse(HttpServletRequest request, HttpServletResponse response, String exception) throws IOException {
        log.error("Rest Request - Commence Get Exception : {}", exception);

        if (exception != null) {
            if (exception.equals(JwtExceptionCode.INVALID_TOKEN.getCode())) {
                log.error("entry point >> invalid token");
                setResponse(response, JwtExceptionCode.INVALID_TOKEN);
            } else if (exception.equals(JwtExceptionCode.EXPIRED_TOKEN.getCode())) {
                log.error("entry point >> expired token");
                setResponse(response, JwtExceptionCode.EXPIRED_TOKEN);
            } else if (exception.equals(JwtExceptionCode.UNSUPPORTED_TOKEN.getCode())) {
                log.error("entry point >> unsupported token");
                setResponse(response, JwtExceptionCode.UNSUPPORTED_TOKEN);
            } else if (exception.equals(JwtExceptionCode.NOT_FOUND_TOKEN.getCode())) {
                log.error("entry point >> not found token");
                setResponse(response, JwtExceptionCode.NOT_FOUND_TOKEN);
            } else {
                setResponse(response, JwtExceptionCode.UNKNOWN_ERROR);
            }
        } else {
            setResponse(response, JwtExceptionCode.UNKNOWN_ERROR);
        }
    }

    private void setResponse(HttpServletResponse response, JwtExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        HashMap<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", exceptionCode.getMessage());
        errorInfo.put("code", exceptionCode.getCode());
        Gson gson = new Gson();
        String responseJson = gson.toJson(errorInfo);
        response.getWriter().print(responseJson);
    }
}