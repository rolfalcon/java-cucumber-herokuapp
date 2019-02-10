package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getPostion;

public class Header extends Page {

    private String headerText;

    @FindBy(xpath="//span[contains(@class,'position-center')]")
    private WebElement headerTitle;

    @FindBy(xpath="//a[@href='/login']")
    private WebElement login;

    @FindBy(xpath="//a[@href='/recruit']")
    private WebElement recruit;

    @FindBy(xpath = "//a[@href='/new_candidate']")
    private WebElement apply;


    @FindBy(xpath = "//a[@href='/my_jobs']")
    private WebElement myJobs;

    @FindBy(xpath="//span[@class='logout-box']/a")
    private WebElement name;

 //   @FindBy(xpath="//a[contains(@href, 'positions')]")
    @FindBy(xpath = "//li" )
    private List<WebElement> listPostions;

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public void clickLogin() {
        click(login);
    }

    public void clickRecurit () {
        click(recruit);
    }

    public void clickApply () {
        click(apply);
    }

    public void clickJobs () { click(myJobs);}

    public String clickNewPosition () {
        WebElement newPostion = listPostions.get(listPostions.size()-1);

        String id = newPostion.getAttribute("id");
        String idHref = "//*[@id='" + id + "']//a[@href]";

        WebElement href = newPostion.findElement(By.xpath(idHref));
        click(href);

        return id;
    }

    public void clickPositionfromID (String id) {
        String xpath = "//a[@href='/positions/"+ id + "']";
        WebElement positionId =getDriver().findElement(By.xpath(xpath));

        click(positionId);

    }

    public void assertHeader () {
        assertThat(headerTitle.getText().equals(headerText)).isTrue();
    }

    public String getNameofLoggedUser () {
        return name.getText();
    }
}
