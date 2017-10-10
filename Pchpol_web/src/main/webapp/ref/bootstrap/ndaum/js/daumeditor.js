	var daumEditor;	// global variable
	$(document).ready(function () {
	});

	var DaumEditor = function(moduleType) {
		var config = {
			txHost: serverName, /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
			txPath: contextroot + '/resources/kt/ndaum/', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/  */
			txService: 'sample', /* 수정필요없음. */
			txProject: 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
			initializedId: '', /* 대부분의 경우에 빈문자열 */
			wrapper: 'tx_trex_container', /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
			form: 'tx_editor_form', /* 등록하기 위한 Form 이름 */
			txIconPath: contextroot + '/resources/kt/ndaum/images/icon/editor/', /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정. */
			txDecoPath: contextroot + '/resources/kt/ndaum/images/deco/contents/', /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정. */
			canvas: {
				styles: {
					color: '#000', /* 기본 글자색 #123456 */
					fontFamily: '굴림', /* 기본 글자체 */
					fontSize: '10pt', /* 기본 글자크기 */
					backgroundColor: '#fff', /*기본 배경색 */
					lineHeight: '1.5', /*기본 줄간격 */
					padding: '8px', /* 위지윅 영역의 여백 */
					height: '280px' /* 위지윅 영역의 높이*/
				},
				showGuideArea: false
			},
			events: {
				preventUnload: false
			},
			sidebar: {
				attachbox: {
					show: false,
					confirmForDeleteAll: true
				},
				attacher: 
	            {
	                image:
	                {
						popPageUrl: TrexConfig.getUrl("#host#path/pages/trex/image.jsp?moduleType=#moduleType", {'moduleType': moduleType})
	                }
	            }
			},
			size: {
				//contentWidth: 700 /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
			}
		};
		this.drawEditor = function() {  
			var ed = '';
			ed += '<div id="tx_trex_container" class="tx-editor-container" ><div id="tx_toolbar_basic" class="tx-toolbar tx-toolbar-basic"><div class="tx-toolbar-boundary">';
			ed += '<ul class="tx-bar tx-bar-left"><li class="tx-list"><div id="tx_fontfamily" unselectable="on" class="tx-slt-70bg tx-fontfamily"><a href="javascript:;" title="글꼴">굴림</a></div><div id="tx_fontfamily_menu" class="tx-fontfamily-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left"><li class="tx-list"><div unselectable="on" class="tx-slt-42bg tx-fontsize" id="tx_fontsize"><a href="javascript:;" title="글자크기">9pt</a></div><div id="tx_fontsize_menu" class="tx-fontsize-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-font"><li class="tx-list"><div unselectable="on" class="tx-btn-lbg tx-bold" id="tx_bold"><a href="javascript:;" class="tx-icon" title="굵게 (Ctrl+B)">굵게</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-underline" id="tx_underline"><a href="javascript:;" class="tx-icon" title="밑줄 (Ctrl+U)">밑줄</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-italic" id="tx_italic"><a href="javascript:;" class="tx-icon" title="기울임 (Ctrl+I)">기울임</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-strike" id="tx_strike"><a href="javascript:;" class="tx-icon" title="취소선 (Ctrl+D)">취소선</a></div></li><li class="tx-list"><div unselectable="on" class="tx-slt-tbg tx-forecolor" id="tx_forecolor"><a href="javascript:;" class="tx-icon" title="글자색">글자색</a><a href="javascript:;" class="tx-arrow" title="글자색 선택">글자색 선택</a></div><div id="tx_forecolor_menu" class="tx-menu tx-forecolor-menu tx-colorpallete" unselectable="on"></div></li><li class="tx-list"><div unselectable="on" class="tx-slt-brbg tx-backcolor" id="tx_backcolor"><a href="javascript:;" class="tx-icon" title="글자 배경색">글자 배경색</a><a href="javascript:;" class="tx-arrow" title="글자 배경색 선택">글자 배경색 선택</a></div><div id="tx_backcolor_menu" class="tx-menu tx-backcolor-menu tx-colorpallete" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-align"><li class="tx-list"><div unselectable="on" class="tx-btn-lbg tx-alignleft" id="tx_alignleft"><a href="javascript:;" class="tx-icon" title="왼쪽정렬 (Ctrl+,)">왼쪽정렬</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-aligncenter" id="tx_aligncenter"><a href="javascript:;" class="tx-icon" title="가운데정렬 (Ctrl+.)">가운데정렬</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-alignright" id="tx_alignright"><a href="javascript:;" class="tx-icon" title="오른쪽정렬 (Ctrl+/)">오른쪽정렬</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-rbg tx-alignfull" id="tx_alignfull"><a href="javascript:;" class="tx-icon" title="양쪽정렬">양쪽정렬</a></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-tab"><li class="tx-list"><div unselectable="on" class="tx-btn-lbg tx-indent" id="tx_indent"><a href="javascript:;" title="들여쓰기 (Tab)" class="tx-icon">들여쓰기</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-rbg tx-outdent" id="tx_outdent"><a href="javascript:;" title="내어쓰기 (Shift+Tab)" class="tx-icon">내어쓰기</a></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-list"><li class="tx-list"><div unselectable="on" class="tx-slt-31lbg tx-lineheight" id="tx_lineheight"><a href="javascript:;" class="tx-icon" title="줄간격">줄간격</a><a href="javascript:;" class="tx-arrow" title="줄간격">줄간격 선택</a></div><div id="tx_lineheight_menu" class="tx-lineheight-menu tx-menu" unselectable="on"></div></li><li class="tx-list"><div unselectable="on" class="tx-slt-31rbg tx-styledlist" id="tx_styledlist"><a href="javascript:;" class="tx-icon" title="리스트">리스트</a><a href="javascript:;" class="tx-arrow" title="리스트">리스트 선택</a></div><div id="tx_styledlist_menu" class="tx-styledlist-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-etc"><li class="tx-list"><div unselectable="on" id="tx_image" class="tx-btn-lbg tx-img"><a href="javascript:;" title="이미지 첨부" class="tx-icon">이미지 첨부</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-link" id="tx_link"><a href="javascript:;" class="tx-icon" title="링크 (Ctrl+K)">링크</a></div><div id="tx_link_menu" class="tx-link-menu tx-menu"></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-specialchar" id="tx_specialchar"><a href="javascript:;" class="tx-icon" title="특수문자">특수문자</a></div><div id="tx_specialchar_menu" class="tx-specialchar-menu tx-menu"></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-rbg tx-horizontalrule" id="tx_horizontalrule"><a href="javascript:;" class="tx-icon" title="구분선">구분선</a></div><div id="tx_horizontalrule_menu" class="tx-horizontalrule-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left"><li class="tx-list"><div unselectable="on" class="tx-btn-lbg tx-richtextbox" id="tx_richtextbox"><a href="javascript:;" class="tx-icon" title="글상자">글상자</a></div><div id="tx_richtextbox_menu" class="tx-richtextbox-menu tx-menu"><div class="tx-menu-header"><div class="tx-menu-preview-area"><div class="tx-menu-preview"></div></div><div class="tx-menu-switch"><div class="tx-menu-simple tx-selected"><a><span>간단 선택</span></a></div><div class="tx-menu-advanced"><a><span>직접 선택</span></a></div></div></div><div class="tx-menu-inner"></div><div class="tx-menu-footer"><img class="tx-menu-confirm" src="../resources/kt/ndaum/images/icon/editor/btn_confirm.gif?rv=1.0.1" alt=""/><img class="tx-menu-cancel" hspace="3" src="../resources/kt/ndaum/images/icon/editor/btn_cancel.gif?rv=1.0.1" alt=""/></div></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-rbg tx-quote" id="tx_quote"><a href="javascript:;" class="tx-icon" title="인용구 (Ctrl+Q)">인용구</a></div><div id="tx_quote_menu" class="tx-quote-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-undo"><li class="tx-list"><div unselectable="on" class="tx-btn-lbg tx-undo" id="tx_undo"><a href="javascript:;" class="tx-icon" title="실행취소 (Ctrl+Z)">실행취소</a></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-rbg tx-redo" id="tx_redo"><a href="javascript:;" class="tx-icon" title="다시실행 (Ctrl+Y)">다시실행</a></div></li></ul>';
			ed += '</div></div>';
			ed += '<div id="tx_toolbar_advanced" class="tx-toolbar tx-toolbar-more"><div class="tx-toolbar-boundary">';
			ed += '<ul class="tx-bar tx-bar-left"><li class="tx-list"><div class="tx-tableedit-title"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-align"><li class="tx-list"><div unselectable="on" class="tx-btn-lbg tx-table" id="tx_table"><a href="javascript:;" class="tx-icon" title="표만들기">표만들기</a></div><div id="tx_table_menu" class="tx-table-menu tx-menu" unselectable="on"><div class="tx-menu-inner"><div class="tx-menu-preview"></div><div class="tx-menu-rowcol"></div><div class="tx-menu-deco"></div><div class="tx-menu-enter"></div></div></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-lbg tx-mergecells" id="tx_mergecells"><a href="javascript:;" class="tx-icon2" title="병합">병합</a></div><div id="tx_mergecells_menu" class="tx-mergecells-menu tx-menu" unselectable="on"></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-bg tx-insertcells" id="tx_insertcells"><a href="javascript:;" class="tx-icon2" title="삽입">삽입</a></div><div id="tx_insertcells_menu" class="tx-insertcells-menu tx-menu" unselectable="on"></div></li><li class="tx-list"><div unselectable="on" class="tx-btn-rbg tx-deletecells" id="tx_deletecells"><a href="javascript:;" class="tx-icon2" title="삭제">삭제</a></div><div id="tx_deletecells_menu" class="tx-deletecells-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left tx-group-align"><li class="tx-list"><div id="tx_cellslinepreview" unselectable="on" class="tx-slt-70lbg tx-cellslinepreview"><a href="javascript:;" title="선 미리보기"></a></div><div id="tx_cellslinepreview_menu" class="tx-cellslinepreview-menu tx-menu" unselectable="on"></div></li><li class="tx-list"><div id="tx_cellslinecolor" unselectable="on" class="tx-slt-tbg tx-cellslinecolor"><a href="javascript:;" class="tx-icon2" title="선색">선색</a><div class="tx-colorpallete" unselectable="on"></div></div><div id="tx_cellslinecolor_menu" class="tx-cellslinecolor-menu tx-menu tx-colorpallete" unselectable="on"></div></li><li class="tx-list"><div id="tx_cellslineheight" unselectable="on" class="tx-btn-bg tx-cellslineheight"><a href="javascript:;" class="tx-icon2" title="두께">두께</a></div><div id="tx_cellslineheight_menu" class="tx-cellslineheight-menu tx-menu" unselectable="on"></div></li><li class="tx-list"><div id="tx_cellslinestyle" unselectable="on" class="tx-btn-bg tx-cellslinestyle"><a href="javascript:;" class="tx-icon2" title="스타일">스타일</a></div><div id="tx_cellslinestyle_menu" class="tx-cellslinestyle-menu tx-menu" unselectable="on"></div></li><li class="tx-list"><div id="tx_cellsoutline" unselectable="on" class="tx-btn-rbg tx-cellsoutline"><a href="javascript:;" class="tx-icon2" title="테두리">테두리</a></div><div id="tx_cellsoutline_menu" class="tx-cellsoutline-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-left"><li class="tx-list"><div id="tx_tablebackcolor" unselectable="on" class="tx-btn-lrbg tx-tablebackcolor" style="background-color:#9aa5ea;"><a href="javascript:;" class="tx-icon2" title="테이블 배경색">테이블 배경색</a></div><div id="tx_tablebackcolor_menu" class="tx-tablebackcolor-menu tx-menu tx-colorpallete" unselectable="on"></div></li></ul>';
			//ed += '<ul class="tx-bar tx-bar-left"><li class="tx-list"><div id="tx_tabletemplate" unselectable="on" class="tx-btn-lrbg tx-tabletemplate"><a href="javascript:;" class="tx-icon2" title="테이블 서식">테이블 서식</a></div><div id="tx_tabletemplate_menu" class="tx-tabletemplate-menu tx-menu tx-colorpallete" unselectable="on"></div></li></ul>';
			ed += '<ul class="tx-bar tx-bar-right tx-nav-opt"><li class="tx-list"><div unselectable="on" class="tx-switchtoggle" id="tx_switchertoggle"><a href="javascript:;" title="HTML 보기">HTML</a></div></li></ul>';
			//ed += '<ul class="tx-bar tx-bar-right tx-group-main"><li class="tx-list" style="z-index: 4;"><div unselectable="on" class="tx-switcher tx-slt-56bg" id="tx_switcher"><a href="javascript:;" title="에디터 타입"><span>에디터</span></a></div><div id="tx_switcher_menu" class="tx-switcher-menu tx-menu" unselectable="on"></div></li></ul>';
			ed += '</div></div>';
			ed += '<div id="tx_canvas" class="tx-canvas">';
			ed += '<div id="tx_loading" class="tx-loading"><div><img src="../resources/kt/ndaum/images/icon/editor/loading2.png" width="113" height="21" align="absmiddle"/></div></div>';
			ed += '<div id="tx_canvas_wysiwyg_holder" class="tx-holder" style="display:block;"><iframe id="tx_canvas_wysiwyg" name="tx_canvas_wysiwyg" allowtransparency="true" frameborder="0"></iframe></div>';
			ed += '<div class="tx-source-deco"><div id="tx_canvas_source_holder" class="tx-holder"><textarea id="tx_canvas_source" rows="30" cols="30"></textarea></div></div>';
			ed += '<div id="tx_canvas_text_holder" class="tx-holder"><textarea id="tx_canvas_text" rows="30" cols="30"></textarea></div>';
			ed += '</div></div>';
			document.getElementById('daum_editor_panel').innerHTML = ed; // innerHTML is performance best way!!!
		};
		this.create = function() {
			var that = this;
			this.drawEditor();
			var editor = new Editor(config);
			Editor.getCanvas().setCanvasSize({height: '300px' });
			/* @add webkit image resize - START */
			var ifr = document.getElementById('tx_canvas_wysiwyg');
			var wrapImages = function () {
		        $('#tx_canvas_wysiwyg').contents().find('img').each(function (i, img) {
					if (!$(img).parent().hasClass('image-wrap')) {
						$(img).wrap('<span class=image-wrap></span>');
					}
				});
			};
			$('#tx_canvas_wysiwyg').webkitimageresize({
		        afterReset: function () {
				    wrapImages();
		        },
	 			afterRefresh: function (img) {
		            wrapImages();
				},
		        afterElementSelect: function (img) {
				    var range = ifr.contentWindow.document.createRange();                       
		            range.selectNodeContents($(img).parent()[0]);                       
				    var sel = window.getSelection();
		            if (sel.rangeCount > 0)
				        sel.removeAllRanges();
					sel.addRange(range);
				}
			});						
			/* @add webkit image resize - END */
		};
		this.getContent = function() {
			return Editor.getContent();
		};
		this.setContent = function() {
			Editor.modify({
				'content': document.getElementById('editor_contents_source') /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
			});
		};
		this.valid = function() {
			return validForm(Editor);
		};
		this.focus = function() {
			Editor.focus();
		};

	};