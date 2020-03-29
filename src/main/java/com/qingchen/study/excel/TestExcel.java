package com.qingchen.study.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @ClassName TestExcel
 * @description:
 * @author: WangChen
 * @create: 2020-03-14 13:47
 **/
public class TestExcel {


    public static void writeExcel(String fileName, Map<Class<?>, List< ? extends AbstractExcel>> map){


        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        /*填充背景颜色格子没有*/
        //headWriteCellStyle.setFillBackgroundColor(IndexedColors.ROSE.getIndex());
        /*填充前景颜色格子线存在*/
        //headWriteCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headWriteCellStyle.setBorderTop(BorderStyle.DOTTED);
        headWriteCellStyle.setBorderBottom(BorderStyle.DOTTED);
        headWriteCellStyle.setBorderLeft(BorderStyle.DOTTED);
        headWriteCellStyle.setBorderRight(BorderStyle.DOTTED);
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteCellStyle.setWriteFont(headWriteFont);


        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        //contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色 (同头得规则相同)
        //contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        //contentWriteCellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriter excelWriter = EasyExcel.write(fileName)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .build();


        List<Class<?>> classes = new ArrayList<>();
        classes.add(DemoDate.class);
        classes.add(DemoDate1.class);

        for (int i = 0; i< classes.size(); i++) {

            WriteSheet writeSheet = EasyExcel.writerSheet(i,"模板"+ i).head(classes.get(i)).build();
            excelWriter.write(map.get(classes.get(i)), writeSheet);

        }
            // 每隔2行会合并 把eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
            //LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(v.size(), 0);


        excelWriter.finish();

        /*EasyExcel.write(fileName, DemoDate.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                //.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                //.head(head())
                .sheet("自定义名字").doWrite(list);*/

    }

    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("统计");
        head0.add("姓名");
        List<String> head1 = new ArrayList<String>();
        head1.add("统计");
        head1.add("电话");
        List<String> head2 = new ArrayList<String>();
        head2.add("统计");
        head2.add("地址");
        List<String> head3 = new ArrayList<String>();
        head2.add("统计");
        head2.add("时间");
        List<String> head4 = new ArrayList<String>();
        head2.add("统计");
        head2.add("数字标题");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        return list;
    }

    private static void testT() throws Exception{
        List<DemoDate> list = new ArrayList<>();
        DemoDate demoDate = new DemoDate();
        demoDate.setName("王晨");
        demoDate.setPhone(15242957219L);
        demoDate.setAddress("兴城");
        //demoDate.setFilePath("D:\\Huawei Share\\OneHop\\3.jpeg");
        demoDate.setDate(new Date());
        demoDate.setDoubleData((double)0.09993);
        list.add(demoDate);

        for (DemoDate demoDate1 : list) {



            Field[] declaredFields = demoDate1.getClass().getDeclaredFields();

            for (Field declaredField : declaredFields) {

                declaredField.setAccessible(true);
                Class<?> type = declaredField.getType();

                System.out.println(type.getName());

                if (type.equals(List.class)){
                    // 当前集合的泛型类型
                    Type genericType = declaredField.getGenericType();
                    if (null == genericType) {
                        continue;
                    }
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        // 得到泛型里的class类型对象
                        Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
                        System.out.println(actualTypeArgument.getName());
                    }

                }


            }
        }



    }

    public static void main(String[] args) throws Exception{

        /*Field[] declaredFields = DemoDate.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length ; i++) {
            ExcelProperty declaredAnnotation = declaredFields[i].getDeclaredAnnotation(ExcelProperty.class);
            InvocationHandler handler = Proxy.getInvocationHandler(declaredAnnotation);
            Field value = handler.getClass().getDeclaredField("value");
            System.out.println(value);
            declaredFields[i].setAccessible(true);

        }*/

        /*String fileName = "D:\\王晨.xlsx";

        Map<Class<?>, List< ? extends AbstractExcel>> listMap = new HashMap<>();

        List<DemoDate> list = new ArrayList<>();
        List<DemoDate1> list1 = new ArrayList<>();

        for (int i = 0; i < 5 ; i++) {
            DemoDate demoDate = new DemoDate();
            demoDate.setName("王晨");
            demoDate.setPhone(15242957219L);
            demoDate.setAddress("兴城");
            //demoDate.setFilePath("D:\\Huawei Share\\OneHop\\3.jpeg");
            demoDate.setDate(new Date());
            demoDate.setDoubleData((double)0.09993);
            list.add(demoDate);
        }

        for (int i = 0; i < 5 ; i++) {
            DemoDate1 demoDate= new DemoDate1();
            demoDate.setName("王晨1");
            demoDate.setPhone(15242957219L);
            demoDate.setAddress("兴城");
            //demoDate.setFilePath("D:\\Huawei Share\\OneHop\\3.jpeg");
            demoDate.setDate(new Date());
            demoDate.setDoubleData((double)0.09993);
            list1.add(demoDate);
        }

        listMap.put(DemoDate.class, list);
        listMap.put(DemoDate1.class, list1);



       //writeExcel(fileName, listMap);

        testT();*/

        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        stringJoiner.add("123");
        stringJoiner.add("456");

        System.out.println(stringJoiner);

       /* Stream<String> lines = Files.lines(Paths.get("D:\\Huawei Share\\OneHop\\2.mp4"), StandardCharsets.UTF_8);

        List<Path> paths = Files.list(Paths.get("D:\\Huawei Share\\OneHop\\my")).collect(Collectors.toList());

        Path path = Files.move(Paths.get("D:\\Huawei Share\\OneHop\\my\\2.mp4"),
                Paths.get("D:\\3.mp4"), StandardCopyOption.REPLACE_EXISTING);

        //只能删除文件,不能删除文件夹
        List<Path> paths = Files.list(Paths.get("D:\\Huawei Share\\OneHop\\my" )).collect(Collectors.toList());
        paths.forEach(path -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Files.delete(Paths.get("D:\\Huawei Share\\OneHop\\my1"));
        Files.deleteIfExists(Paths.get("D:\\Huawei Share\\OneHop\\my"));*/

        List<Path> collect = Files.walk(Paths.get("D:\\Huawei Share\\OneHop\\my1"), FileVisitOption.FOLLOW_LINKS)
                .collect(Collectors.toList());
        System.out.println(collect.toString());

        byte[] bytes = Files.readAllBytes(Paths.get("D:\\Huawei Share\\OneHop\\2.mp4"));

        Path write = Files.write(Paths.get("D:\\5.mp4"), bytes);

        URI uri = write.toUri();

        System.out.println(uri.toString());


    }



}
