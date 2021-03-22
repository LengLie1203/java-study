package com.lqw.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteFile {

    public static void main(String[] args) throws IOException {
        String path="./io/file";
        File writeFile= new File(path+"/write.txt") ;//new File("../write.txt");

        if(!writeFile.exists()){
            File pathFile = new File(path);
            pathFile.mkdirs();
            writeFile.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(writeFile,true);

        FileInputStream fileInputStream=new FileInputStream(writeFile);

        FileChannel outChannel = fileOutputStream.getChannel();

        FileChannel inChannel=fileInputStream.getChannel();

        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bw.newLine();
        bw .write("new line");
        bw.flush();

        ByteBuffer bb=ByteBuffer.allocate(1024) ;
        while (inChannel.read(bb)!=-1){
            System.out.println(bb);
            System.out.println(new String(bb.array(),0,bb.position()));
        }

    }
}
