package com.capg.oms.service;

import java.util.List;
import java.util.Map;

import com.capg.oms.dao.UserDao;
import com.capg.oms.dao.UserDaoImpl;
import com.capg.oms.model.User;
import com.capg.oms.ui.LoginUser;

public class UserServiceImpl implements UserService {
	
	static UserDao dao=new UserDaoImpl();
	
	public boolean addUser(User user) {
		return dao.addUser(user); 
	}

	

	public Map<Long, User> getCustomer() {
		return dao.getCustomer();	
	}
	
	public Map<Long, User> getAdmin() {
		return dao.getAdmin();	
	}

	public User viewAdmin(long userId) {
		return dao.viewAdmin(userId);	
	}
	
	public User viewCustomer(long userId) {
		return dao.viewCustomer(userId);	
	}

	public void initialAdminList() {
		dao.addSomeAdmins();
	}
	
	public void initialCustomerList() {
		dao.addSomeCustomers();
	}
	
	public boolean validatePhoneNo(long phoneNo) throws InvalidDetailsException {
		String s=Long.toString(phoneNo);
		if(s.length()==10 && s.charAt(0)!=0)
			return true;
		else
			throw new InvalidDetailsException("Invalid Phone Number");	
	}

	public boolean validateEmail(String email) throws InvalidDetailsException {
		
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      if(email.matches(regex))
	    	  return true;
	      else
	    	  throw new InvalidDetailsException("Invalid email Id");
	   }
	
	public boolean validateCustomerId(long id) throws InvalidDetailsException {
		int count=0;
		while(id>0) {
			long d=id % 10;
			count++;
			id=id/10;
		}
		if(count>=6)
			return true;
		else  	
			throw new InvalidDetailsException("Invalid ID");	
	}

	public boolean validatePassword(String pw) throws InvalidDetailsException {
		if(pw.length()>=8) {
			return true;
		}
		else {
			throw new InvalidDetailsException("Password should be of minimun 8 characters");
		}
	}



	public void viewCitiesToBookTicket() {
		dao.listOfCities();	
	}



	public void addCityIntoListOfCities(String city) {
		dao.listOfCities().add(city);
		
	}



	public void removeCityFromListOfCities(String movie) {
		dao.listOfCities().remove(movie);
		
	}



	public void viewMoviesToBookTicket() {
		dao.listOfMovies();
		
	}



	public void addMovieIntoListOfMovies(String movie) {
		dao.listOfMovies().add(movie);
		
	}



	public void removeMovieFromListOfMovies(String movie) {
		dao.listOfMovies().remove(movie);
		
	}

}
