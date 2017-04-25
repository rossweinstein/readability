# READABILITY
This program computes a .txt file's readability, a metric to determine how difficult a text is to understand, and returns the results in a Map.  

## Sample Output
```
Title: Iliad.txt  
Results:  
{words per sentence=36.888888888888886, average readability=14.534687124848151, sentences=18.0, words=664.0, complex words=69.0, syllables per word=1.5647590361445782, gunning fog=18.912182061579653, syllables=1039.0, letters per word=4.225903614457831, SMOG=11.33607618648975, automated readability=16.918450468540826, flesch-kincaid grade level=17.260823293172695, flesch reading ease=37.014163319946476, letters=2806.0, unique words=314.0, coleman-liau=8.24590361445783}
```

## Sample Code
```java
/**
 * This method cleans, removes all unnecessary punctuation, from a provided
 * text
 * 
 * @param text
 *            List the text to clean up
 */
private void cleanText(List<String> text) {

  for (String word : text) {

    if (this.isAtSentenceEnd(word)) {
      this.sentenceCount++;
    }

    if (this.hasDoubleHyphen(word)) {
      List<String> miniList = this.separateWords(word);
      this.cleanText(miniList);
    } else {

      String cleanedWord = this.cleanWord(word);

      if (cleanedWord.length() != 0) {
        this.cleanedList.add(cleanedWord.toLowerCase());
      }
    }
  }
}
```

## About This Project
### Origin
This project is an extension of one of my classroom assignments.  We were tasked to find each unique word in Lewis Carroll's *Alice's Adventures in Wonderland* and record their frequency.  While working on this assignment, which was originally in Python, I had the idea to expand on the basic requirements by adding readability tests.

### Installation
Currently, the only way to get this code is to clone the repository.
```
$ git clone https://github.com/rossweinstein/readability
```

### Text Readability
This program calculates six readability indexes:

  1. [Flesch Reading Ease](https://en.wikipedia.org/wiki/Flesch–Kincaid_readability_tests)
  1. [Flesch-Kincaid Grade Level](https://en.wikipedia.org/wiki/Flesch–Kincaid_readability_tests)
  1. [Gunning Fog](https://en.wikipedia.org/wiki/Gunning_fog_index)
  1. [Coleman-Liau](https://en.wikipedia.org/wiki/Coleman–Liau_index)
  1. [SMOG (Simple Measure of Gobbledygook)](https://en.wikipedia.org/wiki/SMOG)
  1. [Automated Readability](https://en.wikipedia.org/wiki/Automated_readability_index)
  
Each of these indexes, except for Flesch Reading Ease, produces a number which estimates the number of years of formal education a person would require to fully understand the text on first reading.  Flesh Reading Ease produces a score out of 100 where the lower the value, the more difficult the text.    

To calculate these formulas, several pieces of information were collected from the text:

  1. Total words
  1. Total sentences
  1. Total syllables
  1. Total letters
  1. Total complex words (words with three or more syllables)
  1. Average words per sentence
  1. Average syllables per words
  1. Average letters per word

### How To Use
This program can do 4 different things:

1. Find all the unique words in a text and report their usage along with a sentence count. 
```java
ReadText text = new ReadText(Paths.get("somePath"));
String title = text2.getFileName();
int sentences = text2.getSentenceCount();
Map<String, Integer> textDict = text2.getTextDictionary();
```
The text dictionary uses each unique word as the key with the integer as the number of times that word is found in the text.  
  
2. Analyze a text and collect a number of common statistics.  
```java
TextAnalysis text = TextAnalyzer.analyzeText(Paths.get("somePath"), AnalysisOption.STATS_ONLY);
text.analyze();
String title = text.getTitle();
Map<String, Double> results = text.getResults();
```
The results will be a Map with these keys:  
* words
* sentences
* letters
* syllables
* complex words
* unique words
* words per sentence
* letters per word
* syllable per word
  
3. Analyze a text and collect their readability values.
```java
TextAnalysis text = TextAnalyzer.analyzeText(Paths.get("somePath"), AnalysisOption.READABILITY_ONLY);
text.analyze();
String title = text.getTitle();
Map<String, Double> results = text.getResults();
```
The results will be a Map with these keys:  
* flesch reading ease
* flesch-kincaid grade level
* gunning fog
* coleman-liau
* SMOG
* automated readability
* average readability
  
4. Analyze a text and return all the results from numbers 2 and 3
```java
TextAnalysis text = TextAnalyzer.analyzeText(Paths.get("somePath"), AnalysisOption.ANALYZE_ALL);
text.analyze();
String title = text.getTitle();
Map<String, Double> results = text.getResults();
```
  
### Testing
I used JUnit to test all my code in this project.  Those tests can be seen [here](https://github.com/rossweinstein/readability/tree/master/test).

## Outside Resources
I used the [optimized syllable dictionary](https://github.com/troywatson/Lawrence-Style-Checker/blob/master/dict/syllables-optimized-list.txt) from [Troy Watson](https://github.com/troywatson/Lawrence-Style-Checker) in my SyllableDetector class.

## Sample Texts
I used a combination of [The Open Library](https://openlibrary.org) and [The Internet Classics Archive](http://classics.mit.edu/Browse/index.html) for my text samples in the example above.

## License
[MIT License](https://en.wikipedia.org/wiki/MIT_License)
