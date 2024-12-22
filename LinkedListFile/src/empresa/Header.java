package empresa;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import auxiliar.Convert;

/*
 * primer elemento guardado
 * ultimo elemento guardado
 * nombre empresa
 */
public class Header 
{
    private String name;
    private long first;
    private long last;
    private File dir;


    public Header(String path) throws IOException, ClassNotFoundException
    {
        dir = new File(path);
        if(dir.exists())
            readHeader();
    }

    public Header(String name, String path) throws IOException, ClassNotFoundException
    {
        this(path);
        if(!dir.exists())
            buildHeader(name);
    }

    private void buildHeader(String name) throws IOException, ClassNotFoundException
    {
        RandomAccessFile file = new RandomAccessFile(dir, "rw");
        setInFileFirst(-1, file);
        setInFileLast(-1, file);
        setInFileName(name, file);        
        file.close();
    }

    private void readHeader() throws IOException, ClassNotFoundException
    {
        RandomAccessFile file = new RandomAccessFile(dir, "rw");
        setFirst(file.readLong());
        setLast(file.readLong());
        int size = file.readInt();
        byte[] bytes = new byte[size];
        file.read(bytes);
        setName((String) Convert.toObject(bytes));
        file.close();
    }
    public void setInFileName(String name, RandomAccessFile file) throws IOException {
        setName(name);
        byte[] bytes = Convert.toBytes(name);
        file.writeInt(bytes.length);
        file.write(bytes);
    }
    public void setInFileFirst(long first, RandomAccessFile file) throws IOException {
        setFirst(first);
        file.seek(0);
        file.writeLong(first);//primer elemento guardado
    }
    public void setInFileLast(long last, RandomAccessFile file) throws IOException {
        setLast(last);
        file.seek(0);
        file.skipBytes(8);
        file.writeLong(last);//ultimo elemento guardado
    }

    private void setName(String name) {
        this.name = name;
    }
    private void setFirst(long first) {
        this.first = first;
    }
    private void setLast(long last) {
        this.last = last;
    }
    public String getName() {
        return name;
    }
    public long getFirst() {
        return first;
    }
    public long getLast() {
        return last;
    }
    
    public File getDir() {
        return dir;
    }

    
}
