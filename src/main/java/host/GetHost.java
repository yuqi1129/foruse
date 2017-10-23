package host;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.net.InetAddresses;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.DoubleAccumulator;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/10/23
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */

public class GetHost {

	//private static final List<IpLantency> list = Lists.newArrayList();
	private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
			Runtime.getRuntime().availableProcessors() * 2,
			Runtime.getRuntime().availableProcessors() * 2,
			10,
			TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(1000),
			new ThreadFactoryBuilder().setNameFormat("Get ip in thread pool -%d").build(),
			new ThreadPoolExecutor.CallerRunsPolicy()
	);

	public static void main(String[] args) {
		//read text;
		String content = null;
		File file = null;
		try {
			InputStream inputStream = GetHost.class.getClassLoader().getResourceAsStream("host.txt");
			file = new File("result.txt");
			content = IOUtils.toString(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (content == null) {
			System.out.println("null from result.txt");
			return;
		}
		String[] ips = content.trim().split(" ");
		for (String ip : ips) {
			String ipAndGateString = ip.trim().split(":")[1];
			String[] ipAndGate = ipAndGateString.trim().split("/");
			pingIp(ipAndGate[0], ipAndGate[1], file);
		}

		//now sort and write to text

	}

	private static void pingIp(String ip, String gate, final File file) {
		int nu = Integer.valueOf(gate);
		int number = (1 << nu);
		int addrInt = stringToInt(ip);
		for (int i = 1; i < number; i++) {
			int tmp = addrInt + i;
			final String s = intToStirng(tmp);
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					List<String> tmps = Lists.newArrayList("ping", s, "-n", "4");
					try {
						ProcessBuilder processBuilder = new ProcessBuilder(tmps);
						Process process = processBuilder.start();
						process.waitFor(6, TimeUnit.SECONDS);
						String output = IOUtils.toString(process.getInputStream(), Charset.forName("UTF-8"));
						String[] strings = output.trim().split("\n");
						String resultString = strings[strings.length -1];

						if (resultString.startsWith("rtt")) {
							//list.add(new IpLantency(s, Double.valueOf(getLantency(resultString))));
							FileUtils.write(file, s + " " + getLantency(resultString));
							System.out.println(s + " " + getLantency(resultString));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	private static int stringToInt(String ip) {
		InetAddress addresses = InetAddresses.forString(ip);
		return InetAddresses.coerceToInteger(addresses);
	}

	private static String intToStirng(int ip) {
		Inet4Address addresses = InetAddresses.fromInteger(ip);
		return InetAddresses.toAddrString(addresses);
	}

	private static String getLantency(String s) {
		String tmp[] = s.trim().split(" ");
		String filed = tmp[tmp.length - 1];
		String[] results = filed.trim().split("/");
		if (results.length > 1) {
			return results[1];
		}

		return "10000";
	}


	private static class IpLantency {
		private String host;
		private double lantency;

		public IpLantency() {
		}

		public IpLantency(String host, double lantency) {
			this.host = host;
			this.lantency = lantency;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public double getLantency() {
			return lantency;
		}

		public void setLantency(double lantency) {
			this.lantency = lantency;
		}
	}
}
