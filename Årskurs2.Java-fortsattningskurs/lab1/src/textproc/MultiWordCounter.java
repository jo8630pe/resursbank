package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	
	public MultiWordCounter(String[] word) {
		m = new HashMap<>();
		for(String w: word) {
			m.put(w, 0);
		}
	}
	
	@Override
	public void process(String w) {
		
		if (m.containsKey(w)) {
			m.put(w, m.get(w) + 1);
		}
	}

	@Override
	public void report() {
		for(String key: m.keySet()) {
			System.out.println(key + ": " + m.get(key));
		}
		
	}
	
}
