package com.unidev.polyfunction;

import com.google.common.collect.ImmutableMap;
import com.unidev.polyfunction.evaluator.ScriptEvaluator;
import com.unidev.polyfunction.exception.PolyFunctionException;
import com.unidev.polyfunction.exception.ScriptNotFoundException;
import java.util.List;
import javax.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolyFunctionCore {

    private static Logger LOG = LoggerFactory.getLogger(PolyFunctionCore.class);

    @Autowired
    private ScriptService scriptService;

    @Autowired
    private List<ScriptEvaluator> scriptEvaluators;

    public FunctionResponse evaluateHTTPRequest(HTTPFunctionRequest functionRequest,
        ServletRequest servletRequest) {
        LOG.info("Evaluating HTTP request {}", functionRequest);

        String script = scriptService.fetchScript(functionRequest.getScript()).orElseThrow(
            ScriptNotFoundException::new);
        for (ScriptEvaluator evaluator : scriptEvaluators) {
            if (evaluator.canHandle(functionRequest.getScript())) {
                try {
                    LOG.info("Evaluating script {} on {}", functionRequest.getScript(), evaluator);
                    ImmutableMap<String, Object> contextMap = ImmutableMap
                        .of("request", functionRequest, "servletRequest", servletRequest);
                    return evaluator.evaluate(script, contextMap);
                } catch (Throwable t) {
                    LOG.error("Failed to evaluate script {}", functionRequest.getScript(), t);
                    throw new PolyFunctionException(t);
                }
            }
        }

        throw new ScriptNotFoundException();
    }

}
