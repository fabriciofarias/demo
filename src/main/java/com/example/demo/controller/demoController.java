package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@RestController
public class demoController {
	
	private String result = "";
	
	@RequestMapping("/echo")
	public String listar(@RequestParam("file") MultipartFile file ) throws CsvValidationException, IOException {
		
		/**
		 * Reads the file guiven by the user and convert it
		 * to File.Class, so that it will be used in the 
		 * CSVReader.Class
		 */
		Path filepath = Paths.get("", file.getOriginalFilename());

	    try (OutputStream os = Files.newOutputStream(filepath)) {
	        os.write(file.getBytes());
	    }		
		
		try {
			CSVReader csvReader = new CSVReader(new FileReader(filepath.toFile()));
			
			List<List<String>> linhas = new ArrayList<List<String>>();
			String[] colunas = null;
			
			while( (colunas = csvReader.readNext()) != null ) {
				linhas.add(Arrays.asList(colunas));
			}			
			for (int i = 0; i < linhas.size(); i++) {
				if(i < 2)
					result += linhas.get(i) + "\n";
				else 
					result += linhas.get(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	@RequestMapping("/invert")
	public String invert(@RequestParam("file") MultipartFile file ) throws CsvValidationException, IOException {
		Path filepath = Paths.get("", file.getOriginalFilename());
	    try (OutputStream os = Files.newOutputStream(filepath)) {
	        os.write(file.getBytes());
	    }		
		try {
			CSVReader csvReader = new CSVReader(new FileReader(filepath.toFile()));
			
			List<List<String>> linhas = new ArrayList<List<String>>();
			String[] colunas = null;
			
			while( (colunas = csvReader.readNext()) != null ) {
				linhas.add(Arrays.asList(colunas));
			}
			
			/**
			 * Create a temporary new matrix to receive the inverted 
			 * new arrays
			 */
			List<String> novaMatrix1 = new ArrayList<String>();
			List<String> novaMatrix2 = new ArrayList<String>();
			List<String> novaMatrix3 = new ArrayList<String>();
			for (int i = 0; i < linhas.size(); i++) {
				for (int j = 0; j < linhas.size(); j++) {
					if(j == 0) {
						novaMatrix1.add(linhas.get(i).get(j));
					}else if(j == 1) {
						novaMatrix2.add(linhas.get(i).get(j));
					}else if(j == 2) {
						novaMatrix3.add(linhas.get(i).get(j));
					}					
					result = novaMatrix1 + "\n" + novaMatrix2 + "\n" + novaMatrix3;					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/flatten")
	public String flatten(@RequestParam("file") MultipartFile file ) throws CsvValidationException, IOException {
		Path filepath = Paths.get("", file.getOriginalFilename());
	    try (OutputStream os = Files.newOutputStream(filepath)) {
	        os.write(file.getBytes());
	    }		
		try {
			CSVReader csvReader = new CSVReader(new FileReader(filepath.toFile()));
			
			List<List<String>> linhas = new ArrayList<List<String>>();
			String[] colunas = null;
			
			while( (colunas = csvReader.readNext()) != null ) {
				linhas.add(Arrays.asList(colunas));
			}
			
			/***
			 * Print the result as flatten and give them a commam, except 
			 * for the last one
			 */
			for (int i = 0; i < linhas.size(); i++) {
				for (int j = 0; j < linhas.size(); j++) {
					result += linhas.get(i).get(j);
					if(i != 2 || j != 2)
						result += ",";
				}								
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/sum")
	public String sum(@RequestParam("file") MultipartFile file ) throws CsvValidationException, IOException {
		Path filepath = Paths.get("", file.getOriginalFilename());
	    try (OutputStream os = Files.newOutputStream(filepath)) {
	        os.write(file.getBytes());
	    }	
		try {
			CSVReader csvReader = new CSVReader(new FileReader(filepath.toFile()));
			
			List<List<String>> linhas = new ArrayList<List<String>>();
			String[] colunas = null;
			
			while( (colunas = csvReader.readNext()) != null ) {
				linhas.add(Arrays.asList(colunas));
			}
			/***
			 * Print the result as flatten and give them a comman, except 
			 * for the last one;
			 */
			int sum = 0;
			for (int i = 0; i < linhas.size(); i++) {
				for (int j = 0; j < linhas.size(); j++) {
					sum += Integer.parseInt(linhas.get(i).get(j));					
				}								
			}
			result = "" + sum;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}				
		return result;
	}
	
	@RequestMapping("/multiply")
	public String multiply(@RequestParam("file") MultipartFile file ) throws CsvValidationException, IOException {
		Path filepath = Paths.get("", file.getOriginalFilename());
	    try (OutputStream os = Files.newOutputStream(filepath)) {
	        os.write(file.getBytes());
	    }
		try {
			CSVReader csvReader = new CSVReader(new FileReader(filepath.toFile()));
			
			List<List<String>> linhas = new ArrayList<List<String>>();
			String[] colunas = null;
			
			while( (colunas = csvReader.readNext()) != null ) {
				linhas.add(Arrays.asList(colunas));
			}
			
			int multiply = 0;
			for (int i = 0; i < linhas.size(); i++) {
				for (int j = 0; j < linhas.size(); j++) {
					if(i == 0 && j == 0)
						multiply = Integer.parseInt(linhas.get(i).get(j));
					else
						multiply *= Integer.parseInt(linhas.get(i).get(j));
				}								
			}
			result = "" + multiply;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
