package utils;

public class PathConstructor {
    private Path path;

    public PathConstructor(){
        path = Path.getInstance();
    }

    public String construct(String text){
        return path.getDomain()  + text +  path.getApiKey();
    }

    public String construct(String text, String sessionId){
        return path.getDomain()  + text +  path.getApiKey() + path.getSessionId() + sessionId;
    }

    public String getRequestTokenEndPoint(){
        return construct(path.getAuthPath() + path.getRequestTokenPath());
    }
    public String getSessionWithLoginEndPoint(){
        return construct(path.getAuthPath() + path.getSessionWithLoginPath());
    }
    public String getNewSessionEndPoint(){
        return construct(path.getAuthPath() + path.getNewSessionPath());
    }
    public String getListEndPoint(String sessionId){
        return construct(path.getListPath(), sessionId);
    }

    public String getAddMovieToListEndPoint(String sessionId, String listId){
        return construct(path.getListPath() + "/" + listId + path.getAddMoviePath() , sessionId);
    }

    public String getSearchEndPoint(String movie){
        return construct(path.getSearchPath()) + "&query="+movie;
    }
}
