package cn.edu.ecnu.filter;

import cn.edu.ecnu.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // tokenHeader = Authorization，如果请求头中没有 Authorization 说明是登录或注册请求，则直接放行
        String tokenHeader =  request.getHeader(JwtUtil.TOKEN_HEADER);
         if (tokenHeader == null || !tokenHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
             chain.doFilter(request, response);
             return;
         }
        // 这里请求头中有 Authorization 证明已经登录需要进行权限认证，解析并设置认证信息
        // 解析就通过 jwtUtil 中使用密钥得到 token 原有的载荷的方式进行
        // SecurityContextHolder 保留系统当前的安全上下文细节，其中就包括当前使用系统的用户的信息。
        // Authentication 对象存储了当前与系统交互的用户的信息，这里用继承了该接口的 UsernamePasswordAuthenticationToken 存储。
        // 疑问：这里如果携带 token 就代表身份正确吗，不应该与服务器中已存在的 token 做比对吗，
        // 这样的话携带别人的 token 是否可以利用其他人的身份了
        // 回答：好像确实，不过如果与 ip 相绑定可以解决部分问题
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
         super.doFilterInternal(request, response, chain);
    }

    /* 从 token 中获取用户信息，并新建一个 token*/
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtUtil.TOKEN_PREFIX, "");
        String username = JwtUtil.getUsername(token);
        String role = JwtUtil.getUserRole(token);
        if (username != null) {
            // 解析方法载荷中 jwtUtil 创建 token 时添加的角色，加入权限判断
            // 认证的过滤器中密码也需要存储在 token 中，此处为何不需要加上 password ?
            return new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(new SimpleGrantedAuthority(role)));
        }
        return null;
    }
}
