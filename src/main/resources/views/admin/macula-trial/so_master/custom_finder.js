function do_so_master_finder_delete(data) {
	if (data.success && data.returnObject) {
		alert('删除成功！');
	} else {
		data.exceptionMessage || alert(data.exceptionMessage);
	}

}