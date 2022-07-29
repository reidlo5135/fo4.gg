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
                contentType: 'application/json; charset=utf-8'
            }).done(function (res) {
                alert(JSON.stringify(res));
            }).fail(function (err) {
                alert(JSON.stringify(err));
            });
        });
    });
</script>
</html>
