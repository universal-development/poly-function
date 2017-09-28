package com.unidev.polyfunction;


import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HTTPFuncitonRequest {

    private String script;

    private Map<String, Object> parameters = new HashMap<>();

    public <T> HTTPFuncitonRequest withParameter(String param, T value) {
        parameters.put(param, value);
        return this;
    }

}
