package BusinessLogic;

import PageLocators.HomePageLocators;
import PageLocators.LoginPageLocators;


import Util.ExcelReaderFillo;
import Util.GlobalFunction;
import Util.ReusableFunction;
import com.codoid.products.exception.FilloException;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


public class BussinessFun extends ReusableFunction {

    public static BussinessFun INSTANCE = new BussinessFun();
    static Properties prop = new Properties();
    public static String userName;
    public static String password;
    public static Hashtable<String, String> dict_login;


    //Create instance for Resuable function
    public static BussinessFun getInstance() {
        return INSTANCE;
    }
    private static final Logger logger = LogManager.getLogger(BussinessFun.class.getName());
    public void loginAuth() throws IOException, InterruptedException {
        PageFactory.initElements(GlobalFunction.driver, LoginPageLocators.class);
        loadProperty();
        logger.info("Application property loaded");

        Actions act = new Actions(GlobalFunction.driver);
        act.moveToElement(LoginPageLocators.account).build().perform();
        LoginPageLocators.signBtn.click();

        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedString = new String(decodedBytes);
        Thread.sleep(2000);
        LoginPageLocators.email.sendKeys(userName);
        LoginPageLocators.continueBtn.click();
        if (LoginPageLocators.usernameerrorlabel.isDisplayed()) {
            GlobalFunction.scenario.write(LoginPageLocators.usernameerrorlabel.getText());
            GlobalFunction.Screenshot();
        }
        // LoginPageLocators.pass.sendKeys(decodedString);
        //GlobalFunction.Screenshot();

        if ((!userName.isEmpty()) && (!decodedString.isEmpty())) {
            GlobalFunction.scenario.write("Username and  password is accepted ");
            LoginPageLocators.sgnBtn.click();
            GlobalFunction.Screenshot();

            Thread.sleep(1000);
            // reusableFunc.Screenshot();
        }
//
        if ((userName.isEmpty()) && (decodedString.isEmpty())) {
            //Screenshot();
            LoginPageLocators.sgnBtn.click();
            GlobalFunction.scenario.write(LoginPageLocators.errorlabel.getText());
        }


    }

    public void loadProperty() throws IOException {

        String filename = "src/test/resources/UserCredentials.properties";
        BussinessFun.class.getClassLoader().getResourceAsStream(filename);
        System.out.println(filename);
        prop.load(new FileInputStream(filename));
        userName = prop.getProperty("UserName");
        password = prop.getProperty("Password");


    }

    public void loginAuthentication(String sheetname, String strQuery) throws IOException, FilloException {
        //dict_login = ExcelReaderFillo.getInstance().readExcel(strQuery, sheetname);

        PageFactory.initElements(GlobalFunction.driver, LoginPageLocators.class);
        loadProperty();
        Actions act = new Actions(GlobalFunction.driver);
        act.moveToElement(LoginPageLocators.account).build().perform();
        LoginPageLocators.signBtn.click();
        LoginPageLocators.email.sendKeys(dict_login.get("userName"));


        LoginPageLocators.continueBtn.click();
        if (LoginPageLocators.usernameerrorlabel.isDisplayed()) {
            GlobalFunction.scenario.write(LoginPageLocators.usernameerrorlabel.getText());
            GlobalFunction.Screenshot();
        }
        String encodedString = new String( Base64.getDecoder().decode(dict_login.get(password)));
        if (LoginPageLocators.pass.isDisplayed()) {
            LoginPageLocators.pass.sendKeys(dict_login.get("password"));
            LoginPageLocators.sgnBtn.click();
            GlobalFunction.Screenshot();
        }

//
        if ((userName.isEmpty()) && (password.isEmpty())) {
            //Screenshot();
            LoginPageLocators.sgnBtn.click();
            GlobalFunction.scenario.write(LoginPageLocators.errorlabel.getText());
        }


    }

    public void validateOerdeItms() {
        PageFactory.initElements(GlobalFunction.driver, HomePageLocators.class);
        if (HomePageLocators.ordersItems.isDisplayed()) {
            HomePageLocators.ordersItems.click();
        }

        List<String> view_Order = HomePageLocators.View_order_details.stream().map(WebElement::getText)
                .collect(Collectors.toList());
        for (String ActualViewOrder : view_Order) {
            if (ActualViewOrder.equalsIgnoreCase("View order details")) {
                HomePageLocators.View_order_details.stream().filter(It -> It.getText().equalsIgnoreCase("View order details")).findFirst().ifPresent(WebElement::click);
                GlobalFunction.scenario.write("Order#:"+HomePageLocators.ordernumber.getText());
                GlobalFunction.Screenshot();
                break;

            }
        }

    }


    public void validalogi(String sheetname, String strQuery) throws IOException {
        dict_login =  ExcelReaderFillo.getInstance().getExcelData(sheetname, strQuery);
        PageFactory.initElements(GlobalFunction.driver, LoginPageLocators.class);
        loadProperty();
        logger.info("Application property loaded");

        Actions act = new Actions(GlobalFunction.driver);
        act.moveToElement(LoginPageLocators.account).build().perform();
        LoginPageLocators.signBtn.click();
        String password=dict_login.get("password");
        String decodedString = new String( Base64.getDecoder().decode(password.trim()));

        LoginPageLocators.email.sendKeys(dict_login.get("userName"));


        LoginPageLocators.continueBtn.click();
        if (LoginPageLocators.usernameerrorlabel.isDisplayed()) {
            GlobalFunction.scenario.write(LoginPageLocators.usernameerrorlabel.getText());
            GlobalFunction.Screenshot();
        }
        if (LoginPageLocators.pass.isDisplayed()) {
            LoginPageLocators.pass.sendKeys(decodedString);
            LoginPageLocators.sgnBtn.click();
            GlobalFunction.Screenshot();
        }

//
        if ((userName.isEmpty()) && (password.isEmpty())) {
            //Screenshot();
            LoginPageLocators.sgnBtn.click();
            GlobalFunction.scenario.write(LoginPageLocators.errorlabel.getText());
        }



    }
}
