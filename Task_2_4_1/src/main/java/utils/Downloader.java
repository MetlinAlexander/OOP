package utils;

/**
 * class for download repositories.
 */
public class Downloader {

    private String path = "";

    /**
     * default consturctor.
     *
     * @param path path where save repo
     */
    public Downloader(String path) {
        this.path = path;
    }

    /**
     * method to download repo from github.
     *
     * @param repo repo name
     * @param folder name of folder
     * @return false/true
     */
    public boolean download(String repo, String folder) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("git",
                    "clone",
                    repo,
                    this.path + "/" + folder);
            Process process = processBuilder.start();
            int code = process.waitFor();
            return (code == 0);
        } catch (Exception e) {
            return false;
        }
    }
}
