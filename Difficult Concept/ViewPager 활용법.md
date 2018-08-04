ViewPager Ȱ���ϱ�

1. ViewPager�� XML�� �����ϱ�
 <android.support.v4.view.ViewPager
	android.id = "@+id/pager"
	android:layout_width = "match_parent"
	android:layout_height = "match_parent" />

2. ViewPager�� Adapter�� �����̹Ƿ� Adapter�� ����������Ѵ�.
 Adapter�� �����ش� �⺻ PagerAdapter�� Fragment�� �����ϱ� ���� FragmentPagerAdapter�� �ִ�

1) �⺻ PagerAdapter ��ӹ������
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
	//�����ǿ� ���� �´� �׸��� ������ �並 �غ��Ͽ� container �� addView�Լ��� �߰������ش�. 
	
	3. public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeItem((View)object);
	}
	//�䰴ü �������� �Ҹ��Ű�� ����
	
	4. void isViewFromObject(View arg0, Object arg1) {}
}


2) Fragment ���������� ���� FragmentPagerAdapter�� ��ӹ޴� ���
class MyPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> fragments; // Fragment ArrayList�� ����
	public MyPagerAdapter(FragmentManager manager) {} // Fragment manager�� �޴� ������ ȣ��
	public int getCount();
	public Fragment getItem(int position) {} // ���������� Fragment Transaction �����ϹǷ� ���� �����ʿ� ����
}