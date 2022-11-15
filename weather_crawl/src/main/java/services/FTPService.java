package services;

import constants.StrConstants;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FTPService {

    public boolean sendFileFtp(String ftpHost, String userName, String password,
                               String localFilePath, String remoteFileName, String remoteFolderName) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(ftpHost);
        ftp.login(userName, password);
        System.out.println("Connected to " + ftpHost + ".");
        int reply = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            System.err.println("FTP server refused connection.");
            System.exit(1);
        }
        String remoteFilePath = remoteFileName;

        if (!ftp.changeWorkingDirectory(remoteFolderName)) {
            ftp.makeDirectory(remoteFolderName);
            remoteFilePath = remoteFolderName.concat("/" + remoteFileName);
        }

        return uploadSingleFile(ftp, localFilePath, remoteFilePath);
    }

    public boolean uploadSingleFile(FTPClient ftpClient, String localFilePath, String remoteFilePath) {
        boolean success = false;
        try {
            InputStream inputStream = new FileInputStream(localFilePath);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            success = ftpClient.storeFile(remoteFilePath, inputStream);
            inputStream.close();
            ftpClient.logout();
            System.out.println("Disconnected ....");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean checkDirectoryExists(FTPClient ftpClient, String dirPath) throws IOException {
        return ftpClient.changeWorkingDirectory(dirPath);
    }

    public boolean checkFileExists(FTPClient ftpClient, String fileRemote) throws IOException {
        InputStream inputStream = ftpClient.retrieveFileStream(fileRemote);
        int returnCode = ftpClient.getReplyCode();
        if (inputStream == null || returnCode == 550) {
            return false;
        }
        return true;
    }

    public void downloadSingleFile(FTPClient ftpClient, String remoteFile, String localPath) throws IOException {
        System.out.println(remoteFile);
        System.out.println(localPath);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(localPath));
        InputStream is = ftpClient.retrieveFileStream(remoteFile);
        byte[] bytesArray = new byte[102400];
        int bytesRead;
        while ((bytesRead = is.read(bytesArray)) != -1) {
            os.write(bytesArray, 0, bytesRead);
        }
        os.close();
        is.close();
    }

    public void downloadFileFTP(String ftpHost, String username, String password, String fileLocalPath, String remoteFileName) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(ftpHost);
        ftp.login(username, password);
        System.out.println("Connected to " + ftpHost + ".");

        downloadSingleFile(ftp, remoteFileName, fileLocalPath);
    }
}
