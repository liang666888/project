package pers.wl.album.config.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import pers.wl.album.common.constants.BaseConstants;
import pers.wl.album.controller.wechat.dto.LoginUser;
import pers.wl.album.service.LoginService;
import pers.wl.album.util.LogUtil;
import pers.wl.album.util.SpringBeanUtil;
import pers.wl.album.util.UserContextUtil;
import pers.wl.album.util.WebUtil;
import pers.wl.common.enums.RetCodeEnum;
import pers.wl.common.utils.result.ApiResult;
import pers.wl.common.utils.result.ApiResultUtil;

/**
 * 登录过滤器 验证登录状态
 * 
 * @author wuliang
 * @version $Id: LoginFilter.java, v 0.1 2018年11月7日 下午2:57:26 wuliang Exp $
 */
@Component
@WebFilter(filterName="tokenFilter",description="登录过滤器",urlPatterns="/*")
//指定过滤器的执行顺序,值越小越靠后执行
@Order(6)
public class TokenFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
	
	@Value("${login.except.uri:}")
	private String exceptURI;

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		String httpMethod = request.getMethod();
		LogUtil.info(logger, MessageFormat.format("接收到web请求[IP:{0},URL:{1},HTTP_METHOD:{2}]",
				WebUtil.getIpAddress(request), request.getRequestURL().toString(), httpMethod));
		// 非拦截URL列表，逗号分隔
		List<String> exceptURIList = Arrays.asList(exceptURI.split(","));
		if (uri.startsWith("/static")) {
			// 静态资源不进行过滤
			chain.doFilter(servletRequest, servletResponse);
			return;
		} else if (!exceptURIList.contains(uri)) {
			// 校验登录状态
			if (!checkLoginToken(request)) {
				LogUtil.info(logger, "TokenFilter登录校验失败", "请求地址", uri, request.getHeader("X-Requested-With"));
				PrintWriter out = response.getWriter();
				ApiResult<Object> result = ApiResultUtil.error(RetCodeEnum.TOKEN_EXPIRED);
				out.write(JSON.toJSONString(result));
				out.flush();
				out.close();
				return;
			} else {
				chain.doFilter(servletRequest, servletResponse);
			}
		} else {
			chain.doFilter(servletRequest, servletResponse);
			return;

		}
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * 校验登录token
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkLoginToken(HttpServletRequest request) {
		boolean validated = false;
		// 网关令牌
		String loginToken = request.getHeader(BaseConstants.WECHAT_APP_LOGIN_TOKEN);
		LogUtil.info(logger, MessageFormat.format("请求token:{0}", loginToken));
		// 判断是否已登录
		if (StringUtils.isNotBlank(loginToken)) {
			// 判断登token是否已过期
			LoginService loginService = SpringBeanUtil.getBean(LoginService.class);
			LoginUser loginUser = loginService.checkWechatAppLoginToken(loginToken);
			if (loginUser != null) {
				// 登录状态有效，设置登录用户上下文
				UserContextUtil.setUserContext(loginUser);
				// 设置登录用户权限过滤链
				validated = true;
			} else {
				LogUtil.info(logger, MessageFormat.format("请求token:{0}无效", loginToken));
			}
		} else {
		}
		return validated;
	}
}
