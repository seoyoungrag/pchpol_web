// JavaScript Document

$(document).ready(function(){


	//menu
	$('#header .m_btn').click(function(){
		$('.menu_wrap').animate({'right':'0'},400);
		$('.wrap_hide').css({'overflow':'hidden'});
		$('.wrap_hide .cover').fadeIn();
		
	});
	
	$('.wrap_hide .cover, .close_btn').click(function(){
		$('.menu_wrap').animate({'right':'-274px'},300);
		$('.wrap_hide').css({'overflow':'auto'});
		$('.wrap_hide .cover').fadeOut();
	});

	//rnb
	$('.de1').click(function(){

       function init(){
         $('.de1').removeClass('on');
         $('.de2').stop().slideUp();
       }

      if ($(this).hasClass('on') ==true ){
        init();
        $(this).removeClass('on');
        $(this).siblings('.de2').stop().slideUp();
      } else {
        init();
		$(this).addClass('on');
		$(this).siblings('.de2').stop().slideDown();
      }
    });


	var fileTarget = $('.filebox .upload-hidden'); 
		fileTarget.on('change', function(){ // 값이 변경되면
			if(window.FileReader){ // modern browser 
				var filename = $(this)[0].files[0].name; 
			} else { // old IE var 
				filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
				} // 추출한 파일명 삽입 
				$(this).siblings('.upload-name').val(filename); });

	
});

/*paging*/
function paging(){
  $('.wrap_pager .num').on('click',function(){		
	$(this).parent('.wrap_pager').children('.num').removeClass('on');		
	$(this).addClass('on');		
  });	
}