	var fdata = {}; // initial file data
	var cropsave = false; // crop save check
	$(document).ready(function() {
		var $form = $('#imgForm');
		if (lxr.edt.usehtml5) { // HTML5 Support(at header.jsp) - Drag&Drop
			var drop_box = document.getElementById('img_preview');
			drop_box.addEventListener('dragenter', function(evt) {
				evt.preventDefault();
				evt.stopPropagation();
				evt.dataTransfer.dropEffect = 'copy';
				$('#img_preview').css('border', '3px dotted #D94467');
			}, false);
			drop_box.addEventListener('dragleave', function(evt) {
				evt.preventDefault();
				evt.stopPropagation();
				$('#img_preview').css('border', '0px solid');
			}, false);
			drop_box.addEventListener('dragover', function(evt) {
				evt.preventDefault();
				evt.stopPropagation();
				evt.dataTransfer.dropEffect = 'copy';
				$('#img_preview').css('border', '3px dotted #D94467');
			}, false);
			drop_box.addEventListener('drop', function(evt) {
				evt.stopPropagation();
		    	evt.preventDefault();
		    	$('#img_preview').css('border', '0px solid');
				var file = evt.dataTransfer.files[0];
	    		if (file.name.indexOf('/editor_img') >= 0) {
	        		return false;
	    		}			
				var ext = file.name.split('.').pop().toLowerCase();
	    		if ($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
	        		alert(edt_cfg.msg01);
	        		return false;
	    		}
				var xhr = new XMLHttpRequest();
				xhr.upload.addEventListener('loadstart', function() {}, false);  
				xhr.upload.addEventListener('progress', function() {}, false);  
				xhr.upload.addEventListener('load', function() {}, false);  
				xhr.upload.addEventListener('error', function() {}, false);  
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var req = $.parseJSON(xhr.responseText);
						fdata = {
							'imageurl': req.attach.filePath,
							'filename': req.attach.fileId,
							'filesize': req.attach.fileSize,
							'imagealign': 'C',
							'originalurl': req.attach.filePath,
							'thumburl': req.attach.filePath
						};
						$('#flid').val(req.attach.fileId);
						$('#img_preview').attr('src', req.attach.filePath).css({width:70,height:70});//preview
						$('#img_name').html(req.attach.fileName);
						$('#img_size').html('&nbsp;(' + formatFileSize(req.attach.fileSize) + ')');
						$('#img_resizer').empty();
						$('<img>').addClass('daum_edt_preview').attr('src', edt_cfg.imgprv).appendTo('#img_resizer');
						$('#img_crop').attr('disabled', 'disabled');
					}
				};
				xhr.open('post', edt_cfg.h5url, true);	
				xhr.setRequestHeader('Cache-Control', 'no-cache'); 
				xhr.setRequestHeader('X-File-Name', encodeURIComponent(file.name));
				xhr.setRequestHeader('X-File-Size', file.size);
				xhr.send(file);
			}, false);
		}
		$('form').delegate('#daum_editor_img', 'change', function() {
			$('#img_crop').attr('checked', false);
			$('#img_crop').attr('disabled', 'disabled');
			var f = $(this).val();
            $form.ajaxForm({
                //dataType: 'json', // ie9 json download bug
				dataType: 'text',
                beforeSubmit: function() {
                	if (f != '') {
                		var ext = f.split('.').pop().toLowerCase();
                		if ($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
                    		alert(edt_cfg.msg01);
                    		return false;
                		}
                	}
                	return true;
                },
                //success: function(req, status) {// ie9 json download bug
				success: function(text, status) {
					var req = $.parseJSON(text);
                    fdata = {
						'imageurl': req.attach.filePath,
						'filename': req.attach.fileId,
						'filesize': req.attach.fileSize,
						'imagealign': 'C',
						'originalurl': req.attach.filePath,
						'thumburl': req.attach.filePath
                    };
                    $('#flid').val(req.attach.fileId);
                    $('#img_preview').attr('src', req.attach.filePath).css({width:70,height:70});//preview
                    $('#img_name').html(req.attach.fileName);
                    $('#img_size').html('&nbsp;(' + formatFileSize(req.attach.fileSize) + ')');
                    $('#img_resizer').empty();
					$('<img>').addClass('daum_edt_preview').attr('src', edt_cfg.imgprv).appendTo('#img_resizer');
					$('#img_crop').attr('disabled', 'disabled');
                }
            }).submit();
        });

		// set image preview for image resize - START
		$('form').delegate('#img_preview', 'click', function() {
			if ($(this).attr('src').indexOf('/editor_img') >= 0) { // not upload image
				return;
			}
			var ext = $(this).attr('src').substring($(this).attr('src').lastIndexOf(".") + 1).toLowerCase();
			if (ext.indexOf('gif') == -1) { // gif image not crop
				$('#img_crop').removeAttr('disabled'); // when click image preview, set image crop enabled
			}
			$('#img_crop').attr('checked', false);
			$("#aspect_ratio,#horizontal_pixel,#vertical_pixel").each(function() {
				$(this).attr('disabled', 'disabled');
			});
			$("#type_percentage,#type_pixel,#ratio_percentage").each(function() {
				$(this).removeAttr('disabled');
			});
			$('#img_resizer').empty();
			// chrome image load bug fixed - image size after image load
			$('<img>').attr('id', 'img_resize_area').attr('src', $(this).attr('src')).appendTo('#img_resizer').load(function() {
				glb_width = $('#img_resize_area').width(); // when image preview click, set original width
				glb_height = $('#img_resize_area').height(); // when image preview click, set original height
				$('#horizontal_pixel').val(glb_width);
				$('#vertical_pixel').val(glb_height);
				$(this).css({width:glb_width, height:glb_height, border:10});
			});
			$('#img_resize_area').draggable({
				cursor:'move'
			});
			$('#type_percentage').removeAttr('disabled');
			$('#ratio_percentage').removeAttr('disabled');
			$('#type_pixel').removeAttr('disabled');			
			//glb_width = $('#img_resize_area').width(); // when image preview click, set original width
			//glb_height = $('#img_resize_area').height(); // when image preview click, set original height
			//$('#horizontal_pixel').val(glb_width);
			//$('#vertical_pixel').val(glb_height);
		});
		// set image preview for image resize - END
		
		// insert image to editor body - START
		$('form').delegate('#img_insert_editor', 'click', function() {
			if (typeof(execAttach) == 'undefined') {
		        return;
		    }
			if ($('#img_preview').attr('src').indexOf('/editor_img') >= 0) {
				alert(edt_cfg.msg02);
				return;
			}
		    
			// image align - START
			var img_align = $(':radio[name=img_align]:checked').val();
			if (img_align != '') {
				fdata.imagealign = img_align;
			}		
			// image align - END	
			
			if ($('#img_crop').is(':checked') && !cropsave) {
				alert(edt_cfg.msg06);
				return;
			}
			
			// new attach data struct with image size - START
			if ($('#img_resize_area').length > 0) { // set image resize
				fdata = {
					'imageurl': fdata.imageurl,
					'filename': fdata.filename,
					'filesize': fdata.filesize,
					'imagealign': fdata.imagealign,
					'originalurl': fdata.originalurl,
					'thumburl': fdata.thumburl,
					'width': $('#img_resize_area').width(),
					'height': $('#img_resize_area').height()
				};
			}
			// new attach data struct with image size - END
		    try {
				execAttach(fdata);// IE11 - txlib.js isExistAgentStringByRegx function useragent look carefully
				closeWindow();
		    } catch(e) {closeWindow();}
		});
		// insert image to editor body - END

		// remove uploaded image and close window - START
		$('form').delegate('#img_cancel_editor', 'click', function() {
			deleteAndCloseWindow();
		});
		$('form').delegate('#close_img_window', 'click', function() {
			deleteAndCloseWindow();
		});
		// remove uploaded image and close window - END
		
		// image resize - START 
		var glb_width; // original image width
		var glb_height; // original image height

		$('form').delegate('#type_percentage', 'click', function() {
			$('#aspect_ratio').attr('disabled', 'disabled');
			$('#horizontal_pixel').attr('disabled', 'disabled');
			$('#vertical_pixel').attr('disabled', 'disabled');
			$('#ratio_percentage').removeAttr('disabled');
			
		});
		$('form').delegate('#type_pixel', 'click', function() {
			$('#horizontal_pixel').val(glb_width);
			$('#vertical_pixel').val(glb_height);
			$('#img_resize_area').css({width:glb_width, height:glb_height});
			$('#ratio_percentage').attr('disabled', 'disabled');
			$('#aspect_ratio').removeAttr('disabled');
			$('#horizontal_pixel').removeAttr('disabled');
			$('#vertical_pixel').removeAttr('disabled');
		});
		$('form').delegate('#ratio_percentage', 'blur', function() {
			var rv = parseInt($(this).val(), 10);
			if (rv < 10) { // set minimum image size 10 percentage
				$(this).val('10');
			}	
		});
		$('form').delegate('#ratio_percentage', 'keyup', function() {
			if(!util.is_number($(this).val())) {
				alert(edt_cfg.msg03);
				$(this).val(util.removeSpecialChar($(this).val(), '3'));
				if ($(this).val() == '') { $(this).val('100'); }
				return false;
			}
			var rv = parseInt($(this).val(), 10);
			if (rv < 10) { // set minimum image size 10 percentage
				rv = 10;
			}
			var features = {width: Math.round(glb_width * (rv / 100)), height: Math.round(glb_height * (rv / 100))};
			$('#img_resize_area').css({width: features.width, height: features.height});
		});
		$('form').delegate('#horizontal_pixel', 'blur', function() {
			var rv = parseInt($(this).val(), 10);
			if (rv < 10) { // set minimum image size 10 pixel
				$(this).val('10');
			}
		});
		$('form').delegate('#horizontal_pixel', 'keyup', function() {
			if(!util.is_number($(this).val())) {
				alert(edt_cfg.msg03);
				$(this).val(util.removeSpecialChar($(this).val(), '3'));
				if ($(this).val() == '') { $(this).val(glb_width); }
				return false;
			}			
			var rv = parseInt($(this).val(), 10);
			rv = (rv < 10) ? 10 : rv; // set minimum image size 10 pixel
			var ck = $('#aspect_ratio').is(':checked');	
			if (ck) {
				var change_height = Math.round((rv * $('#img_resize_area').height()) / $('#img_resize_area').width());
				$('#vertical_pixel').val(change_height);
				$('#img_resize_area').css({width:rv, height:change_height});
			} else {
				$('#img_resize_area').css({width:rv, height:$('#img_resize_area').height()});
			}
		});
		$('form').delegate('#vertical_pixel', 'blur', function() {
			var rv = parseInt($(this).val(), 10);
			if (rv < 10) { // set minimum image size 10 pixel
				$(this).val('10');
			}
		});
		$('form').delegate('#vertical_pixel', 'keyup', function() {
			if(!util.is_number($(this).val())) {
				alert(edt_cfg.msg03);
				$(this).val(util.removeSpecialChar($(this).val(), '3'));
				if ($(this).val() == '') { $(this).val(glb_height); }
				return false;
			}	
			var rv = parseInt($(this).val(), 10);
			rv = (rv < 10) ? 10 : rv; // set minimum image size 10 pixel	
			var ck = $('#aspect_ratio').is(':checked');	
			if (ck) {
				var change_width = Math.round((rv * $('#img_resize_area').width()) / $('#img_resize_area').height());
				$('#horizontal_pixel').val(change_width);
				$('#img_resize_area').css({width:change_width, height:rv});
			} else {
				$('#img_resize_area').css({width:$('#img_resize_area').width(), height:rv});
			}
		});
		$('form').delegate('#aspect_ratio', 'change', function() {
			if ($(this).is(':checked')) {
				$('#horizontal_pixel').val(glb_width);
				$('#vertical_pixel').val(glb_height);
				$('#img_resize_area').css({width:glb_width, height:glb_height});
			}
		});		

		$('form').delegate('#percentage_up', 'click', function() {
			if ($('#type_percentage').is(':disabled') || !$('#type_percentage').is(':checked')) {
				return;
			}
			var r = $('#ratio_percentage');
			r.select();
			var rv = parseInt(r.val(), 10) + 1; 
			if (rv < 10) { // set minimum image size 10 percentage
				rv = 10;
				r.val('10');
			} else {
				r.val(rv);
			}
			var features = {width: Math.round(glb_width * (rv / 100)), height: Math.round(glb_height * (rv / 100))};
			$('#img_resize_area').css({width: features.width , height: features.height});
		});
		$('form').delegate('#percentage_down', 'click', function() {
			if ($('#type_percentage').is(':disabled') || !$('#type_percentage').is(':checked')) {
				return;
			}	
			var r = $('#ratio_percentage');
			r.select();
			var rv = parseInt(r.val(), 10) - 1; 
			if (rv < 10) { // set minimum image size 10 percentage
				rv = 10;
				r.val('10');
			} else {
				r.val(rv);
			}
			var features = {width: Math.round(glb_width * (rv / 100)), height: Math.round(glb_height * (rv / 100))};
			$('#img_resize_area').css({width: features.width, height: features.height});					
		});

		$('form').delegate('#pixel_horizon_up', 'click', function() {
			if ($('#type_pixel').is(':disabled') || !$('#type_pixel').is(':checked')) {
				return;
			}	
			var rv = parseInt($('#horizontal_pixel').val(), 10) + 1;
			var ck = $('#aspect_ratio').is(':checked');	
			if (ck) {
				var change_height = Math.round((rv * $('#img_resize_area').height()) / $('#img_resize_area').width());
				$('#horizontal_pixel').val(rv);
				$('#vertical_pixel').val(change_height);
				$('#img_resize_area').css({width:rv, height:change_height});
			} else {
				$('#horizontal_pixel').val(rv);
				$('#img_resize_area').css({width:rv, height:$('#img_resize_area').height()});
			}					
		});
		$('form').delegate('#pixel_horizon_down', 'click', function() {
			if ($('#type_pixel').is(':disabled') || !$('#type_pixel').is(':checked')) {
				return;
			}	
			var rv = parseInt($('#horizontal_pixel').val(), 10) - 1;
			rv = (rv < 10) ? 10 : rv; // set minimum image size 10 pixel
			var ck = $('#aspect_ratio').is(':checked');	
			if (ck) {
				var change_height = Math.round((rv * $('#img_resize_area').height()) / $('#img_resize_area').width());
				$('#horizontal_pixel').val(rv);
				$('#vertical_pixel').val(change_height);
				$('#img_resize_area').css({width:rv, height:change_height});
			} else {
				$('#horizontal_pixel').val(rv);
				$('#img_resize_area').css({width:rv, height:$('#img_resize_area').height()});
			}					
		});

		$('form').delegate('#pixel_vertic_up', 'click', function() {
			if ($('#type_pixel').is(':disabled') || !$('#type_pixel').is(':checked')) {
				return;
			}	
			var rv = parseInt($('#vertical_pixel').val(), 10) + 1;
			var ck = $('#aspect_ratio').is(':checked');	
			if (ck) {
				var change_width = Math.round((rv * $('#img_resize_area').width()) / $('#img_resize_area').height());
				$('#horizontal_pixel').val(change_width);
				$('#vertical_pixel').val(rv);
				$('#img_resize_area').css({width:change_width, height:rv});
			} else {
				$('#vertical_pixel').val(rv);				
				$('#img_resize_area').css({width:$('#img_resize_area').width(), height:rv});
			}					
		});
		$('form').delegate('#pixel_vertic_down', 'click', function() {
			if ($('#type_pixel').is(':disabled') || !$('#type_pixel').is(':checked')) {
				return;
			}
			var rv = parseInt($('#vertical_pixel').val(), 10) - 1;
			rv = (rv < 10) ? 10 : rv; // 최소 사이즈는 10pixel로 지정함.
			var ck = $('#aspect_ratio').is(':checked');	
			if (ck) {
				var change_width = Math.round((rv * $('#img_resize_area').width()) / $('#img_resize_area').height());
				$('#horizontal_pixel').val(change_width);
				$('#vertical_pixel').val(rv);
				$('#img_resize_area').css({width:change_width, height:rv});
			} else {
				$('#vertical_pixel').val(rv);
				$('#img_resize_area').css({width:$('#img_resize_area').width(), height:rv});
			}						
		});	
		// image resize - END

		// image crop - START
		$('form').delegate('#img_crop_save', 'click', function() {
			if ($('#img_crop').is(':checked')) {
				$.ajax({
					type:'post',
					async:true,
					data:$('#imgForm').serialize(),
					url:edt_cfg.crurl,
					success:function(req) {
						$('img#img_resize_area').imgAreaSelect({remove:true});
						$('#img_resizer').empty();
						$('<img>').attr('id', 'img_resize_area').attr('src', $('#img_preview').attr('src') +'?v=' + new Date().getTime()).appendTo('#img_resizer');
						fdata.imageurl = $('#img_resize_area').attr('src');
						$('#img_size').html('&nbsp;(' + formatFileSize(req.attach.fileSize) + ')');
						$('#img_preview').attr('src', $('#img_resize_area').attr('src'));
						cropsave = true;
					},
					error:function(data,sataus,err) {
					}
				});				
			}
		});
		$('form').delegate('#img_crop', 'change', function() {
			if ($(this).is(':checked')) {
				if (!confirm(edt_cfg.msg05)) {
					$(this).attr('checked', false); 
					return;
				}
				// description
				$('#crop_desc').show('fast', function() { $(this).fadeOut(5000, 'linear'); });
				$("#img_resize_area").draggable('disable');
				// image resize reset
				$('#img_resize_area').css({width:glb_width, height:glb_height});
				// image resize disabled
				$("#type_percentage,#type_pixel,#ratio_percentage,#aspect_ratio,#horizontal_pixel,#vertical_pixel").each(function() {
					$(this).attr('disabled', 'disabled');
				});
				
				$('#ratio_percentage').val('100');
				$('img#img_resize_area').imgAreaSelect(
					{
						/* aspectRatio: '1:1', */
						onSelectEnd: function(i, el) {
							$('#pos_x').val(el.x1);
							$('#pos_y').val(el.y1);
							$('#pos_w').val(el.width);
							$('#pos_h').val(el.height);
						}
					}
				);
			} else {
				$("#img_resize_area").draggable('enable');
				// image resize default check enabled
				$("#type_percentage,#type_pixel,#ratio_percentage").each(function() {
					$(this).removeAttr('disabled');
				});
				$('img#img_resize_area').imgAreaSelect({remove:true});
			}
		});
				
		// image crop - END
	});
	
	var deleteAndCloseWindow = function() {
		if ($('#img_preview').attr('src').indexOf('/editor_img') >= 0) { // not image uploaded - close window
			closeWindow();
		} else { // image uploaded - remove image and close window
			var f = (fdata.filename == 'undefined') ? '' : fdata.filename;
			if (f != '') {
				$.ajax({
					type:'post',
					async:true,
					data:'dfile=' + f,
					url:edt_cfg.dlurl,
					success:function(data) {
						closeWindow();
					},
					error:function(data,sataus,err) {
						closeWindow();
					}
				});
			} else {
				closeWindow();
			}
		}
	};
    var formatFileSize = function(fileSize) {
        if (fileSize >= 1073741824) {
        	return (fileSize / 1073741824).toFixed(2) + ' GB';
        }
        if (fileSize >= 1048576) {
        	return (fileSize / 1048576).toFixed(2) + ' MB';
        }
        if (fileSize >= 1024) {
        	return (fileSize / 1024).toFixed(2) + ' KB';
        }            
        return (fileSize).toFixed(2) + ' B';
    };
	var initUploader = function(){
		$('#loading_red').hide(); //util.js의 로딩바 삭제
	    var _opener = PopupUtil.getOpener();
	    if (!_opener) {
	        alert(edt_cfg.msg04);
	        return;
	    }
	    var _attacher = getAttacher('image', _opener);
	    registerAction(_attacher);
	};