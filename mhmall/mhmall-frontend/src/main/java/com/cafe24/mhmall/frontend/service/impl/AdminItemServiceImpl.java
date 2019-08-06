package com.cafe24.mhmall.frontend.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.AdminItemService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.ItemVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminItemServiceImpl implements AdminItemService {
	private static final String SAVE_PATH = "/mhmall-uploads";
	private static final String URL = "/images";

	// 상품작성 요청
	@Override
	public ResponseJSONResult<Boolean> add(String mockToken, ItemVo itemVo, MultipartFile thumbnailFile) {
		
		// 썸네일 이미지 이름
		String url = generateSaveFileName(thumbnailFile);
		itemVo.setThumbnail(URL + "/" + url);
		
		// 파라미터 설정
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("name", itemVo.getName());
	    params.put("description", itemVo.getDescription());
	    params.put("money", itemVo.getMoney());
	    params.put("thumbnail", itemVo.getThumbnail());
	    params.put("categoryNo", itemVo.getCategoryNo());
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request("/api/admin/item", HttpMethod.POST, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		// 성공하면 실제 저장
		if("success".equals(rJson.getResult())) {
			restore(thumbnailFile, url);
		}
		
		return rJson;
	}

	// 상품 리스트 요청
	@Override
	public ResponseJSONResult<ListItemVo> getList(String mockToken, Optional<Long> categoryNo) {
		Long cateNo = -1L;
		if(categoryNo.isPresent()) {
			cateNo = categoryNo.get();
		}
		System.out.println(cateNo + "----------------------------------------front");
		ResponseJSONResult<ListItemVo> rJson = MhmallRestTemplate.request("/api/admin/item/list/" + cateNo, HttpMethod.GET, null, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		ListItemVo data = mapper.convertValue(rJson.getData(), ListItemVo.class);
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
			
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
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
