/*
	파일명: AuthenticationService.java
    설명: LoginFailHandler / LoginSuccessHandler 시 계정 인증 관련 비즈니스 로직을 담당하는 Service 
    작성일: 2021-01-03
    작성자: 백희승
*/
package com.olive.authentication.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olive.authentication.dao.AuthenticationDao;
import com.olive.dto.Emp;

@Service
public class AuthenticationService {

	@Autowired
	private SqlSession sqlsession;

	// 계정 로그인 실패 횟수 초기화
	public void initFailCount(String empno) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		dao.initFailCount(empno);
	}
	
	// 계정이 존재하는지 검색
	public Emp selectEmp(String empno) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		return dao.selectEmp(empno);
	}

	// 계정의 로그인 실패 횟수 검색
	public int getFailCount(String empno) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		return dao.getFailCount(empno);
	}

	// 계정의 로그인 실패 횟수 증가
	public void addFailCount(String empno) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		dao.addFailCount(empno);
	}

	// 계정 잠금
	public void disable(String empno) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		dao.disable(empno);
	}
	
	// 이메일 업데이트
	public void updateEmail(String empno, String email) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		Emp emp = new Emp();
		emp.setEmpNo(Integer.parseInt(empno));
		emp.setEmail(email);
		dao.updateEmail(emp);
	}
	
	// General 인서트
	public void setGeneralRole(String empno) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		dao.setGeneralRole(empno);
		
	}
	
	// 계정 신입 -> 활성화
	public void setActivate(String empno) {
		AuthenticationDao dao = sqlsession.getMapper(AuthenticationDao.class);
		dao.setActivate(empno);
	}
}
