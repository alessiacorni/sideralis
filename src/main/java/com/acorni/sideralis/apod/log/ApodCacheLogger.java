package com.acorni.sideralis.apod.log;

import com.acorni.sideralis.apod.models.ApodResponse;
import com.acorni.sideralis.apod.services.ApodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApodCacheLogger {
    private final ApodService apodService;
    @Autowired
    public ApodCacheLogger(ApodService apodService) {
        this.apodService = apodService;
    }
    public void fetchAndLogApod(String date){
        log.debug("Requesting APOD for date: " + date);
        ApodResponse response = apodService.getApod(date);
        log.info("APOD for date " + date + "-->" + response);
    }
}
