package services;

import java.io.*;

public class FileService {
    // create folder
    private void creatFolder(String absolutePath) {
        File folder = new File(absolutePath);
        if (!folder.exists()) folder.mkdir();
    }

    // Create file
    private File createFile(String filePath) {
        return new File(filePath);
    }

    // save the file into local storage
    public boolean saveCSV(String path, String naturalKey, String province, String forecastDate, String description, String minTemp, String maxTemp, String humidity, String windSpeed, String sunrise, String sundown) {
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(naturalKey + "," + province + "," + forecastDate + "," + description + "," + minTemp + "," + maxTemp + "," + humidity + "," + windSpeed + "," + sunrise + "," + sundown);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // send file to server
    public boolean sendFileFTP(String ip, String username, String password, String fileLocalPath, String remoteFileName, String remoteFolderName) throws IOException {
        FTPService ftpServices = new FTPService();
        return ftpServices.sendFileFtp(ip, username, password, fileLocalPath, remoteFileName, "logs/" + remoteFolderName);
    }

    // download file from server
    public String downloadCSV(String ip, String username, String password, String fileLocalPath, String remoteFileName) throws IOException {
        FTPService ftpService = new FTPService();
        ftpService.downloadFileFTP(ip, username, password, fileLocalPath, remoteFileName);

        return null;
    }

    public boolean deleteFileLocal(String localPath) {
        File file = new File(localPath);
        return file.exists() ? file.delete() : false;
    }
}
