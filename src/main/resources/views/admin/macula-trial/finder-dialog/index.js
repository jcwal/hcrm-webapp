// $Id: list.js 3512 2012-08-23 01:12:33Z jokeway $
$(function() {
	var code = 'doctor-list';

	var tableViewModel = ko.mapping.fromJS({
		content : []
	});
	
	ko.applyBindings(tableViewModel, $('#finder-dialog-content')[0]);

});