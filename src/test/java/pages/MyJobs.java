package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

import static support.TestContext.getDriver;

public class MyJobs extends Header {

    public MyJobs () {
        setHeaderText("My Jobs");
    }

    public List getJobsListed(String value) {
        String xpath = "//*[@id='" + value + "']";
        return getDriver().findElements(By.xpath(xpath));
    }

    public boolean isJobThere (String value) {
        return !getJobsListed(value).isEmpty();
    }
}
