package cn.ohyeah.ww.global;


import cn.ohyeah.ww.utils.ThreadSafeClientConnManagerUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

@Configuration
@ImportResource({"classpath*:/beans.xml"})
public class Configurations {

    @Bean(name={"processorBasePackage"})
    public String getProcessorBasePackage() {
        return "cn.ohyeah.ww.protocol.impl";
    }

    @Bean(name={"platformServer"})
    public String getPlatformServer() {
        return "http://localhost:8080/itvgame/protocolv2/processor";
    }

    @Bean(name={"defaultHttpClient"})
    public DefaultHttpClient buildDefaultHttpClient() {
        return ThreadSafeClientConnManagerUtil.buildDefaultHttpClient();
    }

    @Bean(name={"mapFileRootPath"})
    public String getMapFileRootPath() {
        return "";
    }

    @Bean(name={"mapFileExt"})
    public String getMapFileExt() {
        return "";
    }
}
