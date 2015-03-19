<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	var i = 0;
	$("label[name='userName']").each(function() {
		switch (i % 4) {
		case 0:
			$(this).addClass("label").addClass("label-info");
			break;
		case 1:
			$(this).addClass("label").addClass("label-success");
			break;
		case 2:
			$(this).addClass("label").addClass("label-primary");
			break;
		case 3:
			$(this).addClass("label").addClass("label-warning");
			break;
		}
		i++;
	});
</script>

<c:forEach items="${proMembers }" var="userVO">
	<label name="userName"><small>${userVO.getNick() }</small></label>
</c:forEach>
