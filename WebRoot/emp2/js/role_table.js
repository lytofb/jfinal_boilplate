function isNull( str ){ 
if ( str == "" ) return true; 
var regu = "^[ ]+$"; 
var re = new RegExp(regu); 
return re.test(str); 
} 
function checkusername(str)
{
	var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
	if (!patrn.exec(str)) return false;
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
				jqTds[2].innerHTML = '<input type="text" autocomplete="off" class="form-control small" value="'+ jqTds[2].innerText + '">';
				jqTds[3].innerHTML = '<a class="role_rel" href="javascript:;">关联角色</a>';
				jqTds[4].innerHTML = '<a class="permission_rel" href="javascript:;">关联权限</a>';
				jqTds[5].innerHTML = '<a class="scheme_rel" href="javascript:;">关联scheme</a>';
				jqTds[6].innerHTML = '<a class="edit" href="">保存</a>';
				jqTds[7].innerHTML = '<a class="cancel" href="">取消</a>';
			}
			function save_Row(oTable, nRow) {

				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				jqTds[0].innerHTML = '<input type="text" readonly="readonly" autocomplete="off" class="form-control small" value="'
						+ aData[0] + '">';
				jqTds[1].innerHTML = '<input type="text" autocomplete="off" class="form-control small" value="'
						+ aData[1] + '">';
				jqTds[2].innerHTML = '<input type="text" autocomplete="off" class="form-control small" value="'
						+ aData[2] + '">';
				jqTds[3].innerHTML = '<input type="text"  readonly="readonly" autocomplete="off" class="form-control small" value="关联用户">';
				jqTds[4].innerHTML = '<input type="text"  readonly="readonly" autocomplete="off" class="form-control small" value="关联权限">';
				jqTds[5].innerHTML = '<input type="text"  readonly="readonly" autocomplete="off" class="form-control small" value="关联scheme">';
				jqTds[6].innerHTML = '<a class="save" href="">保存</a>';
			}

			function saveRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				var jqTds = $('>td', nRow);
				$.post("/role/addRole", {
					roleName : jqInputs[1].value,
					roleNick : jqInputs[2].value
				}, function(result) {
					if (result == "roleNameExist") {
						alert("角色已存在,添加失败!!");
					} else if (result == "roleNickExist") {
						alert("角色昵称已存在,添加失败!!");
					}else{
					  $("#editable-sample_new").removeAttr("disabled");
					  oTable.fnUpdate(result, nRow, 0, false);
					  oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
					  oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
					  oTable.fnUpdate('<a class="user_rel" href="javascript:;">关联用户</a>', nRow,
							  3, false); 
					  oTable.fnUpdate('<a class="permission_rel" href="javascript:;">关联权限</a>', nRow,
					  4, false); 
					  oTable.fnUpdate('<a class="scheme_rel" href="javascript:;">关联scheme</a>',
					  nRow, 5, false);
					  oTable.fnUpdate('<a class="edit" href="javascript:;">修改</a>', nRow,
							  6, false); 
					  oTable.fnUpdate('<a class="delete" href="javascript:;">删除</a>',
							  nRow, 7, false);
					}
				});
			}

			function updateRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqInputs = $('input', nRow);
				var jqTds = $('>td', nRow);
				$.post("/role/updateRole", {
					id : aData[0],
					roleNick : jqInputs[0].value
				}, function(result) {
					if (result == "roleNameExist") {
						alert("用户名已存在,添加失败!!");
					}else if (result == "roleNickExist") {
						alert("用户昵称已存在,添加失败!!");
					}else{
						  $("#editable-sample_new").removeAttr("disabled");
						  oTable.fnUpdate(result, nRow, 0, false);
						  jqTds[2].innerHTML = jqInputs[0].value;
						  jqTds[3].innerHTML = '<a class="user_rel" href="javascript:;">关联用户</a>';
						  jqTds[4].innerHTML = '<a class="permission_rel" href="javascript:;">关联权限</a>';
						  jqTds[5].innerHTML = '<a class="scheme_rel" href="javascript:;">关联scheme</a>';
						  jqTds[6].innerHTML = '<a class="edit" href="">修改</a>';
					      jqTds[7].innerHTML = '<a class="delete" href="">删除</a>';
					}
				});
			}

			function cancelEditRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
				oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
				oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4,
						false);
				oTable.fnDraw();
			}

			var oTable = $('#editable-sample')
					.dataTable(
							{
								"aLengthMenu" : [ [ 5, 15, 20, -1 ],
										[ 5, 15, 20, "All" ] // change per
																// page values
																// here
								],
								// set the initial value
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
					.addClass("form-control medium"); // modify table search
														// input
			jQuery('#editable-sample_wrapper .dataTables_length select')
					.addClass("form-control xsmall"); // modify table per page
														// dropdown

			var nEditing = null;

			$('#editable-sample_new')
					.click(
							function(e) {
								e.preventDefault();
								$("#editable-sample_new").attr({
									"disabled" : "disabled"
								});
								var aiNew = oTable
										.fnAddData([ '', '', '', '', '', '',
												'',
												'<a class="cancel" data-mode="new" href="">取消</a>' ]);
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
				$.post("/role/delRole", {
					id : aData[0]
				}, function(result) {
					alert("Data: " + result + "\nStatus: " + result);
				});
			});

			$('#editable-sample a.cancel').live('click', function(e) {
				$("#editable-sample_new").attr({
					"disabled" : " "
				});
				if ($(this).attr("data-mode") == "new") {
					var nRow = $(this).parents('tr')[0];
					oTable.fnDeleteRow(nRow);
				} else {
					restoreRow(oTable, nEditing);
					nEditing = null;
				}
			});

			$('#editable-sample a.edit').live('click', function(e) {
				e.preventDefault();
				/* Get the row as a parent of the link that was clicked on */
				var nRow = $(this).parents('tr')[0];
				if (nEditing == nRow && this.innerHTML == "保存") {
					/* Editing this row and want to save it */
					var jqInputs = $('input', nRow);
					
					if(jqInputs[0].value==""){
						alert("角色昵称不能为空!");
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
			$('#editable-sample a.user_rel').live('click', function(e) {
				var nRow = $(this).parents('tr')[0];
				var aData = oTable.fnGetData(nRow);
				var roleid = document.getElementById("roleid");
				roleid.setAttribute("roleid", aData[0]);
				$('input:checkbox').each(function() {
					$(this).attr('checked', false);
				});
				$.post("/role/getUserRel", {
					id : aData[0]
				}, function(result) {
					var ids = eval("(" + result + ")");
					for (var i = 0; i < ids.length; i++) {
						var c = document.getElementById("checkuser_" + ids[i]);
						c.checked = true;
					}
				});
				$('#userModel').modal();

			});

			$('#editable-sample a.scheme_rel').live('click', function(e) {
				var nRow = $(this).parents('tr')[0];
				var aData = oTable.fnGetData(nRow);
				var roleid = document.getElementById("roleid");
				roleid.setAttribute("roleid", aData[0]);
				$('input:checkbox').each(function() {
					$(this).attr('checked', false);
				});
				$.post("/role/getSchemeRel", {
					id : aData[0]
				}, function(result) {
					var ids = eval("(" + result + ")");
					for (var i = 0; i < ids.length; i++) {
						var c = document.getElementById("checkdata_" + ids[i]);
						c.checked = true;
					}
				});
				$('#schemeModel').modal();

			});

			$('#editable-sample a.permission_rel').live('click',function(e) {
				var nRow = $(this).parents('tr')[0];
				var aData = oTable.fnGetData(nRow);
				var roleid = document.getElementById("roleid");
				roleid.setAttribute("roleid", aData[0]);
				$('input:checkbox').each(function() {
					$(this).attr('checked', false);
				});
				$.post("/role/getPermissionRel", {
					id : aData[0]
				}, function(result) {
					var ids = eval("(" + result + ")");
					for (var i = 0; i < ids.length; i++) {
						var c = document
								.getElementById("checkpermission_"
										+ ids[i]);
						c.checked = true;
					}
				});
				$('#permissionModel').modal();

			});
			$('#userModelClick').on("click",function(e) {
				var roleid = document.getElementById("roleid");
				var checkids = document.getElementsByName("checkbox");
				var ids = new Array();
				var count = 0;
				for (var i = 0; i < checkids.length; i++) {
					if (checkids[i].checked) {
						ids[count] = checkids[i].id.substring(
								checkids[i].id.indexOf("_")).replace(
								"_", "");
						count++;
					}
				}
				var url = "/role/editUserRel";
				var fun = function(data) {
					$('#userModel').modal('hide');
				};
				var errfun = function() {
				};
				var datatype = 'json';
				var data = {};
				data.checkids = ids.toString();
				data.id = roleid.getAttribute("roleid");
				commonPost(url, fun, errfun, datatype, data);
			});
			$('#permissionModelClick').on(
					"click",
					function(e) {
						var roleid = document.getElementById("roleid");
						var checkids = document.getElementsByName("checkbox");
						var ids = new Array();
						var count = 0;
						for (var i = 0; i < checkids.length; i++) {
							if (checkids[i].checked) {
								ids[count] = checkids[i].id.substring(
										checkids[i].id.indexOf("_")).replace(
										"_", "");
								count++;
							}
						}
						var url = "/role/editPermissionRel";
						var fun = function(data) {
							$('#permissionModel').modal('hide');
						};
						var errfun = function() {
						};
						var datatype = 'json';
						var data = {};
						data.checkids = ids.toString();
						data.id = roleid.getAttribute("roleid");
						commonPost(url, fun, errfun, datatype, data);
					});

			$('#dataSourceModelClick').on(
					"click",
					function(e) {
						var roleid = document.getElementById("roleid");
						var checkids = document.getElementsByName("checkbox");
						var ids = new Array();
						var count = 0;
						for (var i = 0; i < checkids.length; i++) {
							if (checkids[i].checked) {
								ids[count] = checkids[i].id.substring(
										checkids[i].id.indexOf("_")).replace(
										"_", "");
								count++;
							}
						}
						var url = "/role/editDataSourceRel";
						var fun = function(data) {
							$('#schemeModel').modal('hide');
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

				/* Get the row as a parent of the link that was clicked on */
				var nRow = $(this).parents('tr')[0];

				if (nEditing == nRow && this.innerHTML == "保存") {
					/* Editing this row and want to save it */
					var jqInputs = $('input', nRow);
					if(checkusername(jqInputs[1].value)==false){
						alert("只能输入5-20个以字母开头、可带数字、“_”、“.”的字串");
					}else{
						saveRow(oTable, nEditing);
						nEditing = nRow;
					}
				} else {
					editRow(oTable, nRow);
					nEditing = nRow;
				}
			});

		}

	};

}();