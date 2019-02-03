package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static support.TestContext.getDriver;

public class Candidate extends Header{

    //@FindBy(xpath="//li[not(contains(@class, 'li-selected'))]")
    @FindBy (xpath = "//li[not(contains(@class, 'li-selected'))][contains(@class, 'list-item')]")
    private List<WebElement> jobsListedNotApplied;

    @FindBy (xpath="//li[contains(@class, 'li-selected')]")
    private List<WebElement> jobsListedApplied;



    public Candidate () {
        setHeaderText("Careers");

    }

    public String clickNewPostionCandidate() throws Throwable{
        WebElement job = jobsListedNotApplied.get(jobsListedNotApplied.size()-1);

        String jobId = job.getAttribute("id");
        Actions actions = new Actions (getDriver());
        actions.moveToElement(job).perform();
        actions.click(job).perform();

        String buttonXpath = "//*[@id='" + jobId + "']//button/i";
        WebElement checkJob = job.findElement(By.xpath(buttonXpath));
        actions.moveToElement(checkJob).perform();
        click(checkJob);

        return jobId;


    }

    public boolean isJobMarked (String value) {
        String xpath = "//li[@id='"+ value + "'][contains(@class, 'li-selected')]";
        return !getDriver().findElements(By.xpath(xpath)).isEmpty();

    }


}
