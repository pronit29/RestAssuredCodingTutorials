package resources;

public enum APIResources {

    Add("/maps/api/place/add/json"),
    Get("/maps/api/place/get/json"),
    Delete("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
