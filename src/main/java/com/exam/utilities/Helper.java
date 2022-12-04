package com.exam.utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.exam.model.quiz.Question;

public class Helper {

	// check file is of excel type
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}
		return false;

	}

	// convert the data of excel to list of products
	@SuppressWarnings("unchecked")
	public static List<Question> convertExcelToListOfProduct(InputStream is) {
		List<Question> list = new ArrayList<>();
		try {

			try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
				XSSFSheet sheet = workbook.getSheet("Questions");

				int rowNumber = 0;

				Iterator<?> iterator = sheet.iterator();

				while (((Iterator<Row>) iterator).hasNext()) {

					Row row = ((Iterator<Row>) iterator).next();

					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}

					Iterator<Cell> cells = row.iterator();

					int cid = 0;

					Question question = new Question();

					while (cells.hasNext()) {
						Cell cell = cells.next();

						switch (cid) {
						case 0:
							question.setContent(cell.getStringCellValue());
							break;

						case 1:
							question.setOption1(cell.getStringCellValue());
							break;

						case 2:
							question.setOption2(cell.getStringCellValue());
							break;
							
						case 3:
							question.setOption3(cell.getStringCellValue());
							break;

						case 4:
							question.setOption4(cell.getStringCellValue());
							break;

						case 5:
							question.setAnswer(cell.getStringCellValue());
							break;

						default:
							break;
						}
						if(cid == 6)
						{
							//System.out.println(cell.getStringCellValue());
						}
						cid++;
						System.out.println(cid);
					}
					list.add(question);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
