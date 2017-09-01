package mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/31
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */

public class MockTest {

//    public static void main(String[] args) {
//        List listMock = mock(List.class);
//
//    }

    @Test
    public void testMock1() {

        List listMock = Mockito.mock(List.class);
        Assert.assertTrue(listMock instanceof List);

        when(listMock.add("one")).thenReturn(true);
        when(listMock.size()).thenReturn(1);


        Assert.assertTrue(listMock.add("one"));
        Assert.assertTrue(listMock.add("two"));
    }


}
