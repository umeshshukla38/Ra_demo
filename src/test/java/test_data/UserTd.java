package test_data;
import org.json.JSONObject;

public class UserTd {
    
    public static int [] pagination = {0, 1, 2};  
    public static final int INVOCATION_COUNT = 3; // must be equal to paination

    public static String [] Users_Object_Keys = {"id", "email", "first_name", "last_name", "avatar"}; 


    public static JSONObject createUserTd(){
        JSONObject user = new JSONObject();
        user.put("name", "Test_01");
        user.put("job", "Qa Lead");
        return user;
    }
}
