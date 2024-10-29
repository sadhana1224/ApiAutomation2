package endpoints;
//https://jsonplaceholder.typicode.com/posts
//https://jsonplaceholder.typicode.com/comments
public class Routes {
    public static String base_url="https://jsonplaceholder.typicode.com";
    //user module
    public static String post_url = base_url + "/comments";               // Create or get all posts
    public static String get_url = base_url + "/comments/{id}";           // Get a specific post by ID
    public static String update_url = base_url + "/comments/{id}";        // Update a specific post by ID
    public static String delete_url = base_url + "/comments/{id}";
}

