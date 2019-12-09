package kr.or.ddit.food.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/food")
public class JsoupTest {

	@RequestMapping
	public String view() {
		return "crawler";
	}
	
	@Inject
	RestTemplate template;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String proxy(String SPRDNM) throws IOException {
		String serviceUrl = "https://www.foodsafetykorea.go.kr/ajax/portal/specialinfo/searchPrdList_1.do";

		int cnt = 1;
		MultiValueMap<String, Object> uriResponse = new LinkedMultiValueMap<String, Object>();
		uriResponse.add("s_mode", "1");
		uriResponse.add("s_opt", "all");
		uriResponse.add("s_induty_cd", "106,402,108,131,132,604,611,612,613,118,119");
		uriResponse.add("s_prdlst_grp", "all");
		uriResponse.add("s_prdlst_cd", "all");
		uriResponse.add("s_prd_nm", SPRDNM);
		uriResponse.add("s_opt1", "I");
		uriResponse.add("s_opt2", "Y");
		uriResponse.add("s_opt3", "0");
		uriResponse.add("s_order_by", "prdlst_nm"); // ????
		uriResponse.add("s_list_cnt", "10");
		uriResponse.add("s_page_num", "1");
		uriResponse.add("s_tx_id", Integer.toString(cnt++));
		uriResponse.add("prdlst_grp", "all");
		uriResponse.add("prd_nm", SPRDNM);
		uriResponse.add("opt1", "I");
		uriResponse.add("chk_prdlst_cd", "all");
		uriResponse.add("opt1", "I");
		uriResponse.add("opt2", "Y");
		uriResponse.add("opt3", "1");
		
		String result = template.postForObject(serviceUrl, uriResponse, String.class);

		System.out.println(result);
		return result;

		// URL url = new
		// URL("https://www.foodsafetykorea.go.kr/ajax/portal/specialinfo/searchPrdList_1.do");
		// // 호출할 url
		//
		// int cnt = 1;
		// Map<String, Object> uriResponse = new LinkedHashMap<>(); // 파라미터 세팅
		// uriResponse.put("s_mode", 1);
		// uriResponse.put("s_opt", "all");
		// uriResponse.put("s_induty_cd",
		// "106,402,108,131,132,604,611,612,613,118,119");
		// uriResponse.put("s_prdlst_grp", "all");
		// uriResponse.put("s_prdlst_cd", "all");
		// uriResponse.put("s_prd_nm", SPRDNM);
		// uriResponse.put("s_opt1", "I");
		// uriResponse.put("s_opt2", "Y");
		// uriResponse.put("s_opt3", 0);
		// uriResponse.put("s_order_by", "prdlst_nm"); // ????
		// uriResponse.put("s_list_cnt", 10);
		// uriResponse.put("s_page_num", 1);
		// uriResponse.put("s_tx_id", cnt++);
		// uriResponse.put("prdlst_grp", "all");
		// uriResponse.put("prd_nm", SPRDNM);
		// uriResponse.put("opt1", "I");
		// uriResponse.put("chk_prdlst_cd", "all");
		// uriResponse.put("opt1", "I");
		// uriResponse.put("opt2", "Y");
		// uriResponse.put("opt3", 1);
		//
		// StringBuilder postData = new StringBuilder();
		// for (Map.Entry<String, Object> param : uriResponse.entrySet()) {
		// if (postData.length() != 0)
		// postData.append('&');
		// postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		// postData.append('=');
		// postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
		// "UTF-8"));
		// }
		//
		// byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		//
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("POST");
		// conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// conn.setRequestProperty("Content-Length",
		// String.valueOf(postDataBytes.length));
		// conn.setDoOutput(true);
		// conn.getOutputStream().write(postDataBytes); // POST 호출
		//
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(conn.getInputStream(), "UTF-8"));
		//
		// String inputLine;
		// while ((inputLine = in.readLine()) != null) { // response 출력
		// System.out.println("inputLine : " +inputLine);
		// }
		//
		//
		//
		//
		// return inputLine;

	}

}
