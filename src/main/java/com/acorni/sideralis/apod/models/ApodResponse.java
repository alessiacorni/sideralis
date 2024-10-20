package com.acorni.sideralis.apod.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApodResponse {
    private Resource resource;
    private Boolean conceptTags;
    private String title;
    private String date;
    private String url;
    private String hurl;
    private String mediaType;
    private String explanation;
    private String[] concepts;
    private String thumbnailUrl;
    private String copyright;
    private String serviceVersion;
    private boolean valid;

    public ApodResponse() {
    }

    public ApodResponse(Resource resource, Boolean conceptTags, String title, String date, String url, String hurl,
                        String mediaType, String explanation, String[] concepts, String thumbnailUrl,
                        String copyright, String serviceVersion) {
        this.resource = resource;
        this.conceptTags = conceptTags;
        this.title = title;
        this.date = date;
        this.url = url;
        this.hurl = hurl;
        this.mediaType = mediaType;
        this.explanation = explanation;
        this.concepts = concepts;
        this.thumbnailUrl = thumbnailUrl;
        this.copyright = copyright;
        this.serviceVersion = serviceVersion;
        this.valid = false;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @JsonProperty("concept_tags")
    public Boolean getConceptTags() {
        return conceptTags;
    }

    @JsonProperty("concept_tags")
    public void setConceptTags(Boolean conceptTags) {
        this.conceptTags = conceptTags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHurl() {
        return hurl;
    }

    public void setHurl(String hurl) {
        this.hurl = hurl;
    }

    @JsonProperty("media_type")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("media_type")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String[] getConcepts() {
        if (!conceptTags) {
            return new String[0];
        }
        return concepts;
    }

    public void setConcepts(String[] concepts) {
        this.concepts = concepts;
    }

    @JsonProperty("thumbnail_url")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @JsonProperty("service_version")
    public String getServiceVersion() {
        return serviceVersion;
    }

    @JsonProperty("service_version")
    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    private static class Resource {
        private String imageSet;
        private String planet;

        public Resource() {
        }

        public Resource(String imageSet, String planet) {
            this.imageSet = imageSet;
            this.planet = planet;
        }

        @JsonProperty("image_set")
        public String getImageSet() {
            return imageSet;
        }

        @JsonProperty("image_set")
        public void setImageSet(String imageSet) {
            this.imageSet = imageSet;
        }

        public String getPlanet() {
            return planet;
        }

        public void setPlanet(String planet) {
            this.planet = planet;
        }
    }

    @Override
    public String toString() {
        return "ApodResponse{" + "resource=" + resource + ", conceptTags=" + conceptTags + ", title='" + title + '\'' + ", date='" + date + '\'' + ", url='" + url + '\'' + ", hurl='" + hurl + '\'' + ", mediaType='" + mediaType + '\'' + ", explanation='" + explanation + '\'' + ", concepts=" + Arrays.toString(concepts) + ", thumbnailUrl='" + thumbnailUrl + '\'' + ", copyright='" + copyright + '\'' + ", serviceVersion='" + serviceVersion + '\'' + '}';
    }
}
