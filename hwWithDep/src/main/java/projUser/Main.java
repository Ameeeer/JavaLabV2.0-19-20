package projUser;

public class Main {
    public static void main(String[] args) {
        System.out.println("Write count of files");
        DownloadInterface download = new Download();
        download.download();
        System.out.println("Downloaded");
    }
}