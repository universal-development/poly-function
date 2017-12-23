package com.unidev.app.groovyscripts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.google.common.collect.ImmutableMap;
import com.unidev.polyfunction.Application;
import com.unidev.polyfunction.FunctionResponse;
import com.unidev.polyfunction.GenericFunctionResponse;
import com.unidev.polyfunction.ScriptService;
import com.unidev.polyfunction.evaluator.ScalaScriptEvaluator;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.sshd.common.util.IoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PolyFunctionTests {

	@Autowired
	private ScriptService scriptService;

	@Autowired
	private ScalaScriptEvaluator scalaScriptEvaluator;

	@Test
	public void contextLoads() {
	}

	@Test
	public void notFoundScript() {
        Optional<String> file = scriptService.fetchScript("potato");
        assertThat(file, is(notNullValue()));
        assertThat(file.isPresent(), is(false));
    }

    @Test
    public void scriptFetching() throws IOException {
	    String location = scriptService.getScriptsLocation();
	    new File(location).mkdirs();
	    File testFile = new File(location, "qwe.groovy");
	    testFile.createNewFile();
        FileUtils.writeStringToFile(testFile, "test", "UTF-8");

        Optional<String> file = scriptService.fetchScript("qwe.groovy");
        assertThat(file, is(notNullValue()));
        assertThat(file.isPresent(), is(true));
        assertThat(file.get(), is("test"));
    }

    @Test
    public void testScalaScript() {
        GenericFunctionResponse result = (GenericFunctionResponse) scalaScriptEvaluator.evaluate("var a:Int =  10", ImmutableMap.of());
	    assertThat(result.getPayload(), equalTo(10));
    }

}
