import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetClassString(){
        String a = this.getClassString();
        String b = "hello";
        String c = "Hello";

        if (a.contains(b)){
            Assert.assertTrue(true);
        } else if (a.contains(c)){
            Assert.assertTrue(true);
        } else {
            Assert.fail("No hello word");
        }
    }
}
