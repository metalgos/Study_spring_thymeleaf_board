package kr.studyprj2.hancoding.board.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//설정 클래스로 등록
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath ="/upload/**"; //view단에서 접근할 경로
    private String savePath = "file:///C:/temp/"; //실제 파일의 저장된 경로
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
    }

}

