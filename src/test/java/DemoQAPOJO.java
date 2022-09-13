import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DemoQAPOJO extends Base{

    //Locator for the main sections of the page
    By categoryCards = By.xpath("//*[@class=\"category-cards\"]/div[@class=\"card mt-4 top-card\"]");
    //The span for the forms section practice
    By practiceFormSpan = By.xpath("//span[contains(text(),\"Practice Form\")]");
    //Label for Gender options of radio buttons
    By labelGenderMale = By.xpath("//label[contains(text(),'Male')]");

    By labelGenderFemale = By.xpath("//label[contains(text(),'Female')]");

    By labelGenderOther = By.xpath("//label[contains(text(),'Other')]");

    //The radio buttons of gender
    By inputGender = By.xpath("//input[@name=\"gender\"]");
    //The checkbox for Hobbies
    By checkboxSport = By.xpath("//label[contains(text(),\"Sports\")]");

    By checkboxReading = By.xpath("//label[contains(text(),\"Reading\")]");

    By checkboxMusic = By.xpath("//label[contains(text(),\"Music\")]");
    //the inputs of the checkbox
    By inputCheckbox = By.xpath("//input[@type=\"checkbox\"]");
    //The label for picture
    By labelPicture = By.xpath("//label[contains(text(),\"Picture\")]");
    //The span of browser section
    By spanBrowserWindows = By.xpath("//span[contains(text(),\"Browser Windows\")]");
    //button to open new tabs
    By openTabButton = By.xpath("//button[@id=\"tabButton\"]");
    //Button for the alert section
    By spanAlerts = By.xpath("//span[contains(text(),\"Alerts\")]");
    //The button of the alert with time
    By timerAlertButton = By.xpath("//button[@id=\"timerAlertButton\"]");
    //constructor of the class
    public DemoQAPOJO(WebDriver driver) {
        super(driver);
    }

    //Method that makes all the alerts process
    public void alertsProcess() throws InterruptedException {
        //this method clicks on alert section
        click(spanAlerts);
        //this method clicks on the alert of five seconds
        click(timerAlertButton);
        //this method validates the five seconds for the alert to appear
        waitCertainAmountOfTimeForAlert(5);
        //This method closes the alert
        closeAlert();
    }
    //This method clicks on the form section
    public void clickOnForms() throws InterruptedException {
        //first we scroll to the categories of the main page
        scrollToElement(categoryCards);
        //Then we click on the desired element that is forms
        clickOnDesiredElement(categoryCards,1);
        //We wait until the clickable span for the form example is loaded
        waitForElementIsVisible(practiceFormSpan);
        //we click on the practice form span
        click(practiceFormSpan);
    }

    //This method take us to the Browser windows sections of the qademo page
    public void clickOnBrowserWindows(String baseUrl) throws InterruptedException {
        //we navigate back to the base url
        visit(baseUrl);
        //We wait until the categories are visible
        waitForElementIsVisible(categoryCards);
        //We scroll to the categories elements
        scrollToElement(categoryCards);
        //We click into the third option index 2 that is the browser option
        clickOnDesiredElement(categoryCards,2);
        //We wait until the span of the browser windows is visible
        waitForElementIsVisible(spanBrowserWindows);
        //We click into the browser windows option
        click(spanBrowserWindows);
    }

    //this method clicks on the desired radio button
    public void clickRadioButtons() throws InterruptedException {
        //We generate a random number
        int randomNumber = getRandomNumber(1,3);
        //we print in console the number generated
        System.out.println("Random number is: "+randomNumber);
       //We evaluate the case with the switch statement
        switch (randomNumber){
            //Depending on the number generated before that is the option that we are going to click
            case 1:
                click(labelGenderMale);
                break;
            case 2:
                click(labelGenderFemale);
                break;
            case 3:
                click(labelGenderOther);
                break;
        }
    }

    public void clickCheckboxHobbies() throws InterruptedException {
        //We generate a random number between 1 and 3
        int randomNumber = getRandomNumber(1,3);
        //We scroll to the label of picture to give a better visibility
        scrollToElement(labelPicture);
        //We evaluate the case of the random number generated
        switch (randomNumber){
            //we have to click on the opposite option of the number generated
            case 1:
                click(checkboxMusic);
                click(checkboxReading);
                break;
            case 2:
                click(checkboxSport);
                click(checkboxMusic);
                break;
            case 3:
                click(checkboxSport);
                click(checkboxReading);
                break;
        }

    }

    //This method is to validate which checkbox is selected
    public void verifyWhichCheckboxIsSelected(){
        //with if an else clause we validate which checkbox are selected and print the options
        if(isSelectedFromList(findElements(inputCheckbox),0) &&
                isSelectedFromList(findElements(inputCheckbox),1)){
            //We use the isSelectedFromList method declared in Base
            //We get the text from the label of the checkbox option
            System.out.println("Is Selected: "+getText(checkboxSport));
            System.out.println("Is Selected: "+getText(checkboxReading));
            System.out.println("Not Selected: "+getText(checkboxMusic));
        }else if(isSelectedFromList(findElements(inputCheckbox),0) &&
                isSelectedFromList(findElements(inputCheckbox),2)){
            System.out.println("Is Selected: "+getText(checkboxSport));
            System.out.println("Not Selected: "+getText(checkboxReading));
            System.out.println("Is Selected: "+getText(checkboxMusic));
        }else if(isSelectedFromList(findElements(inputCheckbox),1) &&
                isSelectedFromList(findElements(inputCheckbox),2)){
            System.out.println("Not Selected: "+getText(checkboxSport));
            System.out.println("Is Selected: "+getText(checkboxReading));
            System.out.println("Is Selected: "+getText(checkboxMusic));
        }

    }
    //This method is to verify which radio button is selected
    public void verifyWhichRadioIsSelected(){
        //With the method isSelectedFromList we put there the element
        if(isSelectedFromList(findElements(inputGender),0))
            //and get the text from the label of the element with getText method
            System.out.println("Is Selected: "+getText(labelGenderMale));
        else if (!isSelectedFromList(findElements(inputGender),0)) {
            System.out.println("Is Not Selected: "+getText(labelGenderMale));
        }
        if(isSelectedFromList(findElements(inputGender),1))
            System.out.println("Is Selected: "+getText(labelGenderFemale));
        else if (!isSelectedFromList(findElements(inputGender),1)) {
            System.out.println("Is Not Selected: "+getText(labelGenderFemale));
        }if(isSelectedFromList(findElements(inputGender),2))
            System.out.println("Is Selected: "+getText(labelGenderOther));
        else if (!isSelectedFromList(findElements(inputGender),2)) {
            System.out.println("Is Not Selected: "+getText(labelGenderOther));
        }
    }
    //This method help us to open a new tab and verify its title
    public void openTabAndVerifyTitle() throws InterruptedException {
        //We click into the span of browser windows
        click(spanBrowserWindows);
        //We click on the open tab button to open a new tab
        click(openTabButton);
        //We validate with an assert if the title of the page contains "page" on it
        containsWithAssert(getTabTitle(),"page");
        //We verify the amount of tabs opened
        verifyTabsOpened(2);
        //We change to the last tab opened
        changeToLastTab();
        //we made a little wait
        Thread.sleep(500);
        //We close the las tab opened
        closeTab();
        //We change to the main tab
        changeToMainTab();
        //We verify that the amount of tabs opened is 1
        verifyTabsOpened(1);
    }

    //This method generates and return a random number between a range
    public static int getRandomNumber(int min, int max) {
        //With the Math.random() method
        return (int) ((Math.random() * (max - min)) + min);
    }

}
