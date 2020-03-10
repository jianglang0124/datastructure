package com.datastructure.huffmancode;

import java.io.*;
import java.util.*;

// 哈弗曼编码
public class HuffmanCode {
    public static void main(String[] args) {

        //  测试压缩文件
       /* String sourceFile= "d://11.bmp";
        String desFile = "d://dst.zip";
        zipFile(sourceFile,desFile);
        System.out.println("文件压缩ok~~~~");*/
       // 测试压缩文件
        String zipFile="d://dst.zip";
        String srcFile="d://112.bmp";
        unZipFile(zipFile,srcFile);
        System.out.println("解压成功~~~~");

       /* String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();  // 40个字节
        for (Byte b:contentBytes){
            System.out.println(b);
        }
        System.out.println(contentBytes.length);*/

        /*// 分步过程
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes===="+nodes);
        // 测试一下
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        preOrder(huffmanTreeRoot);

        // 测试一下
        // getCode(huffmanTreeRoot,"",stringBuilder);
        getCode(huffmanTreeRoot);
        System.out.println("生成的赫夫曼的编码表 "+HuffmanCode);
        byte[] zip = zip(contentBytes, HuffmanCode);
        System.out.println(Arrays.toString(zip));*/

       /* byte[] zip = huffmanZip(contentBytes);
        System.out.println("压缩后的："+Arrays.toString(zip));
        System.out.println(zip.length); // 17 个字节
        // 压缩比是 57.5%
        // 测试解码
        byte[] decode = decode(HuffmanCode, zip);
        System.out.println(new String(decode));*/

    }
    // 编写一个方法 完成对压缩文件的解压

    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public  static  void  unZipFile(String zipFile,String dstFile){

        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            // 创建文件的输入流
            is = new FileInputStream(zipFile);
            // 创建和 is关联的对象输入流
            ois= new ObjectInputStream(is);
            // 读取文件信息
            // 读取byte数组
            byte[] bytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte,String> map=(Map<Byte, String>) ois.readObject();
            // 解码

            byte[] decode = decode(map, bytes);
            // 将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写数据到desFile 文件
            os.write(decode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    // 编写方法 将一个文件进行压缩  version2
    /**
     *
     * @param srcFile  你传入的希望压缩文件的全路径
     * @param desFile  我们压缩后的文件放在哪个压缩的目录
     */
      public  static  void zipFile(String srcFile,String desFile) {

          InputStream is = null;
          OutputStream os = null;
          ObjectOutputStream oos= null;

        try {
            // 创建文件的输入流
            is = new FileInputStream(srcFile);
            // 创建和源文件一样的byte数组
            byte[] bytes = new byte[is.available()];
            // 将文件的字节读取到bytes数组中
            is.read(bytes);
            // 对bytes数组进行压缩
            byte[] HuffmanBytes = huffmanZip(bytes);
            // 创建文件输出流 存放压缩文件
            os = new FileOutputStream(desFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos= new ObjectOutputStream(os);
            // 将压缩后的赫夫曼编码写到流中
            oos.writeObject(HuffmanBytes);
            //  将对于的赫夫曼编码也写入到流中 是为了以后我们恢复文件的时候使用
            oos.writeObject(HuffmanCode);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
      }

    // 完成数据的解压
    // 思路： 两步
    // 1 将 压缩后的：[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //  转换成 赫夫曼对应的二进 制字符串
    // 2 赫夫曼对应的二进制字符串 ===》 根据赫夫曼的编码 ====》 l like ......

    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼得到的字节的数组
     * @return  就是原来的字符创对应的数组
     */
    public static  byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){

        // 将原来的byte 数组转换成一个二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean  flag = (i==huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(b, !flag));
        }
        // 根据赫夫曼的编码表转换成逆编码
        // 将赫夫曼的编码进行调换 因为是反向查询 a->100 100->a
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry: huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        // 通过二进制的字符串和逆哈弗曼编码表 转换成之前的
        // 创建集合 存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i <stringBuilder.length() ;) {  // 注意没有i++ i的变化在后面
            int count=1;  // 小的计数器  每一次循环都重置
            boolean  flag = true;
            Byte b = null;

            while (flag){
                // 1010100010111...
                // 递增取出str
                String str = stringBuilder.substring(i,i+count);// 让i不动 让count移动 直到匹配到一个字符
                b = map.get(str);
                if(b!=null){
                    // 匹配到
                    flag =false;
                }else {
                    count++;
                }
            }
            list.add(b);
            i+=count;
        }
        // 将这个list 转换成Byte数组
        // 当for循环结束的时候 我们list 中就存放了所有的字符 i like like like java do you like a java
        // 将list 中的数据放入到byte[] 中 并返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i]=list.get(i);
        }

        return  bytes;
    }
    /**
     *  将一个byte  转换成一个二进制的字符串
     * @param b  传入的byte
     * @param flag 标志  如果是 true 则说明需要补高位  如果是 false 说明不需要补高位
     * @return 是该b 对应的二进制字符串
     */
    private  static  String  byteToBitString(byte b,boolean flag){
        // 使用变量保存b
        int temp = b;
        // 如果是正数 我们还存在补高位
        if(flag){
            temp |= 256; // 按位与256  1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); // 返回的是 temp 对应的二进制的补码
        if(flag){
            return  str.substring(str.length()-8);
        }else {
            return  str;
        }
    }


    /**
     *  使用一个方法 将前面额方法封装起来 便于我们调用
     * @param bytes  原始数组的字符串对应的字节数组
     * @return  是 经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private  static  byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        // 根据 Nodes 创建huffman 树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 根据 赫夫曼树生成赫夫曼编码
        Map<Byte, String> HuffmanCode = getCode(huffmanTreeRoot);
        // 根据生成的赫夫曼编码压缩得到压缩后的赫夫曼编码额字节数组
        byte[] zip = zip(bytes, HuffmanCode);
        return  zip ;

    }

    // 编写一个方法
   public  static  byte[] zip(byte[] bytes,Map<Byte,String> huffmanCode){
        // 用于拼接
       StringBuilder stringBuilder = new StringBuilder();
       //
       for (byte b : bytes){
           stringBuilder.append(huffmanCode.get(b));
       }
       // 长度计算
       int len;
       if(stringBuilder.length()%8==0){
           len =stringBuilder.length()/8;
       }else {
           len =stringBuilder.length()/8+1;
       }
       // 创建byte数组
       byte[] buffmanCodeBytes = new byte[len];
       int index =0;
       for (int i = 0; i < stringBuilder.length(); i+=8) { // 步长为8
           String strByte;
           // 不满8位的时候直接截取剩下的
           if((i+8)>stringBuilder.length()){   // i+8 这边注意一下
               strByte = stringBuilder.substring(i);
           }else {
               // 每次截取8位
               strByte= stringBuilder.substring(i,i+8);
           }
           buffmanCodeBytes[index]= (byte) Integer.parseInt(strByte,2);
           index++;
       }
        return  buffmanCodeBytes;
   }





    // 生成赫夫曼编码
    // 思路
    // 1.将赫夫曼编码存放到一个Map<Byte,String> 形式
    // 32->01 97->100 等形式
    static  Map<Byte,String> HuffmanCode = new HashMap<>();
    // 2. 在生成赫夫曼编码表示 需要拼接路径 定义一个StringBuilder 存储某个叶子结点的路径
    static  StringBuilder stringBuilder=new StringBuilder();

    // 重载
    public static Map<Byte,String> getCode(Node root){
        if(null == root){
            return  null;
        }else {
            // 处理root左子树
            getCode(root.left,"0",stringBuilder);
            // 处理root 右子树
            getCode(root.right,"1",stringBuilder);
        }
        return  HuffmanCode;

    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并且放入到HuffmanCode 中
     * @param node 传入结点
     * @param code 路径 左子节点是0 右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    public static  void  getCode(Node node, String code,StringBuilder stringBuilder){

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code 加入到stringBuilder2
        stringBuilder2.append(code);
        if(node !=null){
            if(node.data == null){ // 非叶子结点
                // 递归处理
                // 向左递归
                getCode(node.left,"0",stringBuilder2);
                // 向右递归
                getCode(node.right,"1",stringBuilder2);
            }else {  // 是 叶子结点
                HuffmanCode.put(node.data,stringBuilder2.toString());
            }

        }


    }


    // 前序遍历
    public static void  preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空");
        }
    }

    //
    public static List<Node> getNodes(byte[] bytes){

        // 创建返回的存放的Node的集合
        List<Node> nodes = new ArrayList<Node>();
        // 使用map来存放数据
        Map<Byte, Integer> map = new HashMap<Byte, Integer>();
        for (Byte b :bytes ){
            Integer count = map.get(b);
            if (null == count){
                map.put(b,1);
            }else {
                map.put(b,count+1);
            }
        }
        // 遍历map 创建Node  并放入到集合中
        for (Map.Entry<Byte,Integer> entry :map.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return  nodes;
    }
    // 创建对应的赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes){

        while (nodes.size()>1){
            // 排序 从小到大
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null,leftNode.weight+rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return  nodes.get(0);


    }


}
// 创建 Node 存放数据和权值
class  Node implements  Comparable<Node>{
    Byte data; // 存放数据本身(字符) 比如 'a'=> 97
    int weight; // 出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大
        return this.weight-o.weight;
    }
    // 前序遍历
    public  void  preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}

