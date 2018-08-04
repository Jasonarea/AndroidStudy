WebView Ȱ����

1. WebView ����� ���ؼ��� Internet �۹̼��� �޾ƾ��Ѵ�
	<uses-permission android;name = "android.permission.INTERNET"/>
2. XML���� WebView�� �غ��Ѵ�
	<WebView.... />
3. Java���� URL�� �������ش�
	WebSettings settings = webView.getSettings();
	settings.setJavaScriptEnabled(true);
	webView.loadUrl("https://www.naver.com");	

4. �Ϲ� url�� �ƴ� asset�� �ִ� ���Ͽ� loadUrl() �Լ��� �̿��Ͽ� �ε��� �� �ִ�.

**�ڹٽ�ũ��Ʈ�� �ڹٿ���**
1. �ڹٽ�ũ��Ʈ���� �ڹ� ȣ��
class JavaScriptText {
	@JavaScriptInterface	// �� ������̼��� �߰��ؾ� �ڹٽ�ũ��Ʈ�� �Լ��� �����ȴ�.
	public String getCharData() {
		//...
		return buffer.toString();
	}
}

webView.addJavascriptEnabled(new JavaScriptText(), "android") --> android��� �̸����� ������ ��ü�� getCharData()��� �Լ� ȣ��
--> var data = window.android.getCharData();

2. �ڹٿ��� �ڹٽ�ũ��Ʈ ȣ��
webView.loadUrl("javaScript:lineChart()");

3. HTML���� �߻��� ����� �̺�Ʈ�� �ڹٿ��� ó���ϰ� ������? 
	--> WebViewClient�� ��ӹ޴� �̺�Ʈ �ۼ�
  1) shouldOverrideUrlLoading() �Լ� �̿�
	: HTML���� ����� �̺�Ʈ�� �߻��Ͽ� ���ο� URL�� �ε��Ǵ� ������ �̺�Ʈ.(����ڰ� ��ũ�� Ŭ���� ���� �̺�Ʈ ȣ��)
	Parameter : �ε��ϰ��� �ϴ� Url�� �Ű������� �����Ѵ�.

	--> WebChromeClient�� ��ӹ޴� �̺�Ʈ �ۼ�
  2) onJsAlert() �Լ� �̿� 
	: ���������� �˸� â�� �ߴ� ������ ó���� �ڵ�. �Ű������� �˸�â�� �޽����� ���޵Ǹ� �˸�â�� ��ü�� 
	JsResult Ÿ������ ���޵ȴ�. 
	confirm() �Լ��� �̿��Ͽ� �˸� â�� ���� �ʰ� �� ���� �ִ�.
	public boolean onJsAlert(Webview view, String url, String message, JsResult result) {
		//...
		result.confirm();	//�˸�â�� �ȶ߰�
		return true;
	}
