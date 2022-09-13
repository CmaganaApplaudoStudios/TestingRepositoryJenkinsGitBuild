public class Payload {
    //This is a Payload that will help us recreate a body to send in the post request to create a user
    public static String userDetails(String name,String job){
        return "{\n" +
                "    \"name\":\""+name+"\",\n" +
                "    \"job\":\""+job+"\"\n" +
                "}";
        }
    }
