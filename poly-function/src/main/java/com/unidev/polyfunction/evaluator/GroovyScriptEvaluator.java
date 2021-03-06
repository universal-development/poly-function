package com.unidev.polyfunction.evaluator;

import com.unidev.polyfunction.FunctionResponse;
import com.unidev.polyfunction.GenericFunctionResponse;
import groovy.lang.GroovyShell;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;

@Service
public class GroovyScriptEvaluator implements ScriptEvaluator {

    @Override
    public boolean canHandle(String scriptName) {
        return scriptName.toLowerCase().endsWith(".groovy");
    }

    @Override
    public FunctionResponse evaluate(String script, Map<String, Object> context) {
        GroovyShell groovyShell = new GroovyShell();
        for (Entry<String, Object> entry : context.entrySet()) {
            groovyShell.setVariable(entry.getKey(), entry.getValue());
        }
        return GenericFunctionResponse.builder().payload(groovyShell.evaluate(script)).build();
    }


}
