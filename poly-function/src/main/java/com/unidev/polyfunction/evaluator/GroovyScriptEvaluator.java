package com.unidev.polyfunction.evaluator;

import com.unidev.polyfunction.FunctionResponse;
import com.unidev.polyfunction.GenericFunctionResponse;
import com.unidev.polyfunction.ScriptService;
import com.unidev.polyfunction.exception.ScriptNotFoundException;
import groovy.lang.GroovyShell;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroovyScriptEvaluator implements ScriptEvaluator {

    @Autowired
    private ScriptService scriptService;

    @Override
    public boolean canHandle(String scriptName) {
        return scriptName.toLowerCase().endsWith(".groovy");
    }

    @Override
    public FunctionResponse evaluate(String script, Map<String, Object> context) {
        GroovyShell groovyShell = new GroovyShell();
        for(Entry<String, Object> entry : context.entrySet()) {
            groovyShell.setVariable(entry.getKey(), entry.getValue());
        }
        String groovyScript = scriptService.fetchScript(script).orElseThrow(
            ScriptNotFoundException::new);
        return GenericFunctionResponse.builder().payload(groovyShell.evaluate(groovyScript)).build();
    }


}
