
var EditableTable = function () {

    return {

        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                	
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }

                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
            	
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '<input type="text" readonly="readonly" autocomplete="off" class="form-control small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" class="form-control small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<input type="text" class="form-control small" value="' + aData[2] + '">';
                jqTds[3].innerHTML = '<input type="text" readonly="readonly" autocomplete="off" class="form-control small" value="关联角色">';
                jqTds[4].innerHTML = '<a class="edit" href="">Save</a>';
                jqTds[5].innerHTML = '<a class="cancel" href="">Cancel</a>';
            }
            function save_Row(oTable, nRow) {
            	
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '<input type="text" readonly="readonly" autocomplete="off" class="form-control small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text"  class="form-control small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<input type="text" class="form-control small" value="' + aData[2] + '">';
                jqTds[3].innerHTML = '<input type="text" readonly="readonly" autocomplete="off" class="form-control small" value="关联角色">';
                jqTds[4].innerHTML = '<a class="save" href="">Save</a>';
                jqTds[5].innerHTML = '<a class="cancel" href="">Cancel</a>';
              
            }

            function saveRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
                oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 5, false);
                $.post("/user/addUser",
                		  {
                		    userName:jqInputs[1].value,
                		    password:jqInputs[2].value
                		  },
                		  function(result){
                			  if(result=="false"){
                				  alert("用户名已存在,添加失败!!");
                			  }else{
                			   oTable.fnUpdate(result, nRow, 0, false);
                		     }
                 });
                oTable.fnDraw();
                window.location.href ="/user/getUserList";
            }
            
            function updateRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
                oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 5, false);
                $.post("/user/updateUser",
                		  {
                			id:jqInputs[0].value,
                		    userName:jqInputs[1].value,
                		    password:jqInputs[2].value
                		  },
                		  function(result){
                			  if(result=="noUser"){
                				  alert("用户名已存在,修改失败!!");
                			  }else if(result=="true"){
                			    //  oTable.fnUpdate(result, nRow, 0, false);
                				  window.location.href ="/user/getUserList";
                		      }else{
                		    	  alert("修改失败!!");
                		      }
                 });
               
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
                oTable.fnDraw();
            }

            var oTable = $('#editable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records per page",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
                e.preventDefault();
                $("#editable-sample_new").attr({"disabled":"disabled"});
                var aiNew = oTable.fnAddData(['', '', '', '',
                        '<a class="edit" href="">Edit</a>', '<a class="cancel" data-mode="new" href="">Cancel</a>'
                ]);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                save_Row(oTable, nRow);
                nEditing = nRow;
            });

            $('#editable-sample a.delete').live('click', function (e) {
                e.preventDefault();
                if (confirm("你确定要删除这行数据么 ?") == false) {
                    return;
                }
                var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
                oTable.fnDeleteRow(nRow);
                $.post("/template/delTemplate",
              		  {
              		    id:aData[0]
              		  },
              		  function(result){
              		    alert("Data: " + result + "\nStatus: " + result);
                });
            });

            $('#editable-sample a.cancel').live('click', function (e) {
                $("#editable-sample_new").attr({"disabled":" "});
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                    
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });

            $('#editable-sample a.edit').live('click', function (e) {
                e.preventDefault();
                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "Save") {
                    /* Editing this row and want to save it */
                    var aData = oTable.fnGetData(nRow);
                    alert(aData[0]);
                    updateRow(oTable, nEditing);
                    nEditing = null;
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
            $('#editable-sample a.user_rel').live('click', function (e) {
                var nRow = $(this).parents('tr')[0];
          	    var aData = oTable.fnGetData(nRow);
          	    var roleid= document.getElementById("roleid");
          	    roleid.setAttribute("roleid", aData[0]);
          	    $('input:checkbox').each(function () {
                  $(this).attr('checked',false);
          	  	});
          	    $.post("/template/getTemplateUser", 
                 		  {
                 		    id:aData[0]
                 		  },
                 		  function(result){
                 			  
                 			var ids = eval("("+result+")");
                 		    for(var i=0;i<ids.length;i++){
                 		        var c=document.getElementById("checkuser_"+ids[i]);
                    		    c.checked=true;
                 		    }
                });
          	    $('#userModel').modal();
            
            });
            $('#userModelClick').on("click",function(e){
                var roleid= document.getElementById("roleid");
                var checkids=document.getElementsByName("checkbox");
                var ids = new Array();
                var count = 0 ;
                for(var i=0;i<checkids.length;i++){
                    if(checkids[i].checked){
                 	   ids[count]=checkids[i].id.substring(10);
                 	   count++;
                    }
                } 
                var url = "/template/editTemplateRel";
        		   var fun = function (data) {
        			 $('#userModel').modal('hide');
        		   };
        		   var errfun = function (){
        		   };
        		   var datatype = 'json';
        		   var data = {};
        		   data.checkids =ids.toString();
        		   data.id = roleid.getAttribute("roleid");
        		   commonPost(url,fun,errfun,datatype,data);
            });
            
        	function commonPost(url,fun,errfun,datatype,data,async){
      		  if (!datatype) {
      			datatype="text";
      		  };
      		  if (!async) {
      		  	async = false;
      		  };
      		  $.ajax({
      		      url:url,
      		      dataType:datatype,
      		      async:async,
      		      type: "POST",
      		      success:fun,
      		      data:data
      		  }).fail(
      		  	  errfun
      		  );
      		}
            $('#editable-sample a.save').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    saveRow(oTable, nEditing);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "Save") {
                    /* Editing this row and want to save it */
                    var aData = oTable.fnGetData(nRow);
                    alert(aData[0]);
                    saveRow(oTable, nEditing);
                    nEditing = null;
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

}();