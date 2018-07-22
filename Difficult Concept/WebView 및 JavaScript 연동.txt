WebView 활용방법

1. WebView 사용을 위해서는 Internet 퍼미션을 받아야한다
	<uses-permission android;name = "android.permission.INTERNET"/>
2. XML에서 WebView를 준비한다
	<WebView.... />
3. Java에서 URL을 지정해준다
	WebSettings settings = webView.getSettings();
	settings.setJavaScriptEnabled(true);
	webView.loadUrl("https://www.naver.com");	

4. 일반 url이 아닌 asset에 있는 파일오 loadUrl() 함수를 이용하여 로딩할 수 있다.

**자바스크립트와 자바연동**
1. 자바스크립트에서 자바 호출
class JavaScriptText {
	@JavaScriptInterface	// 이 어노테이션을 추가해야 자바스크립트에 함수가 공개된다.
	public String getCharData() {
		//...
		return buffer.toString();
	}
}

webView.addJavascriptEnabled(new JavaScriptText(), "android") --> android라는 이름으로 공개한 객체의 getCharData()라는 함수 호출
--> var data = window.android.getCharData();

2. 자바에서 자바스크립트 호출
webView.loadUrl("javaScript:lineChart()");

3. HTML에서 발생한 사용자 이벤트를 자바에서 처리하고 싶으면? 
	--> WebViewClient를 상속받는 이벤트 작성
  1) shouldOverrideUrlLoading() 함수 이용
	: HTML에서 사용자 이벤트가 발생하여 새로운 URL이 로딩되는 순간의 이벤트.(사용자가 링크를 클릭한 순간 이벤트 호출)
	Parameter : 로딩하고자 하는 Url을 매개변수로 전달한다.

	--> WebChromeClient를 상속받는 이벤트 작성
  2) onJsAlert() 함수 이용 
	: 브라우저에서 알림 창이 뜨는 순간을 처리한 코드. 매개변수로 알림창의 메시지가 전달되며 알림창의 객체가 
	JsResult 타입으로 전달된다. 
	confirm() 함수를 이용하여 알림 창이 뜨지 않게 할 수도 있다.
	public boolean onJsAlert(Webview view, String url, String message, JsResult result) {
		//...
		result.confirm();	//알림창이 안뜨게
		return true;
	}
