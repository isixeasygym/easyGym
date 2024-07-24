
function goToDetail(detailNo){
	let detailForm = document.createElement('form');
	let inputData = document.createElement('input');
	inputData.setAttribute('type', 'hidden');
	inputData.setAttribute('name', 'detailNo');
	inputData.setAttribute('value', detailNo);
	detailForm.appendChild(inputData);
	document.body.appendChild(detailForm);
	detailForm.setAttribute('action', '/detail/detail.do');
	detailForm.setAttribute('method', 'get');
	detailForm.submit();
}