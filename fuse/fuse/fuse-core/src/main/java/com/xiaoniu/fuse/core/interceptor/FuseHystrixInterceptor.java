package com.xiaoniu.fuse.core.interceptor;

import com.xiaoniu.fuse.core.command.servlet.ServletHystrixCommand;
import com.xiaoniu.fuse.core.context.FuseContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Chris on 2017/9/24.
 */


public class FuseHystrixInterceptor extends HandlerInterceptorAdapter {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.request = request;
        this.response = response;
        this.handler = handler;
        if (FuseContext.urlMapConfig.containsKey(request.getRequestURI()))
            return new ServletHystrixCommand(this, FuseContext.urlMapConfig.get(request.getRequestURI())).execute();
        return super.preHandle(request, response, handler);

    }

    public boolean doPreHandle() throws Exception {
        return super.preHandle(request, response, handler);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }
}
