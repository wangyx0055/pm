(function($) {

	"use strict";
	var proId = $("#hiddenProId").val();
	var options = {
		language : 'zh-CN',
		events_source : 'milestoneController/calendarEvents?id='+proId,
		modal : "#events-modal",
		view : 'month',
		tmpl_path : 'js/bootstrap-calendar/tmpls/',
		tmpl_cache : false,
		onAfterEventsLoad : function(results) {
			if (!results) {
				return;
			}
		},
		onAfterViewLoad : function(view) {
			$('.page-header span').text(this.getTitle());
			$('.btn-group button').removeClass('active');
			$('button[data-calendar-view="' + view + '"]').addClass('active');
		},
		classes : {
			months : {
				general : 'label'
			}
		}
	};

	var calendar = $('#calendar').calendar(options);

	$('.btn-group button[data-calendar-nav]').each(function() {
		var $this = $(this);
		$this.click(function() {
			calendar.navigate($this.data('calendar-nav'));
		});
	});

	$('.btn-group button[data-calendar-view]').each(function() {
		var $this = $(this);
		$this.click(function() {
			calendar.view($this.data('calendar-view'));
		});
	});

	$('#first_day').change(function() {
		var value = $(this).val();
		value = value.length ? parseInt(value) : null;
		calendar.setOptions({
			first_day : value
		});
		calendar.view();
	});

	$('#events-modal .modal-header, #events-modal .modal-footer').click(
			function(e) {
//				 e.preventDefault();
//				 e.stopPropagation();
			});
}(jQuery));