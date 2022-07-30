<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <title>FO4.GG</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <center>
        <h1>FO4.GG</h1>
        <input type="text" class="nickname" name="nickname" />
        <button type="button" id="btn_search">SEARCH</button>

        <h2>
            <span>ACCESS ID : </span>
            <span id="val_accessId"></span>
        </h2>
        <h2>
            <span>NICKNAME : </span>
            <span id="val_nickname"></span>
        </h2>
        <h2>
            <span>LEVEL : </span>
            <span id="val_level"></span>
        </h2>
    </center>
</body>
<script>
    $(document).ready(function () {
        $('#btn_search').on('click', function () {
            const nickname = $('.nickname').val();
            console.log('nickname : ', nickname);
            $.ajax({
                type: 'GET',
                url: '${path}/v1/api/user/' + nickname,
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8'
            }).done(function (res) {
                console.log('find user info done response : ' + JSON.stringify(res));
                const json = JSON.parse(JSON.stringify(res));
                console.log('find user info don response json : ' + json.accessId);
                $('#val_accessId').html(json.accessId);
                $('#val_nickname').html(json.nickname);
                $('#val_level').html(json.level);
            }).fail(function (err) {
                alert(JSON.stringify(err));
            });
        });
    });
</script>
</html>
