package BusinessLogic;

import PageLocators.HomePageLocators;
import PageLocators.LoginPageLocators;
import PageLocators.OrderPageLocators;
import Util.*;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class BussinessFun {

  public static BussinessFun INSTANCE = new BussinessFun();
  private static  Logger logger = LogManager.getLogger(BussinessFun.class.getName());

  public void loginAuthentication(String username, String pass) throws IOException {
    PageFactory.initElements(WebDriverFactory.getInstance().getDriver(), LoginPageLocators.class);
    logger.debug("Application property loaded");
    Actions act = new Actions(WebDriverFactory.getInstance().getDriver());
    act.moveToElement(LoginPageLocators.account).build().perform();
    LoginPageLocators.signBtn.click();
    String decodedString = new String(Base64.getDecoder().decode(pass.trim()));
    logger.debug("Decoded password:" + pass);
    LoginPageLocators.email.sendKeys(username);
    logger.debug("User name Entered :" + username);
    LoginPageLocators.continueBtn.click();
    if (LoginPageLocators.usernameerrorlabel.isDisplayed()) {
      GlobalFunction.scenario.write(LoginPageLocators.usernameerrorlabel.getText());
      GlobalFunction.selectionScreenshot();
    }
  if( LoginPageLocators.pass.isDisplayed()) {
    LoginPageLocators.pass.sendKeys(decodedString);
    LoginPageLocators.sgnBtn.click();
    GlobalFunction.selectionScreenshot();
  }

    if ((username.trim().isEmpty()) && (pass.trim().isEmpty())) {
      LoginPageLocators.sgnBtn.click();
      GlobalFunction.scenario.write(LoginPageLocators.errorlabel.getText());
    }


  }


  public void validateOerdeItms() {
    PageFactory.initElements(WebDriverFactory.getInstance().getDriver(), HomePageLocators.class);
    if (HomePageLocators.ordersItems.isDisplayed()) {
      HomePageLocators.ordersItems.click();
      GlobalFunction.scenario.write("Order items Clicked #:" + HomePageLocators.ordersItems.getText());
      logger.debug("Order items Clicked #:" + HomePageLocators.ordersItems.getText());

    }
  }


  public static BussinessFun getInstance() {
    return INSTANCE;
  }

  public void userSelectLastestOrderAndViewDetails() {
    PageFactory.initElements(WebDriverFactory.getInstance().getDriver(), HomePageLocators.class);
    List<String> listMMYYYY = HomePageLocators.listofMMYYY.stream().map(WebElement::getText)
            .collect(Collectors.toList());
    for (String actualmmyyy : listMMYYYY) {
      if (actualmmyyy.equalsIgnoreCase("2023")) {
        HomePageLocators.listofMMYYY.stream().filter(It -> It.getText().equalsIgnoreCase("2023")).findFirst().ifPresent(WebElement::click);
        //GlobalFunction.fullpageScreenshot();
        break;
      }
    }
    List<String> view_Order = HomePageLocators.View_order_details.stream().map(WebElement::getText)
            .collect(Collectors.toList());
    for (String ActualViewOrder : view_Order) {
      if (ActualViewOrder.equalsIgnoreCase("View order details")) {
        HomePageLocators.View_order_details.stream().filter(It -> It.getText().equalsIgnoreCase("View order details")).findFirst().ifPresent(WebElement::click);
        //GlobalFunction.fullpageScreenshot();
        break;
      }
    }

  }

  public void userGetTheInformation() {
    PageFactory.initElements(WebDriverFactory.getInstance().getDriver(), HomePageLocators.class);
    PageFactory.initElements(WebDriverFactory.getInstance().getDriver(), OrderPageLocators.class);
    if (HomePageLocators.ordernumber.isDisplayed()) {
      GlobalFunction.scenario.write("Order#:" + HomePageLocators.ordernumber.getText());
      logger.debug("Order#:" + HomePageLocators.ordernumber.getText());
    }
    int count = 0;
    List<String> orderListTimes = OrderPageLocators.orderListTimes.stream().map(WebElement::getText)
            .collect(Collectors.toList());
    for (String actualorderDetails : orderListTimes) {
      if (actualorderDetails.equalsIgnoreCase(actualorderDetails)) {
        GlobalFunction.scenario.write("Order#:" + OrderPageLocators.orderListTimes.get(count).getText());
        logger.debug("Order#:" + OrderPageLocators.orderListTimes.get(count).getText());
      }
      count++;
    }
    GlobalFunction.fullpageScreenshot();
  }
}