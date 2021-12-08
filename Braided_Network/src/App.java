import java.util.Scanner;
public class App {
    public static void main(String[] args) throws InterruptedException {
        Braid b = new Braid();
        Scanner sc = new Scanner(System.in);
        int n;
        String pt, k;
        do{
            System.out.println(
                "===MENU===\n"+
                "1. Encryption\n"+
                "2. Decryption\n"+
                "3. Exit\n"+
                "Enter Choice : "
            );
            n = Integer.parseInt(sc.nextLine());
            switch(n){
                case 1:
                System.out.print("Plain Text : ");
                pt = sc.nextLine();
                System.out.print("Key : ");
                k = sc.nextLine();
                System.out.println("Cipher Text : "+b.enc(pt, k));
                break;
                case 2:
                System.out.print("Cipher Text : ");
                pt = sc.nextLine();
                System.out.print("Key : ");
                k = sc.nextLine();
                System.out.println("Plain Text : "+b.dec(pt, k));
                break;
            }
        }while(n!=3);
        sc.close();
    }
}
