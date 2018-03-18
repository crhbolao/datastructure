package tire;

import com.hui.tire.MineTire;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * 测试 修改的中文 tire 树
 */
public class MineTireTest {

    /**
     * 用来构建 tire 树
     */
    public static List<String> words;

    /**
     *  静态代码块，将中文数据内容加载到words中。
     */
    static {
        String path = "C:\\Users\\sssd\\Desktop\\中文测试.txt";
        try {
            FileReader fileReader = new FileReader(new File(path));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            words = new ArrayList<String>();
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                words.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        // 初始化 tire 树
        MineTire mineTire = new MineTire();

        // tire 树中插入中国
        mineTire.insert("中国");
/*        HashMap<String, Integer> map = mineTire.getAllWords();
        for(String key:map.keySet()){
            System.out.println(key+" 出现: "+ map.get(key)+"次");
        }*/

        // 通过前缀 “中” 获取相关词语
        HashMap<String, Integer> map = mineTire.getWordsForPrefix("中");
        System.out.println("\n\n包含chin（包括本身）前缀的单词及出现次数：");
        for (String key : map.keySet()) {
            System.out.println(key + " 出现: " + map.get(key) + "次");
        }

/*
        // 用来判断该关键词是否存在
        if(mineTire.isExist("国")==false){
            System.out.println("\n\n字典树中不存在：国 ");
        }*/
    }

    /**
     * 测试 HasdMap 获取值得时间
     */
    @Test
    public void testHashMap() {

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words) {
            map.put(word, 1);
        }

        // 要查询获取的关键词
        long startTime = System.currentTimeMillis();
        String str = "出水芙蓉";
        for (int i = 0; i < 10000; i++) {
            Integer integer = map.get(str);
        }
        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime);
        System.out.println("执行时间：" + excTime + "ms");
    }

    @Test
    public void testTire() {
        MineTire mineTire = new MineTire();
        for (String word : words) {
            // 将相关词 添加到 tire 树中
            mineTire.insert(word);
        }
        // 要查询获取的关键词
        long startTime = System.currentTimeMillis();
        String str = "出水芙蓉";
        for (int i = 0; i < 10000; i++) {
            // tire 树通过前缀 获取相关词语。
            mineTire.getWordsForPrefix(str);
        }
        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
    }

    @Test
    public void testTreeMap() {

        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for (String word : words) {
            map.put(word, 1);
        }

        // 要查询获取的关键词
        long startTime = System.currentTimeMillis();
        String str = "出水芙蓉";
        for (int i = 0; i < 10000; i++) {
            Integer integer = map.get(str);
        }
        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
    }
}