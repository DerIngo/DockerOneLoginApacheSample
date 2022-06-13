package deringo.oneloginjavaappsample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deringo.configuration.Config;


@WebFilter("/*")
public class AccessFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class.getName());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AccessFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("AccessFilter doFilter");
		
		// Reinit Config every time to load config changes
		// for testing only, don't do this in real world
		Config.reinit();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if(Config.is("accessfilter.dumpheaders")) {
			dumpHeaders(httpRequest);
		}
		
		// Check User ID
		String userid = httpRequest.getHeader(Config.get("accessfilter.useridheader"));
		if (StringUtils.isBlank(userid) && Config.is("accessfilter.activate")) {
			logger.warn("Login attempt with no userid");
			response.getWriter().println("Access denied");
			if(Config.is("accessfilter.showheaders")) {
				showheaders(httpRequest, response.getWriter());
			}
			response.getWriter().close();
		} 
		
		// Check Role
		String oidc_claim_params = httpRequest.getHeader("oidc_claim_params");
		List<String> roles = getRoles(oidc_claim_params);
		String requiredRole = Config.get("accessfilter.requiredrole");
		if (!roles.contains(requiredRole) && Config.is("accessfilter.activate")) {
			logger.warn("Login attempt without required role");
			response.getWriter().println("Access denied");
			if(Config.is("accessfilter.showheaders")) {
				showheaders(httpRequest, response.getWriter());
			}
			response.getWriter().close();
		} 

		// Grant access
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("AccessFilter destroy");
	}

	private static List<String> getRoles(String oidc_claim_params) {
		List<String> rolesList = new ArrayList<>();
		try {
			JSONObject o = new JSONObject(oidc_claim_params);
			String rolesS = o.get("roles").toString();
			String[] roles = StringUtils.split(rolesS, ";");
			rolesList = Arrays.asList(roles);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return rolesList;
	}
	
	public static void dumpHeaders(HttpServletRequest httpRequest) {
		logger.info("AccessFilter: New Request to: "+httpRequest.getRequestURL());
		Enumeration<String> mHeaderNames = httpRequest.getHeaderNames();
		while (mHeaderNames.hasMoreElements()) {
			String element = mHeaderNames.nextElement();
			Enumeration<String> values = httpRequest.getHeaders(element);
			StringBuffer value = new StringBuffer();
			while (values.hasMoreElements()) {
				value.append(values.nextElement());
				value.append(" | ");					
			}
			logger.info("AccessFilter: Header: "+ element+": "+value.toString());
		}
		Enumeration<String> mParameterNames = httpRequest.getParameterNames();
		if (!mParameterNames.hasMoreElements())
			logger.info("AccessFilter: No parameters.");
		while (mParameterNames.hasMoreElements()) {
			String element = mParameterNames.nextElement();
			String[] values = httpRequest.getParameterValues(element);
			StringBuffer value = new StringBuffer();
			for (int i = 0; i < values.length; i++)
			{
				value.append((String) values[i]);
				value.append(" | ");					
			}
			logger.info("AccessFilter: Parameter: "+ element+": "+value.toString());
		}
	}
	
	public static void showheaders(HttpServletRequest httpRequest, PrintWriter writer) {
		writer.println("AccessFilter: New Request to: "+httpRequest.getRequestURL());
		Enumeration<String> mHeaderNames = httpRequest.getHeaderNames();
		while (mHeaderNames.hasMoreElements()) {
			String element = mHeaderNames.nextElement();
			Enumeration<String> values = httpRequest.getHeaders(element);
			StringBuffer value = new StringBuffer();
			while (values.hasMoreElements()) {
				value.append(values.nextElement());
				value.append(" | ");					
			}
			writer.println("AccessFilter: Header: "+ element+": "+value.toString());
		}
		Enumeration<String> mParameterNames = httpRequest.getParameterNames();
		if (!mParameterNames.hasMoreElements())
			writer.println("AccessFilter: No parameters.");
		while (mParameterNames.hasMoreElements()) {
			String element = mParameterNames.nextElement();
			String[] values = httpRequest.getParameterValues(element);
			StringBuffer value = new StringBuffer();
			for (int i = 0; i < values.length; i++)
			{
				value.append((String) values[i]);
				value.append(" | ");					
			}
			writer.println("AccessFilter: Parameter: "+ element+": "+value.toString());
		}
	}
}
