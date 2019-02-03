package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class Apply extends Header{

    @FindBy (xpath="//button[@type='submit']")
    private WebElement apply;

    @FindBy (xpath="//*[@for='candidateFirstName']/../input")
    private WebElement firstName;

    @FindBy (xpath="//*[@for='candidateLastName']/../input")
    private WebElement lastName;

    @FindBy (xpath="//*[@for='candidateEmail']/../input")
    private WebElement email;

    @FindBy (xpath="//*[@for='candidatePassword']/../input")
    private WebElement password;

    @FindBy (xpath="//*[@for='candidateConfirmPassword']/../input")
    private WebElement confirmPassword;

    @FindBy (xpath="//*[@for='candidateAddress']/../input")
    private WebElement address;

    @FindBy (xpath="//*[@for='candidateSummary']/../textarea")
    private WebElement summary;

    @FindBy (xpath="//*[@for='candidateState']/../select")
    private WebElement state;

    @FindBy (xpath="//*[@for='candidateZip']/../input")
    private WebElement zipCode;

    @FindBy (xpath="//*[@for='candidateCity']/../input")
    private WebElement city;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement submitButton;


    public Apply() {
        setHeaderText("Apply");
    }

    public void clickApply () {
        click(apply);
    }

    public void fillCandidate(HashMap<String, String> candidate) {

        sendKeys(firstName, candidate.get("firstName"));
        sendKeys(lastName, candidate.get("lastName"));
        sendKeys(email, candidate.get("email"));
        sendKeys(password, candidate.get("password"));
        sendKeys(confirmPassword, candidate.get("password"));
        sendKeys(address, candidate.get("address"));
        sendKeys(city, candidate.get("city"));
        sendKeys(summary, candidate.get("summary"));
        sendKeys(zipCode, candidate.get("zipCode"));
        new Select(state).selectByValue(candidate.get("state"));

    }

    public void clickSubmit() {
        click(submitButton);
    }
}
