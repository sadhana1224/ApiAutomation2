package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

import static io.restassured.RestAssured.given;

public class UserAEndpoints {

  public static Response createUser(User payload)
    {

     Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);
     return response;


    }
    public static Response ReadUser(int  Id)
    {

        Response response=  given()
                .pathParam("id",Id)
                .when()
                .get(Routes.get_url);
        return response;


    }

    public static Response UpdateUser(int Id,User payload)
    {

        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",Id)
                .body(payload)
                .when()
                .put(Routes.update_url);
        return response;


    }

    public static Response DeleteUser(int  Id)
    {

        Response response= given()
                .pathParam("id",Id)
                .when()
                .delete(Routes.delete_url);
        return response;


    }

    }

