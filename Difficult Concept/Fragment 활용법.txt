Support Library 종류와 활용방법
 
** Fragment 활용하기 **
1. Fragment 생명주기
	Created - Started - Resumed - Paused - Stopped - Destroyed
	(onAttach - onCreate - onCreateView - onActivityCreated)  -  (onStart) - (onResume) - (onStop) - (onDestroyView - onDestroy - onDetech)

2. Fragment XML 작성하기
 <fragment 태그로 작성 --> class = "com.example.test..." --> class,명 등록하여 어느액티비티에 fragment가 들어갈건지 확인필요

3. Java Class 에서 Fragment 적용
 - FragmentManager 선언
 - FragmentTransaction 선언 
 - commit() 으로 저장

4. FragmentTransaction을 이용하여 Fragment 제어 가능 
 --> add, replace, remove, commit 

5. Fragment 에 BackStack 적용
 --> ft.addToBackStack(null);


** Fragment 종류 **
1. Base Fragment
 - LayoutInflater 활용하여 View에 뿌려주는 방법 이용

public class OneFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_one, container, false);
	}
}

--> MainActivity 에서 Fragment 선언
FragmentManager manager = getFragmentManager();
oneFragment = new OneFragment();
FragmentTransaction ft = manager.beginTransaction();
ft.add(R.id.main_container, oneFragment);
ft.commit();

2. ListFragment
 - ListAdapter를 그대로 Fragment에 적용

public class OneFragment extends ListFragment {
	public View onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		String[] datas = {"박찬호", "류현진", "김현수", "오승환"};
		ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.....//, datas) // ArrayAdapter선언
		setListAdapter(aa);
	}
}

3. DialogFragment
AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
builder.setIcon(""); / setTitle, setMessage, setPositiveButton
AlertDialog dialog = builder.create();
return dialog;

4. WebView Fragment
	-- ListFragment와 동일
WebView webView = getWebView();
WebSettings settings = webView.getSettings();
settings.setJavaScriptEnabled(true);
webView.setWebViewClient(new WebViewClient());
webView.loadUrl("http://www.naver.com");

5. Preference Fragment

--> dialog를 제외하고 전부 FragmentTransaction선언 후 replace 후 commit
--> dialog같은 경우 그냥 fragment.show();