package hudson.plugins.blazemeter;

import hudson.plugins.blazemeter.api.ApiVersion;
import hudson.plugins.blazemeter.api.urlmanager.BmUrlManager;
import hudson.plugins.blazemeter.api.urlmanager.BmUrlManagerV2Impl;
import hudson.plugins.blazemeter.api.urlmanager.BmUrlManagerV3Impl;
import hudson.plugins.blazemeter.api.urlmanager.UrlManagerFactory;
import hudson.plugins.blazemeter.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dzmitrykashlach on 9/01/15.
 */
public class TestUrlManagerFactory {
    private BmUrlManager bmUrlManager=null;

    @Test
    public void getUrlManager(){
        bmUrlManager=UrlManagerFactory.getURLManager(ApiVersion.v2, TestConstants.mockedApiUrl);
        Assert.assertTrue(bmUrlManager instanceof BmUrlManagerV2Impl);
        Assert.assertTrue(bmUrlManager.getServerUrl().equals(TestConstants.mockedApiUrl));

        bmUrlManager=UrlManagerFactory.getURLManager(ApiVersion.v3, TestConstants.mockedApiUrl);
        Assert.assertTrue(bmUrlManager instanceof BmUrlManagerV3Impl);
        Assert.assertTrue(bmUrlManager.getServerUrl().equals(TestConstants.mockedApiUrl));
    }
}
