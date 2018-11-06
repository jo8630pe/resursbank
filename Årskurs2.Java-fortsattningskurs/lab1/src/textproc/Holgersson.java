package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();

		Scanner s = new Scanner(new File("../lab1/nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner stopwords = new Scanner(new File("undantagsord.txt"));
		
		ArrayList<TextProcessor> tp = new ArrayList<>();
		
		tp.add(new SingleWordCounter("nils"));
		tp.add(new SingleWordCounter("norge"));
		tp.add(new MultiWordCounter(REGIONS));
		tp.add(new GeneralWordCounter(stopwords));
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(int i = 0; i < tp.size(); i++) {
				tp.get(i).process(word);
			}
			
		}
		for(int i = 0; i < tp.size(); i++) {
			tp.get(i).report();
			System.out.println();
		}

		s.close();
		
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");

	}
}