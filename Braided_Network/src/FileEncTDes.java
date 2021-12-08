import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class FileEncTDes {
    public void enc(String in, String out, String k) throws FileNotFoundException, IOException, InterruptedException {
        TDES b = new TDES();
        FileReader r = new FileReader(k);
        String key="", pt="";
        int i;
        while((i=r.read())!=-1){
            key+=(char)i;
        }
        key = b.strToHex(key);
        r.close();
        r = new FileReader(in);
        FileWriter w = new FileWriter(out);
        i=0;
        while(i!=-1){
            for(int c=0;c<16 && (i=r.read())!=-1;c++)
            {
                pt+=(char)i;
            }
            if(pt.length()==0)
            break;
            String pad = "";
            for(int j=0;j<16-pt.length();j++)
                pad += " ";
            pt = pt + pad;
            pt = b.strToHex(pt);
            w.write(b.enc(pt, key));
            pt="";
        }
        w.close();
        r.close();
    }
    public void dec(String in, String out, String k) throws FileNotFoundException, IOException, InterruptedException {
        TDES b = new TDES();
        FileReader r = new FileReader(k);
        String key="", pt="";
        int i;
        while((i=r.read())!=-1){
            key+=(char)i;
        }
        key = b.strToHex(key);
        r.close();
        r = new FileReader(in);
        FileWriter w = new FileWriter(out);
        i=0;
        while(i!=-1){
            for(int c=0;c<32 && (i=r.read())!=-1;c++)
            {
                pt+=(char)i;
            }
            if(pt.length()==0)
            break;
            // String pad = "";
            // for(int j=0;j<32-pt.length();j++)
            //     pad += " ";
            // pt = pt + pad;
            pt = b.hexToStr(b.dec(pt, key));
            //System.out.println(b.dec(pt, key));
            w.write(pt);
            pt="";
        }
        w.close();
        r.close();
    }
    public static void main(String args[]) throws IOException, FileNotFoundException, InterruptedException{
        long t;
        for(int i=0;i<20;i++){
            t = System.nanoTime();
            new FileEnc().enc("file.txt", "out.txt", "key.txt");
            System.out.println(System.nanoTime()-t);
            new FileEnc().dec("out.txt", "out1.txt", "key.txt");
        }
    }
}
