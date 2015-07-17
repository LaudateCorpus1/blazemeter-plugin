package hudson.plugins.blazemeter;

import com.google.common.collect.LinkedHashMultimap;
import hudson.plugins.blazemeter.api.APIFactory;
import hudson.plugins.blazemeter.api.ApiVersion;
import hudson.plugins.blazemeter.api.BlazemeterApiV3Impl;
import hudson.plugins.blazemeter.api.TestType;
import hudson.plugins.blazemeter.entities.TestStatus;
import hudson.plugins.blazemeter.utils.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by dzmitrykashlach on 12/01/15.
 */
public class TestBlazemeterApiV3Impl {
    private BlazemeterApiV3Impl blazemeterApiV3 =null;


    @BeforeClass
    public static void setUp()throws IOException{
        MockedAPI.startAPI();
        MockedAPI.userProfile();
        MockedAPI.getSessionStatus();
        MockedAPI.getTests();
        MockedAPI.getTestReport();
        MockedAPI.startTest();
    }

    @AfterClass
    public static void tearDown()throws IOException{
        MockedAPI.stopAPI();
    }


    @Test
    public void createTest_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3, TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.createTest(null), null);
    }

    @Test
    public void retrieveJUNITXML_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.retrieveJUNITXML(null), null);
    }


    @Test
    public void getTresholds_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.getTresholds(null), null);
    }


    @Test
    public void updateTestInfo_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.postJsonConfig(null, null), null);
    }

    @Test
    public void getTestInfo_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.getTestConfig(null), null);
    }

    @Ignore("Broken during cherry-pick action")
    @Test
    public void getTestStatus_Running(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,ApiVersion.v3,
                TestConstants.mockedApiUrl);
        TestStatus testStatus=blazemeterApiV3.getTestStatus(TestConstants.TEST_SESSION_100);
        Assert.assertEquals(testStatus, TestStatus.Running);
    }

    @Ignore("Broken during cherry-pick action")
    @Test
    public void getTestInfo_NotRunning(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,ApiVersion.v3,
                TestConstants.mockedApiUrl);
        TestStatus testStatus=blazemeterApiV3.getTestStatus(TestConstants.TEST_SESSION_140);
        Assert.assertEquals(testStatus, TestStatus.NotRunning);
    }


    @Test
    public void getTestInfo_Error(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,ApiVersion.v3,
                TestConstants.mockedApiUrl);
        TestStatus testStatus=blazemeterApiV3.getTestStatus(TestConstants.TEST_SESSION_NOT_FOUND);
        Assert.assertEquals(testStatus, TestStatus.Error);
    }

    @Test
    public void getTestInfo_NotFound(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI("",ApiVersion.v3,
                TestConstants.mockedApiUrl);
        TestStatus testStatus=blazemeterApiV3.getTestStatus("");
        Assert.assertEquals(testStatus, TestStatus.NotFound);
    }



    @Test
    public void getUser_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.getUser(), null);
    }

    @Test
    public void getTestCount_zero(){
        try {
            blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null, ApiVersion.v3,TestConstants.mockedApiUrl);
            Assert.assertEquals(blazemeterApiV3.getTestCount(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testReport_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.testReport(null), null);
    }

   @Test
    public void stopTest_null(){
       blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
       Assert.assertEquals(blazemeterApiV3.stopTest(null), null);
    }

   @Test
    public void startTest_null() throws JSONException{
       blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,null);
       Assert.assertEquals(blazemeterApiV3.startTest(null,null), null);
    }

    @Test
    public void startTest_http() throws JSONException{
       blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,ApiVersion.v3,TestConstants.mockedApiUrl);
       Assert.assertEquals(blazemeterApiV3.startTest(TestConstants.TEST_SESSION_ID, TestType.http), "15102806");
    }

    @Test
    public void startTest_jmeter() throws JSONException{
       blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,ApiVersion.v3,TestConstants.mockedApiUrl);
       Assert.assertEquals(blazemeterApiV3.startTest(TestConstants.TEST_SESSION_ID, TestType.jmeter), "15102806");
    }

    @Test
    public void startTest_followme() throws JSONException{
       blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,ApiVersion.v3,TestConstants.mockedApiUrl);
       Assert.assertEquals(blazemeterApiV3.startTest(TestConstants.TEST_SESSION_ID, TestType.followme), "15102806");
    }

    @Test
    public void startTest_multi() throws JSONException{
       blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,ApiVersion.v3,TestConstants.mockedApiUrl);
       Assert.assertEquals(blazemeterApiV3.startTest(TestConstants.TEST_SESSION_ID,TestType.multi), "15105877");
    }


   @Test
    public void getTestRunStatus_notFound(){
       blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
       Assert.assertEquals(blazemeterApiV3.getTestStatus(null), TestStatus.NotFound);
    }

    @Test
    public void uploadBinaryFile_null(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);
        Assert.assertEquals(blazemeterApiV3.uploadBinaryFile(null, null), null);
    }


    @Test
    public void getTestList_6_10() throws IOException,JSONException,ServletException,MessagingException{
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        LinkedHashMultimap<String,String> testList=blazemeterApiV3.getTestsMultiMap();
        Assert.assertTrue(testList.asMap().size()==6);
        Assert.assertTrue(testList.size()==10);

    }

    @Test
    public void getTestList_6_6() throws IOException,JSONException,ServletException,MessagingException{
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_6_TESTS,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        LinkedHashMultimap<String,String> testList=blazemeterApiV3.getTestsMultiMap();
        Assert.assertTrue(testList.asMap().size()==6);
        Assert.assertTrue(testList.size()==6);

    }

    @Ignore("Broken while cherry-pick action")
    @Test
    public void getTestReport(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        JSONObject testReport=blazemeterApiV3.testReport(TestConstants.TEST_SESSION_ID);
        Assert.assertTrue(testReport.length()==33);


    }

    @Test
    public void getTestList_null() throws IOException,JSONException,ServletException,MessagingException{
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_EXCEPTION,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        LinkedHashMultimap<String,String> testList=blazemeterApiV3.getTestsMultiMap();
        Assert.assertTrue(testList==null);

    }

