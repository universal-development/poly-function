package com.unidev.polyfunction.evaluator;

import com.unidev.polyfunction.FunctionResponse;
import com.unidev.polyfunction.GenericFunctionResponse;
import com.unidev.polyfunction.exception.PolyFunctionException;
import groovy.lang.GroovyShell;
import java.util.Map;
import java.util.Map.Entry;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class ScalaScriptEvaluator implements ScriptEvaluator {

    @Override
    public boolean canHandle(String scriptName) {
        return scriptName.toLowerCase().endsWith(".scala");
    }

    @Override
    public FunctionResponse evaluate(String script, Map<String, Object> context) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("scala");

        for(Map.Entry<String, Object> entry : context.entrySet()) {
            engine.put(entry.getKey(), entry.getValue());
        }
        try {
            return GenericFunctionResponse.builder().payload(engine.eval(script)).build();
        } catch (ScriptException e) {
            log.error("Failed to run scala script", e);
            throw new PolyFunctionException(e);
        }
    }


}
