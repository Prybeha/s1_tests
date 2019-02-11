package SupportClasses.TestLink;

import org.apache.log4j.Logger;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class TestLinkResult {
    private String TestLink_KEY = "b6b4dca82724b8b027b196c967472932";
    private String TestLink_URL = "http://localhost/testlink-1.9.18/lib/api/xmlrpc/v1/xmlrpc.php";
    private String Project_NAME = "ProjectForManual";
    private String Plan_NAME = "TestPlanForManual";
    private String Build_NAME = "BuildForManual";

    private static final Logger log = Logger.getLogger(TestLinkResult.class);

    private String result = null;
    private String exception = null;

    public void TestLinkFail(String TestCaseName, String e) throws Exception{
        result = TestLinkAPIResults.TEST_FAILED;
        exception = e;
        log.error("Test case " + TestCaseName + " failed!");
        updateTestLinkResult(TestCaseName, exception, result);
    }

    public void TestLinkPass(String TestCaseName) throws Exception{
        result = TestLinkAPIResults.TEST_PASSED;
        log.debug("Test case " + TestCaseName + "  passed");
        updateTestLinkResult(TestCaseName, exception, result);
    }

    private void updateTestLinkResult(String testCase, String exception, String result) throws TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(TestLink_KEY, TestLink_URL);
        testlinkAPIClient.reportTestCaseResult(Project_NAME, Plan_NAME, testCase, Build_NAME, exception, result);
    }
}