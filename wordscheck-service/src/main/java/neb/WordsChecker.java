package neb;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WordsChecker {
    private final Set<String> words;

    public static WordsChecker simple() {
        return new WordsChecker(Arrays.asList("fee", "nee", "cruul", "leent"));
    }

    private WordsChecker(List<String> words) {
        this.words = Collections.unmodifiableSet(new HashSet<>(words));
    }

    public boolean containsBadWords(String text) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (words.contains(matcher.group()))
                return true;
        }
        return false;
    }

}
