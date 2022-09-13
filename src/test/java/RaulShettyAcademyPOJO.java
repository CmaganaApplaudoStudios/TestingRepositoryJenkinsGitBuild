import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class RaulShettyAcademyPOJO extends Base {
    //Locators used to locate the radio buttons
    By radioButton1 = By.xpath("//input[@value=\"radio1\"]");
    By radioButton2 = By.xpath("//input[@value=\"radio2\"]");
    By radioButton3 = By.xpath("//input[@value=\"radio3\"]");

    //Locators used to locate the text value of the radio buttons
    By radioButton1Text = By.xpath("//label[@for=\"radio1\"]");

    By radioButton2Text = By.xpath("//label[@for=\"radio2\"]");

    By radioButton3Text = By.xpath("//label[@for=\"radio3\"]");

    //Locator used to locate the autocomplete countries input
    By inputCountries = By.xpath("//input[@id=\"autocomplete\"]");

    //Locator used to simulate the selection of the first option of the autocomplete
    By firstTabIndex = By.xpath("//div[@tabindex=-1]");

    //Locator used to locate the legend above input countries
    By legendAboveInputCountries = By.xpath("//legend[contains(text(),\"Suggession Class Example\")]");

    //locator to reach the dropdown example
    By dropdownExample = By.xpath("//select[@id=\"dropdown-class-example\"]");

    //Locator to get the value from the option 3
    By dropdownOption3 = By.xpath("//option[@value=\"option3\"]");

    //Locator used to reach the home button
    By buttonHome = By.xpath("//button[contains(text(),\"Home\")]");

    //Locator used to reach the Practice button
    By buttonPractice = By.xpath("//button[contains(text(),\"Practice\")]");
    //Locator used to reach the Login button
    By buttonLogin = By.xpath("//button[contains(text(),\"Login\")]");
    //Locator used to reach the SignUp button
    By buttonSignUp = By.xpath("//button[contains(text(),\"Signup\")]");

    //Locator used to reach the openTabButton
    By openTabButton = By.xpath("//a[@id=\"opentab\"]");

    //The constructor of the class
    public RaulShettyAcademyPOJO(WebDriver driver) {
        super(driver);
    }

    //Gets the placeholder of the input countries
    public void showPlaceHolderOfInputCountries() {
        showPlaceHolder(inputCountries);
    }

    //Method that writes on the input countries
    public void typeOnInputCountries(String string) {
        type(string, inputCountries);
    }

    //This method selects the first option of the autocomplete input
    public void selectFirstOptionFromInputCountries() {
        //First we wait until the element is visible
        waitForElementIsVisible(firstTabIndex);
        //Then we click on the first element of the results
        click(firstTabIndex);
        //We click out also to leave the option selected
        click(legendAboveInputCountries);
        //We show the text of the input
        showTextValue(inputCountries);
    }

    //Method that validate which radio button is selected
    public void whichRadioBtnIsSelected() {
        //We create one boolean value that will contain true of false if a button is selected
        Boolean radio1 = isSelected(radioButton1);
        Boolean radio2 = isSelected(radioButton2);
        Boolean radio3 = isSelected(radioButton3);

        //If the radio button is selected whe show the following
        if (radio1) {
            System.out.println("SELECTED RadioButton: " + getText(radioButton1Text));
            //If its not selected we show the following sentence
        } else if (!radio1) {
            System.out.println("NOT SELECTED RadioButton: " + getText(radioButton1Text));
        }
        //If the radio button is selected whe show the following
        if (radio2) {
            System.out.println("SELECTED RadioButton: " + getText(radioButton2Text));
            //If its not selected we show the following sentence
        } else if (!radio2) {
            System.out.println("NOT SELECTED RadioButton: " + getText(radioButton2Text));
        }
        //If the radio button is selected whe show the following
        if (radio3) {
            System.out.println("SELECTED RadioButton: " + getText(radioButton3Text));
            //If its not selected we show the following sentence
        } else if (!radio3) {
            System.out.println("NOT SELECTED RadioButton: " + getText(radioButton3Text));
        }
    }

    //With this method we make a click in the desired radio button
    public void clickOnDesiredRadio(int n) {
        //It will depends on which int value we send that button will be clicked
        switch (n) {
            case 1:
                click(radioButton1);
                break;
            case 2:
                click(radioButton2);
                break;
            case 3:
                click(radioButton3);
                break;
        }
    }

    //This method helps to click in an option from the dropdown
    public void clickOnDropdownOption() {
        //We click first in the dropdown element
        click(dropdownExample);
        //We wait for the option 3 to get visible
        waitForElementIsVisible(dropdownOption3);
        //We click into that option
        click(dropdownOption3);
        //We make a click into the text of the above the dropdown
        click(dropdownExample);
        //We get the value from the dropdown
        String textFromDropdown = getValue(dropdownExample, "value");
        //We show the value of the dropdown in console
        System.out.println("Expected result: " + textFromDropdown);
    }

    //This method selects an option from the dropdown element
    public void selectChoiceFromDropdown(int x) {
        //We pass the value that we want to the following method
        selectFromDrowpdown(dropdownExample, x);
        //We show the value from the dropdown in console
        System.out.println("Expected result: " + getValue(dropdownExample, "value"));
    }

    //This method clicks every single one button of the navbar
    public void clickAndCompareButtonsUrl(String baseUrl) throws InterruptedException {
        //We click into the button
        click(buttonHome);
        //gets the actual url and compare the url with the baseurl
        if (!Objects.equals(baseUrl, getUrl())) {
            //Make a 5 seconds pause until everything is loaded
            Thread.sleep(5000);
            //We navigate back to the homepage(baseurl)
            navigateBack();
            //wait until the button that we clicked before is visible
            waitForElementIsVisible(buttonHome);
            //We print different url if the url was different from baseurl
            System.out.println("Different URL - Btn: " + getText(buttonHome));
        } else if (Objects.equals(baseUrl, getUrl())) {
            //If the url is the same we print same url
            System.out.println("Same URL - Btn: " + getText(buttonHome));
        }
        //We click into the button
        click(buttonLogin);
        //gets the actual url and compare the url with the baseurl
        if (!Objects.equals(baseUrl, getUrl())) {
            //Make a 5 seconds pause until everything is loaded
            Thread.sleep(5000);
            //We navigate back to the homepage(baseurl)
            navigateBack();
            //wait until the button that we clicked before is visible
            waitForElementIsVisible(buttonLogin);
            //We print different url if the url was different from baseurl
            System.out.println("Different URL - Btn: " + getText(buttonLogin));
        } else if (Objects.equals(baseUrl, getUrl())) {
            //If the url is the same we print same url
            System.out.println("Same URL - Btn: " + getText(buttonLogin));
        }
        click(buttonPractice);
        //gets the actual url and compare the url with the baseurl
        if (!Objects.equals(baseUrl, getUrl())) {
            //Make a 5 seconds pause until everything is loaded
            Thread.sleep(5000);
            //We navigate back to the homepage(baseurl)
            navigateBack();
            //wait until the button that we clicked before is visible
            waitForElementIsVisible(buttonPractice);
            //We print different url if the url was different from baseurl
            System.out.println("Different URL - Btn: " + buttonPractice);
        } else if (Objects.equals(baseUrl, getUrl())) {
            //If the url is the same we print same url
            System.out.println("Same URL - Btn: " + getText(buttonPractice));
        }
        click(buttonSignUp);
        //gets the actual url and compare the url with the baseurl
        if (!Objects.equals(baseUrl, getUrl())) {
            //Make a 5 seconds pause until everything is loaded
            Thread.sleep(5000);
            //We navigate back to the homepage(baseurl)
            navigateBack();
            //wait until the button that we clicked before is visible
            waitForElementIsVisible(buttonSignUp);
            //We print different url if the url was different from baseurl
            System.out.println("Different URL - Btn: " + getText(buttonSignUp));
        } else if (Objects.equals(baseUrl, getUrl())) {
            //If the url is the same we print same url
            System.out.println("Same URL - Btn: " + getText(buttonSignUp));
        }
    }
    //This method makes a click in the button open tab
    public void openTabWithButton(){
        click(openTabButton);
    }
}
