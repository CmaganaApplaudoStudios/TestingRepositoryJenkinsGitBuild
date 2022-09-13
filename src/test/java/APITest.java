import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class APITest {

    //The email that we will compare in the second task
    private String email= "george.edwards@reqres.in";
    //The server that we are expecting
    private String server = "Cloudflare";
    //priority of the test to execute
    @Test(priority = 1)
    public void serverValidation(){
        try{
            //Doing a request of get type to this endpoint and saving it into a response object
            Response response = RestAssured.get("https://reqres.in/api/users?page=2");
            //Doing an Assert to check if the status code of the response matches with 200 (success)
            Assert.assertEquals(response.getStatusCode(),200);
            //printing that the status code is correct because of the assert
            System.out.println("The status code is 200");
            //Doing an assert to validate the server of the response we are expecting to get Cloudflare as a server
            Assert.assertEquals(RestAssured.get("https://reqres.in/api/users?page=2")
                    .getHeader("server"),server);
        //If an assert fails this block will be executed
        }catch (AssertionError e){
            //first we make the request again
            Response response = RestAssured.get("https://reqres.in/api/users?page=2");
            //We print both the server and the status code to check which one didn't match
            System.out.println("This block will be executed if one of two options fail");
            System.out.println("The status code is not 200 or");
            System.out.println("The status code of the response is: "+response.getStatusCode());
            System.out.println("The server was not: "+server);
            System.out.println("The server from the response is: "+response.getHeader("server"));

        }
        //We make another request and save it into a response object
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        //We save the body of the response into a responseBody object
        ResponseBody body = response.getBody();
        //We print the body of the response in json format
        System.out.println("Response from the endpoint in JSON format: "+body.asPrettyString());

    }

    @Test(priority = 2)
    public void getUserThatMatchEmail(){
        //We declare the base url
        RestAssured.baseURI= "https://reqres.in";
        //We use the method given().when().get(put the endpoint you want to hit)
        Response res = given()
                .when()
                .get("/api/users?page=2");

        //We declare a jsonpath object that will help us to iterate between the json objects
        JsonPath json = new JsonPath(res.asString());

        //We save the size of the data array in the json
        int jsonObjectSize = json.getInt("data.size()");

        //This int will save later the id that we are searching for
        int idFromJsonThatMatchEmail =0;

        //With this for will iterate the json object
        for(int i=0;i<jsonObjectSize;i++){
            //We check if the current object email matches the email that we want
            if(json.getString("data["+i+"].email").equals(email)){
                //if it match we save the id of that object of the json array
                idFromJsonThatMatchEmail = Integer.parseInt(json.getString("data["+i+"].id"));
                //We print the id that matches
                System.out.println("the id is: "+idFromJsonThatMatchEmail);
            }
        }
        //now we make the request to the endpoint to get the information of that only user
        res= given()
                .get("/api/users/"+idFromJsonThatMatchEmail);
        //we save the body of the request into a ResponseBody object
        ResponseBody body = res.getBody();
        //We print the information of the user
        System.out.println("The information from the user is");
        System.out.println(body.asPrettyString());


    }

    //The data provider that will contain the names and the jobs of the users that we are going to create
    @DataProvider(name="dataForUsers")
    public Object[][] users(){
        //The objects need to have the Payload format name: and :job
        return new Object [][] {{"morpheus","leader"},{"Ronaldinho","footballer"},{"Verstappen","driver"}};
    }

    //We pass the dataProvider to our createUser test
    @Test(priority = 3,dataProvider = "dataForUsers")
    public void createUser(String name, String job){
        //We declare the baseUrl
        RestAssured.baseURI = "https://reqres.in";
        //We show the user that we are going to create users
        System.out.println("Creating user");
        //We make a responseobject and make a log with all the headers that are going to be send
        //We pass the header content type application/json because we are sending a json object in the payload
        //we pass the body with the method userDetails of the payload class and do a post request to the endpoint
        Response response = given()
                .log().all().header("Content-Type","application/json")
                .body(Payload.userDetails(name,job))
                .when().post("/api/users");
        //We print the response from the endpoint with the user details
        System.out.println("The response for the user recently added is: "+response.getBody().asPrettyString());

    }

    //This test uses a Pojo class to store a request
    @Test(priority = 4)
    public void saveRequestInPOJO(){
        //We declare our baseURL
        RestAssured.baseURI = "https://pokeapi.co/api/v2";
        //We pass the endpoint that we want to hit and save the response into a Response object
        Response response = given()
                .when().get("/berry-firmness");
        //now with json path we save the response to have a route to access the json of the response
        JsonPath jsonPath= response.jsonPath();
        //We save that json as a list into the pokeApiResponseList with the format of the class pokeApiPOJO
        List<pokeApiPOJO> pokeApiResponseList = jsonPath.getList("results",pokeApiPOJO.class);
        //We do a for each cycle to iterate the list
        for(pokeApiPOJO pokeObject : pokeApiResponseList){
            //if an url of one object of the json response contains /berry-firmness/4/
            if(pokeObject.getUrl().contains("/berry-firmness/4/")){
                //We make a request to that endpoint and save the request into another Response object
                Response response2= RestAssured.get(pokeObject.getUrl());
                //We save the body of the response into a ResponseBody object and get the body
                ResponseBody responseBody = response2.getBody();
                //We print the body of the url of the object that contains /berry-firmness/4/
                System.out.println("The body of the request to the url that matches with \"/berry-firmness/4/\" is :"+responseBody.asPrettyString());
                //We end the foreach cycle
                break;
            }
        }
        try{
            //We print that we are going to validate the json schema
            System.out.println("Doing the request to validate the json Schema");

            //basically we have defined a schema.json file that contains the expected structure of the json of the response
            given()
                    .when().get("/berry-firmness")
                    .then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("schema.json")));
            //if the schema from the response is right we print this message
            System.out.println("The schema match with the one previously defined ");

        }catch (AssertionError e){
            //if the schema of the response is not what we have defined in the file we print this other message
            System.out.println("The schema of the request doesn't match with the one that we have defined");
        }
    }
}
