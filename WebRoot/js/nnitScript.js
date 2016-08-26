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

    function initSchemaSelector () {
      schema_data.current = schema_data.schema_list[schema_data_seq].schemaname;
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
      }
    });

