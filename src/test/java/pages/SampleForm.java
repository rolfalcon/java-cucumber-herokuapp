package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class SampleForm extends Page {

    public SampleForm() {
        setUrl("https://skryabin.com/webdriver/html/sample.html");
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private WebElement saveButton;


    @FindBy(xpath = "//input[@name='agreedToPrivacyPolicy']")
    private WebElement privacyPolicy;


    public WebElement clickGetDate(String date) {
        return getDriver().findElement(By.xpath("//input[text()='" + date + "']"));
    }


    public void fillUsername(String value) {
        sendKeys(username, value);
    }

    public void fillEmail(String value) {
        sendKeys(email, value);
    }

    public void fillPassword(String value) {
        sendKeys(password, value);
    }

    public void fillConfirmPassword(String value) {
        sendKeys(confirmPassword, value);
    }

    public void fillName(String firstName, String lastName) {
        click(name);
        sendKeys(this.firstName, firstName);
        sendKeys(this.lastName, lastName);
        click(saveButton);
    }

    public void clickPrivacyPolicy() {
       click(privacyPolicy);
    }

    public void clickSubmit() {
        click(submitButton);
    }

    public boolean isConfirmPasswordDisabled() {
        return !confirmPassword.isEnabled();
    }




}
