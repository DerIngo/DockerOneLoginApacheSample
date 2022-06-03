package deringo.oneloginjavaappsample;

import java.io.IOException;
import java.util.ArrayList;
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

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;


//@WebFilter("/*")
public class AccessFilterVersion1 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("AccessFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("AccessFilter doFilter");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String oidc_claim_email = httpRequest.getHeader("oidc_claim_email");
		String oidc_claim_preferred_username = httpRequest.getHeader("oidc_claim_preferred_username");
		String userid = oidc_claim_email;
		
		String oidc_claim_params = httpRequest.getHeader("oidc_claim_params");
		List<String> roles = getRoles(oidc_claim_params);
		boolean hasRequiredRole = roles.contains("user");
		
		if (!StringUtils.isBlank(userid) && hasRequiredRole) {
			// Grant access
			chain.doFilter(request, response);
		} else {
			response.getWriter().println("Access denied");
			response.getWriter().close();
		}
	}

	@Override
	public void destroy() {
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
			e.printStackTrace();
		}
		return rolesList;
	}
}
