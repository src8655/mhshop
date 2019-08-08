package com.cafe24.mhmall.frontend.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.ItemService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.ItemVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ItemServiceImpl implements ItemService {
	private static final String SAVE_PATH = "/mhmall-uploads";
	private static final String URL = "/images";


	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	
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
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/item", HttpMethod.POST, params, mockToken);
	    
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
		ResponseJSONResult<ListItemVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/item/list/" + cateNo, HttpMethod.GET, null, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		ListItemVo data = mapper.convertValue(rJson.getData(), ListItemVo.class);
		rJson.setData(data);
		
		return rJson;
	}

	
	// 상품정보 요청
	@Override
	public ResponseJSONResult<ItemVo> get(Long no) {
		ResponseJSONResult<ItemVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/item/" + no, HttpMethod.GET, null, null);
	    
		ObjectMapper mapper = new ObjectMapper();
		ItemVo data = mapper.convertValue(rJson.getData(), ItemVo.class);
		rJson.setData(data);
		
		return rJson;
	}
	
	
	// 상품수정 요청
	@Override
	public ResponseJSONResult<Boolean> edit(String mockToken, ItemVo itemVo, MultipartFile thumbnailFile) {
		String url = null;
		
		// 썸네일 이미지 수정이 없으면 저장안함
		if(thumbnailFile.isEmpty()) {
			itemVo.setThumbnail(null);
		}else {
			// 썸네일 이미지 이름
			url = generateSaveFileName(thumbnailFile);
			itemVo.setThumbnail(URL + "/" + url);
		}
		
		// 파라미터 설정
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", itemVo.getNo());
	    params.put("name", itemVo.getName());
	    params.put("description", itemVo.getDescription());
	    params.put("money", itemVo.getMoney());
	    params.put("thumbnail", itemVo.getThumbnail());
	    params.put("categoryNo", itemVo.getCategoryNo());
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/item", HttpMethod.PUT, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		if(!thumbnailFile.isEmpty()) {
			// 성공하면 실제 저장
			if("success".equals(rJson.getResult())) {
				restore(thumbnailFile, url);
			}
		}
		
		return rJson;
	}
	
	
	// 상품삭제 요청
	@Override
	public ResponseJSONResult<Boolean> delete(String mockToken, Long no) {
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/item", HttpMethod.DELETE, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}
	
	
	// 상품 진열여부 수정 요청
	@Override
	public ResponseJSONResult<Boolean> display(String mockToken, Long no, String display) {

		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    params.put("display", display);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/item/display", HttpMethod.PUT, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}

	
	// 최근상품리스트
	@Override
	public ResponseJSONResult<ListItemVo> getNewList(Long CategoryNo, Integer cnt) {
	    
	    ResponseJSONResult<ListItemVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/item/list/new/"+CategoryNo+"/"+cnt, HttpMethod.GET, null, null);
	    
		ObjectMapper mapper = new ObjectMapper();
		ListItemVo data = mapper.convertValue(rJson.getData(), ListItemVo.class);
		rJson.setData(data);
		
		return rJson;
	}
	

	// 최근상품이미지
	@Override
	public ResponseJSONResult<ListMainImgVo> getNewImgList(Integer cnt) {

	    ResponseJSONResult<ListMainImgVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/item/list/img/new/"+cnt, HttpMethod.GET, null, null);
	    
		ObjectMapper mapper = new ObjectMapper();
		ListMainImgVo data = mapper.convertValue(rJson.getData(), ListMainImgVo.class);
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
