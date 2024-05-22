package utils;

public class Downloader {

    private String path = "";

    public Downloader (String path) {
        this.path = path;
    }

    public boolean download (String repo, String folder) {
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("git", "clone", repo, this.path + "/" + folder);
            Process process = processBuilder.start();
            int code = process.waitFor();
            return (code == 0);
        } catch (Exception e) {
            return false;
        }
    }
}
