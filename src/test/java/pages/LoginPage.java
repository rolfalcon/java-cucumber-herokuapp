package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sun.rmi.runtime.Log;

public class LoginPage extends Header {
    @FindBy (xpath="//label[@for='loginUsername']/../input")
    private WebElement userName;

    @FindBy (xpath = "//label[@for='loginPassword']/../input")
    private WebElement password;

    @FindBy (xpath = "//button[@id='loginButton']")
    private WebElement submitButton;

    public LoginPage () {
        setHeaderText("Login");
    }

    public void login(String user, String password) {
        sendKeys(userName, user);
        sendKeys(this.password, password);
        click(submitButton);

    }




}
