package com.unidev.polyfunction;


import java.io.File;
import java.io.FileReader;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
@Slf4j
public class ScriptService {

    public static final String EXTS[] = { ".groovy", ".kt", ".scala"};

    @Value("${scripts.root:/tmp/scripts}")
    private String scriptsLocation;


    /**
     * Fetch script code
     */
    public String fetchScript(String scriptName) {

        Optional<File> fileScript = fetchScriptFile(scriptName);
        if (!fileScript.isPresent()) {
            throw new ScriptNotFoundException();
        }
        File file = fileScript.get();

        try (FileReader fileReader = new FileReader(file)) {
            return FileCopyUtils.copyToString(fileReader);
        } catch (Exception e) {
            log.warn("Failed to load script file  {} {}", scriptName, file, e);
            throw new PolyFunctionException("Failed to load file");

        }
    }

    public Optional<File> fetchScriptFile(String scriptName) {
        for(String ext : EXTS) {
            File file = new File(scriptsLocation, scriptName + ext);
            if (file.exists()) {
                return Optional.of(file);
            }
        }

        return Optional.empty();
    }
}
