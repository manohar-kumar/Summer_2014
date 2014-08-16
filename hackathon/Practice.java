import java.nio.file.*;
import java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.LinkOption.*;
import java.nio.file.StandardCopyOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Practice{


	public static void main(String[] args) {
		Path srcFolder = Paths.get("D:\\prac1");
    	Path destFolder = Paths.get("D:\\prac2");

    	if(!Files.exists(srcFolder)) {
 
           System.out.println("Directory does not exist.");
           //just exit
           System.exit(0);
 
        }else{
 
           try{
        	copyFolder(srcFolder,destFolder);
           }catch(IOException e){
        	e.printStackTrace();
        	//error, just exit
                System.exit(0);
           }
        }
 
    	System.out.println("Done");

		
	}

	public static void copyFolder(Path src, Path dest)
    	throws IOException{
    		
    	if(Files.isDirectory(src)){
 
    		//if directory not exists, create it
    		if(!Files.exists(dest)){
    		   Files.createDirectory(dest);
    		  
    		}
 
    		//list all the directory contents

    		try (DirectoryStream<Path> stream = Files.newDirectoryStream(src)) {
    		for (Path file: stream) {
       		   		
    		   //construct the src and dest file structure
    		   Path srcFile = src.resolve(file);
               String s=file.getFileName().toString();
               String temp=s.substring(s.indexOf("_",0)+1);

    		   Path destFile = dest.resolve(Paths.get(temp));
    		  
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
    	}
    		catch (IOException | DirectoryIteratorException x) {
   
    		System.err.println(x);
					}
				}
			
				else{

    		//if file, then copy it
    		//Use bytes stream to support all file types

    		Files.copy(src,dest,StandardCopyOption.REPLACE_EXISTING);
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
    }

}