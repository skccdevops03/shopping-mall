package com.cafe24.shoppingmall.controller.api;


import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.RequestDeleteCartDto;
import com.cafe24.shoppingmall.dto.RequestNonUserAddCartDto;
import com.cafe24.shoppingmall.dto.RequestNonUserOrderDto;
import com.cafe24.shoppingmall.dto.RequestNonUserOrderListDto;
import com.cafe24.shoppingmall.service.CartService;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.service.OrderService;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.CartVo;
import com.cafe24.shoppingmall.vo.CategoryVo;
import com.cafe24.shoppingmall.vo.OptionNameVo;
import com.cafe24.shoppingmall.vo.OrderDetailVo;
import com.cafe24.shoppingmall.vo.OrderVo;
import com.cafe24.shoppingmall.vo.UserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("nonUserAPIController")
@RequestMapping("/api/nonuser")
public class NonUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation(value="????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userVo", value="id: ????????? - ????????? \n password: ???????????? - ????????? \n name: ?????? - ????????? \n"
				+ "passwordQuestion: ???????????? ?????? - ????????? \n"
				+ "passwordAnswer: ???????????? ?????? - ????????? \n"
				+ "phoneNumber: ???????????? - ????????? \n"
				+ "birthDate: ?????? - ????????? \n"
				+ "email: ????????? - ????????? \n", required=true, dataType="UserVo", defaultValue="")
	})

	@PostMapping(value="/join") 
	public ResponseEntity<JSONResult> joinUser(@RequestBody @Valid UserVo userVo,
								BindingResult bindingResult) {
		// java @valid ????????? ??????
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError error : allErrors) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		} 
		 
		// ?????? id??? fail
		if(!userService.checkId(userVo.getId())) { 
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(false));
		}
		 
		Boolean result = userService.joinUser(userVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}	

	@ApiOperation(value="????????? ?????? ??????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="id : ?????????", required=true, dataType="String", defaultValue="")
	})
	@GetMapping(value="/checkId") 
	public ResponseEntity<JSONResult> checkId(@RequestParam(value="id") String id) {

		Boolean exist = userService.checkId(id);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(exist));
	} 
	
	
	@ApiOperation(value="?????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userVo", value="userId: ????????? - ????????? \n password: ???????????? - ????????? \n"
				, dataType="userVo")
	})
	@PostMapping(value="/login")  
	public ResponseEntity<JSONResult> login(@RequestBody UserVo userVo) {
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		
//		Set<ConstraintViolation<UserVo>> validatorResults = validator.validateProperty(userVo, "id");
//		
//		if(validatorResults.isEmpty() == false) {
//			for( ConstraintViolation<UserVo> validatorResult : validatorResults ) {
//				String message = validatorResult.getMessage();
//				JSONResult result = JSONResult.fail(message);
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);				
//			} 
//		}
		
