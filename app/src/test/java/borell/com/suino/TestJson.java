package borell.com.suino;


public class TestJson {
    public static String validCategory = "sports";
    public static int validLevel = 1;
    public static String validDescription = "This should be a very valid description";
    public static double validLatitude = 22.3333;
    public static double validLongitude = 44.1231;
    public static String validKeyword = "beachvolley";
    public static int validPrice = 10;
    public static int validGroupSize = 3;
    public static String validCourseId = "4edd40c86762e0fb12000102";

    public static String courseJson = "{" +
            "\"_id\":\"" + validCourseId + "\"," +
            "\"category\":\"" + validCategory + "\"," +
            "\"description\":\"" + validDescription + "\"," +
            "\"groupSize\":" + validGroupSize + "," +
            "\"keywords\":" + "[\"" + validKeyword + "\"]," +
            "\"level\":" + + validLevel + "," +
            "\"location\":[" + validLatitude + ","+ validLongitude + "]," +
            "\"price\":" + validPrice +
            "}";


    public static String validUserId = "1231242fsdf";
    public static String validFirstName = "Dani";
    public static String validPicture = "somelink.com/picture";
    public static int validAge = 25;
    public static String userJson = "{" +
            "\"_id\":\"" + validUserId + "\"," +
            "\"fbName\":\"" + validFirstName + "\"," +
            "\"fbPictureLink\":\"" + validPicture + "\"," +
            "\"age\":" + validAge +
            "}";

    public static String validEventId = "4edd40c86762e0fb12000102";
    public static int validStart = 1450792800;
    public static int validEnd = 1450796400;

    public static String eventJson = "{" +
            "\"_id\":\"" + validEventId + "\"," +
            "\"start\":" + validStart + "," +
            "\"end\":" + validEnd +
            "}";

    public static String resultItemJson = "{" +
            "\"event\":" + eventJson + "," +
            "\"course\":" + courseJson + "," +
            "\"user\":" + userJson +
            "}";

    public static String resultJson = "[" +
            resultItemJson + "," +
            resultItemJson + "," +
            resultItemJson +
            "]";

}
