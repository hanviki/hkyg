package cc.mrbird.febs.hkgf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlConfig {
    @Value("${urlconfig.url}")
    private String url;

    public UrlConfig() {
    }

    public String getChargeUrl() {
        return this.url + "checkout";
    }

    public String getSyncUrl() {
        return this.url + "sync";
    }
}
