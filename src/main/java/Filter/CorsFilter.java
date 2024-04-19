package Filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class CorsFilter implements Filter {
    private static final String[] allowedOrigins = {"http://127.0.0.1:5558", "http://127.0.0.1:5501", "http://127.0.0.1:5567"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Cast ServletResponse to HttpServletResponse
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String origin = ((HttpServletRequest) request).getHeader("Origin");

        // Add CORS headers here
        if (origin != null && Arrays.asList(allowedOrigins).contains(origin)) {
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
        }
//        httpResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5558"); // Replace with your frontend origin
//        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); // Add the methods you need
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Include the headers you need
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true"); // Allow credentials (cookies)

        // Continue with the request chain
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
