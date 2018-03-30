package anna.poddubnaya.domain.entity;


public class UserEntity {
    private String username;
    private String profileUrl;
    private int age;
    private String objectId;

    public UserEntity() {
    }

    public UserEntity(String username, String profileUrl, int age) {
        this.username = username;
        this.profileUrl = profileUrl;
        this.age = age;
    }

    public UserEntity(String username, String profileUrl, int age, String objectId) {
        this.username = username;
        this.profileUrl = profileUrl;
        this.age = age;
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }


    public String getProfileUrl() {
        return profileUrl;
    }

    public int getAge() {
        return age;
    }

    public String getObjectId() {
        return objectId;
    }
}