@Test
    public void getTestsCount_10() throws IOException,JSONException,ServletException{
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int count=blazemeterApiV3.getTestCount();
        Assert.assertTrue(count==10);

    }

    @Test
    public void getTestsCount_1() throws IOException,JSONException,ServletException{
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_1_TEST,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int count=blazemeterApiV3.getTestCount();
        Assert.assertTrue(count==1);

    }

    @Test
    public void getTestsCount_0() throws IOException,JSONException,ServletException{
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_0_TESTS,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int count=blazemeterApiV3.getTestCount();
        Assert.assertTrue(count==0);

    }

@Test
    public void getTestsCount_null() throws IOException,JSONException,ServletException{
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_INVALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int count=blazemeterApiV3.getTestCount();
        Assert.assertTrue(count == -1);

    }

    @Ignore("Broken during cherry-pick action")
    @Test
    public void getTestSessionStatusCode_25(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int status=blazemeterApiV3.getTestMasterStatusCode(TestConstants.TEST_SESSION_25);
        Assert.assertTrue(status==25);
    }

    @Ignore("Broken during cherry-pick action")
    @Test
    public void getTestSessionStatusCode_70(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int status=blazemeterApiV3.getTestMasterStatusCode(TestConstants.TEST_SESSION_70);
        Assert.assertTrue(status==70);
    }

    @Ignore("Broken during cherry-pick action")
    @Test
    public void getTestSessionStatusCode_140(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int status=blazemeterApiV3.getTestMasterStatusCode(TestConstants.TEST_SESSION_140);
        Assert.assertTrue(status==140);
    }

    @Ignore("Broken during cherry-pick action")
    @Test
    public void getTestSessionStatusCode_100(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_VALID,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int status=blazemeterApiV3.getTestMasterStatusCode(TestConstants.TEST_SESSION_100);
        Assert.assertTrue(status==100);
    }

    @Test
    public void getTestSessionStatusCode_0(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(TestConstants.MOCKED_USER_KEY_EXCEPTION,
                ApiVersion.v3,TestConstants.mockedApiUrl);
        int status=blazemeterApiV3.getTestMasterStatusCode(TestConstants.TEST_SESSION_0);
        Assert.assertTrue(status==0);
    }



    @Ignore("Incomplete")
    @Test
    public void putTestInfo(){
        blazemeterApiV3=(BlazemeterApiV3Impl)APIFactory.getAPI(null,ApiVersion.v3,TestConstants.mockedApiUrl);

    }
}