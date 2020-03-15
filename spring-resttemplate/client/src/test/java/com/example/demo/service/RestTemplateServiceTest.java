package com.example.demo.service;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.demo.vo.JsonVo;
import com.example.demo.vo.XmlVo;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RestTemplateServiceTest {

	@Autowired
	RestTemplateService restTemplateService;
	
	@Test
	public void xml_요청_테스트() {
		//when
		XmlVo xmlVo = restTemplateService.getXmlData();
		
		//then
		assertThat(xmlVo.getType(), is("XML"));
		assertThat(xmlVo.getMessage(), is("This is Xml Data!!"));
	}

	@Test
	public void json_요청_테스트() {
		//when
		JsonVo jsonVo= restTemplateService.getJsonData();
		
		//then
		assertThat(jsonVo.getType(), is("JSON"));
		assertThat(jsonVo.getMessage(), is("This is Json Data!!"));
		
	}
	
	@Test
	public void header_check_테스트_성공() {
		//when
		ResponseEntity<String> responseEntity= restTemplateService.getEntity("LEMON");
		
		//then
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
		
	}
	
	@Test(expected = Unauthorized.class)
	public void header_check_테스트_실패_잘못된_인증키() {
		//when
		ResponseEntity<String> responseEntity= restTemplateService.getEntity("fail");
		
		//then
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
		
	}
	
	@Test
	public void post_테스트() {
		//when
		ResponseEntity<String> responseEntity= restTemplateService.addData();
		
		//then
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
		
	}
}