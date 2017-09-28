package com.unidev.polyfunction;


import java.util.HashMap;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class FuncitonRequest {

    private String script;

    private HashMap<String, Object> parameters;

}
