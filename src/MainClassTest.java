import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetLocalNumber()
    {
        int a = this.getLocalNumber();
        Assert.assertTrue("Number is not 14",a == 14);
    }
}