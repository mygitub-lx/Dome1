import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import	java.lang.Thread.State;
public class test {

//    public static List<Station> line1 = new ArrayList<Station>();
//    public static List<Station> line2 = new ArrayList<Station>();
//    public static List<Station> line3 = new ArrayList<Station>();
//    public static List<Station> line5 = new ArrayList<Station>();
//    public static List<Station> line6 = new ArrayList<Station>();
//    public static List<Station> line9 = new ArrayList<Station>();

    public static HashSet<List<Station>> lineSet = new HashSet<List<Station>>();//所有线集合
    public static int totalStaion = 0;//总的站点数量
    static {

    }

    public static void main(String[] args) throws  IOException{
        FileInputStream inputStream = new FileInputStream("D:\\IDEA的项目\\src\\station2.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            List<Station> line = new ArrayList<Station>();
            String[] lineInformations = str.split(" ");
            for(String s : lineInformations){
                line.add(new Station(s));
//                System.out.println(s);
            }
            for(int i =0;i<line.size();i++){
                if(i<line.size()-1){
                    line.get(i).next = line.get(i+1);
                    line.get(i+1).prev = line.get(i);
                }
            }
            lineSet.add(line);
//            System.out.println(line.get(line.size()-1).getName());
            totalStaion+=line.size();

        }
        //close
        inputStream.close();
        bufferedReader.close();
//        System.out.println(totalStaion);
//        List<Station> a = lineSet.iterator().next();
//        System.out.println(a.get(0).getName());
    }
}
