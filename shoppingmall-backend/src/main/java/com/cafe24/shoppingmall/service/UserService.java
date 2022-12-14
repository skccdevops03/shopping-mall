package com.cafe24.shoppingmall.service;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.UserDao;
import com.cafe24.shoppingmall.vo.UserAddressVo;
import com.cafe24.shoppingmall.vo.UserVo;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserService{
	@Autowired
	private UserDao userDao;
	
	public Boolean joinUser(UserVo userVo) {
		return userDao.insert(userVo)==1;
	}
	
	public Boolean checkId(String id) {
		UserVo vo =  userDao.getId(id);
		Boolean exist = vo==null;
		return exist;
	}

	public UserVo getUser(String id, String password) {
		UserVo vo  = userDao.get(id, password);
		return vo;
	}

	public UserVo getUser(String email) {
		UserVo userVo = userDao.get(email);
		return userVo;
	} 
	
	public UserVo getLogin(String id) {
		UserVo userVo = userDao.getLogin(id);
		return userVo;
	} 
	
	public List<UserVo> getUserList() {
		List<UserVo> list = userDao.getUserList();
		return list;
	}

	public UserVo modifyUser(UserVo userVo) {
		UserVo vo = userDao.update(userVo);
		return vo;
	}

	public Boolean deleteUser(Long no) {
		return userDao.deleteUser(no);
		
	}

	public Boolean addAddress(UserAddressVo userAddressVo) {
		Boolean result =  userDao.insertUserAddress(userAddressVo)==1;
		return result;
	}

	public List<UserAddressVo> getAddressList(Long userNo) {
		return userDao.getUserAddressList(userNo);
	} 
	
	public UserAddressVo getAddress(Long addressNo) {
		return userDao.getUserAddress(addressNo);
	}

	public Boolean deleteAddress(Long addressNo) {
		Boolean result =  userDao.deleteAddress(addressNo)==1;
		return result;
	}

	public UserVo getUserDetail(Long userNo) {
		UserVo vo = userDao.getUserDetail(userNo);
		return vo;
	} 

}
