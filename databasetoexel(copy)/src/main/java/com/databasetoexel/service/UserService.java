package com.databasetoexel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.databasetoexel.entity.UserEntity;
import com.databasetoexel.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class UserService {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/data")
	public String getdata() throws IOException {
// ========================
		List<UserEntity> data = userRepository.getall();
		   File file= new File("E:\\mysqltoexel.xlsx");

		
		/*
		 * response.setContentType("application/vnd.ms-excel");
		 * response.setHeader("Content-Disposition",
		 * "attachment; filename=my_data.xlsx");
		 */
		   FileInputStream fileInputStream = new FileInputStream(file);
	        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
	        Sheet sheet = workbook.getSheetAt(0);
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("Email");
		header.createCell(3).setCellValue("City");

		int rowNum = 1;
		for (UserEntity d : data) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(d.getId());
			row.createCell(1).setCellValue(d.getName());
			row.createCell(2).setCellValue(d.getCity());
			row.createCell(3).setCellValue(d.getEmail());

		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);

		//workbook.write(response.getOutputStream());
		workbook.close();
		  fileInputStream.close();
	        fileOutputStream.close();
		return "data";
		// ===============
	}
}
