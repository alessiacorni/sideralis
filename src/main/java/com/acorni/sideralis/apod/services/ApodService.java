package com.acorni.sideralis.apod.services;

import com.acorni.sideralis.apod.models.ApodResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class ApodService {
    private final RestTemplate restTemplate;
    private static final String APOD_URL = "https://api.nasa.gov/planetary/apod";
    private static final String KEY = "TaQegQcNAhcO3IN8ZnH5h3f0O9fVjeGU34jiKSMl";

    @Autowired
    public ApodService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String createUrl(String date){
        return String.format("%s?api_key=%s&date=%s" +
                        "&thumbs=True&hd=True", APOD_URL,
                KEY, date);
    }
    @Cacheable(value = "apodCache", key = "#date", unless = "!#result.valid")
    public ApodResponse getApod(String date) {
        log.info("Cache MISS for date " + date);
        log.info("Fetching APOD from source for date "+ date);

        try {
            ResponseEntity<ApodResponse> responseEntity = restTemplate.getForEntity(createUrl(date), ApodResponse.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                ApodResponse response = responseEntity.getBody();

                if(isResponseValid(response)){
                    response.setValid(true);
                    return response;
                }
                else{
                    return getDefaultApodResponse();
                }
            }
            else{
                log.error("Received non-OK status for date " + date + ": " + responseEntity.getStatusCode());
                return getDefaultApodResponse();
            }
        } catch (RestClientException e) {
            log.error("Error fetching APOD for date " + date);
            return getDefaultApodResponse();
        }
    }
    private Boolean isResponseValid(ApodResponse response){
        return response != null && response.getTitle() != null && response.getUrl() != null && response.getExplanation() != null;
    }
    private ApodResponse getDefaultApodResponse(){
        ApodResponse defaultResponse = new ApodResponse();
        defaultResponse.setTitle("No Title Available");
        defaultResponse.setExplanation("There was an error retrieving the data.");
        defaultResponse.setUrl("/images/No_Image_Available.jpg");
        return defaultResponse;
    }
}
