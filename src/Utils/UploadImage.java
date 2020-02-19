/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

//import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author mosla
 */
public class UploadImage {

    // Creating FTP Client instance
    FTPClient ftp = null;

    public UploadImage(String host, int port, String username, String password) throws Exception {

        ftp = new FTPClient();
        //ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host, port);
        //System.out.println("FTP URL is:" + ftp.getDefaultPort());
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login(username, password);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    // Method to upload the File on the FTP Server
    public String uploadFTPFile(String localFileFullName, String fileName, String hostDir)
            throws Exception {
        String url = "";
        try {
            InputStream input = new FileInputStream(new File(localFileFullName));

            this.ftp.storeFile(hostDir + fileName, input);
            url = hostDir + fileName;
        } catch (Exception e) {

        }
        return url;
    }

    // Download the FTP File from the FTP Server
    public void downloadFTPFile(String source, String destination) {
        try (FileOutputStream fos = new FileOutputStream(destination)) {
            this.ftp.retrieveFile(source, fos);
        } catch (IOException e) {
            System.out.println("can't");
            e.printStackTrace();
        }
    }

    // Disconnect the connection to FTP
    public void disconnect() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already saved to server
            }
        }
    }

    public String upload(String location, String nom, String destination) {
        String url = "";
        try {
            //UploadImage ftpobj = new UploadImage("127.0.0.1", 21, "yassine", "esprit");
            url = uploadFTPFile(location, nom, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public String generatImageName(String location) {
        location = location.replaceAll(" ", "");
        int j = 0;
        for (int i = 0; i < location.length(); i++) {
            if (location.charAt(i) == '\\') {
                j = 0;
            } else {
                j++;
            }
        }
        String nom = location.substring(location.length() - j);

        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String generatedString = "";
        for (int x = 0; x < 5; x++) {
            int i = (int) Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
            generatedString += chars.charAt(i);
        }
        if (nom.length() < 100) {
            return generatedString + nom;
        } else {
            String nom1 = nom.substring(location.length() - 100);
            return generatedString+nom1;
            
        }

    }
}
