package com.quanao.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/*")
public class SiteMeshFilter implements Filter {

    private static final Pattern TITLE_PATTERN = Pattern.compile("<title[^>]*>(.*?)</title>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern HEAD_PATTERN = Pattern.compile("<head[^>]*>(.*?)</head>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern BODY_PATTERN = Pattern.compile("<body[^>]*>(.*?)</body>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // Exclude static resources, downloads, and decorator page itself
        if (path.startsWith("/assets/") || path.startsWith("/uploads/") || path.startsWith("/download") || path.startsWith("/image")
                || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jpg") || path.endsWith(".jpeg")
                || path.endsWith(".png") || path.endsWith(".gif") || path.endsWith(".webp") || path.endsWith(".ico")
                || path.contains("/WEB-INF/") || Boolean.TRUE.equals(req.getAttribute("ALREADY_DECORATED"))) {
            chain.doFilter(request, response);
            return;
        }

        CharResponseWrapper responseWrapper = new CharResponseWrapper(resp);

        // Mark as being decorated to prevent infinite recursion
        req.setAttribute("ALREADY_DECORATED", Boolean.TRUE);

        try {
            chain.doFilter(request, responseWrapper);
        } finally {
            req.removeAttribute("ALREADY_DECORATED");
        }

        String contentType = responseWrapper.getContentType();
        String html = responseWrapper.toString();

        if (html != null && !html.trim().isEmpty() && (contentType == null || contentType.contains("text/html"))) {
            String title = extract(TITLE_PATTERN, html);
            String head = extract(HEAD_PATTERN, html);
            String body = extract(BODY_PATTERN, html);

            // Clean up title inside head to avoid duplication
            if (head != null && title != null) {
                head = TITLE_PATTERN.matcher(head).replaceAll("");
            }

            // Fallback if body tag is missing
            if (body == null || body.trim().isEmpty()) {
                body = html;
            }

            req.setAttribute("sitemesh_title", title != null ? title.trim() : "Quản Lý Quần Áo");
            req.setAttribute("sitemesh_head", head != null ? head.trim() : "");
            req.setAttribute("sitemesh_body", body);

            req.getRequestDispatcher("/WEB-INF/decorators/main.jsp").forward(req, resp);
        } else {
            resp.getWriter().write(html);
        }
    }

    private String extract(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    @Override
    public void destroy() {}

    private static class CharResponseWrapper extends HttpServletResponseWrapper {
        private final CharArrayWriter charWriter = new CharArrayWriter();
        private final PrintWriter writer = new PrintWriter(charWriter);

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() {
            return writer;
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public boolean isReady() { return true; }
                @Override
                public void setWriteListener(WriteListener writeListener) {}
                @Override
                public void write(int b) throws IOException {
                    charWriter.write(b);
                }
            };
        }

        @Override
        public String toString() {
            writer.flush();
            return charWriter.toString();
        }
    }
}
