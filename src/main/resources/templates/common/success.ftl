<html>
<head>
    <meta charset="utf-8">
    <title>错误消息提示</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div  class="alert alert-dismissable alert-success">
                <button class="close" aria-hidden="true" type="button" data-dismiss="alert">×</button>
                <h4>
                    取消订单成功！
                </h4> ${msg}</strong><a class="alert-link" href="${url}">3秒之后自动跳转...</a>
            </div>
        </div>
    </div>
</div>
</body>
<#--页面跳转-->
<script>
    setTimeout('location.href="${url}"',3000)
</script>
</html>