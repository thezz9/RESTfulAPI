package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.member.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {
	LoginServiceImpl() {
		System.out.println("===> LoginServiceImpl");
	}

	@Autowired
	private LoginDao dao;
		
	@Override
	public MemberVO getLogin(MemberVO vo) {
		return dao.getLogin(vo);
	}
}
