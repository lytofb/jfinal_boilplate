var datePickerInit = function(domSelector){
	if(!domSelector){
		domSelector = '';
	} else {
		domSelector = domSelector+' ';
	}

	//date picker start

	if (top.location != location) {
	    top.location.href = document.location.href ;
	}
	$(function(){
	    window.prettyPrint && prettyPrint();
	    $(domSelector+'.default-date-picker').datepicker({
	        format: 'mm-dd-yyyy'
	    });
	    $(domSelector+'.dpYears').datepicker();
	    $(domSelector+'.dpMonths').datepicker();


	    var startDate = new Date(2012,1,20);
	    var endDate = new Date(2012,1,25);
	    $(domSelector+'.dp4').datepicker()
	        .on('changeDate', function(ev){
	            if (ev.date.valueOf() > endDate.valueOf()){
	                $(domSelector+'.alert').show().find('strong').text('The start date can not be greater then the end date');
	            } else {
	                $(domSelector+'.alert').hide();
	                startDate = new Date(ev.date);
	                $(domSelector+'#startDate').text($(domSelector+'.dp4').data('date'));
	            }
	            $(domSelector+'.dp4').datepicker('hide');
	        });
	    $(domSelector+'.dp5').datepicker()
	        .on('changeDate', function(ev){
	            if (ev.date.valueOf() < startDate.valueOf()){
	                $(domSelector+'.alert').show().find('strong').text('The end date can not be less then the start date');
	            } else {
	                $(domSelector+'.alert').hide();
	                endDate = new Date(ev.date);
	                $(domSelector+'.endDate').text($(domSelector+'.dp5').data('date'));
	            }
	            $(domSelector+'.dp5').datepicker('hide');
	        });

	    // disabling dates
	    var nowTemp = new Date();
	    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

	    var checkin = $(domSelector+'.dpd1').datepicker({
	        onRender: function(date) {
	            return date.valueOf() < now.valueOf() ? 'disabled' : '';
	        }
	    }).on('changeDate', function(ev) {
	            if (ev.date.valueOf() > checkout.date.valueOf()) {
	                var newDate = new Date(ev.date)
	                newDate.setDate(newDate.getDate() + 1);
	                checkout.setValue(newDate);
	            }
	            checkin.hide();
	            $(domSelector+'.dpd2')[0].focus();
	        }).data('datepicker');
	    var checkout = $(domSelector+'.dpd2').datepicker({
	        onRender: function(date) {
	            return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
	        }
	    }).on('changeDate', function(ev) {
	            checkout.hide();
	        }).data('datepicker');
	});

	//date picker end


	//datetime picker start

	$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});

	$(".form_datetime-component").datetimepicker({
	    format: "dd MM yyyy - hh:ii"
	});

	$(".form_datetime-adv").datetimepicker({
	    format: "dd MM yyyy - hh:ii",
	    autoclose: true,
	    todayBtn: true,
	    startDate: "2013-02-14 10:00",
	    minuteStep: 10
	});

	$(".form_datetime-meridian").datetimepicker({
	    format: "dd MM yyyy - HH:ii P",
	    showMeridian: true,
	    autoclose: true,
	    todayBtn: true
	});

	//datetime picker end

	//timepicker start
	$(domSelector+'.timepicker-default').timepicker();


	$(domSelector+'.timepicker-24').timepicker({
	    autoclose: true,
	    minuteStep: 1,
	    showSeconds: true,
	    showMeridian: false
	});

	//timepicker end

	//colorpicker start

	$(domSelector+'.colorpicker-default').colorpicker({
	    format: 'hex'
	});
	$(domSelector+'.colorpicker-rgba').colorpicker();

	//colorpicker end	
}
