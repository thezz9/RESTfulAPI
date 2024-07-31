package com.example.login;

import org.apache.ibatis.annotations.Mapper;

import com.example.member.MemberVO;

@Mapper
public interface LoginDao {
	MemberVO getLogin(MemberVO vo);
}
