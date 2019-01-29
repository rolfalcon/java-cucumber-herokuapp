package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SampleForm;
import pages.SampleResult;
import support.TestContext;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class FormStepDefs {
    @Given("^I open sample page$")
    public void iOpenSamplePage() {
        new SampleForm().open();
    }

    @When("^I fill out sample fields$")
    public void iFillOutSampleFields() throws Exception {
        SampleForm form = new SampleForm();
        HashMap<String, String> data = TestContext.getSample();

        form.fillUsername(data.get("username"));
        form.fillEmail(data.get("email"));
        form.fillPassword(data.get("password"));
        assertThat(form.isConfirmPasswordDisabled()).isFalse();
        form.fillConfirmPassword(data.get("password"));
        form.fillName(data.get("firstName"), data.get("lastName"));
        form.clickPrivacyPolicy();
    }

    @And("^I submit the sample form$")
    public void iSubmitTheSampleForm() {
        new SampleForm().clickSubmit();
    }

    @Then("^I verify all fields$")
    public void iVerifyAllFields() throws Exception {
        SampleResult result = new SampleResult();
        HashMap<String, String> data = TestContext.getSample();

        String resultText = result.getResult();
        assertThat(resultText).contains(data.get("username"));
        assertThat(resultText).contains(data.get("email"));
        assertThat(resultText).contains(data.get("firstName"));
        assertThat(resultText).contains(data.get("lastName"));

        assertThat(result.getPassword()).doesNotContain(data.get("password"));
        assertThat(result.getPrivacyPolicy()).isEqualTo("true");
    }
}
