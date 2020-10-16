package cc.mrbird.febs.hkgf.utils;

import cc.mrbird.febs.hkgf.config.UrlConfig;
import cc.mrbird.febs.hkgf.entity.request.CheckParams;
import cc.mrbird.febs.hkgf.entity.response.CheckResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtils {
    @Autowired
    private UrlConfig config;

    public HttpUtils() {
    }

    public CheckResponse getCheckData(CheckParams params) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1800000);
        requestFactory.setReadTimeout(1800000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity(params.toString(), headers);
        return (CheckResponse)restTemplate.postForObject(this.config.getChargeUrl(), formEntity, CheckResponse.class, new Object[0]);
    }

    public String syncMxItem(String url, String params) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1800000);
        requestFactory.setReadTimeout(1800000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity(params, headers);
        return (String)restTemplate.postForObject(url, formEntity, String.class, new Object[0]);
    }

    public String getSyncStart(String intoHospitalId, String outHospitalId) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1800000);
        requestFactory.setReadTimeout(1800000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String URL = this.config.getSyncUrl() + "?intoHospitalId={intoHospitalId}&outHospitalId={outHospitalId}";
        Map<String, Object> uriVariables = new HashMap();
        uriVariables.put("intoHospitalId", intoHospitalId);
        uriVariables.put("outHospitalId", outHospitalId);
        return (String)restTemplate.getForObject(URL, String.class, uriVariables);
    }
}

