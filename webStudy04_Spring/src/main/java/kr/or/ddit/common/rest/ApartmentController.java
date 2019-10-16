package kr.or.ddit.common.rest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApartmentController {
	@RequestMapping(value = "/apart", produces = MediaType.TEXT_HTML_VALUE)
	public String view() {
		return "apart/view";
	}
	
	@Inject
	RestTemplate template;
	
	@RequestMapping(value="/apart", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map proxy(String LAWD_CD, String DEAL_YMD) { //필수아님 
		String serviceUrl="http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
		StringBuffer url = new StringBuffer(serviceUrl);
		url.append("?LAWD_CD={LAWD_CD}"); //맵의 키로 사용됨
		url.append("&DEAL_YMD={DEAL_YMD}"); //맵의 키로 사용됨. 여기바꾸고
		url.append("&serviceKey={serviceKey}"); //맵의 키로 사용됨여기바꾸고
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("LAWD_CD", "11110"); //여기바꾸고
		uriVariables.put("DEAL_YMD", "201909"); //여기바꾸고 
		uriVariables.put("serviceKey", "QadKn4YYl83VZD9QrYTpfjsQkYD67e4KytBvGtLrHLJDyeNhx22qzAeq5SVBS+cElwSTYShQbUEn8gGsbJ51lA==");
		
//		ResponseEntity<String> resp= template.getForEntity(url.toString(), String.class, uriVariables);
//		return resp.getBody(); //여기서 돌아오는게 xml데이터 ! 바디에서 뽑아야해
		//accept 생성중 .~ 우린 클라이언트닌까 ! 
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
		HttpEntity entity = new HttpEntity<>(headers);
		ResponseEntity<Map> resp = template.exchange(url.toString(), 
				HttpMethod.GET, entity, Map.class, uriVariables);//헤더설정할거 찾음
		Map<String, Object> result = resp.getBody();
		//이단계에서 통계데이터 가져와서 서버에 저장할수있음
		return result;
	}
}
