<%@ page import="java.util.List" %>
<%@ page import="com.smbms.pojo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong>
		<span>用户管理页面</span>
	</div>
	<div class="search">
		<form method="post" action="user_list_2">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>用户名：</span>
			<input name="queryname" class="input-text"	type="text" value="${queryUserName }">

			<span>用户角色：</span>
			<select name="queryUserRole">
				<c:if test="${roleList != null }">
					<option value="0">--请选择--</option>
					<c:forEach var="role" items="${roleList}">
						<option <c:if test="${role.id == queryUserRole }">selected="selected"</c:if>
								value="${role.id}">${role.roleName}</option>
					</c:forEach>
				</c:if>
			</select>

			<input type="hidden" name="currentPageNo" value="0"/>
			<input	value="查 询" type="submit" id="searchbutton">
			<a href="adduser1" >添加用户</a>
		</form>
	</div>
	<!--用户-->
	<table class="providerTable" cellpadding="0" cellspacing="0">
		<tr class="firstTr">
			<th width="10%">用户编码</th>
			<th width="20%">用户名称</th>
			<th width="10%">性别</th>
			<th width="10%">年龄</th>
			<th width="10%">电话</th>
			<th width="10%">用户角色</th>
			<th width="30%">操作</th>
		</tr>
		<c:forEach var="user" items="${userList }" varStatus="status">

			<tr>
				<td>
					<span>${user.userCode }</span>
				</td>
				<td>
					<span>${user.userName }</span>
				</td>
				<td>
							<span>
								<c:if test="${user.gender==1}">男</c:if>
								<c:if test="${user.gender==2}">女</c:if>
							</span>
				</td>
				<td>
					<span>${user.age}</span>
				</td>
				<td>
					<span>${user.phone}</span>
				</td>
				<td>
					<span>${user.userRoleName}</span>

				</td>

				<td>
					<span><a class="viewUser" href="user_view?id=${user.id}" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span>
							<a class="deleteUser" href="javascript:;" userid=${user.id } username=${user.userName }>
							<img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/>
							</a>

						</span>
					<input hidden id="userid" name="userid" value="${user.id }">
					<input hidden id="userRole" name="userid" value="${user.userRole }">
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" id="totalPageCount" value="${pageSupport.getTotalPageCount()}"/>
	<c:import url="rollpage.jsp">
		<c:param name="totalCount" value="${pageSupport.getTotalCount()}"/>
		<c:param name="currentPageNo" value="${pageSupport.getCurrentPageNo()}"/>
		<c:param name="totalPageCount" value="${pageSupport.getTotalPageCount()}"/>
	</c:import>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
	<div class="removerChid">
		<h2>提示</h2>
		<div class="removeMain">
			<p>你确定要删除该用户吗？</p>
			<a href="#" id="yes">确定</a>
			<a href="#" id="no">取消</a>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userlist.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>