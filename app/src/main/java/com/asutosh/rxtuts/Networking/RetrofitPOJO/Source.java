
package com.asutosh.rxtuts.Networking.RetrofitPOJO;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Source {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
