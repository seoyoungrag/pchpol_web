jQuery(function ($) {
	jqgridAdmin = new jqgridAdmin();
	jqgridAdmin.gridLoad();
});        

function jqgridAdmin() {
	this.grid = "#list-grid";
	this.pager = "#list-pager";
	this.url = '${pageContext.request.contextPath}/admin/list?listType=jqgrid';
	this.searchType = jQuery("#searchType");
	this.searchWrod = jQuery("#searchWord");
    this.gridLoad = function(){
    	jQuery(this.grid).jqGrid({
    		url : this.url,
    		datatype: "json",
    		jsonReader : {
    		          		root: "data.rows",
    		          		page: "data.page",
    						total: "data.total",
    						records: "data.records"
    					},
    	   	colNames:['No.','권한','소속','계급','성명','비고'],
    	   	colModel:[
    	   		{name:'adminNo',index:'adminNo', width:"30", align: "center"},
    	   		{name:'code', index:'code', width:"70",align:"center"},
    	   		{name:'adminDept',index:'adminDept', width:"120", align: "center"},
    	   		{name:'adminRank', index:'adminRank', width:"70",align:"center"},
    	   		{name:'adminName',index:'adminName', width:"30", align:"center"},
    	   		{name:'adminEtc',index:'adminEtc', width:"300", align:"center"},
    	   		{name:'adminId', hidden:true, index:'adminId'},		
    	   		{name:'adminPassword',hidden:true,index:'adminPassword'}
    	   	],
    	   	rowNum:10,
    	   	rowList:[10,20,30],
    	   	pager: this.pager,
    	   	sortname: 'adminNo',
    	    viewrecords: true,
    	    sortorder: "desc",
    	    autowidth:true,
    	    shrinkToFit: true,
    	    loadComplete : function()
    		{	
    	    }
    	});
    	jQuery(this.grid).jqGrid('navGrid','#list-pager',{edit:false,add:false,del:false});
    };
    this.gridReload = function(){
    	jQuery(this.grid).jqGrid('setGridParam',{url:this.url+"&searchType="+this.searchType.val()+"&searchWord="+this.searchWord.val(),page:1}).trigger("reloadGrid");
    };
}
