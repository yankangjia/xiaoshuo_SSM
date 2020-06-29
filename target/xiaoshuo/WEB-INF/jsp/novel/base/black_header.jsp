<%@ page language="java" contentType="text/html; charset=UTF-8"
         autoFlush="false" buffer="1000kb" pageEncoding="UTF-8"
         import="java.util.*"%>

</head>
<body>
<header>

<div class="header-div">
    <ul>
        <li>
            <a href="/">起点中文网</a>
            <div class="dropdown">
                <a href="https://www.qdmm.com/">起点女生网</a>
                <a href="http://chuangshi.qq.com/">创世中文网</a>
                <a href="http://yunqi.qq.com/">云起书院</a>
            </div>
        </li>

        <%
            String[][] all_category_name = (String[][]) request.getAttribute("all_category_name");
            String[][] all_category_name_1_6 = new String[6][2];
            String[][] all_category_name_7_12 = new String[6][2];
            for(int i = 0; i < 6; i++){
                all_category_name_1_6[i] = all_category_name[i];
            }
            for(int i = 6; i < 12; i++){
                all_category_name_7_12[i-6] = all_category_name[i];
            }
            request.setAttribute("all_category_name_1_6",all_category_name_1_6);
            request.setAttribute("all_category_name_7_12",all_category_name_7_12);
        %>
        <c:forEach items="${all_category_name_1_6}" var="cate" varStatus="st">
            <li><a href="/novel/index_category/${cate[0]}">${cate[1]}</a></li>
        </c:forEach>

        <li>
            <a href="#">更多</a>
            <div class="dropdown">
                <a href="/novel/whole/">全部作品</a>
                <c:forEach items="${all_category_name_7_12}" var="cate" varStatus="st">
                    <a href="/novel/index_category/${cate[0]}">${cate[1]}</a>
                </c:forEach>
            </div>
        </li>
    </ul>
    <div class="my-book">
        <a href="/account/my_collect/">
            <i class="iconfont icon-bookrack"></i>
            <span>我的书架</span>
        </a>
    </div>
    <div class="auth-group">
        <c:if test="${user.is_authenticated == true}">
            <a href="/account/index/">${request.user.username}</a>
            <c:if test="${user.is_authenticated == true}">
                <a href="/cms/index/">后台管理</a>
            </c:if>
            <a href="/xsauth/logout/">退出</a>
        </c:if>
        <c:if test="${user.is_authenticated == false}">
            <a href="javascript:void(0);" class="sign-in">登录</a>
            <a href="javascript:void(0);" class="sign-up">注册</a>
        </c:if>
    </div>
    <div class="search">
        <form action="/novel/search/">
            <%--{% csrf_token %}--%>
            <input type="text" id="text" name="q" class="text" placeholder="你的灵兽看起来很好吃">
            <label class="submit-btn iconfont icon-baseline-search-px" for="submit"></label>
            <input type="submit" id="submit" hidden>
        </form>
    </div>
</div>

</header>