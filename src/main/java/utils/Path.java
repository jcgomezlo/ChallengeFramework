package utils;

import utils.reader.PropertiesReader;

public class Path {

    private static final String PATH_FILE = "src/main/resources/config.properties";
    private static Path instance;
    private String domain;
    private String apiKey;
    private String authPath;
    private String requestTokenPath;
    private String sessionWithLoginPath;
    private String newSessionPath;
    private String listPath;
    private String sessionId;
    private String addMoviePath;
    private String searchPath;

    private Path(){

        PropertiesReader properties= new PropertiesReader(PATH_FILE);

        this.domain = properties.getValue("domain");
        this.apiKey = properties.getValue("api_key");
        this.authPath = properties.getValue("authentication_path");
        this.requestTokenPath = properties.getValue("request_token_path");
        this.sessionWithLoginPath = properties.getValue("session_with_login_path");
        this.newSessionPath = properties.getValue("new_session_path");
        this.listPath = properties.getValue("list_path");
        this.sessionId = properties.getValue("session_id");
        this.addMoviePath = properties.getValue("add_movie_path");
        this.searchPath = properties.getValue("search_path");

    }

    public static Path getInstance(){
        if(instance == null){
            instance = new Path();
        }
        return instance;
    }

    public String getDomain() {
        return domain;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getRequestTokenPath() {
        return requestTokenPath;
    }

    public String getSessionWithLoginPath() {
        return sessionWithLoginPath;
    }

    public String getAuthPath() {
        return authPath;
    }

    public String getNewSessionPath() {
        return newSessionPath;
    }

    public String getListPath() {
        return listPath;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getAddMoviePath() {
        return addMoviePath;
    }

    public String getSearchPath() {
        return searchPath;
    }
}
