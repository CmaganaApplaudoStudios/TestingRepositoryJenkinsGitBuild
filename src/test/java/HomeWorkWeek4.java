import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomeWorkWeek4 {

    //The driver that will help us to initialize the driver of the POJO class
    private WebDriver driver;
    //The POJO class object that will make all the desired actions
    private RaulShettyAcademyPOJO raulShettyObject;

    //We make the base url final because its value will not change
    private final String baseUrl = "https://rahulshettyacademy.com/AutomationPractice/";

    //This data provider will be helpful to pass the desired browser to the tests.
    @DataProvider
    public Object[][] browserNumberProvider(){
        return new Object [][] {{1}};
    }

    //The method that initialize all the process by selecting the desired browser driver
    @Test (priority = 1)
    public void setup(){

        String browserName = getParameter("browser");
        //We initialize the POJO object with the driver of the class
        raulShettyObject = new RaulShettyAcademyPOJO(driver);

        //We pass the int of the option of browser that we want
        driver = raulShettyObject.selectBrowser(browserName);


        //We expand the browser
        raulShettyObject.expandBrowser();
        //We navigate to the base url
        raulShettyObject.visit(baseUrl);

    }

    @Test (priority = 2)
    public void radioButtonsCheck(){
        //We generate a random number between 1 and 3
        int randomRadioBtn = getRandomNumber(1,3);

        //We click on the radio number that was generated before
        raulShettyObject.clickOnDesiredRadio(randomRadioBtn);

        //We print the generated random number
        System.out.println("The generated random number is: "+randomRadioBtn);

        //We click into the radio button that matches the random number
        raulShettyObject.clickOnDesiredRadio(randomRadioBtn);
        //We check which radio button is selected
        raulShettyObject.whichRadioBtnIsSelected();

    }

    @Test (priority = 3)
    public void autocompleteInputCountries(){
        //We show the placeholder from the input
        raulShettyObject.showPlaceHolderOfInputCountries();
        //We type El Sal in the input waiting for possible results from the autocomplete function
        raulShettyObject.typeOnInputCountries("El Sal");
        //We select the first option that is El Salvador
        raulShettyObject.selectFirstOptionFromInputCountries();
    }

    @Test (priority = 4)
    public void dropdownBehavior(){
        //We select a choice (2) from the dropdown using select object
        raulShettyObject.selectChoiceFromDropdown(2);
        //We click in the dropdown option (3) and show its value later (simulating select behavior)
        raulShettyObject.clickOnDropdownOption();

    }

    @Test (priority = 5)
    //it throws an InterruptedException because inside it has a Thread
    public void clickOnButtonsAndCompareUrl() throws InterruptedException {
       //this method compares the url between the buttons redirections and the baseurl
        raulShettyObject.clickAndCompareButtonsUrl(baseUrl);
    }

    @Test (priority=6)
    public void OpenNineTabs(){
        //We make the following actions until we have 9 tabs opened
        while(raulShettyObject.getAmountOfTabs()<9){
            //We open a tab
            raulShettyObject.openTabWithButton();
            //we return to the principal tab
            raulShettyObject.changeToMainTab();
        }
        //We print the amount of tabs open
        System.out.println("The current number of tabs is: "+raulShettyObject.getAmountOfTabs());
    }

    @Test(priority = 6)
    public void finished(){
        //This method finish the browser session and closes everything
        raulShettyObject.exit();
    }

    //This method generates and return a random number between a range
    public static int getRandomNumber(int min, int max) {
        //With the Math.random() method
        return (int) ((Math.random() * (max - min)) + min);
    }

    private String getParameter(String name){
        String value = System.getProperty(name);

        if(value == null)
            throw new RuntimeException(name + "is not a parameter!");

        if(value.isEmpty())
            throw new RuntimeException(name + "is empty!");

        return value;
    }
}
