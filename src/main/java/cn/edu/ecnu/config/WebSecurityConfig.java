package cn.edu.ecnu.config;

import cn.edu.ecnu.filter.JWTAuthenticationEntryPoint;
import cn.edu.ecnu.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 使用自定义的 UserDetailsService 实现类进行用户名密码的认证
     * 这是将认证托付给 Spring-Security 的重要步骤
     * 配置了编码器后要求数据库中取出的密码也是通过这个编码方式加密过的
     * 因此接收注册请求的 AuthController 中需要先加密再加入数据库
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /*
    * cors() 解决跨域问题
    * csrt().disable() 关闭打开的csrf保护, CSRF会针对PATCH，POST，PUT和DELETE方法进行防护，拒绝请求。
    * 只拦截测试接口 tasks 其他放行
    * hasRole("ADMIN") 也可以写成 hasAuthority("ROLE_ADMIN")
    *
    * .antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("ADMIN")
    * 在添加了注解 @EnableGlobalMethodSecurity(prePostEnabled = true) 后
    * 可以直接在控制器方法上添加注解 @PreAuthorize("hasRole('ADMIN')") 代替
    *
    * 添加过滤器：登录认证、权限认证
    * 关闭 session，将策略设置为 stateless 不使用不生成
    * 添加认证异常处理实现类 JWTAuthenticationEntryPoint，用于处理认证失败异常信息
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/tasks/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }

    /*这玩意暂时不知道是干嘛的*/
    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",
                new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
    */
}
