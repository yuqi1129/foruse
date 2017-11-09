package mokito;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.Serializable;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Map;

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

    @org.junit.Test
    public void test3() {
        ForTest forTest = mock(ForTest.class);
        when(forTest.hehe(Matchers.eq(new MokitoTestOne()))).thenReturn(1);
        when(forTest.ar(Matchers.eq(new Serializable[][] {{1}}))).thenReturn(4);
        System.out.println(forTest.hehe(new MokitoTestOne()));
        System.out.println(forTest.ar(new Serializable[][]{{1}}));
    }

    public static class ForTest {
        public Integer hehe(MokitoTestOne a) {
            return 2;
        }

        public Integer ar(Serializable[][] serializables) {
            return 3;
        }

        public String ma(Map<String, byte[]> map) {
            return "0";
        }

        public Integer st(String a) {
            return 10;
        }

    }

    @Test
    public void test4() {
        ForTest forTest = mock(ForTest.class);
        Map<String, byte[]> map = Maps.newHashMap();
        map.put("test", "test1".getBytes());

        Map<String, byte[]> realMap = Maps.newHashMap();
        realMap.put("test", "test1".getBytes());

        Map<String, String> option = Maps.newHashMap();
        option.put("[test=test1,]", "nice");

        when(forTest.ma(any())).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Map<String, byte[]> map = (Map<String, byte[]>) invocation.getArgument(0);
                String mapKey = map2String(map);
                if (option.containsKey(mapKey)) {
                    return option.get(mapKey);
                } else {
                    return "xx";
                }
            }
        });

        System.out.println(forTest.ma(realMap));

    }

    public static String map2String(Map<String, byte[]> map) {
        StringBuilder b = new StringBuilder("[");
        for (Map.Entry<String, byte[]> entry : map.entrySet()) {
            b.append(entry.getKey()).append("=").append(new String(entry.getValue())).append(",");
        }

        b.append("]");
        return b.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MokitoTestOne{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof MokitoTestOne)) {
            return false;
        }

        return this.toString().equals(((MokitoTestOne) obj).toString());
    }
}
