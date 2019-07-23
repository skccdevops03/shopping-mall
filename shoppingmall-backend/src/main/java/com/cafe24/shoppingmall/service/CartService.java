package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.CartDao;
import com.cafe24.shoppingmall.vo.ProductVo;

@Service
public class CartService{

	@Autowired
	private CartDao cartDao;

	/**
	 * 회원 장바구니에 상품 추가
	 * @param userNo
	 * @param optionNo
	 * @param quantity 
	 * @return
	 */
	public boolean addCart(Long userNo, Long optionNo, Long quantity) {
		boolean result = cartDao.add(userNo, optionNo, quantity);
		return result;
	}

	/**
	 * 해당회원 장바구니 상품 옵션 변경
	 * @param userId
	 * @param cartNo
	 * @param optionNo
	 * @return
	 */
	public boolean modifyCartOption(String userId, Long cartNo, Long optionNo) {
		boolean result = cartDao.modifyOption(userId, cartNo, optionNo);
		return result;
	}

	/**
	 * 해당회원 장바구니 상품 수량 변경
	 * @param userId
	 * @param cartNo
	 * @param quantity
	 * @return
	 */
	public boolean modifyCartQuantity(String userId, Long cartNo, Long quantity) {
		boolean result = cartDao.modifyQuantity(userId, cartNo, quantity);
		return result;
	}

	public boolean deleteCart(String userId, Long cartNo) {
		boolean result = cartDao.delete(userId, cartNo);
		return result;
	}


}
