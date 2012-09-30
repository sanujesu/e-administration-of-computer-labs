/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author NOAH
 */
public class FileUtil {

    public static void storeUser(String username) {
        FileOutputStream fop = null;
        File file;
        String content = username;

        try {

            file = new File(EConstant.E_TEMP_FILE);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getCurrentUser() {
         String strLine = "";
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(EConstant.E_TEMP_FILE);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
           
            //Read first line
            strLine = br.readLine();
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            strLine="";
        }
        return strLine;
    }
}
