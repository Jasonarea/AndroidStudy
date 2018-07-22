ViewPager 활용하기

1. ViewPager을 XML에 적용하기
 <android.support.v4.view.ViewPager
	android.id = "@+id/pager"
	android:layout_width = "match_parent"
	android:layout_height = "match_parent" />

2. ViewPager는 Adapter의 일종이므로 Adapter를 선언해줘야한다.
 Adapter의 종류해는 기본 PagerAdapter와 Fragment로 개발하기 위한 FragmentPagerAdapter가 있다

1) 기본 PagerAdapter 상속받을경우
class MyPagerAdapter extends PagerAdapter {
	//Override Method
	1. public int getCount() {}
	2. public Object intantiateItem(ViewGroup container, int position) {
		if(position == 0) 
			TextView tv = new TextView(Lab2Activity.this);
			//...
			container.addView(tv, position);
	}
	return null;
	//포지션에 값에 맞는 항목을 구성할 뷰를 준비하여 container 에 addView함수로 추가시켜준다. 
	
	3. public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeItem((View)object);
	}
	//뷰객체 동적으로 소멸시키기 위함
	
	4. void isViewFromObject(View arg0, Object arg1) {}
}


2) Fragment 사용목적으로 위한 FragmentPagerAdapter를 상속받는 경우
class MyPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> fragments; // Fragment ArrayList를 선언
	public MyPagerAdapter(FragmentManager manager) {} // Fragment manager을 받는 생성자 호출
	public int getCount();
	public Fragment getItem(int position) {} // 내부적으로 Fragment Transaction 실행하므로 따로 선언필요 없음
}