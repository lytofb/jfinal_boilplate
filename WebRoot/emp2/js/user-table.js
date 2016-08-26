function isTrueName(s) {
	var patrn = /^[a-zA-Z]{1,30}$/;
	if (!patrn.exec(s))
		return false;
	return true;
}
var EditableTable = function() {

	return {

		// main function to initiate the module
		init : function() {
			function restoreRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);

				for (var i = 0, iLen = jqTds.length; i < iLen; i++) {

					oTable.fnUpdate(aData[i], nRow, i, false);
				}

				oTable.fnDraw();
			}

			function editRow(oTable, nRow) {
				$("#editable-sample_new").attr({
					"disabled" : "disabled"
				});
				var jqTds = $('>td', nRow);
				jqTds[0].innerHTML = jqTds[0].innerText;
				jqTds[1].innerHTML = jqTds[1].innerText;
				jqTds[2].innerHTML = '<input type="text" class="form-control small" value="'
						+jqTds[2].innerText+ '">';
				jqTds[3].innerHTML = '<a class="role_rel" href="javascript:;">关联角色</a>';
				jqTds[4].innerHTML = '<a class="edit" href="">保存</a>';
				jqTds[5].innerHTML = '<a class="cancel_edit" href="">取消</a>';
			}
			function save_Row(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				jqTds[0].innerHTML = '<input type="text" readonly="readonly" autocomplete="off" class="form-control small" value="'
						+ aData[0] + '">';
				jqTds[1].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[1] + '">';
				jqTds[2].innerHTML = '<input type="text" class="form-control small" value="'
						+ aData[2] + '">';
				jqTds[3].innerHTML = '<input type="text" readonly="readonly" autocomplete="off" class="form-control small" value="关联角色">';
				jqTds[4].innerHTML = '<a class="save" href="javascript:;">保存</a>';
				jqTds[5].innerHTML = '<a class="cancel_save" href="">取消</a>';
				jqTds[6].innerHTML = '<input type="text"  readonly="readonly" autocomplete="off" class="form-control small" value="重置">';
			}

			function saveRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				$.post("/user/addUser", {
					userName : jqInputs[1].value,
					userNick : jqInputs[2].value
				}, function(result) {
					if (result == "userNameExist") {
						alert("用户名已存在,添加失败!!");
					} else if (result == "userNickExist") {
						alert("用户昵称已存在,添加失败!!");
					} else {
						  $("#editable-sample_new").removeAttr("disabled");
						  oTable.fnUpdate(result, nRow, 0, false);
						  oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
						  oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
						  oTable.fnUpdate('<a class="role_rel" href="javascript:;">关联角色</a>', nRow,
								  3, false); 
						  oTable.fnUpdate('<a class="edit" href="">修改</a>', nRow,
						  4, false); 
						  oTable.fnUpdate('<a class="delete" href="">删除</a>',
						  nRow, 5, false);
						  oTable.fnUpdate('<a class="reset" href="">重置</a>',
								  nRow, 6, false);
					}
				});
			}

			function updateRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqInputs = $('input', nRow);
				var jqTds = $('>td', nRow);
				$.post("/user/updateUser", {
					id : aData[0],
					userName : aData[1],
					userNick : jqInputs[0].value
				}, function(result) {
					if (result == "userNickExist") {
						alert("用户昵称已存在,更改失败!!");
					}else{
						  $("#editable-sample_new").removeAttr("disabled");
						  oTable.fnUpdate(result, nRow, 0, false);
						  jqTds[2].innerHTML = jqInputs[0].value;
						  jqTds[3].innerHTML = '<a class="role_rel" href="javascript:;">关联角色</a>';
						  jqTds[4].innerHTML = '<a class="edit" href="">修改</a>';
						  jqTds[5].innerHTML = '<a class="delete" href="">删除</a>';
					}
					
				});
			}

			function cancelEditRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				var jqTds = $('>td', nRow);
			    jqTds[2].innerHTML = jqInputs[0].value;
				jqTds[3].innerHTML = '<a class="role_rel" href="javascript:;">关联角色</a>';
				jqTds[4].innerHTML = '<a class="edit" href="">修改</a>';
				jqTds[5].innerHTML = '<a class="delete" href="">删除</a>';
			}

			var oTable = $('#editable-sample')
					.dataTable(
							{
								"aLengthMenu" : [ [ 5, 15, 20, -1 ],
										[ 5, 15, 20, "All" ] 
								],
							
								"iDisplayLength" : 5,
								"sDom" : "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
								"sPaginationType" : "bootstrap",
								"oLanguage" : {
									"sLengthMenu" : "_MENU_ records per page",
									"oPaginate" : {
										"sPrevious" : "Prev",
										"sNext" : "Next"
									}
								},
								"aoColumnDefs" : [ {
									'bSortable' : false,
									'aTargets' : [ 0 ]
								} ]
							});

			jQuery('#editable-sample_wrapper .dataTables_filter input')
					.addClass("form-control medium"); 
			jQuery('#editable-sample_wrapper .dataTables_length select')
					.addClass("form-control xsmall"); 

			var nEditing = null;

			$('#editable-sample_new').click(function(e) {
				e.preventDefault();
				$("#editable-sample_new").attr({
					"disabled" : "disabled"
				});
				var aiNew = oTable.fnAddData([ '', '', '', '', '', '', '' ]);
				var nRow = oTable.fnGetNodes(aiNew[0]);
				save_Row(oTable, nRow);
				nEditing = nRow;
			});

			$('#editable-sample a.delete').live('click', function(e) {
				e.preventDefault();
				if (confirm("你确定要删除这条数据么 ?") == false) {
					return;
				}
				var nRow = $(this).parents('tr')[0];
				var aData = oTable.fnGetData(nRow);
				oTable.fnDeleteRow(nRow);
				$.post("/user/delUser", {
					id : aData[0]
				}, function(result) {
					alert(result);
				});
			});

			$('#editable-sample a.cancel_edit').live('click', function(e) {
				var nRow = $(this).parents('tr')[0];
				cancelEditRow(oTable, nRow);
			});
			
			$('#editable-sample a.cancel_save').live('click', function(e) {
				var nRow = $(this).parents('tr')[0];
				cancelSaveRow(oTable, nRow);
			});
			
			$('#editable-sample a.reset').live('click', function(e) {
				e.preventDefault();
				if (confirm("你确定要把该用户密码重置么 ?") == false) {
					return;
				}
				var nRow = $(this).parents('tr')[0];
				var aData = oTable.fnGetData(nRow);
				$.post("/user/resetUser", {
					id : aData[0]
				}, function(result) {
					if (result == 1) {
						alert("重置成功");
					} else {
						alert("重置失败");
					}
				});
			});

			$('#editable-sample a.edit').live('click', function(e) {
				e.preventDefault();
				/* Get the row as a parent of the link that was clicked on */
				var nRow = $(this).parents('tr')[0];
				if (nEditing == nRow && this.innerHTML == "保存") {
					/* Editing this row and want to save it */
					var jqInputs = $('input', nRow);
					if(jqInputs[0].value==""){
						alert("用户昵称不能为空!");
						return;
					}
					updateRow(oTable, nEditing);
					nEditing = nRow;
				} else {
					/* No edit in progress - let's start one */
					editRow(oTable, nRow);
					nEditing = nRow;
				}
			});
			
			$('#editable-sample a.role_rel').live('click', function(e) {
				var nRow = $(this).parents('tr')[0];
				var aData = oTable.fnGetData(nRow);
				var roleid = document.getElementById("roleid");
				roleid.setAttribute("roleid", aData[0]);
				$('input:checkbox').each(function() {
					$(this).attr('checked', false);
				});
				$.post("/user/getUserRel", {
					id : aData[0]
				}, function(result) {
					var ids = eval("(" + result + ")");
					for (var i = 0; i < ids.length; i++) {
						var c = document.getElementById("check_" + ids[i]);
						c.checked = true;
					}
				});
				$('#roleModel').modal();
				
			});
			
			$('#roleModelClick').on("click", function(e) {
				var roleid = document.getElementById("roleid");
				var checkids = document.getElementsByName("checkbox");
				var ids = new Array();
				var count = 0;
				for (var i = 0; i < checkids.length; i++) {
					if (checkids[i].checked) {
						ids[count] = checkids[i].id.substring(6);
						count++;
					}
				}
				var url = "/user/editRoleRel";
				var fun = function(data) {
					$('#roleModel').modal('hide');
				};
				var errfun = function() {
				};
				var datatype = 'json';
				var data = {};
				data.checkids = ids.toString();
				data.id = roleid.getAttribute("roleid");
				commonPost(url, fun, errfun, datatype, data);
			});

			function commonPost(url, fun, errfun, datatype, data, async) {
				if (!datatype) {
					datatype = "text";
				}
				;
				if (!async) {
					async = false;
				}
				;
				$.ajax({
					url : url,
					dataType : datatype,
					async : async,
					type : "POST",
					success : fun,
					data : data
				}).fail(errfun);
			}
			$('#editable-sample a.save').live('click', function(e) {
				e.preventDefault();
				var nRow = $(this).parents('tr')[0];
				if (this.innerHTML == "保存") {
					/* Editing this row and want to save it */
					var jqInputs = $('input', nRow);
					if(jqInputs[1].value==""){
						alert("用户名不能为空!");
						return;
					}
					if(jqInputs[2].value==""){
						alert("用户昵称不能为空!");
						return;
					}
					saveRow(oTable, nEditing);
				}
			});
		}

	};

}();