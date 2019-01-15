package git.example.accessprivate;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/15 下午4:18
 */

import java.lang.reflect.Field;

public class AccessPrivateFiled extends Parent {

	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	private int getParentField() {
		try {
			Field file = this.getClass().getSuperclass().getDeclaredField("number");
			file.setAccessible(true);
			return (int) file.get(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}


	public static void main(String[] args) {
		AccessPrivateFiled accessPrivateFiled = new AccessPrivateFiled();
		accessPrivateFiled.number = accessPrivateFiled.getParentField();
		//expect to be 1
		System.out.println(accessPrivateFiled.number);
	}
}
