package com.masai.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BookingException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Exception.RouteException;
import com.masai.Service.BookingService;
import com.masai.Service.CustomerService;
import com.masai.Service.PackageService;
import com.masai.ServiceImpl.BookingServiceImpl;
import com.masai.model.Booking;
import com.masai.model.Bus;
import com.masai.model.Customer;
import com.masai.model.Hotel;
import com.masai.model.LoginDTO;
import com.masai.model.PackageModule;
import com.masai.model.Routes;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PackageService packageService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> Ragistraion(@Valid @RequestBody Customer customer)throws CustomerException{
		Customer customer2=customerService.Ragistration(customer);
		return new ResponseEntity<Customer>(customer2,HttpStatus.CREATED);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer>GetCustomerDetailsByIdHandler(@PathVariable Integer id,@RequestParam String key)throws CustomerException,LoginException{
		Customer customer=customerService.getCustomerDetailsById(id,key);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<String>deleteCustomerhandler(@RequestBody LoginDTO loginDTO,@RequestParam String key)throws CustomerException,LoginException{
		String msg=customerService.deleteByMobileAndPassword(loginDTO,key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<String> ChangePasswordHandler(@RequestParam String oldPassword,@RequestParam String newPassword, @RequestParam String key)
	throws LoginException,CustomerException
	{
		String msg=customerService.changePassword(oldPassword, newPassword, key);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/customerRoute")
	public ResponseEntity<List<Routes>> GetAllRouteHandler(@RequestParam String key)throws LoginException,CustomerException{
		List<Routes> list=customerService.viewAllRoutes(key);
		return new ResponseEntity<List<Routes>>(list,HttpStatus.OK);
	}
																		
	@GetMapping("/customerBus")
	public ResponseEntity<Set<Bus>> getAllBusBySourceToHandler(@RequestParam String routeFrom,String routeTo,String key,@DateTimeFormat(iso = ISO.DATE)  LocalDate date)throws LoginException,RouteException,BusException{
		Set<Bus> set=customerService.getBusBySourceAndDestincation(routeFrom, routeTo, key,date);
		return new ResponseEntity<Set<Bus>>(set,HttpStatus.OK);
	}
	
	@PutMapping("/CustomerTicket")
	public ResponseEntity<String>BookTicketOfBusHandler(@RequestParam Integer quantity, @RequestParam String key,@RequestParam String BusNumber)
	throws LoginException,BusException{
		String msg=customerService.BookTicket(quantity, key, BusNumber);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
//	------------------------------------Hotel---------------------------------------------
	
	
	@GetMapping("/customerHotel/{id}")
	public ResponseEntity<Hotel>GetHotelByIdHandler(@Valid @PathVariable("id")Integer id,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(customerService.viewHotelById(id, key),HttpStatus.OK);
	}
	
	@GetMapping("/customerHotels")
	public ResponseEntity<List<Hotel>>GetAllHotelIdHandler(@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<List<Hotel>>(customerService.viewAllHotel(key),HttpStatus.OK);
	}
	
	
	
//	--------------------------------------Package----------------------------------------
	
	

	@GetMapping("/package/{id}")
	public ResponseEntity<PackageModule> searchPackageByIdHandler(@PathVariable("id") Integer id,@RequestParam String key) throws PackageException,LoginException{
		return new ResponseEntity<PackageModule>(packageService.searchPackage(id, key), HttpStatus.OK);
	}

	@GetMapping("/viewListOfPackage")
	public ResponseEntity<List<PackageModule>> viewAllPackageHandler(@RequestParam String key) throws PackageException,LoginException{
		return new ResponseEntity<List<PackageModule>>(packageService.viewAllPackages(key), HttpStatus.OK);
	}
	
	
	
//--------------------------------------Booking-----------------------------------------------
	
	

	

	@PostMapping("/booking")
	public ResponseEntity<Booking> makeBookingHandler(@RequestBody Booking booking,@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<Booking>(bookingService.makeBooking(booking,key), HttpStatus.CREATED);
	}

	@DeleteMapping("/booking/{id}")
	public ResponseEntity<Booking> cancelBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<Booking>(bookingService.cancelBooking(id,key), HttpStatus.OK);
	}

	@GetMapping("/booking/{id}")
	public ResponseEntity<Booking> viewBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<Booking>(bookingService.viewBooking(id,key), HttpStatus.OK);
	}
	
	
	
	
}
