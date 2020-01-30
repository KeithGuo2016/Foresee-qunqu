var public = {
		dialogLayer: function(type,title,url,xarea,yarea,number,max,num){  //公用弹层
			var dialog = layer.open({
	            type:type,
	            title:title,
	            content:url,
	            offset:'auto',
	            area:[xarea,yarea],
	            anim:0,
	            fixed:true,
	            scrollbar:false,
	            resize:false,
	            maxmin:max,
	            full:function() { //点击最大化后的回调函数
	            	$(".layui-layer-content,iframe").css("height", "100%");
	            	$(".layui-layer-content,iframe").height($(".layui-layer-content,iframe").height() - 46 + "px");
	            },
                restore:function() { //点击还原后的回调函数
	            	$(".layui-layer-content,iframe").css("height", num);
    	        },
	            closeBtn: number,
	            cancel: function(index, layero){
	            	//
	            }
	        });
		},
		adaptation: function(){  //判断表格渲染高度
			var winW = window.screen.width;
			$(".layui-table-main").css("max-height","570px");
	        if (winW <= 1400){
	            $(".layui-table-main").css("max-height","255px");
	        }
		},
		getmenu: function(){  //获取三级菜单
			var menu_number = sessionStorage.getItem("menuId");
			var allmenu = sessionStorage.getItem("allmenu");
			if(allmenu != "" && allmenu != null){
				allmenu = JSON.parse(allmenu);
				var data = allmenu.dataform;
				var menu = '';
	            $.each(data.data, function(index, value) {
	            	if(menu_number == value.menupid && value.menulevel == 3){
	            		menu += '<button class="layui-btn layui-btn-sm '+value.menuicon+'">'+value.menuname+'</button>';
	            	}
	            });
	            $("#menuBtn").html(menu);
			}
		},
		getYear: function(n){  //获取n年后的今天
			var date = new Date() ;
	        var year,month,day ;
	        year = date.getFullYear() + n;
	        month = date.getMonth()+1;
	        day = date.getDate();
	        s = year + '-' + ( month < 10 ? ( '0' + month ) : month ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
	        return s ;
		},
		getTime: function(time){  //获取时间戳[时间段]
			if(time != ""){
				var time_arr = time.split(' - ');
		    	var time1 = time_arr[0] + ' 00:00:00';
		    	var time2 = time_arr[1] + ' 23:59:59';
		    	/*time1 = new Date(Date.parse(time1.replace(/-/g, "/")));
			    time1 = time1.getTime();
		    	time2 = new Date(Date.parse(time2.replace(/-/g, "/")));
			    time2 = time2.getTime();*/
			    if(String(time1) && String(time2)){
			    	return {"start": time1, "end": time2}
			    }else{
			    	return {"start": "", "end": ""}
			    }
			}else{
				return {"start": "", "end": ""}
			}
		},
		getTimeOne: function(time){  //获取时间戳[单个时间]
			time = new Date(Date.parse(time.replace(/-/g, "/")));
	    	time = time.getTime();
		    if(!isNaN(time)){
		    	return time/1000
		    }else{
		    	return ""
		    }
		},
		getTimeTwo: function(time){  //获取当天的时间段00:00:00 - 23:59:59
			if(time != ""){
				var time_arr = time.split(' ');
		    	var time1 = time_arr[0] + ' 00:00:00';
		    	var time2 = time_arr[0] + ' 23:59:59';
			    if(String(time1) && String(time2)){
			    	return {"start": time1, "end": time2}
			    }else{
			    	return {"start": "", "end": ""}
			    }
			}else{
				return {"start": "", "end": ""}
			}
		},
		formatDate: function(secs,status){  //时间戳转时间[年-月-日]
			var t = new Date(secs);
            var year = t.getFullYear();
            var month = t.getMonth() + 1;
            if(month < 10){month = '0' + month;}
            var date = t.getDate();
            if(date < 10){date = '0' + date;}
            var hour = t.getHours();
            if(hour < 10){hour = '0' + hour;}
            var minute = t.getMinutes();
            if(minute < 10){minute = '0' + minute;}
            var second = t.getSeconds();
            if(second < 10){second = '0' + second;}
			if(status){
				return year+'-'+month+'-'+date+ ' ' + hour + ':' + minute + ':' + second;
			}else{
				return year+'-'+month+'-'+date;
			}
		},
		formatDate2: function(secs,status){  //时间戳转时间[年.月.日]
			var t = new Date(secs);
            var year = t.getFullYear();
            var month = t.getMonth() + 1;
            if(month < 10){month = '0' + month;}
            var date = t.getDate();
            if(date < 10){date = '0' + date;}
            var hour = t.getHours();
            if(hour < 10){hour = '0' + hour;}
            var minute = t.getMinutes();
            if(minute < 10){minute = '0' + minute;}
            var second = t.getSeconds();
            if(second < 10){second = '0' + second;}
			if(status){
				return year+'.'+month+'.'+date+ ' ' + hour + ':' + minute + ':' + second;
			}else{
				return year+'.'+month+'.'+date;
			}
		},
		nowTime: function() {  //获取当前时间[年-月-日]
	        var time = new Date(), year = time.getFullYear(), month=time.getMonth()+1, date = time.getDate(),hour = time.getHours(), minutes = time.getMinutes(), second = time.getSeconds();
	        date < 10 ? date = '0' + date : date;month < 10 ? month = '0' + month : month;hour < 10 ? hour = '0' + hour : hour;minutes < 10 ? minutes = '0' + minutes : minutes;second < 10 ? second = '0' + second : second;
	        var now_time = year + '-' + month + '-' + date + ' ' + hour + ':' + minutes + ':' + second;
	        return now_time;
		},
		nowTime2: function(status) {  //获取当前时间[年.月.日]
	        var time = new Date(), year = time.getFullYear(), month=time.getMonth()+1, date = time.getDate(),hour = time.getHours(), minutes = time.getMinutes(), second = time.getSeconds();
	        date < 10 ? date = '0' + date : date;month < 10 ? month = '0' + month : month;hour < 10 ? hour = '0' + hour : hour;minutes < 10 ? minutes = '0' + minutes : minutes;second < 10 ? second = '0' + second : second;
	        if(status == '1'){
	        	var now_time = year + '.' + month + '.' + date;
	        }else{
	        	var now_time = year + '.' + month + '.' + date + ' ' + hour + ':' + minutes + ':' + second;
	        }
	        return now_time;
		},
		unique: function (arr){  //数组查重
	    	for(var i = 0; i < arr.length-1; i++){
  		         for(var j = i+1; j < arr.length; j++){
  		             if(arr[i]==arr[j]){
  		               arr.splice(j,1);
  		                j--;
  		           }
  		       }
  		    }
  		    return arr;
	    },
	    showImg: function(imgurl) {  //表格显示图片
	    	/*var is_img = imgurl.indexOf('/img') != -1;
	    	var is_img2 = imgurl.indexOf('https') != -1;
	    	var is_img3 = imgurl.indexOf('http') != -1;*/
	    	if(imgurl){
	    		var str = '<img src="'+ imgurl +'" layer-src="'+ imgurl +'" />';
	    	}else{
	    		var str = '--';
	    	}
	    	return str;
	    },
	    bigImg: function(images) {  //图片放大
	    	layer.photos({
				closeBtn: 2,
				photos: images,
				anim: 0
			}); 
	    },
	    getParame: function() {  //获取链接后的参数
			var url = window.location.search;
			var obj = {};
			if(url.indexOf("?") != -1){
				var str = url.substring(1);
				var arr = str.split("&");
				for(var i=0; i<arr.length; i++){
					obj[arr[i].split("=")[0]] = unescape(arr[i].split("=")[1]);
				}
			}
			return obj;
		},
		is_null: function(data){  //判断是否为空
			if(data == null || data == "undefined"){
				data = "--";
			}
			return data;
		},
		num_round: function(num){  //金额四舍五入
	    	if(typeof(num) == "number" || typeof(num) == "string"){
				num = Math.round(num * 100) / 100;
			}else if(num == null || num == ""){
				num = 0;
			}
			return num;
		},
		//图片大小判断
	    /*轮播  1
		社刊  2
		文章  3
		社群图标 4
		社群背景 5
		征稿  6*/
		check_img: function(base64img, type) {
			var fa = false;
			$.ajax({
		    	type: 'post',
		    	url: '/img/uploadcheck',
		    	data: {'base64img': base64img.split(",")[1], 'type': type},
		    	async: false,
				dataType: 'json',
				success: function(data) {
					fa = data;
				}
		    });
			return fa;
		}
};