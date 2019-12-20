import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StationIncludeLineName {

    public static HashSet<List<Station>> lineSet = new HashSet<List<Station>>();//所有线集合
    public static int totalStaion = 0;//总的站点数量

    //读取文件
    public static void readFile(String fileName) throws IOException{
        FileInputStream inputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            List<Station> line = new ArrayList<Station>();
            String[] lineInformations = str.split(" ");
            for(String s : lineInformations){
                line.add(new Station(s));
            }
            for(int i =0;i<line.size();i++){
                if(i<line.size()-1){
                    line.get(i).next = line.get(i+1);
                    line.get(i+1).prev = line.get(i);
                }
            }
            lineSet.add(line);
            totalStaion+=line.size();

        }
        inputStream.close();
        bufferedReader.close();
    }

}