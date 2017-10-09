package com.unidev.polyfunction.evaluator;

import com.unidev.polyfunction.FunctionResponse;
import java.util.Map;

/**
 * Service for evaluation of scripts
 */
public interface ScriptEvaluator {

    /**
     * Check if evaluator can handle script
     */
    boolean canHandle(String scriptName);

    /**
     * Evaluate function from script
     */
    FunctionResponse evaluate(String script, Map<String, Object> context);

}
