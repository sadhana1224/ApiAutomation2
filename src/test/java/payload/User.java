package payload;
//pojo class
public class User {
    public int getPostId()
    {

        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {

        return email;
    }
    public  void setemail(String email)
    {
        this.email=email;
    }
    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
    int postId;
    int id;
    String name;
    String email;
    String body;

    int userStatus=0;
}
