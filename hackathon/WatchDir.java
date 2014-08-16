
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;

import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Example to watch a directory (or tree) for changes to files.
 */

public class WatchDir {

    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private final boolean recursive;
    private boolean trace = false;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */



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

               Path destFile = dest.resolve(file.getFileName());
              
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



    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                System.out.format("register: %s\n", dir);
            } else {
                if (!dir.equals(prev)) {
                    System.out.format("update: %s -> %s\n", prev, dir);
                }
            }
        }
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    WatchDir(Path src, boolean recursive) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;

        if (recursive) {
            System.out.format("Scanning %s ...\n", src);
            registerAll(src);
            System.out.println("Done.");
        } else {
            register(src);
        }

        // enable trace after initial registration
        this.trace = true;
    }

    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents(Path dir) {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path src = keys.get(key);
            if (src == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                    if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = src.resolve(name);
                String s1,s2;
                s1=child.toString().substring(5);//code_red: to remove initial /test from pathname_may not be needed in final code
                int b=s1.lastIndexOf("\\");
                if (Files.isDirectory(child, NOFOLLOW_LINKS)){
                    s2=s1;
                }
                else {
                s2=s1.substring(0,b+1)+"kethan_"+s1.substring(b+1);}
                Path man=dir.resolve(Paths.get(s2));
               
                // print out event
                System.out.format("%s: %s\n", event.kind().name(), child);
                try{

                 copyFolder(child,man);}
                 catch (IOException x) {
                        System.out.println(x);
                    }
                if (kind == ENTRY_DELETE){
                    try {
                        System.out.println(dir.resolve(Paths.get(child.toString().substring(5)).toString()));
                       if (Files.exists(man)){ Files.delete(man);}
                        if (Files.exists(dir.resolve(Paths.get(child.toString().substring(5)).toString()))){
                       Files.delete(dir.resolve(Paths.get(child.toString().substring(5)).toString()));
                        }
                    }
                    
                    catch (IOException x) {
                        System.out.println(x);
                    }
                }

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    static void usage() {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
        // parse arguments
        if (args.length == 0 || args.length > 3)
            usage();
        boolean recursive = false;
        int dirArg = 0;
        if (args[0].equals("-r")) {
            if (args.length < 3)
                usage();
            recursive = true;
            dirArg++;
        }

        // register directory and process its events
        Path src = Paths.get(args[dirArg]);
         dirArg++;
         Path dir=Paths.get(args[dirArg]);
        new WatchDir(src, recursive).processEvents(dir);
    }
}