/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Quality of Service
 */
public class FTPClientTesting {

    private static void listFromServerFTP() throws Exception{
        FTPClient client = new FTPClient();
        
        client.connect("localhost");
        client.login("adminroot", "adminroot");
        
        String[] names = client.listNames();
        
        for(String name: names){
            System.out.println("Name: " + name);
        }
        
        FTPFile[] ftpFiles = client.listFiles();
        
        for(FTPFile ff : ftpFiles){
            if(ff.getType() == FTPFile.FILE_TYPE){
                System.out.println("File Name: "+ ff.getName() 
                        + ";;;; " + FileUtils.byteCountToDisplaySize(ff.getSize()));
            }
        }
        client.logout();
        client.disconnect();
    }
    
    private static void downloadFromServerFTP() throws Exception{
        FTPClient client = new FTPClient();
        FileOutputStream fos = null;
        
        client.connect("localhost");
        client.login("adminroot", "adminroot");
        
        String local = ".\\FTPServer2\\RSAPublic.key";
        String remote = "RSAPublic.key";
        
        fos = new FileOutputStream(local);
        client.retrieveFile(remote, fos);
        
        client.noop();
        
        fos.close();
        client.logout();
        client.disconnect();
    }
    
    private static void uploadToServerFTP() throws Exception{
        FTPClient client = new FTPClient();
        FileInputStream fis = null;
        
        client.connect("localhost");
        client.login("adminroot", "adminroot");
        
        String local = ".\\FTPServer2\\UPLOADER.txt";
        String remote = "UPLOADER.txt";
        
        fis = new FileInputStream(local);
        client.storeFile(remote, fis);
        
        client.noop();
        
        fis.close();
        client.disconnect();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        //listFromServerFTP();
        //downloadFromServerFTP();
        //uploadToServerFTP();
    }
}
