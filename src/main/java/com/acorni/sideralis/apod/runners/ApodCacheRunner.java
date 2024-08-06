package com.acorni.sideralis.apod.runners;
import com.acorni.sideralis.apod.log.ApodCacheLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApodCacheRunner implements CommandLineRunner {
    private final ApodCacheLogger apodCacheLogger;

    public ApodCacheRunner(ApodCacheLogger apodCacheLogger) {
        this.apodCacheLogger = apodCacheLogger;
    }

    @Override
    public void run(String... args) throws Exception {
        runCacheLogger();
    }
    private void runCacheLogger(){
        log.info("...Starting APOD fetch demonstration");

        apodCacheLogger.fetchAndLogApod("2024-01-01");
        apodCacheLogger.fetchAndLogApod("2024-02-02");
        apodCacheLogger.fetchAndLogApod("2024-01-01");
        apodCacheLogger.fetchAndLogApod("2024-01-01");
        apodCacheLogger.fetchAndLogApod("2024-03-03");
        apodCacheLogger.fetchAndLogApod("2024-02-02");
        apodCacheLogger.fetchAndLogApod("2024-02-02");

        log.info("...APOD fetch demonstration complete");
    }
}
