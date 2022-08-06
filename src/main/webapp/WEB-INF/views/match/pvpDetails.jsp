<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FO4.GG</title>
    <link rel="shortcut icon" href="${path}/resources/favicon.png">
    <meta name="description" content="fo4.gg" />
    <meta name="keywords" content="bootstrap, bootstrap5" />

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <link rel="stylesheet" href="${path}/resources/fonts/icomoon/style.css">
    <link rel="stylesheet" href="${path}/resources/fonts/flaticon/font/flaticon.css">

    <link rel="stylesheet" href="${path}/resources/css/tiny-slider.css">
    <link rel="stylesheet" href="${path}/resources/css/aos.css">
    <link rel="stylesheet" href="${path}/resources/css/style.css">
</head>
<body>
<div class="hero">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-9 text-center">
                <h1 class="heading" data-aos="fade-up">FO4.GG</h1>
                <h5 class="heading" data-aos="fade-up">Data based on NEXON DEVELOPERS</h5>
                <h1 class="heading" data-aos="fade-up">${userList.get(0).nickname}</h1>
                <h1 class="heading" data-aos="fade-up">${userList.get(0).level}</h1>
                <h1 class="heading" data-aos="fade-up">${userList.get(0).pvpDivisionName}</h1>
                <h1 class="heading" data-aos="fade-up">${userList.get(0).pvpAchievementDate}</h1>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${path}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${path}/resources/js/tiny-slider.js"></script>
<script src="${path}/resources/js/aos.js"></script>
<script src="${path}/resources/js/navbar.js"></script>
<script src="${path}/resources/js/counter.js"></script>
<script src="${path}/resources/js/custom.js"></script>
</html>
