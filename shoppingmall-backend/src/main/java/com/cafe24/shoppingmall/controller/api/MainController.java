package com.cafe24.shoppingmall.controller.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController("mainAPIController")
@RequestMapping("/api/main")
public class MainController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value="메인에서 상품 목록 가져오기")
	@GetMapping(value = "")
	public JSONResult getProductList() {
		
		// 상품 list return
		List<ProductVo> list = productService.getProductList();
		return JSONResult.success(list);
	}
	

	
}
 