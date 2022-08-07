<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>
<c:import url="../commonLink.jsp" />
<div class="hero">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-9 text-center">
                <h1 id="h1_title" class="heading" data-aos="fade-up">FO4.GG</h1>
                <h5 class="heading" data-aos="fade-up">Data based on NEXON DEVELOPERS</h5>
                <h1 class="heading" data-aos="fade-up">공식 경기(1on1) 정보 </h1>
                <h1 class="heading" data-aos="fade-up">구단주 이름 : ${userList.get(0).nickname}</h1>
                <h1 class="heading" data-aos="fade-up">구단주 레벨 : ${userList.get(0).level}</h1>
                <h1 class="heading" data-aos="fade-up">최고 등급 :  ${userList.get(0).pvpDivisionName}</h1>
                <h1 class="heading" data-aos="fade-up">달성 일자 : ${userList.get(0).pvpAchievementDate}</h1>
            </div>
            <button id="btn_coach" type="button" class="btn btn-dark">감독 모드 조회</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#h1_title').on('click', function () {
            location.replace('${path}/');
        });

        $('#btn_coach').on('click', function () {
            const coachUrl = '${path}/details/coach/${userList.get(0).nickname}';
            location.replace(coachUrl);
        });
    });
</script>
</body>
</html>
