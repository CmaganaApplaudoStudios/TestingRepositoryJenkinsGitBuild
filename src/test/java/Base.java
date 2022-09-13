import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import org.testng.asserts.SoftAssert;
import java.util.List;


//This class is going to do one task that is to initialize the driver
public class Base {

    //WebDriver that is going to be helpful to implement the methods of the class
    private WebDriver driver;

    private String browserName;

    //Constructor that initializes the driver received
    public Base(WebDriver driver) {
        this.driver = driver;
    }

    //Method that creates the connection with Google Chrome
    public WebDriver chromeDriverConnection() {
        System.setProperty("webdriver.chrome.driver", "./src/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        return this.driver;
    }

    //Method that creates the connection with Firefoxx
    public WebDriver firefoxDriverConnection() {
        System.setProperty("webdriver.gecko.driver", "./src/drivers/geckodriver.exe");
        this.driver = new FirefoxDriver();
        return this.driver;
    }

    //Method that creates the connection with Edge
    public WebDriver edgeDriverConnection() {
        System.setProperty("webdriver.edge.driver", "./src/drivers/msedgedriver.exe");
        this.driver = new EdgeDriver();
        return this.driver;
    }

    //Method that initialize the desired browser
    public WebDriver selectBrowser(String browserName) {
        this.browserName = browserName;
        switch (this.browserName) {
            case "chrome":
                chromeDriverConnection();
                break;
            case "firefox":
                firefoxDriverConnection();
                break;
            case "edge":
                edgeDriverConnection();
                break;
            default:
                System.out.print("Please select a number from 1 to 3");
                break;
        }
        return null;
    }

    //All the following methods are wrappers that are going to be called from the POJO classes
    //Method that makes click on an element
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    //Mehod that get the text from an element
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    //Method that types into an input
    public void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }

    //Method that navigates to an URL
    public void visit(String url) {
        driver.get(url);
    }

    //Method that looks if an element is selected
    public Boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    //Method that expands the browser window
    public void expandBrowser() {
        driver.manage().window().maximize();
    }

    //Method that gets the placeholder from an element
    public void showPlaceHolder(By locator) {
        System.out.println(driver.findElement(locator).getAttribute("placeholder"));
    }

    //Method that access to the value attribute
    public void showTextValue(By locator) {
        System.out.println("The value of the input is: " + driver.findElement(locator).getAttribute("value"));
    }

