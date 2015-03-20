package com.hdg.rjra.customer.web.filter;

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.JsonUtils;
import com.hdg.rjra.base.error.ErrorType;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Title: ValidateTokenFilter.java    
 * Description: 验证Token
 * @author Sisi       
 * 创建时间 2014年7月8日 下午5:05:56
 */
public class ValidateTokenFilter implements Filter{    
    /**
     * 存储不需要验证Token的Urls
     */
    private List<String> excludeUrls;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {     
        String pExcludeUrls = filterConfig.getInitParameter("excludeUrls");
        
        if (StringUtils.isNotEmpty(pExcludeUrls)){
            excludeUrls = Arrays.asList(pExcludeUrls.split(CommonConstants.SYMBOL_COMMA));
        }    
    }

   /**
    * 
    * @description 验证Token
    * @author Administrator       
    * 创建时间 2014年7月8日 下午5:05:42      
    * @param request request
    * @param response response
    * @param chain chain
    * @throws java.io.IOException IOException
    * @throws javax.servlet.ServletException ServletException
    * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, 
    * javax.servlet.ServletResponse, javax.servlet.FilterChain)
    */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
                
        //判断当前请求是否需要跳过Token验证
        String servletPath = httpRequest.getServletPath();
        if (isSkipValidate(servletPath)){
            //跳过验证
            chain.doFilter(httpRequest, httpResponse);
        }
        else {
            //验证Token
            String token = httpRequest.getParameter("token");
            HttpSession session = httpRequest.getSession(false);

            if (null != session
                    && httpRequest.isRequestedSessionIdValid()
                    && StringUtils.isNotEmpty(token) && token.equals(getSessionToken(session))) {
                if ("/rjra-customer".equals(servletPath)){
                    //验证未通过
                    OutputResult outputResult = new OutputResult();
                    outputResult.setFlag(CommonConstants.SUCCESS);
                    postJson(httpResponse, JsonUtils.objectToJson(outputResult));
                } else {
                    //验证通过
                    chain.doFilter(httpRequest, httpResponse);
                }

            }
            else {
                //验证未通过
                OutputResult outputResult = new OutputResult();
                outputResult.setFlag(CommonConstants.FAIL);
                outputResult.setMsg(ErrorType.INVALID_TOKEN.getMessage());
                outputResult.setErrorCode(ErrorType.INVALID_TOKEN.getCode());
                postJson(httpResponse, JsonUtils.objectToJson(outputResult));
            }
        }       
    }

    /**
     * 获取sessionToken
     * @param session session
     * @return String
     */
    private String getSessionToken(HttpSession session) {
        //获取Session中的Token
        String sessionToken = (String)session.getAttribute(SessionToken.TOKEN);
        return sessionToken;
    }

    public static void postJson(HttpServletResponse response, String json) {
        // 给前台传递参数
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");

        try {
            // 以前使用这种方式提交时，使用tomcat的startup.bat启动后，Ajax获取的中文显示乱码
            // 改为PrintWriter提交后就不会乱码了
            // By Mobi 2014年1月8日10:00:15
            // OutputStream os = response.getOutputStream();
            // os.write(json.getBytes());

            // 用PrintWriter提交不会乱码
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @description 根据被请求路径，判读是否跳过验证
     * @author Administrator       
     * 创建时间 2014年7月8日 下午6:11:31     
     * @param servletPath 请求路径
     * @return isSkip
     */
    private boolean isSkipValidate(String servletPath) {
        boolean isSkip = false;
        for (String excludeUrl : excludeUrls) {
            if (servletPath.startsWith(excludeUrl)) {
                isSkip = true;
                break;
            }
        }
        
        return isSkip;
    }

    @Override
    public void destroy() {
        
    }

}
