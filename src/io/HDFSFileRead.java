
//http://hadooptutorial.info/java-interface-to-hdfs-file-read-write/

package hadoop.read;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSFileRead {

  public static void main(String[] args) {
    Configuration conf = new Configuration();
    try {
      FileSystem fs = FileSystem.get(conf);
      // Hadoop DFS Path - Input file
      Path inFile = new Path(args[0]);
        
      // Check if input is valid
      if (!fs.exists(inFile)) {
        System.out.println("Input file not found");
        throw new IOException("Input file not found");
      }
			
      // open and read from file
      FSDataInputStream in = fs.open(inFile);
      // system.out as output stream to display 
      //file content on terminal 
      OutputStream out = System.out;
      byte buffer[] = new byte[256];
      try {
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) > 0) {
          out.write(buffer, 0, bytesRead);
        }
      } catch (IOException e) {
        System.out.println("Error while copying file");
      } finally {
         // Closing streams
        in.close();
        out.close();
      }      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }		 
  }
}

