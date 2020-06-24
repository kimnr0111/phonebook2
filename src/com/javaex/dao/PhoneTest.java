package com.javaex.dao;

import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneTest {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		
		
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());
		
		
		/*
		PersonVo personVo = new PersonVo("유재석", "010-1111-1111", "02-1111-1111");
		phoneDao.personInsert(personVo);
		*/
		
//		PersonVo personVo = phoneDao.getPerson(7);
//		System.out.println(personVo.toString());

	}

}
