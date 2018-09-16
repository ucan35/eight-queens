import java.util.*;

// this is stupid, dont even bother ¯\_(ツ)_/¯
public class Counter {

		private long c = 0;

		public void increment() {
			c++;
		}

		public void reset() {
			c = 0;
		}

		public long getValue() {
			return c;
		}

		public void setValue(long v) {
			c = v;
		}

	}