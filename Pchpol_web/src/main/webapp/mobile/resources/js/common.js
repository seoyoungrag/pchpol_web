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

function getDateByDate(inDate){
	this.date = inDate||new Date();
	this.yyyy = date.getFullYear();
	this.mm = date.getMonth()+1;
	this.dd = date.getDate();
	this.dashForm = function(){
		if(dd<10) {
		    dd='0'+dd;
		} 
		if(mm<10) {
		    mm='0'+mm;
		} 
		main_today = yyyy+'-'+mm+'-'+dd;
	};
	this.korForm = function(){
		main_today = yyyy+'년 '+mm+'월 '+dd+'일';
	};
}