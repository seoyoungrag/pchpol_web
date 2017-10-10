/*
  BADP MANAGEMENT version 1.0
  
  Copyright (c) 2016 kt corp. All rights reserved.
  
  This is a proprietary software of kt corp, and you may not use this file except in
  compliance with license agreement with kt corp. Any redistribution or use of this
  software, with or without modification shall be strictly prohibited without prior written
  approval of kt corp, and the copyright notice above does not evidence any actual or
  intended publication of such software.
  */
var colSize = 1000000;
var rowCount = 0;

var Format = {
	millisecondsToTime : function(inputMss) {
		var returnVal = '';
		inputMss = new Number(inputMss);
		
		if(inputMss == 0){
			returnVal = inputMss;
		}else {
			if(inputMss >= 1000){
				var milliseconds = new Number(inputMss) % 1000;
				inputMss = inputMss / 1000;
				
				var seconds = Math.floor(inputMss % 60);
				inputMss = inputMss / 60;
				
				var minutes = Math.floor(inputMss % 60);
				inputMss = inputMss / 60;
				
				var hours = Math.floor(inputMss % 60);

				if(hours != 0){
					returnVal += hours+'시간 ';
				}
				if(minutes != 0){
					returnVal += minutes+'분 ';
				}
				if(seconds != 0){
					returnVal += seconds+'초 ';
				}
				/*if(milliseconds != 0){
					returnVal += milliseconds+'밀리초';
				}*/
			}else{
				returnVal = inputMss+'밀리초';
			}
		}
		
		return returnVal;
	},
	resultToString : function(result) {
		var returnVal = '';
		if(result == 'SUCCEEDED'){
			returnVal = '성공';
		}else if(result == 'RUNNING'){
			returnVal = '진행중';
		}else if(result == 'FAILED'){
			returnVal = '실패';
		}
		
		return returnVal;
	},
	tblCntToString : function(input) {
		var returnVal = '';
		inputMss = new Number(input);
		if(inputMss == 0){
			returnVal = input;
		}else {
			if(inputMss >= 10000){
				var cheon = new Number(input) % 10000;
				input = input / 10000;
				var man = Math.floor(input % 10000) ;
				input = input / 10000;
				var eok = Math.floor(input % 10000);
				input = input /10000;
				var jo = Math.floor(input);
				if(jo != 0){
					returnVal += jo+'조 ';
				}
				if(jo==0 && eok != 0){
					returnVal += eok+'억 ';
				}
				if(jo==0 && eok==0 && man != 0){
					returnVal += man+'만 ';
				}
				if(jo==0 && eok==0 && man==0 & cheon != 0){
					returnVal += cheon;
				}
			}else{
				returnVal = input;
			}
		}
		
		return returnVal;
	},
	/*byteToTeraForMain : function(input) {
		var returnVal = '';
		input = new Number(input);
		
		if(input == 0){
			returnVal = input;
		}else {
			if(input >= 1024000){
				console.log('1 : ' + input);
				var byte = new Number(input) % 1024;
				input = input / 1024;
				console.log('2 : ' + input);
				var kByte = Math.floor(input % 1024);
				input = input / 1024;
				console.log('3 : ' + input);
				var mega = Math.floor(input % 1024);
				input = input / 1024;
				console.log('4 : ' + input);
				var giga = Math.floor(input % 1024);
				input = input / 1024;
				console.log('5 : ' + input);
				var tera = Math.floor(input % 1024);
				input = input / 1024;
				console.log('6 : ' + input);
				console.log('tera : ' + tera);
				var peta = Math.floor(input);
				if(peta != 0){
					if(tera>0){
						peta = peta+'.'+tera.toString().substr(0, 1);
					}
					returnVal += peta+'PB ';
				}
				
				if(peta == 0 && tera != 0){
					if(giga>0){
						tera = tera+'.'+giga.toString().substr(0, 1);
					}
					returnVal += tera+'TB ';
				}
				
				if(peta == 0 && tera==0 && giga != 0){
					if(mega>0){
						giga = giga+'.'+mega.toString().substr(0, 1);
					}
					returnVal += giga+'GB ';
				}
				
				if(peta == 0 && tera==0 && giga == 0 && mega != 0){
					if(kByte>0){
						mega = mega+'.'+kByte.toString().substr(0, 1);
					}
					returnVal += mega+'MB ';
				}
				
				if(peta == 0 && tera==0 && giga == 0 && mega == 0 && kByte != 0){
					if(byte>0){
						kByte = kByte+'.'+byte.toString().substr(0, 1);
					}
					returnVal += kByte+'KByte';
				}
				
				if( peta == 0 && tera==0 && giga == 0 && mega == 0 && kByte == 0 && byte != 0){
					returnVal += byte+'Byte';
				}
			}else{
				returnVal = input+'Byte';
			}
		}
		
		return returnVal;
	}*/
	byteToTeraForMain : function(pBytes) { 
		var pUnits = 'IEC';
		
	    // Handle some special cases
	    if(pBytes == 0) return '0 Bytes';
	    if(pBytes == 1) return '1 Byte';
	    if(pBytes == -1) return '-1 Byte';

	    var bytes = Math.abs(pBytes)
	    if(pUnits && pUnits.toLowerCase() && pUnits.toLowerCase() == 'si') {
	        // SI units use the Metric representation based on 10^3 as a order of magnitude
	        var orderOfMagnitude = Math.pow(10, 3);
	        var abbreviations = ['Bytes', 'kB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
	    } else {
	        // IEC units use 2^10 as an order of magnitude
	        var orderOfMagnitude = Math.pow(2, 10);
	        //var abbreviations = ['Bytes', 'KiB', 'MiB', 'GiB', 'TiB', 'PiB', 'EiB', 'ZiB', 'YiB'];
	        var abbreviations = ['Bytes', 'kB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
	    }
	    var i = Math.floor(Math.log(bytes) / Math.log(orderOfMagnitude));
	    var result = (bytes / Math.pow(orderOfMagnitude, i));

	    // This will get the sign right
	    if(pBytes < 0) {
	        result *= -1;
	    }

	    // This bit here is purely for show. it drops the percision on numbers greater than 100 before the units.
	    // it also always shows the full number of bytes if bytes is the unit.
	    if(result >= 99.995 || i==0) {
	        return result.toFixed(0) + ' ' + abbreviations[i];
	    } else {
	        return result.toFixed(2) + ' ' + abbreviations[i];
	    }
	},
	
	numberFormat : function ( number ){

	    var digitCount = (number+"").length;
	    var formatedNumber = number+"";
	    var ind = digitCount%3 || 3;
	    var temparr = formatedNumber.split('');

	    if( digitCount > 3 && digitCount <= 6 ){
	        temparr.splice(ind,0,'.');
	        formatedNumber = temparr.join('');
	    }else if (digitCount >= 7 && digitCount <= 15) {
	        var temparr2 = temparr.slice(0, ind);
	        temparr2.push('.');
	        temparr2.push(temparr[ind]);
	        temparr2.push(temparr[ind + 1]);
	        // temparr2.push( temparr[ind + 2] ); 
	        if (digitCount >= 7 && digitCount <= 9) {
	            temparr2.push(" M");
	        } else if (digitCount >= 10 && digitCount <= 12) {
	            temparr2.push(" B");
	        } else if (digitCount >= 13 && digitCount <= 15) {
	            temparr2.push(" T");
	        }
	        formatedNumber = temparr2.join('');
	    }
	    return formatedNumber;
	}
};

var Ellipsis = {
	typeChk : function(value, options, rowObject){
		var valArray = value.split('//////');
		if(valArray.length>1){
			if(valArray[0]>0){
				valArray[0]='&nbsp;&nbsp;&nbsp;&nbsp;실패: '+valArray[0]+'건\n';
			}else{
				valArray[0]='&nbsp;&nbsp;&nbsp;&nbsp;성공\n';
			}
			value = valArray[0]+'<div style="text-align: left;">check_date: '+valArray[1]+'</div>'+'<div style="text-align: left;">'+valArray[2]+'</div>'; //그냥 배열은 comma가 들어감
		}
		value=value.replace(/\n/gi,'<br>');
		rowCount++;
		var radioHtml = "";
		if(value.indexOf('성공')||value.indexOf('실패')){
			colSize=3;
		}
		if( value.length > colSize){
			radioHtml ='<div><div id='+rowCount+' style="width:90%; float:left; height:20px; overflow-y:hidden; overflow-x:hidden; white-space:nowrap; text-overflow:ellipsis;" toggle="off">';
			radioHtml += value;	
			radioHtml += '</div><span class="pull-right" style="margin-left: -10px; float:right; cursor: pointer; padding-bottom:-20px;" onclick="Ellipsis.rowToggle(this)" ><i class="zmdi zmdi-plus-circle-o"></i></span></div>';	
		}else{
			radioHtml ='<div id='+rowCount+' style="width:100%; height:20px; overflow-y:hidden; overflow-x:hidden;" onclick="Ellipsis.rowToggle('+rowCount+')">';
			radioHtml += value;
			radioHtml += '</div>';
		}
		
		return radioHtml;
	},	
	editable : function(value, options, rowObject){
		rowCount++;
		var radioHtml = "";
		if(!value){
			value = '';
		}
		
		if( value.length > colSize){
			radioHtml ='<div><div id='+rowCount+' style="width:90%; float:left; height:20px; overflow-y:hidden; overflow-x:hidden; white-space:nowrap; text-overflow:ellipsis;" toggle="off">';
			radioHtml += value;	
			radioHtml += '</div><span class="pull-right" style="margin-left: -10px; float:right; cursor: pointer; padding-bottom:-20px;" onclick="Ellipsis.rowToggle(this)" ><i class="zmdi zmdi-plus-circle-o"></i></span></div>';	
		}else{
			radioHtml ='<div id='+rowCount+' style="width:100%; height:20px; overflow-y:hidden; overflow-x:hidden;" onclick="Ellipsis.rowToggle('+rowCount+')">';
			radioHtml += value;
			radioHtml += '</div>';
		}
		
		return radioHtml;
	},
	rowToggle : function(obj){
		var parent = $(obj).parent();
		var selectDiv = $(parent).children().first();
		var toggleSw = $(selectDiv).attr("toggle");
		
		if(toggleSw == "off"){
			var forIconChange = $(obj).children().first();
			$(forIconChange).removeClass( "zmdi-plus-circle-o" ).addClass( "zmdi-close-circle-o" );
			var heightVal = $(selectDiv).css("height");	
			$(selectDiv).attr("toggle",heightVal);
			$(selectDiv).css("white-space","normal");
			$(selectDiv).css("text-overflow","");
			$(selectDiv).css("overflow-y","");
			$(selectDiv).css("overflow-x","");
	    	$(selectDiv).css("height","");
		}else{
			var forIconChange = $(obj).children().first();
			$(forIconChange).removeClass( "zmdi-close-circle-o" ).addClass( "zmdi-plus-circle-o" );
			$(selectDiv).attr("toggle","off");
			$(selectDiv).css("white-space","nowrap");
			$(selectDiv).css("text-overflow","ellipsis");
			$(selectDiv).css("overflow-y","hidden");
			$(selectDiv).css("overflow-x","hidden");
			$(selectDiv).css("height",toggleSw);
		}
	}
}


var Validation = {
	isDate : function(dateString) { 
		var regEx = '/^\d{4}/\d{2}/\d{2}$/';
		if(!dateString.match(regEx)) {
			return false;  // Invalid format
		}	
		var d;
		if(!((d = new Date(dateString))|0)){
			return false; // Invalid date (or this could be epoch)
		}
		
		return true;
	}
};


var selectBeforeTreeWhenClickedSearchBtn = {
		//node의 title로 node의 id를 찾는다. 
		//트리가 갱신될 때 id가 바뀔 수 있으므로 title로 id를 찾는다.
		searchTree : function(element, matchingTitle){
    	     if(element.title == matchingTitle){
    	          return element;
    	     }else if (element.children != null){
    	          var i;
    	          var result = null;
    	          for(i=0; result == null && i < element.children.length; i++){
    	               result = selectBeforeTreeWhenClickedSearchBtn.searchTree(element.children[i], matchingTitle);
    	          }
    	          return result;
    	     }
    	     return null;
		},
		//treeview, db명, tbl명에 해당하는 트리를 exapnd, select 한다.
		selectTree : function(tree,db,tbl){
	    	if(db==''){
	    		return false;
	    	}
			var treeElement = tree[0];
	    	var reloadtree = new Object();
	    	//db명은 unique 하니까 db를 먼저 찾는다. 이름으로 nodeid 찾는다.
    		reloadtree.db = selectBeforeTreeWhenClickedSearchBtn.searchTree(treeElement,db);
	    	if(reloadtree.db){
	    		//먼저 모든 db를 collapse하고,
	    		tree.treeview('collapseAll');
	    		//해당하는 db만 expand한다.
		    	tree.treeview('expandNode', Number(reloadtree.db.attributes['data-nodeid'].value));
		    	//해당 db를 expand했으니 이제 tbl명으로 id를 찾는다. db안에서 tbl명은 unique하다.
		    	reloadtree.tbl = selectBeforeTreeWhenClickedSearchBtn.searchTree(treeElement,tbl);
		    	if(reloadtree.tbl){
		    		//tbl이 있으면 tbl을 select한다.
		    		tree.treeview('selectNode', Number(reloadtree.tbl.attributes['data-nodeid'].value));		    		
		    	}
	    	}
		},
		getSelectedNodeOne : function(tree){
			return node = tree.treeview('getSelected')[0];
		},
		getParentNode : function(tree, node){
			return tree.treeview('getParent', node);
		}
}

var fixPositionsOfFrozenDivs = function () {
    var $rows;
    if (typeof this.grid.fbDiv !== "undefined") {
        $rows = $('>div>table.ui-jqgrid-btable>tbody>tr', this.grid.bDiv);
        $('>table.ui-jqgrid-btable>tbody>tr', this.grid.fbDiv).each(function (i) {
            var rowHight = $($rows[i]).height(), rowHightFrozen = $(this).height();
            if ($(this).hasClass("jqgrow")) {
                $(this).height(rowHight);
                rowHightFrozen = $(this).height();
                if (rowHight !== rowHightFrozen) {
                    $(this).height(rowHight + (rowHight - rowHightFrozen));
                }
            }
        });
        $(this.grid.fbDiv).height(this.grid.bDiv.clientHeight);
        $(this.grid.fbDiv).css($(this.grid.bDiv).position());
    }
    if (typeof this.grid.fhDiv !== "undefined") {
        $rows = $('>div>table.ui-jqgrid-htable>thead>tr', this.grid.hDiv);
        $('>table.ui-jqgrid-htable>thead>tr', this.grid.fhDiv).each(function (i) {
            var rowHight = $($rows[i]).height(), rowHightFrozen = $(this).height();
            $(this).height(rowHight);
            rowHightFrozen = $(this).height();
            if (rowHight !== rowHightFrozen) {
                $(this).height(rowHight + (rowHight - rowHightFrozen));
            }
        });
        $(this.grid.fhDiv).height(this.grid.hDiv.clientHeight);
        $(this.grid.fhDiv).css($(this.grid.hDiv).position());
    }
};