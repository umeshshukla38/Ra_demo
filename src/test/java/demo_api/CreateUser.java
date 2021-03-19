package demo_api;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Endpoints;
import common.GlobalConfigHandler;
import common.RequestHandler;
import config.BaseUrl;
import io.restassured.response.Response;
import test_data.UserTd;

public class CreateUser extends BaseUrl{
    
    String url = "";
    RequestHandler request = new RequestHandler();
    private static Logger log = LoggerFactory.getLogger(GetUsers.class);
    
    @BeforeTest
    public void prepEnv(){
        GlobalConfigHandler.setLocalProps();
        String baseurl = baseurl();
        url = baseurl+Endpoints.createUser;
    }

    @Test(enabled = true, priority = 1)
    public void createNewUser(){
       Response response = request.postDataInRequest(url, UserTd.createUserTd());
       JSONObject user = new JSONObject(response.asString());
       int id = Integer.parseInt(user.get("id").toString().trim());
       Assert.assertEquals(id > 0, true, "Id can't be less than 0 or any other format instead of integer!");
       log.info("New User created with name : "+user.optString("name").toString().trim()+ " and id is : "+id);
    }
}
