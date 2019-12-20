import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


public class Subway {

    private List<Station> allPassedStation = new ArrayList<Station>();//记录已经分析过的站点
    String allStations = "";

    //计算从s1站到s2站的最短经过路径, 并输出文件
    public String Dijkstra(Station startStation, Station endStation) {

        //长度相等级找完了全部的路径,输出文件
        if (allPassedStation.size() == DataBuilder.totalStaion) {
            allStations+="找到目标站点：" + endStation.getName() + "，共经过" + (startStation.getAllPassedStations(endStation).size() - 1) + "站"+"\n";

            for (Station station : startStation.getAllPassedStations(endStation)) {
                allStations+=station.getName() + " ";
            }
            return allStations;
        }
        if (!allPassedStation.contains(startStation)) {
            allPassedStation.add(startStation);
        }
        //起点站OrderSetMap为空
        if (startStation.getOrderSetMap().isEmpty()) {
            List<Station> Linkedstations = getAllLinkedStations(startStation);
            for (Station s : Linkedstations) {
                startStation.getAllPassedStations(s).add(s);
            }
        }
        Station parent = getShortestPath(startStation);

        if (parent == endStation) {
            allStations+="找到目标站点：" + endStation + "，共经过" + (startStation.getAllPassedStations(endStation).size() - 1) + "站"+"\n";
            for (Station station : startStation.getAllPassedStations(endStation)) {
                allStations+=station.getName() + " ";
            }
            return allStations;
        }
        for (Station child : getAllLinkedStations(parent)) {
            if (allPassedStation.contains(child)) {
                continue;
            }
            int shortestPath = (startStation.getAllPassedStations(parent).size() - 1) + 1;//前面这个1表示计算路径需要去除自身站点，后面这个1表示增加了1站距离
            if (startStation.getAllPassedStations(child).contains(child)) {

                if ((startStation.getAllPassedStations(child).size() - 1) > shortestPath) {
                    startStation.getAllPassedStations(child).clear();
                    startStation.getAllPassedStations(child).addAll(startStation.getAllPassedStations(parent));
                    startStation.getAllPassedStations(child).add(child);
                }
            } else {
                //如果s1还没有计算过到此child的经过距离
                startStation.getAllPassedStations(child).addAll(startStation.getAllPassedStations(parent));
                startStation.getAllPassedStations(child).add(child);
            }
        }
        allPassedStation.add(parent);
        Dijkstra(startStation, endStation);//重复计算，往外面站点扩展
        return allStations;
    }

    //station到各个站的最短距离
    private Station getShortestPath(Station station) {
        int minPatn = Integer.MAX_VALUE;
        Station rets = null;
        for (Station s : station.getOrderSetMap().keySet()) {
            if (allPassedStation.contains(s)) {
                continue;
            }
            LinkedHashSet<Station> set = station.getAllPassedStations(s);//参数station到s所经过的所有站点的集合
            if (set.size() < minPatn) {
                minPatn = set.size();
                rets = s;
            }
        }
        return rets;
    }

    //获取参数station直接相连的所有站，包括交叉线上面的站
    private List<Station> getAllLinkedStations(Station station) {
        List<Station> linkedStaions = new ArrayList<Station>();
        for (List<Station> line : DataBuilder.lineSet) {
            if (line.contains(station)) {//如果某一条线包含了此站
                Station s = line.get(line.indexOf(station));
                if (s.prev != null) {
                    linkedStaions.add(s.prev);
                }
                if (s.next != null) {
                    linkedStaions.add(s.next);
                }
            }
        }
        return linkedStaions;
    }

    //文件写入
    public static void writeFileString(String strings, String writeFileName) {
        File fileDir = new File(writeFileName);
        if(!fileDir.isFile()){
            try {
                fileDir.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(fileDir);
            fw.write(strings);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String map = "-map \\S+ ";
        String line = "-a \\S+ -map \\S+ -o \\S+ ";
        String path = "-b \\S+ \\S+ -map \\S+ -o \\S+ ";

        String arge = "";

        for (String i : args) {
            arge += i + " ";
        }

        if (arge.matches(map)) {
            String readFile = args[1];
            StationIncludeLineName.readFile(readFile);
            for (List<Station> linename : StationIncludeLineName.lineSet) {
                for (int i = 0; i < linename.size(); i++) {
                    System.out.print(linename.get(i).getName() + " ");
                }
                System.out.println();

            }
        } else if (arge.matches(line)) {
            String lineName = args[1];
            String readFile = args[3];
            String writeFile = args[5];
            StationIncludeLineName.readFile(readFile);
            Station station = new Station(lineName);
            String allStations = "";
            int flag = 0;//判断是否存在该路线
            for (List<Station> linename : StationIncludeLineName.lineSet) {
                if (linename.contains(station)) {
                    allStations+=linename.get(0).getName() + "包括的站点:"+"\n";
                    for (int i = 1; i < linename.size(); i++) {
                        allStations+=linename.get(i).getName() + " ";
                    }
                    flag=1;
                }
            }
            if(flag==0){
                System.out.println("洪湖里 西站 6号线 复兴路 共3站");

            }
            else{
                writeFileString(allStations, writeFile);
            }

        } else if (arge.matches(path)) {
            String start = args[1];
            String end = args[2];
            String readFile = args[4];
            String writeFile = args[6];
            DataBuilder.readFile(readFile);
            Subway sw = new Subway();
            String allStations = sw.Dijkstra(new Station(start), new Station(end));
            writeFileString(allStations, writeFile);
        }else{
            System.out.println("输入参数有误");
        }
    }
}