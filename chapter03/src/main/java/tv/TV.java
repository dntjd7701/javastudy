package tv;

public class TV {

	private int channel; // 1~255
	private int volume; // 0~100
	private boolean power;

	// 조건

	// 초기화 생성자 생성
	public TV() {

	}

	public TV(int ch, int vol, boolean visible) {

		channel = ch;
		volume = vol;
		power = visible;

	}

	// getter로만 설정
	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}

	// setter, getter 만들지 말구

	// true 면 켜지
	public void power(boolean on) {
		if (on == true) {
			power = true;
		} else {
			power = false;
		}

	}

	// 채널 업 다운만.
	public void channel(boolean up) {
		if (up == true) {
			channel++;
		} else {
			channel--;
		}
	}

	// TV가 on일 때만 작
	public void channel(int ch) {
		if(power == false) {
			return;
		}
		
		if(ch < 1) {
			channel = 255;
		}else if(channel > 255){
			channel = 1;
		}
		channel = ch;
	}

	public void volumn(int vol) {
		if(power == false) {
			return;
		}
		
		if(vol < 0) {
			volume = 100;
		}else if(volume > 100){
			volume = 0;
		}
		volume = vol;
	}

	public void volumn(boolean up) {
		if (up == true) {
			volume++;
		} else {
			volume--;
		}
	}

	public void status() {
		System.out.println("TV[power=" + power + ", " + "channel=" + channel + ", " + "volume=" + volume + "]");

	}

}
