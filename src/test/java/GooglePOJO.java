import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePOJO extends Base{

    //This locator will help us to locate the Search Bar of Google
    By inputGoogleSearchBar = By.xpath("//input[@name=\"q\"]");

    //This is the link that we are expecting on the search
    By herokuAppLink = By.xpath("//a[contains(@href,\"https://the-internet.herokuapp.com/\")]");
    //The hovers link of the herokuApp webpage
    By hoversHerokuApp = By.xpath("//a[contains(@href,\"hovers\")]");

    //The first profile picture
    By firstProfile = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/img[1]");
    //The second profile picture
    By secondProfile = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/img[1]");
    //The third profile picture
    By thirdProfile = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/img[1]");
    //The profile names
    By profileName = By.xpath("//h5");
    //The link of the third profile
    By profileLinkThirdProfile = By.xpath("//a[@href=\"/users/3\"]");
    //The link of the second profile
    By profileLinkSecondProfile = By.xpath("//a[@href=\"/users/2\"]");
    //Constructor
    public GooglePOJO(WebDriver driver) {
        super(driver);
    }

    //This method open the specified amount of tabs that we need
    public void openTabs(int tabsAmount){
        //We get the amount of tabs opened already
        int tabsOpened = getAmountOfTabs();
        //While the amount of tabs opened is less than the tabsAmount that we need
        while(tabsOpened<tabsAmount){
            //We get the amount of tabs opened
            tabsOpened = getAmountOfTabs();
            //We open a new tab
            openTab();
        }
    }

    //This method makes a search on the Google Bar
    public void search(String input){
        //We type the input that we receive on the bar
        type(input,inputGoogleSearchBar);
        //We press enter to make the search
        pressEnter(inputGoogleSearchBar);
        //We click on the link of the results that match the link that we are looking for
        click(herokuAppLink);
    }

    //This method scrolls to hovers link
    public void scrollToHovers() throws InterruptedException {
        scrollToElement(hoversHerokuApp);
    }

    //This method clicks on hovers
    public void clickHovers(){
        click(hoversHerokuApp);
    }

    //This method makes all the actions on the images with hovers
    public void imageHovers() throws InterruptedException {
        //This method makes the hover action to the first image and gets the name
        hoverAndGetNameText(firstProfile,profileName," ");
        //We pause the code a lil bit
        Thread.sleep(500);
        //This method gets the profile linktext of the second profile
        hoverAndGetProfileLinkText(secondProfile,profileLinkSecondProfile);
        //We make another lil pause
        Thread.sleep(500);
        //This method hovers to the third image and clicks on the profile link
        hoverToElementAndClick(thirdProfile,profileLinkThirdProfile);
    }
}
