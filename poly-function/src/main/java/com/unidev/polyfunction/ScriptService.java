package com.unidev.polyfunction;


import com.unidev.polyfunction.exception.PolyFunctionException;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
@Slf4j
public class ScriptService {

    @Value("${scripts.root:/tmp/scripts}")
    @Getter
    private String scriptsLocation;

    /**
     * Fetch script code
     */
    public Optional<String> fetchScript(String scriptName) {

        File fileScript = new File(scriptsLocation, scriptName);
        if (!fileScript.exists()) {
            return Optional.empty();
        }
        try (FileReader fileReader = new FileReader(fileScript)) {
            return Optional.of(FileCopyUtils.copyToString(fileReader));
        } catch (Exception e) {
            log.warn("Failed to load script file  {} {}", scriptName, scriptName, e);
            throw new PolyFunctionException("Failed to load file");

        }
    }

}
