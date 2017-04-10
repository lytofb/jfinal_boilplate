    function commonPost(url,fun,errfun,datatype,data,async,showLayer){
      var layerindex = undefined;
      if(showLayer){
        layerindex = layer.load();        
      }
      if (!datatype) {
        datatype="text"
      };
      if (!async) {
        async = false;
      };
      if(!errfun) {
        errfun = function(){};
      }
      var defered = $.ajax({
          url:url,
          dataType:datatype,
          async:async,
          type: "POST",
          success:fun,
          data:data
      });
      defered.fail(
          errfun
      );
      if(showLayer){
        return defered.then(function(){layer.close(layerindex)}, function(){layer.close(layerindex)});
      } else {
        return defered;
      }
   }

    function initAlert(title,text) {
        $.gritter.add({title:title,text:text})
    }

    function defaultSuccessCallback(){
        initAlert("操作成功","")
    }

    function defaultFailCallback(jqr){
        initAlert("操作失败",jqr.responseText)
    }

    function setMessageReload(){
        setMessage("操作成功")
        location.reload();
    }

    function setMessageSuccess(){
        setMessage("操作成功")
    }

    function initSchemaSelector () {
      schema_data.current = schema_data.schema_list[schema_data_seq].schemaname;
      var schemaSelectorContent = $("#schemaSelectorTemplate").render(schema_data);
      $("#schemaSelector").html(
        schemaSelectorContent
      )
    }

    var delay = (function(){
        var timer = 0;
        return function(callback, ms){
            clearTimeout (timer);
            timer = setTimeout(callback, ms);
        };
    })();

    function initfilterModal (data,uuid) {
        data.uuid = uuid;
        $( "#filterModal" ).html(
            $( "#filterModalTemplate" ).render( data )
        );
    }

    function setMessage(message){
        localStorage.setItem("message",message)
    }

    function getMessage(){
        return localStorage.getItem("message")
    }

    $(function(){
        var message = getMessage();
        if (message) {
            initAlert("",message)
        };
        localStorage.clear()
    })

    //$.views.helpers({
    //
    //  format: function( val, format ) {
    //    var ret;
    //    switch( format ) {
    //      case "upper":
    //        return val.toUpperCase();
    //      case "lower":
    //        return val.toLowerCase();
    //    }
    //  },
    //  exsits:function (val,array) {
    //    var flag = array.indexOf(val);
    //    if(flag<0){
    //      return false;
    //    } else {
    //      return true;
    //    }
    //  }
    //});

