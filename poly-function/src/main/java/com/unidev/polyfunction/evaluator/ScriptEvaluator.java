package com.unidev.polyfunction.evaluator;

import com.unidev.polyfunction.FunctionRequest;
import com.unidev.polyfunction.FunctionResponse;

/**
 * Service for evaluation of scripts
 */
public interface ScriptEvaluator {

    /**
     * Check if evaluator can handle script
     * @param scriptName
     * @return
     */
    boolean canHandle(String scriptName);

    /**
     * Evaluate function
     * @param script
     * @param functionRequest
     * @return
     */
    FunctionResponse evaluate(String script, FunctionRequest functionRequest);

}
