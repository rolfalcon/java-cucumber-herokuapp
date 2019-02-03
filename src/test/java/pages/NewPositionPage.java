package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class NewPositionPage extends Header {

    @FindBy(xpath = "//label[@for='positionTitle']/../input")
    private WebElement title;

    @FindBy (xpath = "//label[@for='positionDescription']/../textarea")
    private WebElement description;

    @FindBy (xpath = "//label[@for='positionAddress']/../input")
    private WebElement address;

    @FindBy (xpath = "//label[@for='positionCity']/../input")
    private WebElement city;


    @FindBy (xpath = "//label[@for='positionState']/../select")
    private WebElement state;

    @FindBy (xpath = "//label[@for='positionZip']/../input")
    private WebElement zip;

    @FindBy (xpath = "//*[@id='positionDateOpen']")
    private WebElement date;

    @FindBy (xpath = "//*[@id='positionSubmit']")
    private WebElement submitButton;




    public void fillPosition(HashMap<String, String> position) {
        sendKeys(title, position.get("title"));
        sendKeys(description, position.get("description"));
        sendKeys(address, position.get("address"));
        sendKeys(city, position.get("city"));
        //sendKeys(state, position.get("state"));

        new Select(state).selectByValue(position.get("state"));
        sendKeys(zip, position.get("zip"));
        sendKeys(date, position.get("dateOpen"));

    }

    public void clickSubmit () {
        click(submitButton);
    }





}
