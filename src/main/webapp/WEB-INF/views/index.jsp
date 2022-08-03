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
                <form action="" class="narrow-w form-search d-flex align-items-stretch mb-3" data-aos="fade-up" data-aos-delay="200">
                    <input type="text" id="input_nickname" class="form-control px-4" placeholder="닉네임을 입력해주세요.">
                    <button type="button" id="btn-search" class="btn btn-dark">Search</button>
                </form>

                <h2 class="heading" data-aos="fade-up">
                    <span id="val_nickname"></span>
                </h2>
                <h2 class="heading" data-aos="fade-up">
                    <span id="val_level"></span>
                </h2>
                <h2 class="heading" data-aos="fade-up">
                    <span id="val_division_1on1"></span>
                </h2>
                <h2 class="heading" data-aos="fade-up">
                    <span id="val_division_coach"></span>
                </h2>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#btn-search').on('click', function () {
            const nickname = $('#input_nickname').val();
            console.log('nickname : ', nickname);
            if(nickname === null || nickname === '') {
                alert('닉네임을 입력해주세요!');
            } else {
                $.ajax({
                    type: 'GET',
                    url: '${path}/v1/api/user/info/' + nickname,
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8'
                }).done(function (response) {
                    console.log('find user info done response : ' + JSON.stringify(response));
                    console.log('find user info done response.data : ' + response.data);
                    if(response.code === 0) {
                        const json = JSON.parse(response.data);
                        console.log('find user info done response json : ' + json.accessId);

                        $.ajax({
                           type: 'GET',
                           url: '${path}/v1/api/user/division/' + json.accessId,
                           dataType: 'json',
                           contentType: 'application/json;charset=UTF-8'
                        }).done(function (response) {
                            console.log('find user division done response : ' + JSON.stringify(response));
                            console.log('find user division done response.data : ' + JSON.stringify(response.data));

                            if(response.code === 0) {
                                const divisionJson = JSON.parse(JSON.stringify(response.data));
                                console.log('find user divison done response json : ' + divisionJson);

                                $.ajax({
                                    type: 'GET',
                                    url: '${path}/v1/api/user/division/json/' + divisionJson.pvpDivision + '/' + divisionJson.coachDivision,
                                    dataType: 'json',
                                    contentType: 'application/json;charset=UTF-8'
                                }).done(function (response) {
                                    console.log('find divisionJSON done response : ' + JSON.stringify(response));
                                    console.log('find divisionJSON done response.data : ' + JSON.stringify(response.data));

                                    const rankJson = JSON.parse(JSON.stringify(response.data));
                                    console.log('find divisionJSON done response rankJson : ' + rankJson);

                                    if(response.code === 0) {
                                        $('#val_nickname').html('구단주 닉네임 : ' + json.nickname);
                                        $('#val_level').html('구단주 레벨 : ' + json.level);
                                        $('#val_division_1on1').html('1on1 최고 등급 : ' + rankJson.pvpDivisionName + '(' + divisionJson.pvpDate + ')');
                                        $('#val_division_coach').html('감독모드 최고 등급 : ' + rankJson.coachDivisionName + '(' + divisionJson.coachDate + ')');
                                    }
                                }).fail(function (err) {
                                    const error = JSON.parse(JSON.stringify(err));
                                    console.error('find user info error json : ' + JSON.stringify(error));
                                    alert('ERROR STATUS : ' + error.status + '\nERROR CODE : ' + error.responseJSON.code + '\nERROR MSG : ' + error.responseJSON.msg);
                                });
                            }
                        }).fail(function (err) {
                            const error = JSON.parse(JSON.stringify(err));
                            console.error('find user info error json : ' + JSON.stringify(error));
                            alert('ERROR STATUS : ' + error.status + '\nERROR CODE : ' + error.responseJSON.code + '\nERROR MSG : ' + error.responseJSON.msg);
                        });
                    }
                }).fail(function (err) {
                    const error = JSON.parse(JSON.stringify(err));
                    console.error('find user info error json : ' + JSON.stringify(error));
                    alert('ERROR STATUS : ' + error.status + '\nERROR CODE : ' + error.responseJSON.code + '\nERROR MSG : ' + error.responseJSON.msg);
                    $('#val_nickname').html('');
                    $('#val_level').html('');
                    $('#val_division_1on1').html('');
                    $('#val_division_coach').html('');
                });
            }
        });
    });
</script>
<script src="${path}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${path}/resources/js/tiny-slider.js"></script>
<script src="${path}/resources/js/aos.js"></script>
<script src="${path}/resources/js/navbar.js"></script>
<script src="${path}/resources/js/counter.js"></script>
<script src="${path}/resources/js/custom.js"></script>
</body>
</html>
