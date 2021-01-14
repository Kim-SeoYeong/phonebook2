<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.PersonVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- PhoneController 포워드 시켰다. personList -->
<%--
<%
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList");	//object형이기 때문에 강제로 List<Person>으로 형변환을 시켜줌
																				//request해준 곳에서 getattribute를 해와라
	System.out.println("========list.jsp=======");
	System.out.println(personList);	//println은 무조건 ~.toString()을 찾게함.

 %>
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 리스트</h1>
	
	<p>입력한 정보 내역입니다.</p><br>

	<c:forEach items="${requestScope.pList}" var="personList">
		<table border="1">
			<tr>
				<td>이름(name)</td>
				<td>${personList.name}</td>	
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td>${personList.hp}</td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td>${personList.company}</td>
			</tr>
			<tr>
				<td><a href="/phonebook2/pbc?action=uform&id=${personList.personId}">수정</a></td>
				<td><a href="/phonebook2/pbc?action=delete&id=${personList.personId}">삭제</a></td>
			</tr>
		</table>
		<br>
	</c:forEach>
	<br><br>
	<a href="">추가번호 등록</a>
</body>
</html>