//		UserVo user = userService.getUser(userVo.getId(), userVo.getPassword());
		UserVo user = userService.getLogin(userVo.getId()); 
		
		// ????????? ??????
		if(user == null) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("???????????? ???????????? ?????? ?????? ???????????????."));			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(user));			
	} 
	
	
	@ApiOperation(value="????????? ??????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="email", value="email : ?????????", required=true, dataType="String", defaultValue="")
	})
	@GetMapping(value="/findId") 
	public ResponseEntity<JSONResult> findId(@RequestParam(value = "email") String email) {
		// ???????????? email????????? false
		UserVo userVo = userService.getUser(email);
		if(userVo!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(userVo.getId()));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("???????????? ???????????? ????????????."));			
		}
	} 

	
	//==================================================================================
	// 								????????? ???????????? ??????
	//==================================================================================
	
	@ApiOperation(value="??????????????? ?????? ????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="nonUserNo", value="nonUserNo: ???????????????", required=false, dataType="Long", defaultValue=""),
		@ApiImplicitParam(name="productOptionNo", value="productOptionNo: ????????? ?????? ??????", required=true, dataType="Long", defaultValue=""),
		@ApiImplicitParam(name="quantity", value="quantity: ??????", required=true, dataType="Long", defaultValue="")
	})
	@PostMapping(value = "/cart/add")
	public ResponseEntity<JSONResult> addNonUserCart(@RequestBody @Valid RequestNonUserAddCartDto nonUserAddCartDto
			, BindingResult bindingResult) {
		// java @valid ????????? ??????
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError error : allErrors) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		
		boolean result = cartService.addCart(nonUserAddCartDto);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}
	
	@ApiOperation(value="???????????? ?????? ????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="nonUserNo", value="nonUserNo: ???????????????", required=false, dataType="String", defaultValue="")
	})
	@GetMapping(value = "/cart/getList")
	public JSONResult getCartList(@RequestBody @Valid RequestNonUserAddCartDto nonUserAddCartDto
			, BindingResult bindingResult) {
		// ?????? list return
		List<CartVo> list = cartService.getCartList(nonUserAddCartDto);
		return JSONResult.success(list);
	}
	
	@ApiOperation(value="???????????? ????????? ?????? ?????? ????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="no: ??????????????????", required=false, dataType="long", defaultValue="")
	})
	@GetMapping(value = "/cart/getOptionList")
	public JSONResult getOptionList(
			@RequestParam(value="no", required = true, defaultValue = "") Long no
			) {
		// ????????? ?????? list return
		List<OptionNameVo> list = productService.getOptionList(no);
		return JSONResult.success(list);
	}
	
	@ApiOperation(value="???????????? ?????? ??????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="List<RequestDeleteCartDto>", value="no: ???????????? ??????", required=true, dataType="List<RequestDeleteCartDto>", defaultValue="")
		
	})
	@DeleteMapping(value = "/cart/delete")
	public JSONResult deleteCart(@RequestBody List<RequestDeleteCartDto> noList) {
		Boolean result = cartService.deleteCart(noList);
		return JSONResult.success(result);
	}
	
	
	//==================================================================================
	// 									????????? ?????? ??????
	//==================================================================================
//	@ApiOperation(value="????????? ????????????")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name="RequestNonUserOrderDto", 
//				value="nonUserNo : ???????????????(??????) \n "
//				+ "name : ????????? ??????\n "
//				+ "gender : ?????? \n "
//				+ "password : ???????????? \n "
//				+ "phoneNumber : ???????????? \n "
//				+ "email : ????????? \n "
//				+ "address : address \n "
//				+ "totalPrice : ?????? \n "
//				+ "message : ??????????????? \n ", required=true, dataType="RequestNonUserOrderDto", defaultValue="")
//	})
//	@PostMapping(value="/order/add") 
//	public ResponseEntity<JSONResult> add(@RequestBody RequestNonUserOrderDto requestNonUserOrderDto) {
//		
//		Boolean result = orderService.addOrder(requestNonUserOrderDto);
//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
//	}	
	
	@ApiOperation(value="????????? ?????? ?????? ????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="OrderVo", 
				value="orderStringNo : ???????????? "
				+ "password : ???????????? \n ", required=true, dataType="OrderVo", defaultValue="")
	})
	@GetMapping(value="/order/list") 
	public ResponseEntity<JSONResult> getList(@RequestBody RequestNonUserOrderListDto requestNonUserOrderListDto) {
		List<OrderVo> list = orderService.getOrderListByNo(requestNonUserOrderListDto);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	} 
	
	@ApiOperation(value="????????? ?????? ?????? ?????? ????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", 
				value="no : ?????? PK ?????? ", required=true, dataType="OrderVo", defaultValue="")
	})
	@GetMapping(value="/order/detail/{no}") 
	public ResponseEntity<JSONResult> getOrderDetailList(@PathVariable(value="no") Long no) {
		
		List<OrderDetailVo> list = orderService.getOrderDetailList(no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	}
	
	//============= ???????????? ?????? ==================
	@ApiOperation(value="???????????? ?????? ?????? ????????????")
	@GetMapping(value="/category/list") 
	public ResponseEntity<JSONResult> getList() {

		List<CategoryVo> list = categoryService.getCategoryList();
//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list)); 
		return new ResponseEntity<JSONResult>(JSONResult.success(list), HttpStatus.OK);
	} 
	
	@ApiOperation(value="?????? ???????????? ?????? ????????????")
	@GetMapping(value="/category/lowList") 
	public ResponseEntity<JSONResult> getLowList() {
		
		List<CategoryVo> list = categoryService.getCategoryLowList();
//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list)); 
		return new ResponseEntity<JSONResult>(JSONResult.success(list), HttpStatus.OK);
	} 
}
















 