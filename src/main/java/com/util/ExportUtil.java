package com.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportUtil {
	/**
     * 样式设置
     */
    public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook){
        // *生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        // 前景色
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        // 背景色
        style.setFillBackgroundColor(HSSFColor.GREEN.index);
        // 填充样式
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置底边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色  
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 设置自动换行
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        HSSFFont font = createCellFont(workbook);
//        // 把字体应用到当前的样式
//        style.setFont(font);
        return style;
    }
     
 
    /**
     * 字体设置
     */
    public static HSSFFont createCellFont(HSSFWorkbook workbook){
        // *生成一个字体
        HSSFFont font = workbook.createFont();
        // 字体颜色
        font.setColor(HSSFColor.VIOLET.index);
        // 字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 是否使用斜体
        font.setItalic( true );
        // 是否使用划线
        //font.setStrikeout(true);
        // 字体名字
        font.setFontName( " 黑体 " );
        return font;
    }
     
 
    /**
     * 注释设置
     */
    public static HSSFComment createCellComment(HSSFPatriarch patriarch, String commentText, String commentName){
        // *添加单元格注释
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1, (short) 2, 2));
        // 设置注释内容
        comment.setString(new HSSFRichTextString(commentText));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor(commentName);
        return comment;
    }
     
 
    /**
     * 图片插入
     */
    public static void createCellPicture(HSSFWorkbook workbook, HSSFPatriarch patriarch, String url, int col, int row) throws Exception{
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        BufferedImage bufferImg =null;
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        bufferImg = ImageIO.read(new File( url )); 
        ImageIO.write(bufferImg,"jpg",byteArrayOut);
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) col, row, (short) col, row);
        anchor.setAnchorType(3);
        //插入图片
        patriarch.createPicture(anchor , workbook.addPicture(byteArrayOut.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));
        byteArrayOut.flush();
        byteArrayOut.close();
    }
 
    /**
     * 导出EXCEL
     */
    public static void excel(List<Map<String, Object>> list, String[] head, String excelName,HttpServletResponse response ) throws Exception{
        HSSFWorkbook workbook = makeWorkBook(list, head, excelName);
        response.setContentType("applicationnd.ms-excel");
        String fileName = new String(excelName.getBytes(),"utf-8");
        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
        workbook.write(response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
    
    /**
     * 生成一个workbook
     */
    public static HSSFWorkbook makeWorkBook(List<Map<String, Object>> list, String[] head, String excelName) throws Exception{
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook(); 
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(excelName); 
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth(20);
        // 字体设置
        HSSFFont font = createCellFont(workbook);
        // 样式设置
        HSSFCellStyle style = createCellStyle(workbook);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 声明一个管理器,HSSFPatriarch是所有注释的容器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // *产生表格标题行
        // 创建行(row)
        HSSFRow row = sheet.createRow(0);
        HSSFComment comment = createCellComment(patriarch, "这是标题行", "123");
        for (int i = 0; i < head.length; i++) {
            // 创建单元格
            HSSFCell cell = row.createCell(i);
            // 获取内容
            HSSFRichTextString text = new HSSFRichTextString(head[i]);
            // 设置单元格内容
            cell.setCellValue(text);
            // 设置单元格样式
            cell.setCellStyle(style);
            // 指定单元格格式：数值、公式或字符串 
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 添加注释
            cell.setCellComment(comment);
        }
        // *遍历集合数据,产生表格数据行
        for (int i=1, k=list.size()+1; i<k; i++) {
            // 创建新行(row)
            row = sheet.createRow(i);
            // 设置行高
            row.setHeightInPoints(60);
            // 设置图片所在列宽度为80px,
            sheet.setColumnWidth(i, (short)(35.7 * 80));
            comment = createCellComment(patriarch, "这是数据行", "123");
            for(int j =0;j<head.length;j++ ){
                // 创建单元格
                HSSFCell cell =row.createCell(j);
                // 获取内容
                Map<String , Object> map=(Map<String, Object>) list.get(i-1);
                HSSFRichTextString text = new HSSFRichTextString(String.valueOf( map.get(String.valueOf(j)) ));
                cell.setCellStyle(style);
                cell.setCellValue(text);
                cell.setCellComment(comment);
                // 插图片(6为图片列)
                // map.get(String.valueOf(j)).toString() 为图片绝对地址
//                if(j==6){
//                    createCellPicture(workbook, patriarch, map.get(String.valueOf(j)).toString(), 6, i);
//                }
            }
        }
        return workbook;
    }
    
    
    //方法调用
    public static void excelTest(){
        try {
            List<Map<String, Object>> listBody = new ArrayList<Map<String, Object>>();
            for(int i=0;i<10;i++){
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("0", i+"_"+"1");
                map.put("1", i+"_"+"abc");
                map.put("2", i+"_"+"xiaoAbc");
                map.put("3", i+"_"+"adf12");
                map.put("4", i+"_"+"部门");
                map.put("5", i+"_"+"开发");
                map.put("6", i+"_"+"哈哈");
                listBody.add(map);
            }
            
            FileOutputStream fileOut = new FileOutputStream("workbook.xls");
            HSSFWorkbook wb = makeWorkBook(listBody, new String[]{"ID","用户名","真实姓名","密码","部门","工作","url"}, "我的表格");
            wb.write(fileOut);
            fileOut.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		excelTest();
	}
 
}
