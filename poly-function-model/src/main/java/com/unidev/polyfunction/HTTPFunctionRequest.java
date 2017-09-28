package com.unidev.polyfunction;


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
public class HTTPFunctionRequest {

    private String script;

    private Map<String, Object> parameters;

    public <T> HTTPFunctionRequest withParameter(String param, T value) {
        parameters.put(param, value);
        return this;
    }

}
