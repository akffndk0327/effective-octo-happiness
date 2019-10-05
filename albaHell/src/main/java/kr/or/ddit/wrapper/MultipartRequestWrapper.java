package kr.or.ddit.wrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

//업로드 되괴있는 파일들만 처리 
public class MultipartRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, PartWrapper[]> filePartWrapperMap; //파일에 대한 데이터 갖음

	public MultipartRequestWrapper(HttpServletRequest request) throws IOException, ServletException { // 원본요청 Wrapping
		super(request);
		filePartWrapperMap = new HashMap<String, PartWrapper[]>();
		parseRequest(request);
		// part로 받아오기 불편해

	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		//문자파트 + 파일파트
		Collection<Part> parts = request.getParts();
		Iterator<Part> it = parts.iterator();
		while (it.hasNext()) {
			Part part = (Part) it.next();
			String partName = part.getName();
			String dispostion = part.getHeader("Content-Disposition");
			
			//문자파트는 건너뜀
			if (!dispostion.contains("filename")) continue; // ! 붙으면 파일이 아닌 일반데티러 라는 의미
			if(part.getSize()<=0)continue; //없다는 의미
			// part하나에 대한 wrapper만ㄷ르기
			PartWrapper wrapper = new PartWrapper(part);
			PartWrapper[] array = filePartWrapperMap.get(partName);
			PartWrapper[] target = null;
			if (array == null) {
				target = new PartWrapper[] { wrapper };
			} else {
				target = new PartWrapper[array.length+1]; //아직 비어잇어
				//기본배열ㅇ르 복사하기
				System.arraycopy(array, 0, target, 0, array.length);
				target[target.length-1] = wrapper;
			}
			filePartWrapperMap.put(partName, target); //put으로 배열에 넣음 

		}
	}
	
	//여기가 뭐하는거,,,?
	public PartWrapper getPartWrappper(String name) { 
		PartWrapper[] array = filePartWrapperMap.get(name);
		if(array!= null && array.length>0) {
			return array[0];
		}else {
			return null; 
		}
	}
	
	public PartWrapper[] getPartWrappers(String name){
		return filePartWrapperMap.get(name);
	}
	
	//맴차레를 넘기기 
	public Map<String, PartWrapper[]> getFilePartWrapperMap() {
		return filePartWrapperMap;
	}
	
	//서비스메서드 - 임시폴더에 저장된데이터를 끌고와. 묶음의 단위 : 청크 
	public void deleteAll() {
		for(Entry<String, PartWrapper[]> entry : filePartWrapperMap.entrySet()) {
			PartWrapper[] array = entry.getValue();
			for(PartWrapper wrapper : array) {
				wrapper.delete();
			}
		}
			}

}