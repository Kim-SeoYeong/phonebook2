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
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//컨트롤러 테스트
		System.out.println("controller");
		
		//파라미터의 action 값을 읽어서
		String action = request.getParameter("action");
		System.out.println(action);
		
		//action = list 이면 
		if("list".equals(action)) {
			
			//리스트 출력 관련
			//리스트 출력 처리
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			//html --> 엄청 복잡하다. --> jsp가 편하다.
			
			//데이터 전달
			request.setAttribute("pList", personList); 	//(실제데이터를 꺼낼때 붙여주는 별명, 보낼 실제 테이터)
			
			//jsp에 포워드 시킨다.
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp");	//jsp 파일 위치를 알려줌
			rd.forward(request, response);
		} else if ("wform".equals(action)) {	
		//action = wform --> 등록폼
			System.out.println("등록 폼 처리");
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		} else if ("insert".equals(action)) {
		//action = insert
			System.out.println("전화번호 저장");
			
			//파라미터 3개 값
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//personVon 묶고
			PersonVo personVo = new PersonVo(name, hp, company);
			
			//new Dao
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if ("uform".equals(action)) {
			//action = uform --> 수정폼
			System.out.println("수정 폼 처리");
			
			int personId = Integer.parseInt(request.getParameter("id"));
			//new Dao 한명 조회
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVo = phoneDao.getPerson(personId);
			
			//데이터를 전달
			request.setAttribute("pvo", personVo);
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/updateForm.jsp");
			rd.forward(request, response);
		} else if ("update".equals(action)) {
			//action = update
			System.out.println("전화번호 수정");
			
			//파라미터 4개 값
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("id"));
			
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personUpdate(personVo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if ("delete".equals(action)) {
			//action = delete
			System.out.println("전화번호 삭제");
			//파라미터 1개 값
			int personId = Integer.parseInt(request.getParameter("id"));
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(personId);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
