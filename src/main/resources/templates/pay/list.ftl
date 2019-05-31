<html>
<head>
    <meta charset="utf-8">
    <title>后台管理模板</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
    <#--表格开始啦-->
        <div class="col-md-12 column">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建日期</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                        <#list orderDtoPage.content as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.getOrderAmountEnum().message}</td>
                            <td>${orderDTO.getPayStatusEnum().message}</td>
                            <td>${orderDTO.createTime}</td>
                        <td>
                            <#if orderDTO.getOrderAmountEnum().message != "已取消">
                                <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}"
                                   class="btn btn-default btn-danger" type="button">取消</a></td>
                            </#if>
                            <td>
                                <a href="/sell/seller/order/detail?orderId=${orderDTO.getOrderId()}"
                                   class="btn btn-default btn-primary" type="button">详情</a>
                            </td>
                        </tr>
                        </#list>
                </tbody>
            </table>
        </div>
    <#--表格结束啦-->
    <#--分页开始啦-->
        <div class="col-md-12 column pull-right">
            <ul class="pagination">
            <#--如果小于1-->
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">前一页</a></li>
                    </#if>
            <#--循环开始了-->
                    <#list 1..orderDtoPage.getTotalPages() as index>
                        <#if currentPage == index>
                             <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                             <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                        </#if>
                    </#list>
            <#--循环结束了-->
                      <#if currentPage gte orderDtoPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                      <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=10">下一页</a></li>
                      </#if>
            </ul>
        </div>
    <#--分页结束啦-->
    </div>
</div>

<#--弹窗开始啦-->
<div class="modal fade" id="deepModal" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提示
                </h4>
            </div>
            <div class="modal-body">
                您有新的订单
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" onclick="location.reload()">查看新订单</button>
            </div>
        </div>
    </div>
</div>
<#--弹窗结束啦-->

<#--播放声音开始-->
<audio id="notice" loop="loop">
    <source src="" type="audio/mpen">
</audio>
<#--播放声音结束-->

<script>
    var websocket = null;
    // 判断浏览器有没有通信功能
    if ('WebSocket' in window) {
        //创建一个通信对象(ws协议)
            websocket = new WebSocket('ws://r9rkvd.natappfree.cc/sell/webSocket')
    } else {
        alert("您的浏览器不支持通信功能，请更换最新浏览器尝试！");
    }
    //windows事件
    websocket.onopen = function (e) {
        console.log('建立连接');
    }
    websocket.onclose = function (e) {
        console.log('关闭连接');
    }
    websocket.onmessage = function (e) {
        //可以是（消息，图片、音乐）
        console.log('收到的消息' + e.data);
        $('deepModal').showModal('show');
        document.getElementById('notice').play();
    }
    websocket.onerror = function (e) {
        console.log('通信错误！')
    }
    // 当浏览器关闭的时候同时也关闭通信
    websocket.onbeforeunload = function (e) {
        websocket.close();
    }
</script>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>





