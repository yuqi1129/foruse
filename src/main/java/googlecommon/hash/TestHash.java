package googlecommon.hash;

import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.util.Iterator;
import java.util.Set;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/27
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */

public class TestHash {

    public static void main(String [] args){
        HashCode hashCode = Hashing.sha256().hashBytes("test".getBytes());

        System.out.println(hashCode.asInt());

        Set<Integer> set1 = Sets.newHashSet(1,2,3);
        Set<Integer> set2 = Sets.newHashSet(1,2,5);

        set1.retainAll(set2);

        Iterator<Integer> integerIterator = set1.iterator();
        while(integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }



    }

}
