package com.acorni.sideralis.apod.controllers;

import com.acorni.sideralis.apod.models.ApodResponse;
import com.acorni.sideralis.apod.services.ApodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ApodController {
    private final ApodService apodService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    public ApodController(ApodService apodService) {
        this.apodService = apodService;
    }
    @GetMapping("/apod")
    public String apodPage(@RequestParam(value = "date", defaultValue = "today") String dateParam, Model model){
        String userDate;
        if ("today".equals(dateParam)) {
            userDate = LocalDateTime.now().format(formatter);
        } else {
            userDate = dateParam;
        }

        ApodResponse response = apodService.getApod(userDate);
        String title = response.getTitle() != null ? response.getTitle() : "No Title Available";
        String explanation = response.getExplanation() != null ? response.getExplanation() : "No Explanation Available";
        String url = response.getHurl() != null ? response.getHurl() : response.getUrl();
        String thumbnailUrl = response.getThumbnailUrl() != null ? response.getThumbnailUrl() : null;
        String date = response.getDate();
        String copyright = response.getCopyright();


        model.addAttribute("title", title);
        model.addAttribute("imageUrl", url);
        model.addAttribute("thumbnailUrl", thumbnailUrl);
        model.addAttribute("explanation", explanation);
        model.addAttribute("date", date);
        model.addAttribute("copyright", copyright);
        return "apod";
    }
}
