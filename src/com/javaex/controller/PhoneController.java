package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/pbc --> doGet()");
		
		String action = request.getParameter("action");
		PhoneDao phoneDao = new PhoneDao();
		
		//리스트일때
		//action.equals("list") action이 null값일때 오류발생 반대로써주면 오류 안남
		if("list".equals(action)) {
			System.out.println("list");
			
			List<PersonVo> pList = phoneDao.getPersonList();
			
			request.setAttribute("personList", pList);
			
			//forward 하는방법
			WebUtil.forward(request, response, "/WEB-INF/list.jsp");
			
		} else if("wform".equals(action)) {
			System.out.println("wform");
			
			WebUtil.forward(request, response, "/WEB-INF/writeForm.jsp");
			
		} else if("insert".equals(action)) {
			System.out.println("insert");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo personVo = new PersonVo(name, hp, company);
			phoneDao.personInsert(personVo);
			
			WebUtil.redirect(request, response, "/pb2/pbc?action=list");
		} else if("uform".equals(action)) {
			System.out.println("uform");
			
			WebUtil.forward(request, response, "/WEB-INF/updateForm.jsp");
		} else if("update".equals(action)) {
			System.out.println("update");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("personid"));
			
			PersonVo personVo = new PersonVo(personId ,name, hp, company);
			phoneDao.personUpdate(personVo);
			
			WebUtil.redirect(request, response, "/pb2/pbc?action=list");
		} else if("delete".equals(action)) {
			System.out.println("delete");
			
			int personId = Integer.parseInt(request.getParameter("personid"));
			phoneDao.personDelete(personId);
			
			WebUtil.redirect(request, response, "/pb2/pbc?action=list");
		}
		
		//등록일때
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
