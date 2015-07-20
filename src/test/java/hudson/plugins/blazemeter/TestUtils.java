package hudson.plugins.blazemeter;

import hudson.plugins.blazemeter.api.TestType;
import hudson.plugins.blazemeter.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zmicer on 17.7.15.
 */
public class TestUtils {
    public String testId_http="429381.http";
    public String testId_jmeter="429381.jmeter";
    public String testId_followme="429381.followme";
    public String testId_unkown_type="429381.cvbhgy";
    public String testId_multi="429381.multi";

    @Test
    public void getTestType(){
        Assert.assertEquals(TestType.http, Utils.getTestType(testId_http));
        Assert.assertEquals(TestType.jmeter, Utils.getTestType(testId_jmeter));
        Assert.assertEquals(TestType.followme, Utils.getTestType(testId_followme));
        Assert.assertEquals(TestType.unknown_type, Utils.getTestType(testId_unkown_type));
        Assert.assertEquals(TestType.multi, Utils.getTestType(testId_multi));

    }

    @Test
    public void getTestId(){
        Assert.assertEquals("12345", Utils.getTestId("12345.2345"));
        Assert.assertEquals("123452345", Utils.getTestId("123452345"));

    }
}
