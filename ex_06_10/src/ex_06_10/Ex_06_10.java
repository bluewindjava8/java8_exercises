package ex_06_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Ex_06_10 {

    public static void main(String[] args) throws Exception {
        //Set<String> links = new ConcurrentHashMap<String, String>().keySet("const");
        List<String> links = new LinkedList<>();
        //readPage("http://www.yahoo.co.jp").thenApply(Ex_06_10::getLinks).thenAccept(System.out::println);
        readPage("http://www.yahoo.co.jp").thenCompose(Ex_06_10::getLinks).thenAccept(links::addAll);
        //System.out.println(links);

        ForkJoinPool.commonPool().awaitQuiescence(30, TimeUnit.SECONDS);
        for(String link : links){
            System.out.println(link);
        }
    }
    
    private static String blockingReadPage(String url) {
 
        try{

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36";
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.connect();
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();

            //print result
            //System.out.println(response.toString());
            return response.toString();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
 
    }
    
    private static List<String> blockingGetLinks(String htmlPage){
        Pattern linkPattern = Pattern.compile("href=\"(.*?)\"");
        Matcher pageMatcher = linkPattern.matcher(htmlPage);
        ArrayList<String> links = new ArrayList<String>();
        while(pageMatcher.find()){
            links.add(pageMatcher.group(1));
        }
        
        return links;
    }
    
    private static CompletableFuture<String> readPage(String url){
        return CompletableFuture.supplyAsync(() -> blockingReadPage(url));
    }
    
    private static CompletableFuture<List<String>> getLinks(String htmlPage){
        return CompletableFuture.supplyAsync(() -> blockingGetLinks(htmlPage));
    }

}
