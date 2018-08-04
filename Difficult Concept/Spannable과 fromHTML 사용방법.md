Spannable과 fromHTML() 정리

블로그처럼 사진, 링크, 텍스트 가 합쳐져있는경우 계속 여러 뷰를 생성하여 출력해야하는 번거로움이있음
--> 이를 해결하기 위해 하나의 TextView에 ImageSpan이나 URLspan을 적용하여 묶어서 출력가능한 Spannable이 있다.

** Spannable 적용방법 **
1. View 에 bufferType 적용 --> android:bufferType = "spannable"
2. 코드에서 출력되는 문자열에 Spannable 적용 
	--> SpannableStringBuilder builder = new SpannableStringBuilder(data);  // Spannable을 포함하는 문자열
	--> ima문자열의 시작위치 파악
		int start = data.indexOf("img")
		if(start > -1) {
			int end = start + "img".length();
			//이미지 획득
			Drawable dr = ResourceCompat.getDrawable(getResources(), R.drawable.img1, null);
			dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
			ImageSpan span = new ImageSpan(dr);
			// SpannableStringBuilder 에 ImageBuilder 적용
			builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
TextView 하나를 이용하여 이미지, URL, Underline, Clickable, AbsolteSize, Image, RelativeSize, Style Span적용 가능



**fromHTML적용방법**
--> HTML태그에 Spannable적용 --> fromHTML() 함수에서 대행하는 구조
String html = "<font color = 'RED'>얼레지</font> <br/> <img src = 'img1' /><br/>곰배령에서 만든 봄꽃"
	fromHTML(String source)
	fromHTML(String source, Html.ImageGetter imageGetter, Html.TagHandler tagHandler)
	fromHTML(String source, ing flags, Html.ImageGetter imageGetter, Html.TagHandler tagHandler)
--> html.setText(Html.fromHtml(html, new MyImageGetter(), null)); --> 사용법 

class MyImageGetter implements Html.ImageGetter {
	//이미지 획득을 위해 getDrawable 자동 호출
	@Override
	public Drawable getDrawable(String source) {
		if(source.equals("img1") {
			//이미지 획득
			Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null);
			dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
			return dr;
		}
		//이미지반환이없으면 null 반환
		return null
	}
}