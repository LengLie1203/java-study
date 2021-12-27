package com.lqw.simhash;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**SimHash算法类
 * 可将一个字符串生成X位的二进制hash码
 *
 * 算法核心步骤:
 * ①:将长文本数据进行分词，可选择英文或中文分词器（中文分词器使用Ansj,初始化比较耗时）
 * ②:将每个分词进行hash转换为二进制， 请看这个方法{@link SimHash#hash(String)}，请关注方法描述
 * ③:将所有分词生成的二进制hash码逐位进行相加（可以乘以权重），最终生成特征向量
 * ④:计算特征向量的每一位大小，大于0记为1，其余记为0.最终生成的数据拼接出二进制码就是这个长文本的simHash值
 * @author LQW
 */
public class SimHash {
    private String tokens;

    private String[] tokenArrays;

    private int[] weights;

    private BigInteger intSimHash;

    private String strSimHash;

    private int hashbits = 64;

    private int weight = 1;

    public SimHash(String tokens) {
        this.tokens = tokens;
        this.intSimHash =this.simHash();
    }

    public SimHash(String tokens, int hashbits) {
        this.tokens = tokens;
        this.hashbits = hashbits;
        this.intSimHash =this.simHash();
    }

    public SimHash(String[] tokenArrays, int[] weights, int hashbits,Boolean chineseFlag) {
        this.tokenArrays = tokenArrays;
        this.weights = weights;
        this.hashbits = hashbits;
        this.intSimHash =this.simHashWithWeight(chineseFlag);
    }

    public String getStrSimHash() {
        return strSimHash;
    }

    private BigInteger simHash() {
        // 定义特征向量/数组
        int[] vector =new int[this.hashbits];
        // 1、将文本去掉格式后, 分词.
        List<String> terms = this.subTerms(Boolean.FALSE, this.tokens);
        for (String term : terms) {
            // 2、将每一个分词hash为一组固定长度的数列.比如 64bit 的一个整数.
            BigInteger t =this.hash(term);
            this.sumVector(vector, t, this.weight);
        }

        BigInteger fingerprint =this.createFingerprint(vector);

        return fingerprint;
    }

    private BigInteger simHashWithWeight(Boolean chineseFlag) {
        // 定义特征向量/数组
        int[] v =new int[this.hashbits];
        // 1、将文本去掉格式后, 分词.
        for (int index = 0; index < tokenArrays.length; index++) {

            String token = tokenArrays[index];
            List<String> terms = this.subTerms(chineseFlag, token);
            for (String term : terms) {
                // 2、将每一个分词hash为一组固定长度的数列.比如 64bit 的一个整数.
                BigInteger t =this.hash(term);
                this.sumVector(v, t, weights[index]);
            }

        }
        BigInteger fingerprint =this.createFingerprint(v);

        return fingerprint;
    }

    /**拆分词
     * @param chineseFlag
     * @param source
     * @return
     */
    private List<String> subTerms(Boolean chineseFlag,String source){

        if(StringUtils.isBlank(source)){
            return new ArrayList<>();
        }

        List<String> terms;

        if(chineseFlag) {
            List<Term> termsList = NlpAnalysis.parse(source).getTerms();

            terms = termsList.stream().map(Term::getName).collect(Collectors.toList());
        }else {
            terms = new ArrayList<>();

            StringTokenizer stringTokens = new StringTokenizer(source);
            while (stringTokens.hasMoreTokens()){

                terms.add(stringTokens.nextToken());
            }
        }

        return terms;
    }

    private void sumVector(int[] v, BigInteger t, int weight) {
        for (int i = 0; i < this.hashbits; i++) {
            BigInteger bitmask = new BigInteger("1").shiftLeft(i);
            // 3、建立一个长度为64的整数数组(假设要生成64位的数字指纹,也可以是其它数字),
            // 对每一个分词hash后的数列进行判断,如果是1000...1,那么数组的第一位和末尾一位加1,
            // 中间的62位减一,也就是说,逢1加1,逢0减1.一直到把所有的分词hash数列全部判断完毕.
            if (t.and(bitmask).signum() != 0) {
                // 这里是计算整个文档的所有特征的向量和
                // 这里实际使用中需要 +- 权重，而不是简单的 +1/-1，
                v[i] += weight;
            } else {
                v[i] -= weight;
            }
        }
    }

    private BigInteger createFingerprint(int[] vector) {
        BigInteger fingerprint =new BigInteger("0");
        StringBuffer simHashBuffer =new StringBuffer();
        for(int i = 0; i < this.hashbits; i++) {
            // 4、最后对数组进行判断,大于0的记为1,小于等于0的记为0,得到一个 64bit 的数字指纹/签名.
            if(vector[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
                simHashBuffer.append("1");
            }else {
                simHashBuffer.append("0");
            }
        }
        this.strSimHash = simHashBuffer.toString();
        return fingerprint;
    }


    /**将字符串转成N位二进制，字符串如果比较短会导致生成的数字较小，转出的二进制可能不到N位
     * 请自己酌情配置N的大小
     * @param source
     * @return
     */
    private BigInteger hash(String source) {
        if(source == null|| source.length() == 0) {
            return new BigInteger("0");
        }else {
            char[] sourceArray = source.toCharArray();
            //这里把第一个字符的char值左移7位（乘以128），不清楚是做什么，如果这个字符串只有1位不清楚有没有影响
            //可能是扩大数值方便数字均匀分布
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            //这里取一个固定值，也不清楚为什么。
            BigInteger m =new BigInteger("1000003");
            //这里是获得一个长度为hashbits的掩码，即全为1的二进制
            BigInteger mask =new BigInteger("2").pow(this.hashbits).subtract(new BigInteger("1"));
            for(char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                //自身乘以固定值 再和自身长度异或 再和掩码做与运算（与运算这一步好像没有实际效果）
                x = x.multiply(m).xor(temp).and(mask);
            }

            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if(x.equals(new BigInteger("-1"))) {
                x =new BigInteger("-2");
            }
            return x;
        }
    }

    /**这个方法比 {@link SimHash#getDistance(String, String)}性能差很多
     * @param other
     * @return
     */
    public int hammingDistance(SimHash other) {

        BigInteger x =this.intSimHash.xor(other.intSimHash);
        int tot = 0;

        // 统计x中二进制位数为1的个数
        // 我们想想，一个二进制数减去1，那么，从最后那个1（包括那个1）后面的数字全都反了，对吧，然后，n&(n-1)就相当于把后面的数字清0，
        // 我们看n能做多少次这样的操作就OK了。

        while(x.signum() != 0) {
            tot +=1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return tot;
    }

    public int getDistance(String str1, String str2) {
        int distance;
        if(str1.length() != str2.length()) {
            distance = -1;
        }else {
            distance =0;
            for(int i = 0; i < str1.length(); i++) {
                if(str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    public List<BigInteger> subByDistance(int distance) {
        // 分成几组来检查
        int numEach = this.hashbits / (distance +1);
        List<BigInteger> characters = new ArrayList<>();

        StringBuilder buffer =new StringBuilder();

        for(int i = 0; i < this.strSimHash.length(); i++) {

            buffer.append(this.strSimHash.charAt(i));

            //每截取规定长度或截取全部返回
            if(((i + 1) % numEach ==0)||((i+1)==this.strSimHash.length())) {
                // 将二进制转为BigInteger
                BigInteger eachValue =new BigInteger(buffer.toString(),2);

                buffer.delete(0, buffer.length());
                characters.add(eachValue);
            }
        }

        return characters;
    }

    public static void main(String[] args) {
//
//        String ss = "你好哦 艾掉的 我啊 很好 哦哦 方法  哦哦哦 命名";
//        new SimHash(ss, 64);
commonTest();
    }

    public static void commonTest(){
        String s = "This is a test string for testing";

        SimHash hash1 = new SimHash(s, 64);
        System.out.println(hash1.strSimHash + "  " + hash1.intSimHash.bitCount());

        hash1.subByDistance( 3);

        System.out.println("\n");
        s = "This is a test string for testing, This is a test string for testing abcdef";
        SimHash hash2 = new SimHash(s, 64);
        System.out.println(hash2.strSimHash+ "  " + hash2.intSimHash.bitCount());
        hash2.subByDistance( 3);
        s = "This is a test string for testing als";
        SimHash hash3 = new SimHash(s, 64);
        System.out.println(hash3.strSimHash+ "  " + hash3.intSimHash.bitCount());
        hash3.subByDistance( 3);
        System.out.println("============================");
        int dis = hash1.getDistance(hash1.strSimHash,hash2.strSimHash);

        System.out.println("hash1 and hash2 hammingDistance=" + dis);

        int dis2 = hash1.getDistance(hash1.strSimHash,hash3.strSimHash);

        System.out.println("hash1 and hash3 hammingDistance=" + dis2);
    }

}
