<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="bean.test.Favor"%>
<jsp:useBean id="db" class="bean.test.DB" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	String id = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	} else {
		out.println("<script>");
		out.println("alert('로그인 정보를 확인하세요');");
		out.println("location.href='Login.jsp'");
		out.println("</script>");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>now.jsp</title>
</head>
<body align="center">
	<%=id%>님이 로그인 하셨습니다
	<a href=Logout.jsp>로그아웃</a>

	<h2>취미 생활 선택 현황</h2>
	<form>
		<table border="1" align="center" style="border-collapse: collapse;">
			<tr>
				<td width="100">취미 생활</td>
				<td width="100">투표 인원</td>
			</tr>
			<%
				int x = 0;
				String[][] list = db.getFavor();
				for(String []fav : list){
					out.print("<tr>");
					out.print("<td width = 100>" + list[x][0] + "</td>");
					out.print("<td width = 100>" + list[x][1] + "</td>");
					out.print("</tr>");
					x++;
				}
			%>
		</table>
	</form>
	<a href=favor.jsp>취미 생활 선택</a>
</body>
</html>