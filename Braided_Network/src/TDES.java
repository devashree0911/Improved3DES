public class TDES {
    public String strToHex(String s){
        String t = "";
        for(byte b:s.getBytes()){
            t += String.format("%02x", b);
        }
        return t;
    }
    public String hexToStr(String s){
        String t="";
        for(int i=0;i<s.length();i+=2){
            t += (char)Long.parseLong(s.substring(i, i+2), 16);
        }
        return t;
    }
    public String enc(String pt, String k) throws InterruptedException{
        DES d1 = new DES();
        DES b1 = new DES();
        DES d2 = new DES();
        DES b2 = new DES();
        DES d3 = new DES();
        DES b3 = new DES();

        // pt = strToHex(pt);
        // k = strToHex(k);

        String pt1 = pt.substring(0, 16);
        String pt2 = pt.substring(16, 32);
        String k1 = k.substring(0, 16);
        String k2 = k.substring(16, 32);

        d1.start(pt1, k1, 'e'); // First block
        d1.join(); // First block

        d2.start(d1.o, k2, 'd'); // Second block
        d2.join(); // Second block

        d3.start(d2.o, k1, 'e'); // Third block
        d3.join(); // Third block

        b1.start(pt2, k1, 'e'); // First block
        b1.join(); // First block

        b2.start(b1.o, k2, 'd'); // Second block
        b2.join(); // Second block

        b3.start(b2.o, k1, 'e'); // Third block
        b3.join(); // Third block

        pt = d3.o+b3.o;
        return pt;
    }
    public String dec(String ct, String k) throws InterruptedException{
        DES d1 = new DES();
        Blowfish b1 = new Blowfish();
        DES d2 = new DES();
        Blowfish b2 = new Blowfish();
        DES d3 = new DES();
        Blowfish b3 = new Blowfish();

        // ct = strToHex(ct);
        // k = strToHex(k);

        String ct1 = ct.substring(0, 16);
        String ct2 = ct.substring(16, 32);
        String k1 = k.substring(0, 16);
        String k2 = k.substring(16, 32);

        d1.start(ct1, k1, 'd'); // First block
        d1.join(); // First block

        d2.start(d1.o, k2, 'e'); // Second block
        d2.join(); // Second block

        d3.start(d2.o, k1, 'd'); // Third block
        d3.join(); // Third block

        b1.start(ct2, k1, 'd'); // First block
        b1.join(); // First block

        b2.start(b1.o, k2, 'e'); // Second block
        b2.join(); // Second block

        b3.start(b2.o, k1, 'd'); // Third block
        b3.join(); // Third block

        ct = d3.o+b3.o;
        return ct;
    }
    
}