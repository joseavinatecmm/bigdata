//http://hadooptutorial.info/java-interface-to-hdfs-file-read-write/

package hadoop.write; 


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSFileWrite {

  public static void main(String[] args) {
    Configuration conf = new Configuration();
    try {
      FileSystem fs = FileSystem.get(conf);
      // Hadoop DFS Path - Input & Output file
      Path inFile = new Path(args[0]);
      Path outFile = new Path(args[1]);
      // Verification
      if (!fs.exists(inFile)) {
        System.out.println("Input file not found");
        throw new IOException("Input file not found");
      }
      if (fs.exists(outFile)) {
        System.out.println("Output file already exists");
        throw new IOException("Output file already exists");
      }
    
      // open and read from file
      FSDataInputStream in = fs.open(inFile);
      // Create file to write
      FSDataOutputStream out = fs.create(outFile);
			
      byte buffer[] = new byte[256];
      try {
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) > 0) {
          out.write(buffer, 0, bytesRead);
          }
      } catch (IOException e) {
        System.out.println("Error while copying file");
      } finally {
        in.close();
        out.close();
      }			
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
