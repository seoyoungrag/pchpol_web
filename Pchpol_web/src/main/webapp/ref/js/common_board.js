function ListObj(){
	this.grid;
	this.pager;
	this.url;
	this.colNames;
	this.colModel;
	this.idColName;
	this.jqgrid = function(onSelectRow, loadError, loadComplete){
		var idColName = this.idColName;
	   	jQuery(this.grid).jqGrid({
	   		recordtext: "{0} - {1} / {2}",
	   		url : this.url,
	   		datatype: "json",
	   		jsonReader : {
	   		          		root: "data.rows", //게시글 리스트
	   		          		page: "data.page", //현재 페이지
	   						total: "data.total", //전체 페이지
	   						records: "data.records" //전체 레코드
	   					},
	   	   	colNames: this.colNames,
	   	   	colModel: this.colModel,
			styleUI : 'Bootstrap',
	        rownumbers: true,
			multiselect: false,
	        height : 'auto',
	   	   	rowNum:10,
	   	   	rowList:[10,20,30],
	   	   	pager: this.pager,
	   	   	sortname: idColName,
	   	    viewrecords: true,
	   	    sortorder: "desc",
	   	    autowidth:true,
	   	    shrinkToFit: true,
		   	onSelectRow:function(rowid){
		   		if(typeof onSelectRow !== 'undefined'){
			   		onSelectRow(rowid);
		   		}else{
		   			var id = jQuery(listObj.grid).jqGrid ('getCell', rowid, idColName);
		   			popup('view',id);
		   		}
		   	},
		   	loadError : function(xhr,st,err) { 
		   		if(typeof loadError !== 'undefined'){
		   			loadError(xhr,st,err);
		   		}else{
		   		    alert("리스트갱신이 실패했습니다.\nType: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
		   		}
		   	},
	   	    loadComplete : function(res)
	   		{	
		   		if(typeof loadComplete !== 'undefined'){
		   			loadComplete(res);
		   		}else{
		   			if(!res.success){
		   		    	alert("리스트갱신이 실패했습니다.\nmessage: "+res.message+"; execDt: "+ res.execDt);
		   			}
		   		}
	   	    }
	   	});
	}
}

function ViewObj(){
	this.url;
	this.id;
	this.ajax = function(successFunc){
		$.ajax({
			type : 'GET',
			url : this.url+this.id,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			cache : false,
			success : function(res){
				if(res.success){
					successFunc(res);
				}else{
					alert('데이터 조회를 실패하였습니다.');
					self.close();
				}
			},
			error : function(res){
					alert('데이터 조회를 실패하였습니다.');
					self.close();
			}
		});
	}
};
function SelectpickerObj(){
	this.divName;
	this.category;
	this.depth;
	this.selectVal;
	this.parentVal;
	this.troopstype;
	this.restrictValue;
	this.setByCode = function(callbackFunc){
		var divName = this.divName;
		var category = this.category;
		var depth = this.depth;
		var selectVal = this.selectVal;
		var parentVal = this.parentVal;
		var troopstype = this.troopstype;
		var restrictValue = this.restrictValue;
		$.ajax({
		 type: "GET",
		 url:contextPath+'/code/list/'+category, 
			 contentType: "application/json; charset=utf-8",
			 dataType: "json",
			 async: false,
			 success: function(res) {
				 if(res.success){
					if(typeof depth !== 'undefined'){
						var codes = [];
						if(depth=='1'){
							$.each(res.data, function (index, text) {
								if($.inArray(text.code1depth, codes) == -1 && text.code1depth!=null){
									if(troopstype=='stand'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}else if(troopstype=='female'){
										if(text.code2depth!='여경'){
											return true;
										}
									}else if(troopstype=='indiv'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}
									if(typeof restrictValue !== 'undefined'){
										$.each(restrictValue, function(index, restrictText){
											if(restrictText.code1depth==text.code1depth){
												codes.push(text.code1depth);
												return false;
											}
										});
									}else{
										codes.push(text.code1depth);
									}
								}
							});
						}else if(depth=='2'){
							$.each(res.data, function (index, text) {
								if($.inArray(text.code2depth, codes) == -1 && text.code2depth!=null){
									if(troopstype=='stand'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}else if(troopstype=='female'){
										if(text.code2depth!='여경'){
											return true;
										}
									}else if(troopstype=='indiv'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}
									if(typeof restrictValue !== 'undefined'){
										$.each(restrictValue, function(index, restrictText){
											if(restrictText.code1depth==text.code1depth
												&&restrictText.code2depth==text.code2depth
												){
												codes.push(text.code2depth);
												return false;
											}
										});
									}else{
										if(text.code1depth==parentVal[0]){
											codes.push(text.code2depth);
										}
									}
								}
							});
						}else if(depth=='3'){
							$.each(res.data, function (index, text) {
								if($.inArray(text.code3depth, codes) == -1 && text.code3depth!=null){
									if(troopstype=='stand'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}else if(troopstype=='female'){
										if(text.code2depth!='여경'){
											return true;
										}
									}else if(troopstype=='indiv'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}
									if(typeof restrictValue !== 'undefined'){
										$.each(restrictValue, function(index, restrictText){
											if(restrictText.code1depth==text.code1depth
												&&restrictText.code2depth==text.code2depth
												&&restrictText.code3depth==text.code3depth
												){
												codes.push(text.code3depth);
												return false;
											}
										});
									}else{
										if(text.code1depth==parentVal[0]&&text.code2depth==parentVal[1]){
											codes.push(text.code3depth);
										}
									}
								}
							});
						}else if(depth=='4'){
							$.each(res.data, function (index, text) {
								if($.inArray(text.code4depth, codes) == -1 && text.code4depth!=null){
									if(troopstype=='stand'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}else if(troopstype=='female'){
										if(text.code2depth!='여경'){
											return true;
										}
									}else if(troopstype=='indiv'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}
									if(typeof restrictValue !== 'undefined'){
										$.each(restrictValue, function(index, restrictText){
											if(restrictText.code1depth==text.code1depth
												&&restrictText.code2depth==text.code2depth
												&&restrictText.code3depth==text.code3depth
												&&restrictText.code4depth==text.code4depth
												){
												codes.push(text.code4depth);
												return false;
											}
										});
									}else{
										if(text.code1depth==parentVal[0]&&text.code2depth==parentVal[1]&&text.code3depth==parentVal[2]){
											codes.push(text.code4depth);
										}
									}
								}
							});
						}else if(depth=='5'){
							$.each(res.data, function (index, text) {
								if($.inArray(text.code5depth, codes) == -1 && text.code5depth!=null){
									if(troopstype=='stand'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}else if(troopstype=='female'){
										if(text.code2depth!='여경'){
											return true;
										}
									}else if(troopstype=='indiv'){
										if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
											return true;
										}
									}
									if(text.code1depth==parentVal[0]&&text.code2depth==parentVal[1]&&text.code3depth==parentVal[2]&&text.code4depth==parentVal[3]){
										codes.push(text.code5depth);
									}
								}
							});
						}
						$.each(codes, function (index, text) {
						    $('select#'+divName).append($('<option>', {
						        value: text,
						        text : text
						    }));
						});
					}else{
						$.each(res.data, function (index, text) {
							if(troopstype=='stand'){
								if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
									return true;
								}
							}else if(troopstype=='female'){
								if(text.code2depth!='여경'){
									return true;
								}
							}else if(troopstype=='indiv'){
								if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
									return true;
								}
							}
						    $('select#'+divName).append($('<option>', {
						        value: res.data[index].codeNo,
						        text : res.data[index].code1depth
						    }));
						});
					}
					if(typeof selectVal === 'undefined'){
					}else{
						$('select#'+divName).val(selectVal);
					}
					$('.selectpicker').selectpicker('refresh')
				 }else{
						alert('데이터 조회를 실패하였습니다.');
				 }
			 },
				error : function(res){
						alert('데이터 조회를 실패하였습니다.');
				}
			});
	}
};
function setSelectPickerTroops(troopstype){
	$.ajax({
	 type: "GET",
	 url:contextPath+'/code/list/troops', 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 success: function(res) {
			 if(res.success){
				var code1depth = [];
				var code2depth = [];
				var code3depth = [];
				var code4depth = [];
				$.each(res.data, function (index, text) {
					if($.inArray(text.code1depth, code1depth) == -1 && text.code1depth!=null){
						if(troopstype=='stand'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}else if(troopstype=='female'){
							if(text.code2depth!='여경'){
								return true;
							}
						}else if(troopstype=='indiv'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}
						code1depth.push(text.code1depth);
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code2depth, code2depth) == -1 && text.code2depth!=null){
						if(troopstype=='stand'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}else if(troopstype=='female'){
							if(text.code2depth!='여경'){
								return true;
							}
						}else if(troopstype=='indiv'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}
						code2depth.push(text.code2depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code3depth, code3depth) == -1 && text.code3depth!=null){
						if(troopstype=='stand'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}else if(troopstype=='female'){
							if(text.code2depth!='여경'){
								return true;
							}
						}else if(troopstype=='indiv'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}
						code3depth.push(text.code3depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code4depth, code4depth) == -1 && text.code4depth!=null){
						if(troopstype=='stand'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}else if(troopstype=='female'){
							if(text.code2depth!='여경'){
								return true;
							}
						}else if(troopstype=='indiv'){
							if(text.code2depth!='경찰관 기동대'&&text.code2depth!='의경 중대'){
								return true;
							}
						}
						code4depth.push(text.code4depth)
					}
				});
				$.each(code1depth, function (index, text) {
				    $('select#code1_code1depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code2depth, function (index, text) {
				    $('select#code1_code2depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code3depth, function (index, text) {
				    $('select#code1_code3depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code4depth, function (index, text) {
				    $('select#code1_code4depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$('.selectpicker').selectpicker('refresh')
			 }else{
					alert('데이터 조회를 실패하였습니다.');
			 }
		 },
			error : function(res){
					alert('데이터 조회를 실패하였습니다.');
			}
		});
}
function sendFormByAjax(){
	var regForm = $("#regForm");
	var formData = regForm.serialize();
	$.ajax({
		type : regForm.attr("method"),
		url : regForm.attr("action"),
		cache : false,
		data : formData,
		success : function(res){
			if(res.success){
				alert('데이터를 입력하였습니다.');
				window.opener.jQuery("#list-grid").trigger("reloadGrid");
				self.close();
			}else{
				alert('데이터 입력을 실패하였습니다.');
				alert(res.message);
			}
		},
		error : function(res){
			alert('데이터 입력을 실패하였습니다.');
		}
	});
}
var dropdwonOption = {
        defaultDate: null,
        defaultDateFormat: 'yyyy-mm-dd',
        displayFormat: 'ymd',
        submitFormat: 'yyyy-mm-dd',
        minAge: 0,
        maxAge: 120,
        minYear: 2017,
        maxYear: 2018,
        submitFieldName: 'date',
        wrapperClass: 'date-dropdowns',
        dropdownClass: null,
        daySuffixes: true,
        monthSuffixes: true,
        monthFormat: 'long',
        required: true,
        dayLabel: '일',
        monthLabel: '월',
        yearLabel: '연도',
        monthLongValues: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthShortValues: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        initialDayMonthYearValues: ['년도', '월', '일'],
        daySuffixValues: ['', '', '', '']
}
function dateFormatterGmt (cellvalue, options, rowObject)
{
    var format = 'YYYY-MM-DD';
    var retVal = moment(cellvalue).utcOffset(+900).format(format);
	if(retVal == 'Invalid date'){
		retVal = '';
	}
	return retVal
}
function setSelectPickerTroopsFemale(){
	$.ajax({
	 type: "GET",
	 url:contextPath+'/code/list/troops_female', 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 success: function(res) {
			 if(res.success){
				var code1depth = [];
				var code2depth = [];
				var code3depth = [];
				$.each(res.data, function (index, text) {
					if($.inArray(text.code1depth, code1depth) == -1 && text.code1depth!=null ){
						code1depth.push(text.code1depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code2depth, code2depth) == -1 && text.code2depth!=null){
						code2depth.push(text.code2depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code3depth, code3depth) == -1 && text.code3depth!=null){
						code3depth.push(text.code3depth)
					}
				});
				$.each(code1depth, function (index, text) {
				    $('select#code1_code1depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code2depth, function (index, text) {
				    $('select#code1_code2depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code3depth, function (index, text) {
				    $('select#code1_code3depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$('.selectpicker').selectpicker('refresh')
			 }else{
					alert('데이터 조회를 실패하였습니다.');
			 }
		 },
			error : function(res){
					alert('데이터 조회를 실패하였습니다.');
			}
		});
}
function setSelectPickerWorkplace(){
	$.ajax({
	 type: "GET",
	 url:contextPath+'/code/list/workplace', 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 success: function(res) {
			 if(res.success){
				var code1depth = [];
				var code2depth = [];
				var code3depth = [];
				var code4depth = [];
				$.each(res.data, function (index, text) {
					if($.inArray(text.code1depth, code1depth) == -1 && text.code1depth!=null){
						code1depth.push(text.code1depth);
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code2depth, code2depth) == -1 && text.code2depth!=null){
						code2depth.push(text.code2depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code3depth, code3depth) == -1 && text.code3depth!=null){
						code3depth.push(text.code3depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code4depth, code4depth) == -1 && text.code4depth!=null){
						code4depth.push(text.code4depth)
					}
				});
				$.each(code1depth, function (index, text) {
				    $('select#workplace_code1depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code2depth, function (index, text) {
				    $('select#workplace_code2depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code3depth, function (index, text) {
				    $('select#workplace_code3depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code4depth, function (index, text) {
				    $('select#workplace_code4depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$('.selectpicker').selectpicker('refresh')
			 }else{
					alert('데이터 조회를 실패하였습니다.');
			 }
		 },
			error : function(res){
					alert('데이터 조회를 실패하였습니다.');
			}
		});
}
function dtConvertDashToTxt(text){
	var ar = text.split('-');
	return ar[0]+'년 '+ar[1]+'월 '+ar[2]+'일'
}