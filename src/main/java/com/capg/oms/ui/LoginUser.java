package com.capg.oms.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.capg.oms.model.User;
import com.capg.oms.service.InvalidDetailsException;
import com.capg.oms.service.UserService;
import com.capg.oms.service.UserServiceImpl;

public class LoginUser {
	static Scanner sc=new Scanner(System.in);
	static User user=new User();
	static UserService service=new UserServiceImpl();
	public static void main(String[] args) {
		login();
	}
	
	public static void newUser() {
		
		System.out.println("Enter the userId:");
		long id=sc.nextLong();
		service.initialCustomerList();
		if(!service.getCustomer().containsKey(id))
		{
   		try {
			if(service.validateCustomerId(id)) {
				user.setUserId(id);
			}
		System.out.println("Enter the password: ");
		String password=sc.next()+sc.nextLine();
		if(service.validatePassword(password)) {
			user.setUserPassword(password);
		}

		System.out.println("Enter the Name: ");
		String name=sc.next()+sc.nextLine();
		user.setUserName(name);
		
		System.out.println("Enter the phone: ");
		long phone=sc.nextLong();
		if(service.validatePhoneNo(phone)) {
			user.setUserPhone(phone);
		}
		
		System.out.println("Enter the email: ");
		String mail=sc.next()+sc.nextLine();
		if(service.validateEmail(mail)) {
			user.setUserEmail(mail);
		}
		
   		
		if(service.addUser(user)) {
			System.out.println("Account created Successfully!!!.....you can login into your account now");
			login();
		}
		}
		catch (InvalidDetailsException e1) {
			System.out.println(e1.getMessage()+"Please try again...");
			login();
		}
   		}
   		else {
			System.out.println("User id already exists....Please try again");
			login();
			
		}
		
	}

	public static void customerLogin() {
		service.initialCustomerList();
		service.addUser(user);
		try {
		System.out.println("Enter the userId: ");
		long customerId = sc.nextLong();
		String customerPassword=null ;
		if(service.getCustomer().containsKey(customerId)) {
			System.out.println("Enter the password: ");
			customerPassword = sc.next()+sc.nextLine();
			String password=service.viewCustomer(customerId).getUserPassword();
			if(password.equals(customerPassword)) {
				System.out.println("Login Successful");
				customerPage();
			}
			else {
					System.out.println("Invalid password!!!.....try again");
					System.out.println("If new user then create a new account...");
					
			}
		}
		else {
			System.out.println("Invalid Id");
			System.out.println("If new user then create a new account...");
			
		}		
		}catch(InputMismatchException err) {
			System.out.println("Please enter valid type...");
		}
	}
	
	public static void adminLogin() {
		service.initialAdminList();
		try {
		System.out.println("Enter the userId: ");
		long adminId = sc.nextLong();
		String adminPassword=null ;
		if(service.getAdmin().containsKey(adminId)) {
			System.out.println("Enter the password: ");
			adminPassword = sc.next()+sc.nextLine();
			String p=service.viewAdmin(adminId).getUserPassword();
			if(p.equals(adminPassword)) {
				System.out.println("Login Successful");
				adminPage();
			}
			else {
					System.out.println("Invalid password!!!......try again");
					login();
			}
		}
		else {
			System.out.println("Invalid id....please try again");
			login();
		
		}
		}catch(InputMismatchException err) {
			System.out.println("Please enter valid type...");
		}
	}
	
	public static void login() {
		int choice=0;
		
		System.out.println("Choose the type of user : ");
		System.out.println("1 - Admin");
		System.out.println("2 - Customer");
		System.out.println("3 - New User");
		System.out.println("4 - Exit");
		try {
		choice=sc.nextInt();
		switch(choice) {
			case 1:
				adminLogin();
				break;
			
			case 2:
				customerLogin();
				break;
				
			case 3:
				newUser();
				break;
				
			case 4:
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid choice....please try again");
				login();
				break;
		}
		}
		catch(InputMismatchException err) {
			System.out.println("Please choose valid option...");
		}
		
	}
	
	public static void adminPage() {
		System.out.println("Choose an option");
		System.out.println("1 - View Cities list");
		System.out.println("2 - Add Cities into list");
		System.out.println("3 - Remove city from list");
		System.out.println("4 - View Movies list");
		System.out.println("5 - Add Movie into list");
		System.out.println("6 - Remove movie from list");
		int choice=sc.nextInt();
		switch(choice) {
			case 1:	service.viewCitiesToBookTicket();
					break;
					
			case 2: System.out.println("Enter the city name to be added:");
					String cityName=sc.next()+sc.nextLine();
					service.addCityIntoListOfCities(cityName);
					break;
			
			case 3: System.out.println("Enter the city name to be removed:");
					String cityNameToRemove=sc.next()+sc.nextLine();
					service.removeCityFromListOfCities(cityNameToRemove);
					break;
					
			case 4:	service.viewMoviesToBookTicket();
					break;	
					
			case 5: System.out.println("Enter the movie name to be added:");
					String movieName=sc.next()+sc.nextLine();
					service.addMovieIntoListOfMovies(movieName);
					break;	
			
			case 6: System.out.println("Enter the movie name to be removed:");
					String movieNameToRemove=sc.next()+sc.nextLine();
					service.removeMovieFromListOfMovies(movieNameToRemove);
					break;
					
			default: System.out.println("Invalid Input");
					break;
						
			
		}
	}
	
	public static void customerPage() {
		System.out.println("Choose an option");
		System.out.println("1 - View Cities list");
		System.out.println("2 - View Movies list");
		int choice=sc.nextInt();
		switch(choice) {
			case 1:	service.viewCitiesToBookTicket();
					break;
			case 2:	service.viewMoviesToBookTicket();
					break;
			default: System.out.println("Invalid Input");
					break;
		}
		
	}
}
