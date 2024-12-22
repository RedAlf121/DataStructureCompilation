package empresa;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import auxiliar.Convert;
import trabajadores.Trabajador;

public class Empresa
{
    private Header head;

    public Empresa(String name, String path) throws ClassNotFoundException, IOException
    {
        File file = new File(path);
        buildHeader(name, path, file);
    }

    public Empresa(String path) throws ClassNotFoundException, IOException
    {
        this("", path);
    }

    private void buildHeader(String name, String path, File file) throws IOException, ClassNotFoundException {
        if(!file.exists())
            head = new Header(name, path);
        else head = new Header(path);
    }

    public void add(Trabajador trab) throws IOException, ClassNotFoundException
    {
        RandomAccessFile file = new RandomAccessFile(head.getDir(), "rw");
        //las variables se inicializan con los valores del caso de que este vacio el fichero
        long length = file.length();
        long first = (head.getLast() == -1)? length : head.getFirst();
        long last = length;
        long prev = (head.getFirst() == -1)? -1 : head.getLast();//registro anterior
        file.seek(length);
        writeTrabajador(trab, file, prev, -1);
        head.setInFileFirst(first, file);
        head.setInFileLast(last, file);

        file.close();
    }

    private void writeTrabajador(Trabajador trab, RandomAccessFile file, long prev, long next) throws IOException {
        long actual = file.getFilePointer();
        byte[] bytes = Convert.toBytes(trab); 
        file.writeLong(prev); //anterior
        if(prev != -1)
        {
            file.seek(prev);
            file.skipBytes(8);
            file.writeLong(actual);
            file.seek(actual);
            file.skipBytes(8);
        }                
        file.writeLong(next); //siguiente
        if(next != -1)
        {
            file.seek(next);
            file.writeLong(actual);
            file.seek(actual);
            file.skipBytes(16);
        }
        int length = bytes.length;
        file.writeInt(length); //size del registro
        file.write(bytes); //registro
    }

    private Trabajador readTrabajador(RandomAccessFile file, long pos) throws IOException, ClassNotFoundException
    {
        file.seek(pos);
        file.skipBytes(16);
        int size = file.readInt();
        byte[] bytes = new byte[size];
        file.read(bytes);
        return (Trabajador) Convert.toObject(bytes);
    }

    public void deleteLast()throws IOException, ClassNotFoundException
    {
        RandomAccessFile file = new RandomAccessFile(head.getDir(), "rw");
        long first = -1;
        long last = -1;
        file.seek(head.getLast());
        long prev = file.readLong();
        if(prev != -1)
        {
            first = head.getFirst();
            file.seek(prev);
            file.skipBytes(8);
            file.writeLong(-1);
            last = prev;
        }
        head.setInFileFirst(first, file);
        head.setInFileLast(last, file);
        file.close();
    }

    public void delete(Trabajador trab) throws IOException, ClassNotFoundException
    {
        RandomAccessFile file = new RandomAccessFile(head.getDir(), "rw");
        long pos = findTrabajador(trab, file);
        if(pos != -1)
        {
            long first = head.getFirst();
            long last = head.getLast();
            //a partir de ahi eliminar
            file.seek(pos);
            long left = file.readLong();//anterior
            long right = file.readLong();//siguiente
            //si no tiene anterior, el encabezado debe apuntar al siguiente de ese registro
            if(left == -1)
                first = right;
            else //si tiene anterior ese toma el siguiente de ese registro
            {
                file.seek(left);
                file.skipBytes(8);
                file.writeLong(right);
            }
            //si no tiene siguiente el encabezado toma el anterior de ese registro
            if(right == -1) last = left;
            else
            {
                //si tiene siguiente, el anterior de ese siguiente pasa a ser el anterior 
                //del registro                
                file.seek(right);
                file.writeLong(left);
            }
            head.setInFileFirst(first, file);
            head.setInFileLast(last, file);
        }
        file.close();
    }

    public long findTrabajador(Trabajador trab, RandomAccessFile file)
            throws IOException, ClassNotFoundException {
        long left = head.getFirst();
        long right = head.getLast();
        long pos = -1;
        //Recorrer hasta que uno encuentre el elemento o hasta que coincidan o se crucen
        do
        {
            //trabajo con la izquierda
            Trabajador read = readTrabajador(file, left);
            if(trab.getID().equals(read.getID()))
                pos = left;
            else
            {
                //trabajo con la derecha
                read = readTrabajador(file, right);
                if(trab.getID().equals(read.getID()))
                    pos = right;
            }
            //actualizar las posiciones
            file.seek(left);
            file.skipBytes(8);
            left = file.readLong();//tomando la posicion siguiente
            file.seek(right);
            right = file.readLong();
        }while(left != right && pos == -1);
        return pos;
    }

    public void readAll() throws IOException, ClassNotFoundException
    {
        RandomAccessFile file = new RandomAccessFile(head.getDir(), "rw");
        long pos = file.readLong();
        while(pos != -1)
        {   
            Trabajador trab = readTrabajador(file, pos);
            System.out.println(trab.getNombre());
            file.seek(pos);
            file.skipBytes(8);
            pos = file.readLong();
        }
        file.close();
    }

    public void update(Trabajador trab, Trabajador newTrab) throws IOException, ClassNotFoundException
    {
        RandomAccessFile file = new RandomAccessFile(head.getDir(), "rw");

        long pos = findTrabajador(trab, file);
        if(pos!=-1)
        {
            file.seek(pos);
            long prev = file.readLong();
            long next = file.readLong();
            long length = file.length();
            file.seek(length);
            writeTrabajador(newTrab, file, prev, next);
            if(prev == -1)
                head.setInFileFirst(length, file);
            
        }

        file.close();
    }


}