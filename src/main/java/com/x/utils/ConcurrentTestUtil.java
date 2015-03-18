package com.x.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>
 * TestUtil
 * </p>
 * <p>
 * Description:并发测试工具
 * </p>
 * 
 * @author chenkangming
 * @date 2015年3月17日
 * @version 1.0
 **/
public class ConcurrentTestUtil {
	private static int thread_num = 200;
	private static int client_num = 2000;

	public static void main(String[] args) {
		//线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 50个线程可以同时访问
		final Semaphore semp = new Semaphore(thread_num);
		// 模拟2000个客户端访问
		for (int index = 0; index < client_num; index++) {
			final int NO = index;
			Runnable run = new Runnable() {

				@Override
				public void run() {
					// 获取许可
					try {
						semp.acquire();
						System.out.println("Thread:" + NO);
						String host = "http://db.game2.test5.9game.cn/DBPlatform/api/CancelBindMobileResult?uid=123456&mobile=18923852070&code=123456";
						System.out.println(host);
						URL url = new URL(host);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setDoInput(true);
						connection.setDoInput(true);

						BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String line = "";
						String result = "";
						while ((line = in.readLine()) != null) {
							result += line;
						}
						System.out.println(result);

						System.out.println("第：" + NO + "个");
						semp.release();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}

		// 退出线程池
		exec.shutdown();
	}
}
