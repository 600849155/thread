package ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseDateDemo {
   //private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<SimpleDateFormat>threadLocal = new ThreadLocal<SimpleDateFormat>();
    public static class ParseDate implements Runnable{
        int i = 0;

        public ParseDate(int i){
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (threadLocal.get() == null){
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date date = threadLocal.get().parse("2018-10-29 00:06:" + i %60);
                System.out.println(i + ":" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i =0;i<1000;i++){
            executorService.submit(new ParseDate(i));
        }
    }

}
