package com.masai.Service;

import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.model.Bus;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;
import com.masai.model.Routes;

import java.time.LocalDate;
import java.util.*;

public interface CustomerService {
	public Customer Ragistration(Customer customer)throws CustomerException;
	public Customer getCustomerDetailsById(Integer id,String key)throws CustomerException,LoginException;
	public String deleteByMobileAndPassword(LoginDTO loginDTO,String key)throws CustomerException,LoginException;
	public String changePassword(String oldPassword,String newPassword,String key)throws LoginException,CustomerException;
	
	public List<Routes> viewAllRoutes(String key)throws RouteException,LoginException;
	public Set<Bus> getBusBySourceAndDestincation(String routeFrom,String routeTo,String key,LocalDate date)throws LoginException,RouteException, BusException;
	public String BookTicket(Integer quantity,String key,String BusNumber)throws LoginException,BusException; 
	
	
}
