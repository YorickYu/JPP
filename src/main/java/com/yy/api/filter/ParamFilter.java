package com.yy.api.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
public class ParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ContentCachingResponseWrapper responseWrapper = null;

        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            /* cached */
            CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);

            // body
            JSONObject body = getPostBody(cachedBodyHttpServletRequest);
            // header
            String header_1 = cachedBodyHttpServletRequest.getHeader("header-1");
            // param
            String parameter_1 = cachedBodyHttpServletRequest.getParameter("param-1");

            // todo ...

            HttpServletResponse response = (HttpServletResponse) servletResponse;
            responseWrapper = new ContentCachingResponseWrapper(response);
            filterChain.doFilter(cachedBodyHttpServletRequest, responseWrapper);

        } catch (Exception ex) {
            log.warn("##ParamFilter## exception: ", ex);
        } finally {
            if (responseWrapper != null) {
                byte[] respBytes = responseWrapper.getContentAsByteArray();
                if (respBytes.length > 0) {
                    String charset = responseWrapper.getCharacterEncoding();

                    // response
                    String respStr = new String(respBytes, charset);

                    // todo ...

                    responseWrapper.resetBuffer();
                    responseWrapper.getWriter().write(respStr);
                }
                responseWrapper.copyBodyToResponse();
            }
        }
    }

    // cache 中获取 body
    private JSONObject getPostBody(HttpServletRequest request) {

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        JSONObject jsonObject = JSONObject.parseObject(jb.toString());

        return jsonObject;
        // Work with the data using methods like...
        // int someInt = jsonObject.getInt("intParamName");
        // String someString = jsonObject.getString("stringParamName");
        // JSONObject nestedObj = jsonObject.getJSONObject("nestedObjName");
        // JSONArray arr = jsonObject.getJSONArray("arrayParamName");
        // etc...
    }
}
