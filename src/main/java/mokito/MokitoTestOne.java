package mokito;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/25
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */

public class MokitoTestOne {

    public String voice() {
        System.out.print("hehe");
        return "haha";
    }


    @Test
    public void test1() {
        MokitoTestOne mokitoTestOne = mock(MokitoTestOne.class);
        when(mokitoTestOne.voice()).thenReturn("nice");

        System.out.println(mokitoTestOne.voice());
        //verify(mokitoTestOne).voice();
    }

    @Test
    public void test2() {
        MokitoTestOne spy = spy(new MokitoTestOne());
        when(spy.voice()).thenReturn("1");
        //doReturn("nice").when(spy).voice();
        System.out.println(spy.voice());

        verify(spy, times(1)).voice();
    }

    public static void main(String[] args) {

    }

}
