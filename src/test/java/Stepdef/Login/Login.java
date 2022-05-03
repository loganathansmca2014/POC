package Stepdef.Login;


import BrowserAccess.BrowserControl;
import BusinessLogic.*;
import Util.ExcelReader;
import Util.ExcelReaderFillo;
import Util.GlobalFunction;
import Util.LoadProperties;
import com.codoid.products.exception.FilloException;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import java.io.IOException;
import java.util.HashMap;

public class Login {


  int Rowno;
  String user_Name,pass;
  public static Login INSTANCE = new Login();
  public static HashMap<String, String> dict_login;


  @Given("^The 'User' launches the application$")
  public void theUserLaunchesTheApplication() {
    BrowserControl.getInstance().openBrowser();
  }

  @When("^The 'User' enters the username and password in the \"([^\"]*)\"$")
  public void theUserEntersTheUsernameAndPasswordInThe(int rowno) throws Throwable {
    Rowno = rowno;
  }

  @And("^The 'User' clicks on Log In Button$")
  public void theUserClicksOnLogInButton() throws IOException {
    String sheetPath = ExcelReader.getInstance().getTestdatafilepath();

    LoadProperties.getInstance().queryProperty();
    String strQuery=LoadProperties.strquery_Login;
    dict_login = ExcelReaderFillo.getInstance().getExcelData(sheetPath, strQuery);
    user_Name= dict_login.get("userName"+Rowno);
    pass=dict_login.get("password"+Rowno);
    BussinessFun.getInstance().loginAuthentication(user_Name,pass);
  }

  @And("^The 'User' clicks order items$")
  public void theUserClicksOrderItems() {
    BussinessFun.getInstance().validateOerdeItms();
  }

  @And("^'User' Select lastest Order and view details$")
  public void userSelectLastestOrderAndViewDetails() {
    BussinessFun.getInstance().userSelectLastestOrderAndViewDetails();
  }

  @Then("^'User' Get the information$")
  public void userGetTheInformation() {
    BussinessFun.getInstance().userGetTheInformation();
  }


  @SuppressWarnings("CucumberJavaStepDefClassIsPublic")
  private static class LoginScenario extends GlobalFunction {


  }
  public static Login getInstance() {
    return INSTANCE;
  }
}