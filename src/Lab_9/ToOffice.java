package Lab_9;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class ToOffice {
    static Workbook book;

    public static void toExcel(String[] tableTitles, String[][] data, File file, String curTableName) {
        book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(curTableName);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableTitles.length));
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(curTableName);
        HSSFCellStyle cellStyle = (HSSFCellStyle) book.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = book.createFont();
        font.setFontHeightInPoints((short) 24);
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        headCreate(sheet, tableTitles);
        dataToSheet(sheet, data);
        for (int i = 0; i < tableTitles.length; i++) {
            sheet.autoSizeColumn(i);
        }
        try {
            book.write(new FileOutputStream(file));
            book.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("xls file was written successfully");
    }

    private static void headCreate(Sheet sheet, String[] tableTitles) {

        Row row = sheet.createRow(1);
        HSSFCellStyle cellStyle = (HSSFCellStyle) book.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THICK);
        cellStyle.setBorderLeft(BorderStyle.THICK);
        cellStyle.setBorderRight(BorderStyle.THICK);
        cellStyle.setBorderTop(BorderStyle.THICK);
        Font font = book.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        for (int i = 0; i < tableTitles.length; i++) {
            Cell temp = row.createCell(i);
            temp.setCellStyle(cellStyle);
            temp.setCellValue(tableTitles[i]);
        }
    }

    private static void dataToSheet(Sheet sheet, String[][] data) {
        HSSFCellStyle cellStyle = (HSSFCellStyle) book.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 2);
            for (int j = 0; j < data[i].length; j++) {
                Cell temp = row.createCell(j);
                temp.setCellValue(data[i][j]);
                temp.setCellStyle(cellStyle);
            }
        }
    }

    public static void toWordDocx(String[] tableTitles, String[][] data, File file, String curTableName) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(curTableName);
        XWPFTable table = document.createTable();
        XWPFTableRow tableHead = table.getRow(0);
        for (int i = 0; i < tableTitles.length; i++) {
            XWPFParagraph paragraph1 = null;
            if (i == 0) {
                paragraph1 = tableHead.getCell(0).getParagraphs().get(0);
            } else {
                XWPFTableCell cell = tableHead.addNewTableCell();
                paragraph1 = cell.getParagraphs().get(0);
            }
            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun tableHeadRun = paragraph1.createRun(); //
            tableHeadRun.setBold(true);
            tableHeadRun.setText(tableTitles[i]);
        }
        for (int i = 0; i < data.length; i++) {
            XWPFTableRow tableRow = table.createRow();
            for (int j = 0; j < data[i].length; j++) {
                tableRow.getCell(j).setText(data[i][j]);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            document.write(out);
            out.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("docx file was written successfully");
    }
}