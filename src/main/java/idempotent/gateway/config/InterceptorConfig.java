package idempotent.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import idempotent.gateway.interceptor.IdempotentItnterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private IdempotentItnterceptor idempotentItnterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(idempotentItnterceptor);
    }

}
