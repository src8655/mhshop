package com.cafe24.mhmall.frontend.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.ItemImgService;
import com.cafe24.mhmall.frontend.service.OptionDetailService.ListOptionDetailVo;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ItemImgServiceImpl implements ItemImgService {
	private static final String SAVE_PATH_IMG = "/mhmall-uploads";
	private static final String URL = "/images";

	
	// 상품이미지 추가 요청
	@Override
	public ResponseJSONResult<Boolean> add(String mockToken, Long itemNo, MultipartFile itemImgFile) {
		if(itemImgFile.isEmpty())
			return ResponseJSONResult.fail("이미지가 없습니다.");
		
		// 이미지 이름
		String url = generateSaveFileName(itemImgFile);
		String itemImg = URL + "/" + url;
		
		// 파라미터 설정
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("itemNo", itemNo);
	    params.put("itemImg", itemImg);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(null, "/api/admin/item/img", HttpMethod.POST, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		// 성공하면 실제 저장
		if("success".equals(rJson.getResult())) {
			restore(itemImgFile, url);
		}
		
		return rJson;
	}
	
	
	// 상품이미지리스트 요청
	@Override
	public ResponseJSONResult<ListItemImgVo> getList(Long itemNo) {
		ResponseJSONResult<ListItemImgVo> rJson = MhmallRestTemplate.request(null, "/api/item/img/"+itemNo, HttpMethod.GET, null, null);
		
		ObjectMapper mapper = new ObjectMapper();
		ListItemImgVo data = mapper.convertValue(rJson.getData(), ListItemImgVo.class);
    	rJson.setData(data);
		
		return rJson;
	}
	

	// 상품이미지 삭제 요청
	@Override
	public ResponseJSONResult<Boolean> delete(String mockToken, Long no) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(null, "/api/admin/item/img", HttpMethod.DELETE, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}
	
	
	
	
	
	
	
	
	public String restore(MultipartFile multipartFile, String fileName) {
		String url = "";

		try {
		
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			
			String saveFileName = fileName;
			long fileSize = multipartFile.getSize();
			
			byte[] fileData = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(SAVE_PATH_IMG + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			

			//multipartFile.transferTo(new File(SAVE_PATH + "/" + saveFileName));
			
			url = URL + "/" + saveFileName;
			
		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		
		return url;
	}

	private String generateSaveFileName(MultipartFile multipartFile) {
		String originalFilename = multipartFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
		
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}










}
