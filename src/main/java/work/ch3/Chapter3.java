package work.ch3;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/17 下午7:45
 */

import work.common.Tree;

public class Chapter3 {
	public static void main(String[] args) {

	}




	public static boolean HasTree(Tree big, Tree small) {
		if (small == null) {
			return true;
		}

		if (small != null && big == null) {
			return false;
		}

		if (big.data == small.data) {
			return HasTree(big.left, big.left) && HasTree(big.right, big.right);
		} else {
			return false;
		}
	}

	public static boolean HasChild(Tree big, Tree small) {
		if (small == null) {
			return true;
		}

		if (big == null && small != null) {
			return false;
		}

		if (HasTree(big, small)) {
			return true;
		} else {
			return HasChild(big.left, small) && HasChild(big.right, small);
		}
	}

}
