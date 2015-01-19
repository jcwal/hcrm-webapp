// $Id: list.js 3512 2012-08-23 01:12:33Z jokeway $
$(function() {
	var code = 'doctor-list';

	// 定义的常量
	var Constants = {
		data_send_event : code + '_data_send_.' + code,
		data_arrive_event : code + '_data_arrive_.' + code
	};
	var PageData = {
		dataTotal : 0
	};

	var Parts = {};
	$([ 'actions', 'search', 'list', 'pager', 'nodata' ]).each(function() {
		Parts[this] = $('#finder-' + this + '-' + code);
	});

	var eventBindingElement = $(document.body);
	eventBindingElement.unbind('.' + code);

	var actionsViewModel = ko.mapping.fromJS({
		selectedRow : null
	});
	actionsViewModel.onAddEnable = ko.dependentObservable(function() {
		var row = actionsViewModel.selectedRow();
		return row != null;
	});
	actionsViewModel.onEditEnable = ko.dependentObservable(function() {
		var row = actionsViewModel.selectedRow();
		return row != null;
	});
	actionsViewModel.onDeleteEnable = ko.dependentObservable(function() {
		var row = actionsViewModel.selectedRow();
		return row != null;
	});
	actionsViewModel.onEditAction = function(e) {
		var row = actionsViewModel.selectedRow();
		if (row != null) {
			$(e.currentTarget).attr('url', 'admin/macula-trial/doctor/edit/' + row.id);
			return true;
		}
		e.stopPropagation();
		return false;
	};
	actionsViewModel.onDeleteAction = function(e) {
		var row = actionsViewModel.selectedRow();
		if (row != null && confirm('您确定要删除医生' + '【' + row.nickname + '】吗？')) {
			$(e.currentTarget).attr('url', 'admin/macula-trial/doctor/delete/' + row.id);
			return true;
		}
		e.stopPropagation();
		return false;
	};
	$('#delete-action-' + code).bind('onCommand', function(e, data) {
		if (data.success) {
			eventBindingElement.trigger(Constants.data_send_event);
		} else {
			MessageBox.error(data.exceptionMessage, true);
		}
	});
	actionsViewModel.onRefreshClick = function() {
		eventBindingElement.trigger(Constants.data_send_event);
	};

	var tableViewModel = ko.mapping.fromJS({
		content : []
	});
	tableViewModel.onRowClick = function(e, row) {
		var $tr = $(e.target).closest('tr');
		$tr.parent().find('tr').removeClass('current');
		$tr.addClass('current');
		actionsViewModel.selectedRow(row);
	};

	ko.applyBindings(actionsViewModel, Parts['actions'][0]);
	ko.applyBindings(tableViewModel, Parts['list'][0]);

	// 处理分页条上的事件
	Parts['pager'].maculapagination({
		code : code
	});
	var formAjaxOptions = {
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				eventBindingElement.trigger(Constants.data_arrive_event, [ data ]);
			} else {
				data.exceptionMessage && MessageBox.error(data.exceptionMessage, true);
			}
		}
	};

	Parts['search'].ajaxForm(formAjaxOptions);
	// 绑定事件
	eventBindingElement.bind(Constants.data_arrive_event, function(e, data) {
		PageData.dataTotal = data.totalElements;
		tableViewModel.content(data.content);
		if (data.content && data.content.length) {
			Parts['nodata'].hideme();
		} else {
			Parts['nodata'].showme();
		}
	}).bind(Constants.data_send_event, function(e, options) {
		if (options) {
			options.pageSize && Parts['search'].find('input[name=rows]').val(options.pageSize);
			options.currentPage && Parts['search'].find('input[name=page]').val(options.currentPage);
		}
		Parts['search'].ajaxSubmit(formAjaxOptions);
	});
	eventBindingElement.trigger(Constants.data_send_event);
});