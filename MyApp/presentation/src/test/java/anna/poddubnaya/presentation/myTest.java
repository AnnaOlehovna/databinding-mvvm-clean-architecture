package anna.poddubnaya.presentation;


import org.junit.Assert;
import org.junit.Test;

public class myTest {

    @Test
    public void test(){
        int a = plus(2,2);

        Assert.assertEquals(4,a);
    }

    public int plus(int a, int b){
        return a+b;
    }
}
