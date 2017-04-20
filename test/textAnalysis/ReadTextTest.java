package textAnalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReadTextTest {

	ReadText reader;
	Path filePath = Paths.get("test/sampleForReadFile/someText.txt/");
	Map<String, Integer> expected;
	
	@Before
	public void setUp() {
		this.reader = new ReadText(filePath);
		this.expected = new HashMap<>();
		this.reader.processText();
		this.populateMap();
	}
	
	private void populateMap() {
		this.expected.put("this", 3);
		this.expected.put("is", 2);
		this.expected.put("a", 2);
		this.expected.put("sentence", 2);
		this.expected.put("second", 1);
		this.expected.put("get", 1);
		this.expected.put("rid", 1);
		this.expected.put("of", 1);
		this.expected.put("please", 1);
	}
	
	@Test
	public void canReadFile() {
		assertTrue("FAIL - did not read file", this.reader.readFile());
	}
	
	@Test
	public void foundCorrectFileName() {
		assertEquals("FAIL - wrong file name", "someText.txt", this.reader.getFileName());
	}
	
	@Test
	public void foundAllSentences() {
		assertEquals("FAIL - could not find all sentences", 3, this.reader.getSentenceCount());
	}
	
	@Test
	public void textAnalyzed() {
		assertThat(this.reader.getTextDictionary(), is(this.expected));
	}
	
	
	
	
}
