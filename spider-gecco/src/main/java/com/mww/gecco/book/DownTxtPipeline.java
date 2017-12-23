package com.mww.gecco.book;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.File;

/**
 * @description:
 * @author rook
 * @date 2016-4-9 上午10:50:49
 */
@PipelineName("downPicPipeline")
public class DownTxtPipeline implements Pipeline<Txt> {

	private CloseableHttpClient httpClient;

	{
		RequestConfig clientConfig = RequestConfig.custom().setRedirectsEnabled(false).build();
		PoolingHttpClientConnectionManager syncConnectionManager = new PoolingHttpClientConnectionManager();
		syncConnectionManager.setMaxTotal(1000);
		syncConnectionManager.setDefaultMaxPerRoute(50);
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(clientConfig).setConnectionManager(syncConnectionManager).build();
	}

	public void process(Txt bean) {
		String url = bean.getUrl();
		String title = bean.getTitle();
		System.out.println("书籍地址：" + url);
		HttpRequestBase request = new HttpGet(url);
		try {
			HttpClientContext context = HttpClientContext.create();
			org.apache.http.HttpResponse response = httpClient.execute(request, context);
			FileUtils.copyInputStreamToFile(response.getEntity().getContent(),new File("D:/boo/" + title+"_"+System.currentTimeMillis() + ".rar"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
	}
}
