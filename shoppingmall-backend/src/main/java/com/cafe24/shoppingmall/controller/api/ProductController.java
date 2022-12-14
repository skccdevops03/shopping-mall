package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.OptionValueVo;
import com.cafe24.shoppingmall.vo.ProductVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("productAPIController")
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value="상품 목록 가져오기")
	@GetMapping(value = "/list")
	public ResponseEntity<JSONResult> getProductList() {
		
		// 해당 카테고리의 list return
		List<ProductVo> list = productService.getProductList();
		
		return new ResponseEntity<JSONResult>(JSONResult.success(list), HttpStatus.OK);
	}
	
	@ApiOperation(value="상품 목록 가져오기")
	@ApiImplicitParams({
		@ApiImplicitParam(name="categoryNo", value="categoryNo: 카테고리 번호", required=true, dataType="Long", defaultValue=""),
		@ApiImplicitParam(name="keyword", value="keyword: 검색어 입력 값", required=false, dataType="String", defaultValue="")
	})
	@GetMapping(value = "/list/{page}") 
	public ResponseEntity<JSONResult> getProductListPage(@PathVariable(value = "page") Long page
			,@RequestParam(value="keyword", required = false, defaultValue = "") String keyword) {
		 
		// 해당 카테고리의 list return
		List<ProductVo> list = productService.getProductListPage(page, keyword);
		
		return new ResponseEntity<JSONResult>(JSONResult.success(list), HttpStatus.OK);
	}
	
	@ApiOperation(value="특정 조건에 맞는 상품 목록 가져오기")
	@ApiImplicitParams({
		@ApiImplicitParam(name="categoryNo", value="categoryNo: 카테고리 번호", required=true, dataType="Long", defaultValue=""),
		@ApiImplicitParam(name="keyword", value="keyword: 검색어 입력 값", required=false, dataType="String", defaultValue="")
	})
	@GetMapping(value = "/{categoryNo}")
	public JSONResult getProductList(
			@PathVariable(value = "categoryNo") Long categoryNo,
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword) {
		
		// 해당 카테고리의 list return
		List<ProductVo> list = productService.getProductList(categoryNo, keyword);
		return JSONResult.success(list);
	}

	@ApiOperation(value="상품 상세 정보")
	@ApiImplicitParams({
		@ApiImplicitParam(name="productNo", value="productNo: 상품 번호", required=true, dataType="Long", defaultValue="")
	})
	@GetMapping(value = "/detail/{productNo}")
	public JSONResult getProductDetail(
			@PathVariable(value = "productNo") Long productNo) {
		
		ProductVo product = productService.getProductDetail(productNo);
		return JSONResult.success(product);
	}
	
	@ApiOperation(value="상품 하위 옵션 가져오기")
	@ApiImplicitParams({
		@ApiImplicitParam(name="productNo", value="productNo: 상품번호", required=false, dataType="Long", defaultValue="")
	})
	@GetMapping(value = "/getlowoption/{productNo}")
	public JSONResult getLowOption(@PathVariable(value = "productNo") Long productNo) {
		
		List<OptionValueVo> optionValueVoList = productService.getLowOption(productNo);
		return JSONResult.success(optionValueVoList);
	}
}
