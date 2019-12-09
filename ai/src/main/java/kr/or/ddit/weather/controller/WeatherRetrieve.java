package kr.or.ddit.weather.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Controller
public class WeatherRetrieve {
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	Date date = new Date();
	SimpleDateFormat formatDay = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat formatTime = new SimpleDateFormat("kk");
	int day = Integer.parseInt(formatDay.format(date))-1;

	@RequestMapping(value = "/weather/weatherView.do")
	public ModelAndView weatherRetrieveList() {
		int hour = Integer.parseInt(formatTime.format(date));
//		if (hour % 2 > 0) {
//			hour = Integer.parseInt(formatTime.format(date)) - 5;
//		} else {
//			hour = Integer.parseInt(formatTime.format(date)) - 4;
//		}
		hour = 10;
		ModelAndView mav = new ModelAndView();
		List<Object> w_list1 = new ArrayList<>();
		List<Object> array[] = new List[6];
		try {
			int index = 0;
			for (int i = 1; i < array.length + 1; i++) {
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://apis.data.go.kr/1480523/MetalMeasuringResultService/MetalService?numOfRows=12&pageNo=1&"
						+ "resultType=xml&stationcode=" + i + "&date=" + day // day
						+ "&timecode=RH02&serviceKey=4pYkIRS94NTrLwWpbZ1vbrYX3em4Zl%2BCW0vb4yZmpwX%2FiRfXCJwJSO8yhHoKXT2d3%2BudVV"
						+ "rNrrzAta%2BsuNL%2BDA%3D%3D";

				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				// root tag
				doc.getDocumentElement().normalize();

				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
//				 System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						String metal = getTagValue("itemcode", eElement);
						if (metal.equals("90319")) {
							String time = getTagValue("sdate", eElement);
							if (Integer.parseInt(time.substring(8, 10)) == hour) {
//								 w_list1.add(getTagValue("sdate", eElement));
								String local = getTagValue("stationcode", eElement);
								switch (local) {
								case "1":
									w_list1.add("수도권");
									break;
								case "2":
									w_list1.add("강원도");
									break;
								case "3":
									w_list1.add("호남권");
									break;
								case "4":
									w_list1.add("중부권");
									break;
								case "5":
									w_list1.add("제주권");
									break;
								default:
									w_list1.add("영남권");
									break;
								}
								// w_list1.add(getTagValue("stationcode", eElement));
								// w_list1.add("칼슘");
								
								w_list1.add(getTagValue("value", eElement));
							} // if hour end
						} // if end
					} // if node end
				} // 파싱리스트 for
				array[index++] = w_list1;
				w_list1 = new ArrayList<>();
				
				mav.addObject("weather", array);
				mav.setViewName("weather/weatherView");
			} // location for
		} catch (Exception e) {
			e.printStackTrace();
		} // try~catch end
		return mav;
	}

	@RequestMapping(value = "/weather/weatherView.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map weatherRetreive(Model model, @RequestParam String location)
			throws SAXException, IOException, ParserConfigurationException {
		int hour = Integer.parseInt(formatTime.format(date));
		hour = 10;
//		if (hour % 2 > 0) {
//			hour = Integer.parseInt(formatTime.format(date)) - 5;
//		} else {
//			hour = Integer.parseInt(formatTime.format(date)) - 4;
//		}
		String url = "http://apis.data.go.kr/1480523/MetalMeasuringResultService/MetalService?numOfRows=12&pageNo=1&"
				+ "resultType=xml&stationcode=" + location + "&date=" + day// day
				+ "&timecode=RH02&serviceKey=4pYkIRS94NTrLwWpbZ1vbrYX3em4Zl%2BCW0vb4yZmpwX%2FiRfXCJwJSO8yhHoKXT2d3%2BudVV"
				+ "rNrrzAta%2BsuNL%2BDA%3D%3D";
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag
		doc.getDocumentElement().normalize();

		// 파싱할 tag
		NodeList nList = doc.getElementsByTagName("item");
		Map<String, String> Metal = new HashMap<>();
		// System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		String heavy = null;
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String time = getTagValue("sdate", eElement);
				if (Integer.parseInt(time.substring(8, 10)) == hour) {
					heavy = getTagValue("itemcode", eElement);
					if (heavy.equals("90319")) {
						Metal.put("Ca",getTagValue("value", eElement));
						System.err.println("Ca"+getTagValue("value", eElement));   
					} else {
						Metal.put("Pb", getTagValue("value", eElement));
						System.err.println("Pb"+getTagValue("value", eElement));
					}
					
				} // if node end
			}
		} // 파싱리스트 for
		if (location.equals("1")) {
			Metal.put("color","#f8ba81");
		}else if(location.equals("2")) {
			Metal.put("color","#f2987d");
		}else if(location.equals("3")) {
			Metal.put("color","#019edf");
		}else if (location.equals("4")) {
			Metal.put("Ca", "40.456");
			Metal.put("Pb", "12.78");
			Metal.put("color","#8ec8a0");
		}else if(location.equals("5")) {
			Metal.put("color","#8284ab");
		}else {
			Metal.put("color","#e7be28");
//			Metal.put("Ca", "45.125");
//			Metal.put("Pb", "8.78");
		}
		
		
		return Metal;
	}

}
