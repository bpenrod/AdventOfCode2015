import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class day5_santas_strings {
    public static void main(final String... args) {
	int niceCount = 0;
	for (String line : getLines(args[0])) {
	    boolean nice = isNice(line);
	    niceCount += (nice ? 1 : 0);
	    System.out.printf("isNice('%s')? %b\n", line, nice);
	}
	System.out.printf("Total nice lines: %d\n", niceCount);
    }

    static final Pattern vowels = Pattern.compile(".*[aeiou].*[aeiou].*[aeiou]");
    static final Pattern dbl = Pattern.compile("(.)\\1");
    static final Pattern excludes = Pattern.compile("(?:ab|cd|pq|xy)");

    private static boolean isNice(final String s) {
	Matcher m1 = vowels.matcher(s);
	Matcher m2 = dbl.matcher(s);
	Matcher m3 = excludes.matcher(s);
	return m1.find() && m2.find() && !m3.find();
    }

    private static List<String> getLines(final String path) {
	try {
	    return Files.readAllLines(Paths.get(path),
				      Charset.forName("UTF-8"));
	} catch (Exception e) {
	    System.out.println(e);
	}
	return null;
    }
}
