<html>
<head>
    <meta charset="utf-8">
    <title>订单详情页面</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
        <#--头表格开始啦-->
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>订单ID</th>
                    <th>总价</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDto.getOrderId()}</td>
                    <td>${orderDto.orderAmount}</td>
                </tr>
                </tbody>
            </table>
        <#--头表格结束啦-->
        </div>
    </div>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
        <#-- -->
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>商品ID</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                    <#list orderDto.getOrderDetailList() as orderDetail>
                    <tr>
                        <td>${orderDetail.getProductId()}</td>
                        <td>${orderDetail.getProductName()}</td>
                        <td>${orderDetail.getProductPrice()}</td>
                        <td>${orderDetail.getProductQuantity()}</td>
                        <td>${orderDetail.getProductQuantity() * orderDetail.getProductPrice()}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#--订单详情表格结束啦-->
        </div>
        <div class="container">
            <#if orderDto.getOrderAmountEnum().message == "新订单">
                <a href="/sell/seller/order/finsh?orderId=${orderDto.orderId}" class="btn btn-default btn-success" type="button">完结订单</a>
                <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}" class="btn btn-default btn-danger" type="button">取消订单</a>
            </#if>
        </div>
    </div>
</div>


</body>
</html>