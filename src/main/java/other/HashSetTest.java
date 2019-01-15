package other;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/6/19 上午10:32
 */

import com.google.common.collect.Sets;
import org.apache.commons.lang.time.StopWatch;

import java.util.Random;
import java.util.Set;

public class HashSetTest {
	public static void main(String[] args) {
		Set<Integer> integerSet = Sets.newHashSet();

		Random random = new Random();

		final Integer totalNum = 1_7386_2754;


		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (int i = 0; i < totalNum; i++) {
			integerSet.add(random.nextInt(1000000));
		}

		System.out.println(stopWatch.getTime());


	}
}
