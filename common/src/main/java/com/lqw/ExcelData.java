package com.lqw;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: 灵枢
 * Date: 2018/12/05
 * Time: 17:21
 * Description:读取Excel数据
 */
public class ExcelData {
    private XSSFSheet sheet;

    private XSSFWorkbook sheets;

    /**
     * 构造函数，初始化excel数据
     *
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    ExcelData(String filePath, String sheetName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            sheet = sheets.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     *
     * @param row
     * @param column
     * @return
     */
    public String getExcelDateByIndex(int row, int column) {
        XSSFRow row1 = sheet.getRow(row);
        String cell = row1.getCell(column).toString();
        return cell;
    }

    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     *
     * @param caseName
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn  目标单元格列的索引
     * @return
     */
    public String getCellByCaseName(String caseName, int currentColumn, int targetColumn) {
        String operateSteps = "";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if (cell.equals(caseName)) {
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    //打印excel数据
    public void readExcelData() {
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for (int j = 0; j < columns; j++) {
                String cell = row.getCell(j).toString();
                System.out.println(cell);
            }
        }
    }

    public static final String property = "public_fang_property";
    public static final String customer = "public_ke_customer";

    //测试方法
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/luoquanwei/Downloads/customer.xlsx";
        ExcelData excel = new ExcelData(filePath, "customer");

        List<IndexInfo> indexInfos = getIndexInfos(excel);



        indexInfos.sort(Comparator.comparing(IndexInfo::getCount).reversed());
        List<String> allIndexNames = getAllIndexNames(customer, 17);

        Map<String, Long> indexMap = allIndexNames.stream().collect(Collectors.toMap(i -> i, i -> 0L));

//        long count = 0;
//        for (String indexName : allIndexNames) {
//            for (int i = 0; i < indexInfos.size(); ) {
//                IndexInfo indexInfo = indexInfos.get(i);
//                if (StringUtils.isNotBlank(indexInfo.getIndexName())) {
//                    i++;
//                    continue;
//                }
//                if (indexInfo.getCount() > 700000) {
//                    indexInfo.setIndexName(indexName);
//
//                    break;
//                } else {
//                    count = count + indexInfo.getCount();
//                    if (count < 700000) {
//                        indexInfo.setIndexName(indexName);
//                        i=i+allIndexNames.size();
//                    } else {
//                        count = 0;
//                        break;
//                    }
//                }
//
//            }
//        }

        for (int i=0;i<indexInfos.size();i++) {
            IndexInfo indexInfo =indexInfos.get(i);
            if(indexInfo.getCount()<=100){
                indexInfo.setIndexName(getIndexName(customer,20));
            }else if(indexInfo.getCount()<=500){
                indexInfo.setIndexName(getIndexName(customer,19));
            }else if(indexInfo.getCount()<=1000){
                indexInfo.setIndexName(getIndexName(customer,18));
            }
            if(StringUtils.isNotBlank( indexInfo.getIndexName())){
                continue;
            }

            String currentIndexName;
            Long count=0L;
            boolean flag=true;
            do {

                currentIndexName = allIndexNames.get(new Random().nextInt(17));
                count = indexMap.get(currentIndexName);

                if ((indexInfo.getCount() > 700000 && count == 0)
                        || (count + indexInfo.getCount() < 705000)
                ) {
                    flag = false;
                }

            }while (flag);

            indexInfo.setIndexName(currentIndexName);
            count=count+indexInfo.getCount();
            indexMap.put(currentIndexName,count);
        }


        indexInfos.sort(Comparator.comparing(IndexInfo::getIndexName));
        indexInfos.forEach(i -> System.out.println(i.getCompanyName() + " " + i.getCount() + " " + i.getIndexName()));

        indexMap.forEach((index,count)->{
            System.out.println(index+" "+count);
        });

        Map<String, IndexInfo> indexInfoMap = indexInfos.stream().collect(Collectors.toMap(i -> i.getCompanyUuid(), i -> i));

        Iterator<Row> rowIterator = excel.sheet.rowIterator();

        while (rowIterator.hasNext()){
            Row next = rowIterator.next();

            String companyUuid = next.getCell(1).getStringCellValue();
            IndexInfo indexInfo = indexInfoMap.get(companyUuid);
            if(indexInfo!=null) {
                next.createCell(3).setCellValue(indexInfo.getIndexName());
            }
        }
        excel.sheets.write(new FileOutputStream(filePath));
        excel.sheets.close();
    }


    public static List<String> getAllIndexNames(String type, int count) {
        List<String> s = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            s.add(getIndexName(type,i));
        }

        return s;
    }

    public static String getIndexName(String type, int count){
        return type + "_" + String.format("%04d", count);
    }


    public static List<IndexInfo> getIndexInfos(ExcelData excel) {

        List<IndexInfo> indexs = new ArrayList<>();

        Iterator<Row> rowIterator = excel.sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row next = rowIterator.next();
            if (next.getRowNum() != 0) {
                indexs.add(new IndexInfo(next.getCell(0).getStringCellValue(),
                        next.getCell(1).getStringCellValue(),
                        (long) next.getCell(2).getNumericCellValue()));
            }
        }

        return indexs;
    }

    public static class IndexInfo {
        private String companyName;

        private String companyUuid;

        private long count;

        private String indexName;

        public IndexInfo(String companyName, String companyUuid, long count) {
            this.companyName = companyName;
            this.companyUuid = companyUuid;
            this.count = count;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getCompanyUuid() {
            return companyUuid;
        }

        public long getCount() {
            return count;
        }

        public String getIndexName() {
            return indexName;
        }

        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }
    }
}

