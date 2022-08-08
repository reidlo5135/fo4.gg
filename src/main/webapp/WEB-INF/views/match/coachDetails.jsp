<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <link rel="shortcut icon" href="${path}/resources/favicon.png">
</head>
<body>
<c:import url="../commonLink.jsp" />
<div class="hero">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-9 text-center">
                <h1 id="h1_title" class="heading" data-aos="fade-up">FO4.GG</h1>
                <h5 class="heading" data-aos="fade-up">Data based on NEXON DEVELOPERS</h5>
                <h1 class="heading" data-aos="fade-up">공식 경기(감독 모드) 정보 </h1>
                <img class="heading" id="img_division" src="${userList.get(0).coachImageUrl}" data-aos="fade-up" />
                <h1 class="heading" data-aos="fade-up">구단주 이름 : ${userList.get(0).nickname}</h1>
                <h1 class="heading" data-aos="fade-up">구단주 레벨 : ${userList.get(0).level}</h1>
                <h1 class="heading" id="h1_divisionName" data-aos="fade-up">최고 등급 : ${userList.get(0).coachDivisionName}</h1>
                <h1 class="heading" id="h1_divisionDate" data-aos="fade-up">달성 일자 : ${userList.get(0).coachAchievementDate}</h1>
            </div>
            <button id="btn_pvp" type="button" class="btn btn-dark">1on1 조회</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#h1_title').on('click', function () {
            location.replace('${path}/');
        });

        if('${userList.get(0).coachDivisionName}' === '기록이 존재하지 않습니다.') {
            $('#h1_divisionName').html('경기 기록이 존재하지 않습니다.');
            $('#h1_divisionDate').html('');
        }

        $('#btn_pvp').on('click', function () {
            const pvpUrl = '${path}/details/pvp/${userList.get(0).nickname}';
            location.replace(pvpUrl);
        });
    });
</script>
</body>
</html>
