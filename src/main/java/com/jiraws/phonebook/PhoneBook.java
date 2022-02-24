package com.jiraws.phonebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PhoneBook {
	
	public static final String PHONE_BOOK_FILE_PATH = "C:\\Users\\k0nezzst4rk\\Documents\\live_youtube\\phonebook.txt";
	public static Scanner sc = null;

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		
		String userLastName 	= getUserInput("Entrez un nom de famille: ");
		String userFirstName  	= getUserInput("Entrez un prénom: ");
		String userPhoneNumber 	= getUserInput("Entrez un numéro de téléphone: ");
		
		Contact newContact  = new Contact(userLastName, userFirstName, userPhoneNumber);
		
		File phoneBookFile = getOrCreatePhoneBookFile(PHONE_BOOK_FILE_PATH);
		appendContactToPhoneBook(phoneBookFile, newContact);
		
		sc.close();
	}
	
	public static String getUserInput(String userRequest) {
		System.out.println(userRequest);
		return sc.nextLine();
	}
	
	public static File getOrCreatePhoneBookFile(String phoneBookFilePath) {
		
		File phoneBookFile = new File(phoneBookFilePath);
		
		if(phoneBookFile.exists()) {
			return phoneBookFile;
		}
		
		try {
			phoneBookFile.createNewFile();
			System.out.println("Le fichier a été créé (" + phoneBookFilePath + ")");
			return phoneBookFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void appendContactToPhoneBook(File phoneBookFile, Contact newContact) {
		
		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(phoneBookFile, true))) {
			
			fileWriter.append(newContact.toString());
			fileWriter.append(System.lineSeparator());
			
			System.out.println("Contact ajouté");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
