<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>
<c:import url="commonLink.jsp" />
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
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
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

                                let divisionUrl = null;

                                if(divisionJson.pvpDivision === null && divisionJson.coachDivision === null) {
                                    divisionUrl = '${path}/v1/api/user/division/json/0/0';
                                } else if(divisionJson.pvpDivision === undefined) {
                                    divisionUrl = '${path}/v1/api/user/division/json/0' + '/' + divisionJson.coachDivision;
                                } else if(divisionJson.coachDivision === undefined) {
                                    divisionUrl = '${path}/v1/api/user/division/json/' + divisionJson.pvpDivision + '/0';
                                } else {
                                    divisionUrl = '${path}/v1/api/user/division/json/' + divisionJson.pvpDivision + '/' + divisionJson.coachDivision;
                                }
                                console.log('find user division done response divisionUrl : ' + divisionUrl);
                                $.ajax({
                                    type: 'GET',
                                    url: divisionUrl,
                                    dataType: 'json',
                                    contentType: 'application/json;charset=UTF-8'
                                }).done(function (response) {
                                    console.log('find divisionJSON done response : ' + JSON.stringify(response));
                                    console.log('find divisionJSON done response.data : ' + JSON.stringify(response.data));

                                    const rankJson = JSON.parse(JSON.stringify(response.data));
                                    console.log('find divisionJSON done response rankJson : ' + rankJson);

                                    if(response.code === 0) {
                                        const userInfoJson = {
                                            'nickname': json.nickname,
                                            'level': json.level,
                                            'pvpDivisionName': rankJson.pvpDivisionName,
                                            'pvpAchievementDate': divisionJson.pvpDate,
                                            'coachDivisionName': rankJson.coachDivisionName,
                                            'coachAchievementDate': divisionJson.coachDate
                                        }

                                        $.ajax({
                                            type: 'POST',
                                            url: '${path}/v1/api/user/register/userInfo',
                                            dataType: 'json',
                                            data: JSON.stringify(userInfoJson),
                                            contentType: 'application/json;charset=UTF-8'
                                        }).done(function (response) {
                                            console.log('register userInfo done response : ' + JSON.stringify(response));
                                            console.log('register userInfo done response.data : ' + JSON.stringify(response.data));
                                        }).fail(function (err) {
                                            const error = JSON.parse(JSON.stringify(err));
                                            console.error('register userInfo error json : ' + JSON.stringify(error));
                                        });

                                        const pvpUrl = '${path}/details/pvp/' + json.nickname;
                                        location.replace(pvpUrl);
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
                });
            }
        });
    });
</script>
</body>
</html>
