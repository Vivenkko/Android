package vivenkko.dd;

class DDCharacter {
    private String name;
    private String imageURL;
    private String classRace;
    private String description;

    public DDCharacter() {

    }

    public DDCharacter(String name, String imageURL, String classRace, String description) {
        this.name = name;
        this.imageURL = imageURL;
        this.classRace = classRace;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getClassRace() {
        return classRace;
    }

    public void setClassRace(String classRace) {
        this.classRace = classRace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
