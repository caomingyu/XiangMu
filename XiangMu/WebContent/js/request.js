/**
 * ����XMLHttpRequest�������������
 * @param reqType���������ͣ�GET��POST��
 * @param url����������ַ
 * @param async���Ƿ��첽����
 * @param resFun����Ӧ�Ļص�����
 * @param parameter :�������
 * @return
 */

function httpRequest(reqType,url,async,resFun,parameter){
	var request = null;
	if(window.XMLHttpRequest){			//��IE�����
		request = new XMLHttpRequest();
	}else if(window.ActiveXObject){		//IE�����  
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	request.open(reqType, url, true);	//�򿪷���������
	//��������ͷ
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange = resFun;//���ô�����Ӧ�Ļص�����
	parameter = encodeURI(parameter);	//�������ַ������б���
	request.send(parameter);			//��������
	
	return request;
}