1. Custom Adapter 작성방법
 1) VO 클래스를 작성한다. --> 한항목에 여러 데이터가 들어간다면 데이터를 추상화한 클래스를 작성
 2) DriveAdapter 작성 --> BaseAdapter / ArrayAdapter / SimpleAdapter 를 상속받아야한다.
	public class DriveAdapter extends ArrayAdapter<DriveVO> {
		Context context;
		int resId;		// 항목 layout xml 정보
		ArrayList<DriveVO> datas;

		//생성자
		public DriveAdapter(Context context, int resId, ArrayList<DriveVO> datas) {
			//..
		}

		//Override 메소드
		public int getCount() --> return datas.size();

 		public View getView(int position, View convertView, ViewGroup parent){
			-->한항목을 구성하기 위해 호출 --> data가 10개라면 10번 호출!
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
			convertView = inflater.inflate(resId, null);

			//findViewId 이용하여 View 획득
			...
			//항목 데이터 획득
			final DdriveVO vo = datas.get(position);

			//View에 데이터 설정
			titleView.setText(...);
			dateView.setText(...);
			//개발자 알고리즘으로 데이터설정
			if(vo.type.equals("doc") ...
			else if(vo.type.equals("file")...
			else if(vo.type.equals("img")...
			
			//view 에 이벤트 연결
			menuimageView.setOnclickListener(new View.OnClickListener() {
			//...
			}
		return convertView;
	}


 3) DriveHolder 작성 --> findViewId 는 메모리 소비가 많이 되기때문에 초기에 한번 호출하기 위한 Holder 클래스 작성
 	public class DriveHolder {
		public DriveHolder(View root) {
			findVIewById...
		}

 4) MainActivity에 DriveAdapter 호출하여 setAdapter로 adapter 적용.


 