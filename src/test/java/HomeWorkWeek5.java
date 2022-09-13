import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomeWorkWeek5 {

    //The object that will allow us to use the methods for the homework
    private GooglePOJO googlePojoObject;

    //Driver that will help us to initialize the POJO object
    private WebDriver driver;

    //URl of Google
    private final String baseUrl = "https://google.com.sv";

    //The phrase that we are looking for in the results of the search
    private final String herokuSearch = "the internet herokuapp";

        //First test is always to setup the browser
    @Test(priority = 1)
    @Parameters("browserOption")
    public void setup(){
        String browserName = getParameter("browser");
        //We initialize the pojo object
        googlePojoObject = new GooglePOJO(driver);
        //We select the desired browser
        googlePojoObject.selectBrowser(browserName);

        //We expand the browser
        googlePojoObject.expandBrowser();
        //We navigate to the base url
        googlePojoObject.visit(baseUrl);
    }

    //This test open the tabs
    @Test (priority = 2)
    public void openTabs() throws InterruptedException {
        //We open five tabs
        googlePojoObject.openTabs(5);
        //We make a switch of the tabs
        googlePojoObject.switchTabsExample();
        //We close all the tabs minus the original
        googlePojoObject.closeTabsMinusTheOriginal();
    }

    //This method makes the search and take us to the hovers section
    @Test(priority =3)
    public void search() throws InterruptedException {
        //We search into the google search bar and gets the result
        googlePojoObject.search(herokuSearch);
        //Scroll to hovers on the herokuapp web
        googlePojoObject.scrollToHovers();
        //we click on hovers
        googlePojoObject.clickHovers();
    }

    //This method do all the hovers actions
    @Test (priority = 4)
    public void hoverActions() throws InterruptedException {
        //This method do all the process required for the image profiles
        googlePojoObject.imageHovers();
        //We pause the code a lil
        Thread.sleep(500);
        //We close and finish the session
        googlePojoObject.exit();
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
