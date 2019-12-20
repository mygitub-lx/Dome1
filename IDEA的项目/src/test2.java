import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test2 {

    public static List<Station> line1 = new ArrayList<Station>();
    public static List<Station> line2 = new ArrayList<Station>();
    public static List<Station> line3 = new ArrayList<Station>();
    public static List<Station> line5 = new ArrayList<Station>();
    public static List<Station> line6 = new ArrayList<Station>();
    public static List<Station> line9 = new ArrayList<Station>();

    public static Set<List<Station>> lineSet = new HashSet<List<Station>>();//所有线集合
    public static int totalStaion = 0;//总的站点数量
    static {
        //1号线
        String line1Str = " 刘园 瑞金新苑 佳园里 本溪路 勤俭道 洪湖里 西站 西北角 西南角  \n" +
                "二纬路 海光寺 鞍山道 营道口 下瓦房 南楼 土城 陈塘庄 复兴门 华山里 财经大学 双林 李楼";
        String[] line1Arr = line1Str.split(" ");
        for(String s : line1Arr){
            line1.add(new Station(s));
        }
        for(int i =0;i<line1.size();i++){
            if(i<line1.size()-1){
                line1.get(i).next = line1.get(i+1);
                line1.get(i+1).prev = line1.get(i);
            }
        }

        /*******************************************************************************/
        //2号线
        String line2Str = "曹庄 卞兴 芥园西道 咸阳路 长虹公园 广开西马路 西南角 \n" +
                "鼓楼 东南角 建国道 天津站 顺驰桥 靖江路 翠阜新村 屿东城 登州路 国山路 空港经济港 滨海国际机场";
        String[] line2Arr = line2Str.split(" ");
        for(String s : line2Arr){
            line2.add(new Station(s));
        }
        for(int i =0;i<line2.size();i++){
            if(i<line2.size()-1){
                line2.get(i).next = line2.get(i+1);
                line2.get(i+1).prev = line2.get(i);
            }
        }

        /*******************************************************************************/
        //3号线
         String line3Str = "南站 杨伍庄 学府工业区 高新区 大学城 华苑 王顶堤 红旗南路 周邓纪念馆 天塔 吴家窑  \n" +
               "西康路 营道口 和平路 津湾广场 天津站 金狮桥 中山路 北站 铁东路 张兴庄 宜兴阜 天士力 华北集团 丰产河 小淀";
       String[] line3Arr = line3Str.split(" ");
       for(String s : line3Arr){
            line3.add(new Station(s));
        }
        for(int i =0;i<line3.size();i++){
           if(i<line3.size()-1){
                line3.get(i).next = line3.get(i+1);
               line3.get(i+1).prev = line3.get(i);
            }
      }

        /*******************************************************************************/
        //5号线
        String line5Str = "北辰科技园北 丹河北道 北辰道 职业大学 淮河道 辽河北道 宜兴阜北 张兴庄 志成路 思源道 建昌道 金钟河大街 月牙河\n" +
                "幸福公园 靖江路 成林道 津塘路 直沽 下瓦房 西南路 文化中心 天津宾馆 肿瘤医院 体育中心 凌宾路 昌凌路 中医一附院 李七庄南";
        String[] line5Arr = line5Str.split(" ");
        for(String s : line5Arr){
            line5.add(new Station(s));
        }
        for(int i =0;i<line5.size();i++){
            if(i<line5.size()-1){
                line5.get(i).next = line5.get(i+1);
                line5.get(i+1).prev = line5.get(i);
            }
        }

        /*******************************************************************************/
        //6号线
        String line6Str = "南孙庄 南河庄 大毕庄 金钟街 徐庄子 金钟河大街 民权门 北宁公园 北站 新开河 外院附中 天泰路 北运河 北竹林\n" +
                "西站 复兴路 人民医院 长虹公园 宜宾道 鞍山西道 天拖 一中心医院 红旗南路 迎风道 南翠屏";
        String[] line6Arr = line6Str.split(" ");
        for(String s : line6Arr){
            line6.add(new Station(s));
        }
        for(int i =0;i<line6.size();i++){
            if(i<line6.size()-1){
                line6.get(i).next = line6.get(i+1);
                line6.get(i+1).prev = line6.get(i);
            }
        }

        /*******************************************************************************/
        //9号线
        String line9Str = "天津站 大王庄 十一经路 直沽 东兴路 中山门 一号桥 二号桥 张贵庄 新立 \n" +
                "东丽开发区 小东庄 军械城 钢管公司 胡家园 塘沽 泰达 市民广场 太湖路 会展中心 东海路";
        String[] line9Arr = line9Str.split(" ");
        for(String s : line9Arr){
            line9.add(new Station(s));
        }
        for(int i =0;i<line9.size();i++){
            if(i<line9.size()-1){
                line9.get(i).next = line9.get(i+1);
                line9.get(i+1).prev = line9.get(i);
            }
        }

        lineSet.add(line1);
        lineSet.add(line2);
        lineSet.add(line3);
        lineSet.add(line5);
        lineSet.add(line6);
        lineSet.add(line9);
//        totalStaion  = line1.size() + line2.size() + line3.size() + line10.size() + lineS1.size() + lineS8.size();
        totalStaion  = line1.size() + line2.size() + line3.size() + line5.size() + line6.size() + line9.size();
        System.out.println("总的站点数量："+totalStaion);
    }
}