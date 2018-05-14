
package com.asutosh.rxtrials.Networking.RetrofitPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;


public class Article {

    @SerializedName("source")
    private Source source;
    @SerializedName("author")
    private Object author;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private Object description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private Object urlToImage;
    @SerializedName("publishedAt")
    private String publishedAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(Object urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
