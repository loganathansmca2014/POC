package BusinessLogic;

import PageLocators.HomePageLocators;
import PageLocators.LoginPageLocators;


import PageLocators.OrderPageLocators;
import Util.*;
import com.codoid.products.exception.FilloException;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class BussinessFun extends ReusableFunction {

  public static BussinessFun INSTANCE = new BussinessFun();

  public static HashMap<String, String> dict_login;
  String strQuery;


  //Create instance for Resuable function
  private static final Logger logger = LogManager.getLogger(BussinessFun.class.getName());
  private int dataRowno;


  public void loginAuthentication(String username, String pass) throws IOException {
    PageFactory.initElements(GlobalFunction.driver, LoginPageLocators.class);
    logger.info("Application property loaded");
    Actions act = new Actions(GlobalFunction.driver);
    act.moveToElement(LoginPageLocators.account).build().perform();
    LoginPageLocators.signBtn.click();
    String decodedString = new String(Base64.getDecoder().decode(pass.trim()));
    logger.info("Decoded password:"+pass);
    LoginPageLocators.email.sendKeys(username);
    logger.info("User name Entered :"+username);
    LoginPageLocators.continueBtn.click();
    if (LoginPageLocators.usernameerrorlabel.isDisplayed()) {
      GlobalFunction.scenario.write(LoginPageLocators.usernameerrorlabel.getText());
      GlobalFunction.selectionScreenshot();
    }
    if (LoginPageLocators.pass.isDisplayed()) {
      LoginPageLocators.pass.sendKeys(decodedString);
      LoginPageLocators.sgnBtn.click();
      GlobalFunction.selectionScreenshot();
    }
    else
    {
      logger.error(LoginPageLocators.pass.isDisplayed());
    }

    if ((username.trim().isEmpty()) && (pass.trim().isEmpty())) {
      //Screenshot();
      LoginPageLocators.sgnBtn.click();
      GlobalFunction.scenario.write(LoginPageLocators.errorlabel.getText());
    }


  }


  public void validateOerdeItms() {
    PageFactory.initElements(GlobalFunction.driver, HomePageLocators.class);
    if (HomePageLocators.ordersItems.isDisplayed()) {
      HomePageLocators.ordersItems.click();
      GlobalFunction.scenario.write("Order items Clicked #:" + HomePageLocators.ordersItems.getText());

    }
  }


  public static BussinessFun getInstance() {
    return INSTANCE;
  }

  public void userSelectLastestOrderAndViewDetails() {
    PageFactory.initElements(GlobalFunction.driver, HomePageLocators.class);

    List<String> view_Order = HomePageLocators.View_order_details.stream().map(WebElement::getText)
            .collect(Collectors.toList());
    for (String ActualViewOrder : view_Order) {
      if (ActualViewOrder.equalsIgnoreCase("View order details")) {
        HomePageLocators.View_order_details.stream().filter(It -> It.getText().equalsIgnoreCase("View order details")).findFirst().ifPresent(WebElement::click);
        GlobalFunction.fullpageScreenshot();
        break;

      }
    }

  }

  public void userGetTheInformation() {
    PageFactory.initElements(GlobalFunction.driver, HomePageLocators.class);
    PageFactory.initElements(GlobalFunction.driver, OrderPageLocators.class);

    if (HomePageLocators.ordernumber.isDisplayed()) {
      GlobalFunction.scenario.write("Order#:" + HomePageLocators.ordernumber.getText());
    }

    int count = 0;

    List<String> orderListTimes = OrderPageLocators.orderListTimes.stream().map(WebElement::getText)
            .collect(Collectors.toList());
    for (String actualorderDetails : orderListTimes) {
      if (actualorderDetails.equalsIgnoreCase(actualorderDetails)) {
        GlobalFunction.scenario.write("Order#:" + OrderPageLocators.orderListTimes.get(count).getText());

      }
      count++;
    }
    GlobalFunction.fullpageScreenshot();
  }


  public void testFile(int dataRowno) throws IOException {


  }

}