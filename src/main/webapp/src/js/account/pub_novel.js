function PubNovel(){

}

// 监听上传图片事件
PubNovel.prototype.listenUploadFileEvent = function(){
    var uploadBtn = $('#thumbnail-btn');    // input
    uploadBtn.change(function(){
        var file = $(this)[0].files[0];
        var formData = new FormData();
        formData.append('file',file);
        myajax.post({
            'url': '/account/upload_file/',
            'data': formData,
            'processData': false,
            'contentType': false,
            'success': function(result){
                if(result['code'] === 200){
                    var url = result['data']['url'];
                    var coverUrlInput = $('#cover_url-form');
                    coverUrlInput.val(url)
                }
            }
        })
    })
};

// 图片上传过程中
PubNovel.prototype.handleFileUploadProgress = function(result){
    var total = result.total;
    var percent = total.percent;    // int  上传百分比
    var percentText = percent.toFixed(0) + '%';     // 去除小数部分
    var progressGroup = PubNovel.progressGroup;
    progressGroup.show();
    var progressBar = PubNovel.progressBar;
    progressBar.css('width',percentText);
    progressBar.text(percentText)
};

// 图片上传错误
PubNovel.prototype.handleFileUploadError = function(error){
    var progressGroup = PubNovel.progressGroup;
    progressGroup.hide();
    console.log(result.message)
};

// 图片上传成功
PubNovel.prototype.handleFileUploadComplete = function(response){
    // response:    { hash: ... , key: ... }
    var progressGroup = PubNovel.progressGroup;
    progressGroup.hide();
    var domain = 'http://q0i4557d7.bkt.clouddn.com/';
    var filename = response.key;
    var url = domain + filename;
    var thumbnailInput = $("input[name='thumbnail']");
    thumbnailInput.val(url)
};

// 将图片上传到七牛云
PubNovel.prototype.listenQiniuUploadFileEvent = function(){
    var self = this;
    var thumbnailInput = $('#thumbnail-btn');
    thumbnailInput.change(function(){
        // 获取文件
        var file = this.files[0];
        myajax.get({
            'url': '/cms/qntoken/',
            'success': function(result){
                if(result['code'] === 200){
                    // 获取token值
                    var token = result['data']['token'];
                    // 设置文件名
                    var key = (new Date()).getTime() + '.' + file.name.split('.')[1];
                    var putExtra = {
                        fname: key,
                        params: {},
                        mimeType: ["image/png","image/jpeg","image/gif","video/mp4"]
                    };
                    var config = {
                        useCdnDomain:true,
                        retryCount: 6,
                        region: qiniu.region.z1,            // z1代表华北地区
                    };
                    var observable = qiniu.upload(file,key,token,putExtra,config);
                    var observer = {
                        // 以下函数中的this不代表PubNovel对象
                        'next': self.handleFileUploadProgress,
                        'error': self.handleFileUploadError,
                        'complete': self.handleFileUploadComplete
                    };
                    observable.subscribe(observer);
                }
            }
        });
    });
};

// 发布/编辑小说
PubNovel.prototype.listenSubmitEvent = function(){
    var submitBtn = $('#submit-btn');
    submitBtn.click(function(){
        event.preventDefault();       // 阻止默认事件
        var btn = $(this);
        var novelId = btn.attr('data-novel-id');

        var name = $("input[name='name']").val();
        var cover_url = $("input[name='cover_url']").val();
        var category = $("select[name='category']").val();
        var tag = $('input[name="tag"]:checked').val();
        var price = $("input[name='price']").val();
        var profile = window.ue.getContent();

        var url = "";
        if(novelId){
            url = '/account/edit_novel/';
        } else{
            url = '/account/pub_novel/';
        }
        myajax.post({
            'url': url,
            'data': {
                'name': name,
                'cover_url': cover_url,
                'category': category,
                'tag': tag,
                'profile': profile,
                'price': price,
                'novel_id': novelId
            },
            'success': function(result){
                if(result['code'] === 200){
                    var message = "";
                    if(novelId){
                        message = '恭喜，小说编辑成功！';
                    } else{
                        message = '恭喜，小说发布成功！'
                    }
                    xfzalert.alertSuccess(message,function(){
                        window.location = '../../../..';
                    })
                } else{
                    console.log(result)
                }
            }
        })
    })
};

// 监听是否免费下拉框
PubNovel.prototype.listenIsFreeEvent = function(){
    var isFreeInput = $("select[name='is_free']");
    var priceInput = $("input[name='price']");
    if(isFreeInput.val() === '1') {
        priceInput.show();
    }
    isFreeInput.change(function(){
        if($(this).val() === '0') {
            priceInput.val(0);
            priceInput.hide();
        } else if($(this).val() === '1') {
            priceInput.val(0);
            priceInput.show();
        }
    });
    priceInput.change(function(){
        if($(this).val() === '0'){
            isFreeInput.children().eq(0).prop("selected", 'selected');
            $(this).hide();
        }
    });
};

// 初始化UEditor
PubNovel.prototype.initUEditor = function(){
    window.ue = UE.getEditor("editor",{
        'initialFrameHeight': 400,
        'serverUrl': '/ueditor/upload/',
    })
};

// 初始化分类和标签
PubNovel.prototype.initCategory = function(){
    var select = $('#category-form');
    var radioGroup = $('.radio-group');
    myajax.get({
        'url': '/account/get_cate_list/',
        'success': function(result){
            if(result['code'] === 200){
                var cateList = result['data']['cate_list'];
                // 初始化分类
                for(var i=0;i<cateList.length;i++){
                    select.append('<option value="'+cateList[i].id+'">'+cateList[i].name+'</option>');
                }
                // 初始化标签
                for(var j=0;j<cateList[0]['tags'].length;j++) {
                    var label = $('<label class="radio-inline"></label>');
                    var redio = $('<input type="radio" name="tag" value="' + cateList[0]['tags'][j].id + '">');
                    label.append(redio);
                    label.append(' ' + cateList[0]['tags'][j].name);
                    radioGroup.append(label);
                }
                // 分类改变时，相应的标签随之改变
                select.change(function(){
                    radioGroup.empty();
                    var category_id = $(this).val();
                    var tags = cateList[category_id-1]['tags'];
                    for(var j=0;j<tags.length;j++){
                        var label = $('<label class="radio-inline"></label>');
                        var redio = $('<input type="radio" name="tag" value="'+tags[j].id+'">');
                        label.append(redio);
                        label.append(' '+tags[j].name);
                        radioGroup.append(label);
                    }
                });
            }
        }
    });
};

PubNovel.prototype.run = function(){
    // 缩略图上传到本地
    this.listenUploadFileEvent();
    // 缩略图上传到七牛云
    // this.listenQiniuUploadFileEvent();
    this.initUEditor();
    this.listenSubmitEvent();
    this.initCategory();
    this.listenIsFreeEvent();
};

$(function(){
    var pubNovel = new PubNovel();
    pubNovel.run();
    PubNovel.progressGroup = $('#progress-group');
    PubNovel.progressBar = $('.progress-bar');
});