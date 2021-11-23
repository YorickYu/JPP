package com.yy.async;

import com.alibaba.fastjson.JSONObject;
import com.yy.utils.CommonParamUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = null;
        CommonParamTransform originCommonParam = null;

        try {
            if (RequestContextHolder.getRequestAttributes() != null) {
                // 获取请求上下文
                request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                // 获取 主线程 threadlocal
                originCommonParam = CommonParamUtil.getPublicContext();
            }else {
                // 获取 子线程 threadlocal
                originCommonParam = CommonParamUtil.getTransformContext();
            }
        } catch (Exception ex) {
            log.error("error happens: ", ex);
        }

        Map<String, Collection<String>> headers = new HashMap<>();
        // todo 将请求 上下文中的header和threadlocal传递的参数组装

        log.debug("interceptor header:{}",JSONObject.toJSONString(headers));
        requestTemplate.headers(headers);
    }
}
