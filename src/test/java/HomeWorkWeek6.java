import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomeWorkWeek6 {

    //The object that will have access to the methods of the desired actions
    private DemoQAPOJO demoQAPOJO;

    //the driver to initialize the pojo object
    private WebDriver driver;
    //the base url of the page
    private final String baseUrl = "https://demoqa.com/";

    //Test to do the configuration process
    @Test(priority = 1)
    public void setup(){
        String browserName = getParameter("browser");
        //Initializing the pojo object with the driver
        demoQAPOJO = new DemoQAPOJO(driver);
        //Selecting the desired browser to run the tests
        demoQAPOJO.selectBrowser(browserName);
        //Method to expand the window of the browser
        demoQAPOJO.expandBrowser();
        //navigating to the base url
        demoQAPOJO.visit(baseUrl);
    }

    //Test that makes all the actions on the forms section
    @Test(priority = 2)
    public void formsTest() throws InterruptedException {
        //this method take us to the forms section
        demoQAPOJO.clickOnForms();
        //Click on the random option generated of the radios
        demoQAPOJO.clickRadioButtons();
        //Verifies which radio button is selected
        demoQAPOJO.verifyWhichRadioIsSelected();
        //clicks on the opposite of the random number generated
        demoQAPOJO.clickCheckboxHobbies();
        //Verifies which checkbox options are selected
        demoQAPOJO.verifyWhichCheckboxIsSelected();
        //goes back to the base url
        demoQAPOJO.visit(baseUrl);
    }

    //Test that does the tabs actions
    @Test(priority = 3)
    public void tabsTest() throws InterruptedException {
        //goes to the browser options of demoqa
        demoQAPOJO.clickOnBrowserWindows(baseUrl);
        //open the new tab verify the title and the amount of tabs opened
        demoQAPOJO.openTabAndVerifyTitle();
    }

    //Test that does all the actions for the alerts
    @Test(priority = 4)
    public void alertsTest() throws InterruptedException {
        demoQAPOJO.alertsProcess();
    }
    //Test that finish the testing session
    @Test(priority = 5)
    public void finishSession(){
        demoQAPOJO.exit();
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
