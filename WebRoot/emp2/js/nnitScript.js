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

    function setMessage(message){
      localStorage.setItem("message",message)
    }

    function getMessage(){
      return localStorage.getItem("message")
    }

    function initSchemaSelector () {
      schema_data.current = window.schema_dict[session_schema_id].schemaname;
      var schemaSelectorContent = $("#schemaSelectorTemplate").render(schema_data);
      $("#schemaSelector").html(
        schemaSelectorContent
      )
    }


    function initfilterModal (data,uuid) {
        data.uuid = uuid;
        $( "#filterModal" ).html(
            $( "#filterModalTemplate" ).render( data )
        );
    }

    $(function(){
        var message = getMessage();
        if (message) {
          initAlert("",message)
        };
        localStorage.clear()
      })

    $.views.helpers({
      format: function( val, format ) {
        var ret;
        switch( format ) {
          case "upper":
            return val.toUpperCase();
          case "lower":
            return val.toLowerCase();
        }
      },
      exsits:function (val,array) {
        var flag = array.indexOf(val);
        if(flag<0){
          return false;
        } else {
          return true;
        }
      },
      exsitsInDict:function(val,key,dictArray){
          var returnFlag = false;
          if(!dictArray){
            return returnFlag;
          }
          dictArray.map(function(singleDict){
            if (val==singleDict[key]) {
              returnFlag = true
            };
          })
          return returnFlag;
      }
    });

