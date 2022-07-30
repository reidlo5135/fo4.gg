<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <center>
        <h1>FO4.GG</h1>
        <input type="text" class="nickname" name="nickname" />
        <button type="button" id="btn_search">SEARCH</button>

        <h2 id="val_accessId"></h2>
        <h2 id="val_nickname"></h2>
        <h2 id="val_level"></h2>
    </center>
</body>
<script>
    $(document).ready(function () {
        $('#btn_search').on('click', function () {
            const nickname = $('.nickname').val();
            console.log('nickname : ', nickname);
            $.ajax({
                type: 'GET',
                url: '/fo4.gg/v1/api/user/' + nickname,
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
