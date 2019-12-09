package kr.or.ddit.search.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 최서희
 * @since 2019. 11. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                       수정자               수정내용
 * -------------     --------    ----------------------
 * 2019. 11. 25.      최서희                최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ImgSearchController {

		
		// 서비스 연결 커넷션
		private HttpURLConnection con;
		
		public void setConnection() {
			String apiURL = "https://kapi.kakao.com/v1/vision/multitag/generate";
			
			URL url;
			try {
				url = new URL(apiURL);
				con = (HttpURLConnection) url.openConnection();
				con.setUseCaches(false);
				con.setDoOutput(true);
				con.setDoInput(true);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/**
		 * 파일 전송 설정
		 */
		public void setFileTransfer(
				@RequestPart("file") MultipartFile file,
				Model model
				) {
			// multipart request
			String boundary = "---" + System.currentTimeMillis() + "---";
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("Host", "kapi.kakao.com");
			con.setRequestProperty("Authorization", "KakaoAK 45191dfb804933d6e5e17271f364ceef");
			try {
				OutputStream outputStream = con.getOutputStream();
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
				String LINE_FEED = "\r\n";
				// file 추가
				String paramName = "file"; // 파라미터명
				byte[] uploadFile = file.getBytes();
				String fileName = file.getName();
				writer.append("--" + boundary).append(LINE_FEED);
				writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"")
				.append(LINE_FEED);
				writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
				writer.append(LINE_FEED);
				writer.flush();
				InputStream inputStream = file.getInputStream();
//				FileInputStream inputStream = new FileInputStream(uploadFile);
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				outputStream.flush();
				inputStream.close();
				writer.append(LINE_FEED).flush();
				writer.append("--" + boundary + "--").append(LINE_FEED);
				writer.close();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 응답수신
		 */
		public String receiveResponse() {
			try {
				BufferedReader br = null;
				int responseCode = con.getResponseCode();
				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 에러 발생
					System.out.println("error!!!!!!! responseCode= " + responseCode);
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				}
				String inputLine;
				if (br != null) {
					StringBuffer response = new StringBuffer();
					while ((inputLine = br.readLine()) != null) {
						response.append(inputLine);
					}
					br.close();
//					System.out.println(response.toString());
					return response.toString();
					
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value="/search/searchImgage", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public String main(
				@RequestPart(required=false, name="file") MultipartFile file,
				Model model
				) {
			ImgSearchController imgSearchTest = new ImgSearchController();
			imgSearchTest.setConnection();
			imgSearchTest.setFileTransfer(file,model);
			String result = imgSearchTest.receiveResponse();
			model.addAttribute("result", result);
			return (String) model.asMap().get("result");
		}
	}

