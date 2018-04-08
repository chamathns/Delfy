package sample.util;


public class FileProfile {

    private String fileName;
    private String time;
    private String location;
    private String algorithm;

    public FileProfile(String fileName, String time, String location, String algorithm){
        this.fileName = fileName;
        this.time = time;
        this.location = location;
        this.algorithm = algorithm;
    }
    public FileProfile(){

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}