    //Method that waits for an element to be visible in the browser
    public void waitForElementIsVisible(By locator) {
        //First we initialize a WebDriverWait variable and put the amount of max milliseconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        //Then we use the method until and with ExpectedConditions what are we waiting for
        //In this case is for the element to get visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //method that selects an element from a dropdown with a Select object
    public void selectFromDrowpdown(By locator, int index) {
        //We search for the element with his locator
        WebElement selectElement = driver.findElement(locator);
        //We pass that WebElement to the Select Object to initialize it
        Select selectObject = new Select(selectElement);
        //We send the index of the element that we want
        selectObject.selectByIndex(index);
    }

    //Method that get an specific attribute from an element
    public String getValue(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }

    //Method that navigates back
    public void navigateBack() {
        driver.navigate().back();
    }

    //Method that gets the current url of the browser
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    //Method that returns the amount of tabs opened
    public int getAmountOfTabs() {
        return driver.getWindowHandles().size();
    }

    //Method that returns to the main open tab
    public void changeToMainTab() {
        //We create an ArrayList of Strings and get the current tabs open
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //We switch to the main tab by getting the first element of the tabs array
        driver.switchTo().window(tabs.get(0));
    }
    //This method opens a new tab in the browser
    public void openTab(){
        driver.switchTo().newWindow(WindowType.TAB);
    }
    //This method change between two different tabs
    public void switchTabsExample() throws InterruptedException {
        int tabsIndex = getAmountOfTabs()-1;
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        Thread.sleep(2000);
        driver.switchTo().window(tabs.get(tabsIndex-1));
        Thread.sleep(2000);
        driver.switchTo().window(tabs.get(tabsIndex));
    }
    //This method closes all the tabs minus the original
    public void closeTabsMinusTheOriginal(){
        //We get the amount of tabs opened and subtract by 1 (because of the index order 0 to n-1)
        int openedTabs = getAmountOfTabs()-1;
        //We put into an arrayList of Strings every single tab reference
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //While there's more than 1 tab opened
        while(getAmountOfTabs()>1){
            //We close the tabs 1 by 1
            driver.close();
            //We subtract by 1 the index
            openedTabs--;
            //We change to the previous tab
            driver.switchTo().window(tabs.get(openedTabs));
        }
    }

    //This method press the Enter Key
    public void pressEnter(By locator){
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    //This method scrolls to an element
    public void scrollToElement(By locator) throws InterruptedException {
        //We create a web element with the reference of the locator
        WebElement element = driver.findElement(locator);
        //We execute a JavaScript Scroll to Element Action
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
    }
    //This method scrolls to an element and then makes click
    public void hoverToElementAndClick(By locator1, By locator2){
        //We declare a new action
        Actions action = new Actions(driver);
        //We declare the web element that we want to scroll to and click
        WebElement element = driver.findElement(locator1);

        //With the action class we move to the element, and then we click on it
        action.moveToElement(element).moveToElement(driver.findElement(locator2)).click().build().perform();
    }

    //This method does the hover action and then gets the name property
    public void hoverAndGetNameText(By locator1, By locator2, String split){
        //We declare a new action
        Actions action = new Actions(driver);
        //We create a new WebElement the main element
        WebElement element = driver.findElement(locator1);

        //We do the hover action
        action.moveToElement(element).build().perform();

        //We get the name that is on the HTML and assign it to a name String
        String name = driver.findElement(locator2).getText();

        //We split the text that we need because it contains a string between it
        String[] nameSplit = name.split(split);
        //We show the name that is on the second position of the array
        System.out.println("The name is: "+nameSplit[1]);
    }

    //This method makes the hover action and gets the link of the profile
    public void hoverAndGetProfileLinkText(By locator1, By locator2){
        //We declare a new action
        Actions action = new Actions(driver);
        //We create a new WebElement for the main element (the profile picture)
        WebElement element = driver.findElement(locator1);
        //We do the hover to the profile picture
        action.moveToElement(element).build().perform();
        //We extract the link of the profile reference
        String profileLink = driver.findElement(locator2).getText();
        //We print the string that we extracted before
        System.out.println(profileLink);
    }
    //This method clicks on the desired index of a list from webElements found
    public void clickOnDesiredElement(By locator, int index) {
        List<WebElement> elements = driver.findElements(locator);

        elements.get(index).click();
    }

    //This method validates if an element is selected from a list of webElements
    public Boolean isSelectedFromList(List<WebElement> elements, int index) {
        return elements.get(index).isSelected();
    }
    //This method is useful to find elements
    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    //This method checks if a String contains a piece of a char sequence
    public void containsWithAssert(String string1,String title){
        //We make a boolean value that validates if the title contains the string we want
        boolean boolean1 = string1.contains(title);
        //We make a soft assert
        SoftAssert softAssert = new SoftAssert();
        //we validate with assert true
        softAssert.assertTrue(boolean1);
    }

    //this method changes to the last tab opened
    public void changeToLastTab(){
        //We get the amount of tabs currently opened and reduce by 1 because of the index on the arrays
        int tabsIndex =getAmountOfTabs()-1;
        //we get the current tabs opened
        ArrayList<String> tabs =new ArrayList<>(driver.getWindowHandles());
        //We change to the last element of the tabs array
        driver.switchTo().window(tabs.get(tabsIndex));

    }

    //This method verifies the amount of tabs opened
    public void verifyTabsOpened(int tabsNumExpected){
        //We create a new soft assert
        SoftAssert softAssert = new SoftAssert();
        //We make an equal by the tabs opened and the tabs expected
        softAssert.assertEquals(driver.getWindowHandles().size(),tabsNumExpected);
    }

    //This method gets the title of a tab
    public String getTabTitle(){
        return driver.getTitle();
    }

    //this method close the current tab
    public void closeTab(){
        driver.close();
    }

    //This method waits for an alert to appear in a certain amount of time
    public void waitCertainAmountOfTimeForAlert(int timeAmount) throws InterruptedException {
        //the counter for the time in seconds
        int i=0;
        //the validator of the current time
        while(i++<timeAmount){
            try
            {
                //we change the context by the alert context
                Alert alert = driver.switchTo().alert();
                break;
            }
            //the exception that is thrown by not found an alert
            catch(NoAlertPresentException e)
            {
                //time unit in this case is seconds
                Thread.sleep(1000);
                //We print the current time
                System.out.println("Alert its still not present time passed in seconds: "+i);
                continue;
            }
        }
    }

    //This method accepts an alert
    public void closeAlert(){
        driver.switchTo().alert().accept();
    }
    //Method to finish the browser session
    public void exit() {
        driver.quit();
    }
}
