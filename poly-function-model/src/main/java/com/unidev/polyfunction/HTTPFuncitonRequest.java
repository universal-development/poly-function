package com.unidev.polyfunction;


import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HTTPFuncitonRequest {

    private String script;

    private Map<String, Object> parameters = new HashMap<>();

    public <T> HTTPFuncitonRequest withParameter(String param, T value) {
        parameters.put(param, value);
        return this;
    }

}
