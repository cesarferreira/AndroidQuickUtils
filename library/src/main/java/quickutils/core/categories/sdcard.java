package quickutils.core.categories;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import quickutils.core.QuickUtils;
import quickutils.core.exceptions.UnexpectedException;

public class sdcard {

    /**
     * protected constructor
     */
    protected sdcard() {
    }

    /**
     * Check if the SD Card is Available
     *
     * @return true if the sd card is available and false if it is not
     */
    public static boolean isSDCardAvailable() {
        boolean mExternalStorageAvailable = false;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;

        } else {
            // Something else is wrong. It may be one of many other states,
            // but all we need
            // to know is we can neither read nor write
            mExternalStorageAvailable = false;
        }

        return mExternalStorageAvailable;

    }

    /**
     * Delete directory
     *
     * @param path path to be deleted
     * @return returns true if deletion was successful
     */
    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    /**
     * Check if the SD Card is writable
     *
     * @return true if the sd card is writable and false if it is not
     */
    public static boolean isSDCardWritable() {

        boolean mExternalStorageWriteable = false;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media

            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states,
            // but all we need
            // to know is we can neither read nor write
            mExternalStorageWriteable = false;
        }

        return mExternalStorageWriteable;

    }

    /**
     * Creates the specified <code>toFile</code> as a byte for byte copy of the
     * <code>fromFile</code>. If <code>toFile</code> already exists, then it
     * will be replaced with a copy of <code>fromFile</code>. The name and path
     * of <code>toFile</code> will be that of <code>toFile</code>.<br/>
     * <br/>
     * <i> Note: <code>fromFile</code> and <code>toFile</code> will be closed by
     * this function.</i>
     *
     * @param fromFile - FileInputStream for the file to copy from.
     * @param toFile   - FileOutpubStream for the file to copy to.
     */
    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

    /**
     * Creates the specified <code>toFile</code> as a byte for byte copy of the
     * <code>fromFile</code>. If <code>toFile</code> already exists, then it
     * will be replaced with a copy of <code>fromFile</code>. The name and path
     * of <code>toFile</code> will be that of <code>toFile</code>.<br/>
     * <br/>
     * <i> Note: <code>fromFile</code> and <code>toFile</code> will be closed by
     * this function.</i>
     *
     * @param fromFile - File to copy from.
     * @param toFile   - File to copy to.
     */
    public static void copyFile(File fromFile, File toFile) throws IOException {
        copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
    }

    /**
     * Get the SDCard Path
     *
     * @return the complete path to the SDCard
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().toString() + "/";
    }

    /**
     * Get the SDCard Path as a File
     *
     * @return the complete path to the SDCard
     */
    public static File getSDCardPathFile() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * Check if file exists on SDCard or not
     *
     * @param filePath - its the path of the file after SDCardDirectory (no need for
     *                 getExternalStorageDirectory())
     * @return boolean - if file exist on SDCard or not
     */
    public static boolean checkIfFileExists(String filePath) {
        File file = new File(filePath);// getSDCardPath(), filePath);
        return (file.exists() ? true : false);
    }

    /**
     * Create folder in the SDCard
     *
     * @param path
     * @return
     */
    public static boolean createFolder(String path) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/" + path);

        if (!direct.exists()) {
            if (direct.mkdir()) {
                return true;
            }

        }
        return false;
    }

    /**
     * Detailed log with a "yyyy/MM/dd HH:mm:ss.SSS" timestamp
     *
     * @param text        text to append
     * @param logFilePath path to the file
     */
    public static void appendTextToLog(String text, String logFilePath) {
        writeToFile(text, logFilePath, true);

    }

    /**
     * Append a new line of text to a certain file provided by `logFilePath`
     *
     * @param text        text to append
     * @param logFilePath path to the file
     */
    public static void appendTextToFile(String text, String logFilePath) {
        writeToFile(text, logFilePath, false);
    }

    /**
     * private write to file method
     *
     * @param text        text to append
     * @param logFilePath path to the file
     * @param isDetailed  if it should show the timestamp or not
     */
    private static void writeToFile(String text, String logFilePath, boolean isDetailed) {
        if (isSDCardAvailable() && isSDCardWritable() && text != null) {

            try {
                File file = new File(logFilePath);
                OutputStream os = new FileOutputStream(file, true);
                if (isDetailed) {
                    os.write(("---" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime()) + "---\n").getBytes());
                }
                os.write((text + "\n").getBytes());
                // os.write(("------\n").getBytes());
                os.close();
            } catch (Exception e) {
                QuickUtils.log.e("Exception", e);
            }
        } else {
            return;
        }
    }

    /**
     * Writes a file to Disk.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform this operation using another thread.
     *
     * @param file The file to write to Disk.
     */
    public void writeToFile(File file, String fileContent) {
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }


    /**
     * Reads a content from a file.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform the operation using another thread.
     *
     * @param file The file to read from.
     * @return A string with the content of the file.
     */
    public String readFileContent(File file) {
        StringBuilder fileContentBuilder = new StringBuilder();
        if (file.exists()) {
            String stringLine;
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((stringLine = bufferedReader.readLine()) != null) {
                    fileContentBuilder.append(stringLine + "\n");
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileContentBuilder.toString();
    }

    /**
     * Returns a boolean indicating whether this file can be found on the underlying file system.
     *
     * @param file The file to check existence.
     * @return true if this file exists, false otherwise.
     */
    public boolean exists(File file) {
        return file.exists();
    }

    /**
     * Warning: Deletes the content of a directory.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform the operation using another thread.
     *
     * @param directory The directory which its content will be deleted.
     */
    public void clearDirectory(File directory) {
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }
    }


    // Amount of bytes on a megabyte
    public static final int BYTES_TO_MB = 1048576;
    private static final int BUFFER_SIZE = 16384;

    /**
     * Reads the inputStream and returns a byte array with all the information
     *
     * @param inputStream The inputStream to be read
     * @return A byte array with all the inputStream's information
     * @throws IOException The exception thrown when an error reading the inputStream happens
     */
    public static byte[] readAsBytes(InputStream inputStream) throws IOException {
        int cnt = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        InputStream is = new BufferedInputStream(inputStream);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            cnt = is.read(buffer);
            while (cnt != -1) {
                outputStream.write(buffer, 0, cnt);
                cnt = is.read(buffer);
            }
            return outputStream.toByteArray();
        } finally {
            safeClose(is);
        }
    }

    /**
     * Reads the file and returns a byte array with all the information
     *
     * @param file The file to be read
     * @return A byte array with all the file's information
     * @throws IOException The exception thrown when an error reading the file happens
     */
    @SuppressWarnings("resource")
    public static byte[] readAsBytes(File file) throws IOException {
        return readAsBytes(new FileInputStream(file));
    }

    /**
     * @param filePath The path to the file
     * @return a file
     */
    public static File checkFile(String filePath) {
        File file = new File(filePath);
        if (!exist(filePath)) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * @param filePath The file path to the file for verify the existence
     * @return True if exist a file with in the file path
     */
    public static boolean exist(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * Deletes an instance of {@link File} even if it is a directory containing files.<br>
     * If the file is a directory and has contents, then executes itself on every content.
     *
     * @see File#delete()
     * @param file The {@link File} to be deleted.
     */
    public static void forceDelete(File file) {
        if (file.exists()) {

            // If the File instance to delete is a directory, delete all it's
            // contents.
            if (file.isDirectory()) {
                for (File contentFile : file.listFiles()) {
                    forceDelete(contentFile);
                }
            }

            if (file.delete()) {
                log.d("File " + file.getPath() + " was succesfully deleted.");
            } else {
                log.w("File " + file.getPath() + " couldn't be deleted.");
            }
        }
    }

    /**
     * Renames or moves a determined {@link File} instance to a destination defined by another {@link File} instance.<br>
     * Differs from the {@link File#renameTo(File)} method in the fact that this method logs if the operation was
     * successful.<br>
     *
     * @see File#renameTo(File)
     * @param fileToBeMoved The file to be renamed or moved.
     * @param destination The {@link File} instance that denotes the new location
     * @return <b>boolean</b> true if the file has been successfully renamed or moved.
     */
    public static boolean renameOrMove(File fileToBeMoved, File destination) {
        boolean result = fileToBeMoved.renameTo(destination);
        if (result) {
            log.d("File " + fileToBeMoved.getPath() + " was succesfully renamed or moved.");
        } else {
            log.e("File " + fileToBeMoved.getPath() + " couldn't be renamed or moved.");
        }
        return result;
    }

    public static File createTempFile() {
        File file = null;
        try {
            file = File.createTempFile("tempFile", ".tmp");
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return file;
    }

    public static File createTempDir() {
        File file = createTempFile();
        File dir = new File(file.getAbsolutePath() + "dir");
        dir.mkdir();
        return dir;
    }

    public static File toTempFile(String content) {

        File file = null;
        try {
            file = File.createTempFile("tempFile", ".tmp");
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.append(content);
            printWriter.close();
            return file;
        } catch (FileNotFoundException e) {
            throw new UnexpectedException(e);
        }
    }

    public static void createFile(String content, String parentPath, String fileName) {
        try {
            new File(parentPath).mkdirs();
            PrintWriter printWriter = new PrintWriter(parentPath + File.separatorChar + fileName);
            printWriter.append(string.defaultString(content));
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * Receives a File and iterates over all its lines and returns a String.
     *
     * @param file The file
     * @return The content of the file as String
     */
    public static String toString(File file) {
        try {
            return toString(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new UnexpectedException(
                    new StringBuilder("The file doesn't exist [").append(file).append("]").toString(), e);
        }
    }

    public static String toString(InputStream in, Boolean closeStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder contentBuilder = new StringBuilder();
        String text = null;

        // repeat until all lines are read
        try {
            Boolean firstLine = true;
            while ((text = reader.readLine()) != null) {
                if (!firstLine) {
                    contentBuilder.append(System.getProperty("line.separator"));
                }
                firstLine = false;
                contentBuilder.append(text);
            }
        } catch (IOException e) {
            throw new UnexpectedException("Error reading the stream", e);
        } finally {
            if (closeStream) {
                safeClose(in);
            }
        }
        return contentBuilder.toString();
    }

    /**
     * Receives an InputStream and iterates over all its lines and returns a String.
     *
     * @param in the InputStream to be converted
     * @return The content of the file as String
     */
    public static String toString(InputStream in) {
        return toString(in, true);
    }

    /**
     * @param source the source {@link InputStream}
     * @param destin the destin {@link File}
     */
    @SuppressWarnings("resource")
    public static void copyStream(InputStream source, File destin) {
        FileOutputStream out = null;
        try {
            File dir = destin.getParentFile();
            if (dir != null) {
                dir.mkdirs();
            }
            out = new FileOutputStream(destin);
            copyStream(source, out);
        } catch (IOException e) {
            throw new UnexpectedException(
                    new StringBuilder("Error copying the file to [").append(destin).append("]").toString(), e);
        } finally {
            safeClose(out);
        }
    }

    /**
     * @param source the source {@link InputStream}
     * @param destin the destin {@link OutputStream}
     * @param closeOutputStream
     */
    public static void copyStream(InputStream source, OutputStream destin, Boolean closeOutputStream) {
        try {
            int count = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((count = source.read(buffer, 0, BUFFER_SIZE)) != -1) {
                destin.write(buffer, 0, count);
            }
        } catch (IOException e) {
            throw new UnexpectedException("Error copying file", e);
        } finally {
            if (closeOutputStream) {
                safeClose(destin);
            }
        }
    }

    /**
     * @param source the source {@link InputStream}
     * @param destin the destin {@link OutputStream}
     */
    public static void copyStream(InputStream source, OutputStream destin) {
        copyStream(source, destin, true);
    }

    /**
     * @param source the source {@link InputStream}
     * @return the input stream that can be reset {@link java.io.ByteArrayInputStream}
     */
    public static ByteArrayInputStream copy(InputStream source) {
        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
        copyStream(source, tmp, true);
        return new ByteArrayInputStream(tmp.toByteArray());
    }

    public static File zipFile(String directoryToZipPath) {
        ZipOutputStream zipOutputStream = null;
        try {
            File zipFile = createTempFile();
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));

            // Get a list of the files to zip
            File directoryToZip = new File(directoryToZipPath);
            zipFileItem(directoryToZipPath, zipOutputStream, directoryToZip, null);
            return zipFile;
        } catch (FileNotFoundException e) {
            throw new UnexpectedException(e);
        } finally {
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                log.w("Exception thrown when the zipOutputStream was being closed", e);
            }
        }
    }

    private static void zipFileItem(String directoryToZipPath, ZipOutputStream zipOutputStream, File fileItem,
                                    String parentItemPath) {

        try {
            String files[] = fileItem.list();

            for (int i = 0; i < files.length; i++) {
                String itemRelativePath = (parentItemPath != null ? parentItemPath + File.separatorChar : "")
                        + files[i];
                File itemFile = new File(directoryToZipPath + File.separatorChar + itemRelativePath);
                if (itemFile.isDirectory()) {
                    zipFileItem(directoryToZipPath, zipOutputStream, itemFile, itemRelativePath);
                } else {
                    FileInputStream entryInputStream = new FileInputStream(fileItem.getAbsolutePath()
                            + File.separatorChar + files[i]);
                    ZipEntry entry = new ZipEntry(itemRelativePath);
                    zipOutputStream.putNextEntry(entry);
                    copyStream(entryInputStream, zipOutputStream, false);
                    entryInputStream.close();
                }
            }
        } catch (FileNotFoundException e) {
            throw new UnexpectedException(e);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.w("Exception thrown when trying to close the closeable", e);
            }
        }
    }

    /**
     * Counts the size of a directory recursively (sum of the length of all files).
     *
     * @param directory directory to inspect, must not be null
     * @return size of directory in bytes, 0 if directory is security restricted
     */
    public static long getDirectorySize(File directory) {
        if (!directory.exists()) {
            throw new IllegalArgumentException(directory + " does not exist");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory");
        }
        long size = 0;
        File[] files = directory.listFiles();
        if (files == null) {
            // null if security restricted
            return 0L;
        }
        for (int i = 0; i < files.length; i++) {
            File file = files[i];

            if (file.isDirectory()) {
                size += getDirectorySize(file);
            } else {
                size += file.length();
            }
        }
        return size;
    }

    public static long getFileSize(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        return file.length();
    }

    public static float getDirectorySizeInMB(File directory) {
        return getDirectorySize(directory) / (float)BYTES_TO_MB;
    }

    public static float getFileSizeInMB(File file) {
        return getFileSize(file) / (float)BYTES_TO_MB;
    }

}
