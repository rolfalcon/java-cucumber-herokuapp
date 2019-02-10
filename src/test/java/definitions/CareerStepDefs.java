package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import pages.*;
import support.RestWrapper;
import support.TestContext;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;


public class CareerStepDefs {
    @Given("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String arg0) throws Throwable {
        new LandingPage().open();
    }

    @And("^I login as \"([^\"]*)\"$")
    public void iLoginAs(String role) throws Throwable {

        HashMap<String, String> user = getData(role);
        LandingPage landingPage = new LandingPage();
        landingPage.clickLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.assertHeader();
        loginPage.login(user.get("email"), user.get("password"));



    }

    @Then("^I verify \"([^\"]*)\" login$")
    public void iVerifyLogin(String role) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        HashMap<String, String> user = getData(role);

        String actualName = new LandingPage().getNameofLoggedUser();
        assertThat(actualName).isEqualTo(user.get("name"));

    }

    @When("^I create new position$")
    public void iCreateNewPosition() throws Exception {
        new LandingPage().clickRecurit();
        HashMap<String, String> position = getPostion();

        Recruiter recruiter = new Recruiter();
        recruiter.assertHeader();
        recruiter.clickNewPostion();

        String title = position.get("title");
        title = TestContext.addTimestamp(title);
        position.put("title", title);

        NewPositionPage newPositionPage = new NewPositionPage();

        TestContext.setTestData("positionTitle", title);
        position.put("title", title);
        newPositionPage.fillPosition(position);
        newPositionPage.clickSubmit();


    }

    @And("^I verify position created$")
    public void iVerifyPositionCreated() throws Exception {

        boolean isPresent = new Recruiter().isPositionPresentOnPage(TestContext.getTestData("positionTitle"));
        assertThat(isPresent).isTrue();
    }

    @And("^I apply to a new position$")
    public void iApplyToANewPosition() throws Throwable {

        String newPostion = new LandingPage().clickNewPosition();

        setTestData("jobId", newPostion);

        Apply apply = new Apply();
        apply.clickApply();

        HashMap <String, String> applicant = getCandidate();
        //String email = applicant.get("email");
        String email = TestContext.addTimestampEmail();
        applicant.put("email", email);
        setTestData("candidateEmail", email);

        apply.fillCandidate(applicant);
        apply.clickSubmit();

    }

    @And("^I see position in my jobs$")
    public void iSeePositionInMyJobs() throws Throwable{
        String job = getTestData("jobId");

        boolean isJobThere = new MyJobs().isJobThere(job);
        assertThat(isJobThere).isTrue();


    }

    @Then("^I verify profile is created$")
    public void iVerifyProfileIsCreated() throws Throwable {
        HashMap<String, String> user = getCandidate();
        String firstLastName = user.get("firstName") + " " + user.get("lastName");

        String actualName = new MyJobs().getNameofLoggedUser();
        assertThat(actualName).isEqualTo(firstLastName);
    }

    @When("^I apply for a new job$")
    public void iApplyForANewJob() throws Throwable {
        //li[not(contains(@class, 'li-selected'))]
        String newJob = new Candidate().clickNewPostionCandidate();
        setTestData("jobId", newJob);



    }

    @Then("^I see position marked as applied$")
    public void iSeePositionMarkedAsApplied() throws Throwable {

        String appliedNewJob = getTestData("jobId");
        boolean marked = new Candidate().isJobMarked(appliedNewJob);

        assertThat(marked).isTrue();


    }

    @And("^I apply to a new position using RestAPI$")
    public void iApplyToANewPositionUsingRestAPI() throws Throwable{
        HashMap<String, String> recruiter = TestContext.getData("recruiter");
        RestWrapper restWrapper = new RestWrapper();

        restWrapper.login(recruiter);

        restWrapper.createPosition(TestContext.getPostion());

        setTestData("jobId", restWrapper.getJobID());

        getDriver().navigate().refresh();

        new LandingPage().clickPositionfromID(restWrapper.getJobID());
        Apply apply = new Apply();
        apply.clickApply();

        HashMap <String, String> applicant = getCandidate();
        //String email = applicant.get("email");
        String email = TestContext.addTimestampEmail();
        applicant.put("email", email);
        setTestData("candidateEmail", email);

        apply.fillCandidate(applicant);
        apply.clickSubmit();

    }

    @And("^I see position in my jobs using RestAPI$")
    public void iSeePositionInMyJobsUsingRestAPI() throws Throwable{
        String job = getTestData("jobId");

        boolean isJobThere = new MyJobs().isJobThere(job);
        assertThat(isJobThere).isTrue();

        //Clean up
        RestWrapper restWrapper = new RestWrapper();
        HashMap<String, String> recruiter = TestContext.getData("recruiter");
        restWrapper.login(recruiter);
        restWrapper.deletePosition(job);

    }
}
