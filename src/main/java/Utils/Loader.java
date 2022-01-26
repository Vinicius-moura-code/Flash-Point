package Utils;


public class Loader {

    public static void Loading(Boolean run ) throws Exception {
        String anim= "|/-\\";
        int x = 0;
        do{
            x++;
            String data = "\r" + anim.charAt(x % anim.length()) + " " + x;
            System.out.write(data.getBytes());
            Thread.sleep(100);
        }while (run && x <=100);
    }
}
