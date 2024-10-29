package tests;

import com.github.javafaker.Faker;
import endpoints.UserAEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payload.User;
import utilities.Reports;

import static utilities.Reports.setTCDesc;

public class UserTests extends Reports {
    Faker faker;
    User userPayload;
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][] {
                { 1, "testUser1", "test1@example.com", "Sample body text 1" },
                { 2, "testUser2", "test2@example.com", "Sample body text 2" },
                {3,"Sadhana","abbc@gmail.com","Sample bbodyyy"}
                // Add more test data as needed
        };
    }
    /*
@BeforeClass //i will pass the value to pojo class
    public void setupData()
    {

        faker=new Faker();
        userPayload =new User();

        userPayload.setPostId(faker.idNumber().hashCode());//randaomly generate the id number
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setName(faker.name().name());
        userPayload.setemail(faker.internet().emailAddress());
        userPayload.setBody(faker.lorem().sentence());
    }*/
    @Test()
    public void testGetPosts() {
    System.out.println("Verify the URL");
        setTCDesc("Verify the URL");
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/comments");

        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("URL is verified");
    }

    //post requestes TCs
    @Test(dataProvider = "userData",priority = 1)
   public void testPostUser(int postId, String name, String email, String body)
   {
       System.out.println("Create New user");
       setTCDesc("Test to Post a New User");
       User userPayload = new User();
       userPayload.setPostId(postId);
       userPayload.setName(name);
       userPayload.setemail(email);
       userPayload.setBody(body);

       Response response =UserAEndpoints.createUser(userPayload);
     response.then().log().all();
     Assert.assertEquals(response.getStatusCode(),201);
       int createdId = response.jsonPath().getInt("id");
       userPayload.setId(createdId); // Set the correct ID for subsequent tests

   }
    @Test(priority = 2)
    public void testGetUserById()
    {

        System.out.println("Get User");
        setTCDesc("Test to Get User by ID");
        int idToRead = 7;
        System.out.println("Fetching user with ID: " + idToRead);

        Response response = UserAEndpoints.ReadUser(idToRead);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        // Extract and validate the response data
        String name = response.jsonPath().getString("name");
        String  email=response.jsonPath().getString("email");
        String body = response.jsonPath().getString("body");

        // Validate against expected values for ID 7
        Assert.assertEquals(name, "repellat consequatur praesentium vel minus molestias voluptatum");
        Assert.assertEquals(email,"Dallas@ole.me");
        Assert.assertEquals(body, "maiores sed dolores similique labore et inventore et\nquasi temporibus esse sunt id et\neos voluptatem aliquam\naliquid ratione corporis molestiae mollitia quia et magnam dolor");
    }
    @Test(priority = 3)

        public void testUpdateUserById()
    {
        System.out.println("Update User");

        setTCDesc("Test to Update User by ID");
        User userPayload = new User();
        int existingUserId = 10;  // Use an ID you know exists in your test setup
        userPayload.setId(existingUserId);
        userPayload.setPostId(123);
        userPayload.setName("Subashini");
        userPayload.setemail("updated.email@example.com");
        userPayload.setBody("This is an updated body for testing.");
        // Update user data using payload
        Response response = UserAEndpoints.UpdateUser(existingUserId, userPayload);
        response.then().log().all();

        // Validate update response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // Checking data after update
        int idToRead = userPayload.getId();
        Response responseAfterUpdate = UserAEndpoints.ReadUser(idToRead);
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }
    @Test(priority =4)
public void testDeleteUserById()
    {
        System.out.println("Delete User");
        setTCDesc("Test to Delete User by ID");
        int userIdToDelete = 15;


            Response response = UserAEndpoints.DeleteUser(userIdToDelete);
            Assert.assertEquals(response.getStatusCode(), 200);


       // Response getResponse = UserAEndpoints.ReadUser(userIdToDelete);
      //  Assert.assertEquals(getResponse.getStatusCode(), 404, "User should not exist after deletion");
    }
    }


