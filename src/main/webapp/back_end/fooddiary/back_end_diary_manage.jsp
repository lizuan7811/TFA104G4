<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.fooddiary.model.*"%>
<%@ page import="com.diarytype.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日誌管理</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/backstage.css" rel="stylesheet">
<style>
    table {
        border-spacing: 0;
        width: 100%;
        table-layout: fixed;
        word-break: break-all;
    }
    
    tr {
        text-align: center;
        height: 80px;
    }
    
    td {
        /* color: red; */
        text-overflow: ellipsis;
        /* 將顯示不完的以...顯示 */
        white-space: nowrap;
        /* 禁止td換行 */
        overflow: hidden;
        /* 隱藏X,Y滾動條 */
        border-bottom: 1px solid #8CE600;
    }
    
    th {
        padding: 10px;
    }
    
    table tbody tr:nth-child(odd) {
        background-color: #eee
    }
    
    table th{
        background-color: #8CE600;
        color: white;
        font-size: 16px;
        font-weight: bolder;
    }
    
    table th:first-child {
        border-radius: 5px 0 0 0;
        border: 1px solid #8CE600;
    }
    
    table th:last-child {
        border-radius: 0 5px 0 0;
        border-right: 1px solid #8CE600;
    }
    
    nav ul {
    margin-top: 20px;
    font-size: 24px;
    font-weight: bolder;
    color: white;
    text-align: center;
    }

    nav ul li {
        margin-bottom: 15px;
    }

    nav ul li a {
        text-decoration: none;
        color: white;
    }

    nav ul ul {
        display: none;
        margin: 0px;
    }

    nav ul li:hover>ul {
        display: block;
    }

    nav ul ul li {
        margin-top: 5px;
        margin-bottom: 5px;
    }

    nav ul ul li a {
        position: absolute;
        float: none;
        left: 70px;
        font-size: 16px;
        color: red;
        text-decoration: none;
    }

</style>

</head>
<body>
    <header>
        <img src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png" alt="logo">
        <div class="top_box">
            <apean class="stage1">日誌管理</apean>
            <apean class="stage2">●全部日誌</apean>
        </div>
    </header>
    <main>

        <div class="aside">
            <nav>
                <ul class="nav_list">
                    <li>會員管理</li>

                    <li>
                        <a href="#">產品管理</a>
                        <ul>
                            <li><a href="insert_product.html">新增商品</a></li>
                            <li><a href="search_product.html">搜尋、修改商品</a></li>
                            <li><a href="searchAll_product.html">全部商品</a></li>
                        </ul>
                    </li>

                    <li><a href="#">食譜管理</a>
                        <ul>
                            <li><a href="Insert_Recipe.html">新增食譜</a></li>
                            <li><a href="Search_Recipe.html">搜尋、修改食譜</a></li>
                            <li><a href="searchAll_Recipe.html">全部食譜</a></li>
                        </ul>
                    </li>

                    <li>訂單管理</li>
                    <li><a href="#">日誌管理</a>
                        <ul>
                            <li><a href="">全部日誌</a></li>
                            <li><a href="">檢舉管理</a></li>
                            
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>

        <table class="mainbox">
            <tr class="list">
                <th>日誌編號</th>
                <th>會員編號</th>
                <th width="120px">日誌標題</th>
                <th>創建時間</th>
                <th>按讚數</th>
                <th>日誌分類</th>
                <th>檢舉狀態</th>
                <th>查詢</th>
                <th>移除</th>
            </tr>

<%
	FoodDiaryDAO dao = new FoodDiaryDAOimpl();
	List<FoodDiaryVO> list = dao.getAll();
	DiaryTypeService typeSvc = new DiaryTypeService();
	//   DiaryTypeVO typeVO = typeSvc.getOneType(all_diary.getDiaryType());
	Map map=typeSvc.getMap();
	for (FoodDiaryVO all_diary : list) {
%>		        


                <tr>
                    <td><%= all_diary.getDiaryID() %></td>
                    <td><%= all_diary.getCustID() %></td>
                    <td><%= all_diary.getSubject() %></td>
                    <td><fmt:formatDate pattern="yyyy-M-d" value="<%= all_diary.getCreatedTime() %>" /></td>
                    <td><%= all_diary.getForumLikeNum() %></td>
                    <td><%= map.get(all_diary.getDiaryType()) %></td> 
<%--                     <td><%= all_diary.getDiaryType() %></td>  --%>
                    <td><%= all_diary.getDiaryStatus()==true? "已被檢舉":"未被檢舉"%></td> 
                    <td>
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/DiaryManageServlet" style="margin-bottom: 0px;">
                        <input type="submit" value="查詢">
                        <input type="hidden" name="diaryID"  value="<%= all_diary.getDiaryID() %>">
                        <input type="hidden" name="action"	value="SEARCH">
                    </FORM>
                    </td>
                    <td>
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/DiaryManageServlet" style="margin-bottom: 0px;">
                        <input type="submit" value="移除">
                        <input type="hidden" name="diaryID"  value="<%= all_diary.getDiaryID() %>">
                        <input type="hidden" name="action" value="DELLET">
                    </FORM>                  
                    </td>               
            </tr>
 <% } %> 
		</table>
 
    </main>
</body>
</html>