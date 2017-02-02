package com.jizhela.helloworld;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.jizhela.helloworld.bean.RoncooUserLog;

//前面介绍过spring的MVC结合不同的view显示不同的数据，如：结合json的view显示json、结合xml的view显示xml文档。
//那么这些数据除了在WebBrowser中用JavaScript来调用以外，还可以用远程服务器的Java程序、C#程序来调用。之前的添加CORS就是让js能远程调用跨域的请求，
//现在这个是用远程服务器的Java程序、C#程序来调用跨域的请求
//也就是说现在的程序不仅在BS中能调用，在CS中同样也能调用，不过你需要借助RestTemplate这个类来完成。
//RestTemplate有点类似于一个WebService客户端请求的模版，可以调用http请求的WebService，并将结果转换成相应的对象类型。至少你可以这样理解！

//RestTemplate的getForObject完成get请求、postForObject完成post请求、put对应的完成put请求、delete完成delete请求；还有execute可以执行任何请求的方法，需要你设置RequestMethod来指定当前请求类型。
//RestTemplate.getForObject(String url, Class responseType, String... urlVariables)
//参数url是http请求的地址，参数Class是请求响应返回后的数据的类型，最后一个参数是请求中需要设置的参数。
//template.getForObject(url + "get/{id}.do", String.class, id);
//如上面的参数是{id}，返回的是一个string类型，设置的参数是id。最后执行该方法会返回一个String类型的结果。



@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldRestServiceTests {
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	/**
	 * get请求
	 */
	@Test
	public void getForObject() {

		//get测试，参数在url中
		RoncooUserLog bean = restTemplateBuilder.build().getForObject("http://localhost:8080/rest/update/{id}",
				RoncooUserLog.class, 1);
		System.out.println(bean);
		//get测试，参数在json对象中
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		bean = restTemplateBuilder.build().postForObject("http://localhost:8080/rest/update", map, RoncooUserLog.class);
		System.out.println(bean);

		//get测试，使用代理
		String result = restTemplateBuilder.additionalCustomizers(new ProxyCustomizer()).build()
				.getForObject("http://www.roncoo.com", String.class);
		System.out.println(result);

	}

	static class ProxyCustomizer implements RestTemplateCustomizer {
		@Override
		public void customize(RestTemplate restTemplate) {
			String proxyHost = "43.255.104.179";
			int proxyPort = 8080;

			HttpHost proxy = new HttpHost(proxyHost, proxyPort);
			HttpClient httpClient = HttpClientBuilder.create().setRoutePlanner(new DefaultProxyRoutePlanner(proxy) {
				@Override
				public HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context)
						throws HttpException {
					System.out.println(target.getHostName());
					return super.determineProxy(target, request, context);
				}
			}).build();
			HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
					httpClient);
			//这两个一定要设置，不然采用默认的链接时间和超时时间，并发减少，内存被耗光
			httpComponentsClientHttpRequestFactory.setConnectTimeout(10000);
			httpComponentsClientHttpRequestFactory.setReadTimeout(60000);
			restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
		}
	}

}
