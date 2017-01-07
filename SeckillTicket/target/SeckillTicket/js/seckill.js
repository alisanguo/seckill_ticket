seckill={
    URL:{
        nowTime:function () {
            return '/ticket/nowTime'
        },

        exposerUrl:function (ticketId) {
            return '/ticket/'+ticketId+'/exposer';
        },

        executeSeckill:function (ticketId,md5) {
            return '/ticket/'+ticketId+'/'+md5+'/execution';
        },

        getTicketNum:function (ticketId) {
            return '/ticket/'+ticketId+'/number';
        }

    },

    isEmail:function (email) {
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        return email&&email!=''&&reg.test(email);
    },

    handleSeckill:function (ticketId,ticketName,node) {
        $.post(seckill.URL.exposerUrl(ticketId),{},function (result) {
            if(result&&result['success']){
                var exposer=result['data'];
                if(exposer['exposed']){
                    var md5=exposer['md5'];
                    node.hide().html('<button class="btn btn-primary" id="seckill_ticket">抢票</button>').show();
                    //绑定一次click事件，防止重复点击
                    $('#seckill_ticket').one('click',function () {
                        // 禁用button
                        $('#seckill_ticket').addClass('disabled');
                        //发送抢票请求
                        $.post(seckill.URL.executeSeckill(ticketId,md5),{ticketName:ticketName},function (result) {
                            if(result&&result['success']){
                                node.html('<label class="label label-success">'+result['data']['stateInfo']+'</label>');
                            }else if(result&&!result['success']){
                                node.html('<label class="label label-success">'+result['data']+'</label>');
                            }else{
                                node.html('<label class="label label-success">请求错误,请重试！</label>');
                            }
                        })
                    });
                }else{
                    seckill.countdown(ticketId,ticketName,exposer['now'],exposer['startTime'],exposer['endTime']);
                }
            }else{
                console.log("result="+result);
            }
        });
    },

    countdown:function (ticketId,ticketName,now,start,end) {
        var seckillBox=$('#time_box');
        if(now>end){
            //秒杀结束
            seckillBox.html('抢票结束');
        }else if(now<start){
            //秒杀未开启，倒计时
            var killTime=start+1000;
            seckillBox.countdown(killTime,function (event) {
                var format=event.strftime('距抢票开始还有: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //倒计时结束后,执行秒杀
            }).on('finish.countdown',function () {
                seckill.handleSeckill(ticketId,ticketName,$('#result_box'));
            });
        }else{
            seckill.handleSeckill(ticketId,ticketName,$('#result_box'));
            var killTime=end;
            var count=3;
            seckillBox.countdown(killTime,function (event) {
                var format=event.strftime('距抢票结束还有: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //每3秒刷新页面库存
                count--;
                if(count==0){
                    $.post(seckill.URL.getTicketNum(ticketId),{},function (result) {
                        if (result&&result['success']){
                            var num=result['data'];
                            if(num>0){
                                $('#ticketNum').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩余库存数：'+num+'张');
                            }else{
                                $('#ticketNum').html('售罄');
                            }
                        }else{
                            console.log('result='+result);
                        }
                    })
                    count=3;
                }
            }).on('finish.countdown',function () {
                seckillBox.html('抢票结束');
            });
        }
    },

    detail:{
        init:function(params){
            var ticketId=params['ticketId'];
            var ticketName=params['ticketName'];
            var startTime=params['startTime'];
            var endTime=params['endTime'];
            var killEmail=$.cookie('killEmail');

            //邮箱绑定
            if(seckill.isEmail(killEmail)){
                $.get(seckill.URL.nowTime(),{},function (result) {
                    if(result&&result['success']){
                        var now=result['data'];
                        seckill.countdown(ticketId,ticketName,now,startTime,endTime);
                    }else{
                        console.log('result='+result);
                    }
                })
            }else{
                $('#emailModal').modal({
                    show:true,
                    backdrop:'static',
                    keyboard:false
                });

                $('#modelBtn').click(function(){
                    var inputEmail=$('#killEmail').val();
                    if(seckill.isEmail(inputEmail)){
                        //写入邮箱到cookie
                        $.cookie('killEmail',inputEmail,{expires:7,page:'/ticket'});
                        window.location.reload();
                    }else{
                        $('#modelMessage').hide().html('<label class="label label-danger">邮箱错误!</label>').show(300);
                    }
                });
            }
        }
    }
}