package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import com.masai.Exception.AdminException;
import com.masai.Exception.BookingException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Exception.RouteException;
import com.masai.Service.AdminService;
import com.masai.Service.BookingService;
import com.masai.Service.PackageService;
import com.masai.Service.TravelsService;
import com.masai.model.Admin;
import com.masai.model.Booking;
import com.masai.model.Bus;
import com.masai.model.CustomerDTO;
import com.masai.model.Hotel;
import com.masai.model.PackageModule;
import com.masai.model.Routes;
import com.masai.model.Ticket;
import com.masai.model.Travels;

@RestController
public class AdminController {
	

	@Autowired 
	private AdminService adminService;
	
	@Autowired
	private TravelsService travelService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PackageService packageService;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> InsertAdminHandler(@Valid @RequestBody Admin admin)throws AdminException{
		Admin admin2=adminService.InsertAdmin(admin);
		return new ResponseEntity<Admin>(admin2,HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Admin> GetByIdHandler(@RequestParam String key, @PathVariable Integer id)throws AdminException,LoginException{
		Admin admin=adminService.GetAdminById(id,key);
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomer(@RequestParam String key)throws CustomerException,LoginException{
		List<CustomerDTO> customerDTO=adminService.GetAllCustomerDetails(key);
		return new ResponseEntity<List<CustomerDTO>>(customerDTO,HttpStatus.OK);
	}
	
	@PostMapping("/route")
	public ResponseEntity<Routes> addRoutesHandler(@RequestParam String key,@Valid @RequestBody Routes route)throws LoginException{
		return new ResponseEntity<>(adminService.MakeRoute(route,key),HttpStatus.CREATED);
	}
		
	@DeleteMapping("/route/{id}")
	public ResponseEntity<String> deleteRoutesHandler(@RequestParam String key,@Valid @PathVariable("id") Integer id)throws LoginException{
		return new ResponseEntity<>(adminService.DeleteRoute(id,key),HttpStatus.OK);
	}
	@GetMapping("/routes")
	public ResponseEntity<List<Routes>> getAllRoutesHandler(@RequestParam String key)throws LoginException{
		return new ResponseEntity<>(adminService.routes(key),HttpStatus.OK);
	}
	
	@GetMapping("/route/{id}")
	public ResponseEntity<Routes> getRouteByIdHandler(@RequestParam String key,@Valid @PathVariable("id") Integer id)throws LoginException{
	  	return new ResponseEntity<>(adminService.findById(id,key),HttpStatus.OK);
	}
	
//	***************************************************Bus****************************************************
	
	@PostMapping("/Bus")
	public ResponseEntity<Bus> RagistorBusHandler(@RequestBody Bus bus,@RequestParam String key)throws BusException,LoginException{
		Bus bus2=adminService.RagistorBus(bus, key);
		return new ResponseEntity<Bus>(bus2,HttpStatus.CREATED);
	}
	
	@GetMapping("/Bus/{id}")
	public ResponseEntity<Bus> GetBusByIdHandler(@PathVariable Integer id ,@RequestParam String key)throws BusException,LoginException{
		Bus bus2=adminService.GetBusById(id, key);
		return new ResponseEntity<Bus>(bus2,HttpStatus.OK);
	}
	
	@GetMapping("/Buses")
	public ResponseEntity<List<Bus>> GetAllBusHandler(@RequestParam String key)throws BusException,LoginException{
		List<Bus> bus2=adminService.getAllTheBuses(key);
		return new ResponseEntity<List<Bus>>(bus2,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/Bus/{id}")
	public ResponseEntity<String> DeleteBusByIdHandler(@PathVariable Integer id ,@RequestParam String key)throws BusException,LoginException{
		String msg=adminService.RemoveBus(id, key);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/BusAssign")
	public ResponseEntity<String>AssignBusByRouteHandler(@RequestParam String routeCode,@RequestParam String BusNumber,@RequestParam String key)
			throws LoginException,BusException,RouteException{
		String msg=adminService.AssingBusToRoute(routeCode, BusNumber, key);
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	

//	******************************************************************Hotel***********************************8****************************************
	
	@PostMapping("/adminHotel")
	public ResponseEntity<Hotel> ragistorHotelHandler(@Valid @RequestBody Hotel hotel,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(adminService.ragistorHotel(hotel, key),HttpStatus.CREATED);
	}
	
	@PutMapping("/adminHotel")
	public ResponseEntity<Hotel>UpdateHotelHandler(@Valid @RequestBody Hotel hotel,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(adminService.updateHotel(hotel, key),HttpStatus.OK);
	}
	
	@GetMapping("/adminHotel/{id}")
	public ResponseEntity<Hotel>GetHotelByIdHandler(@Valid @PathVariable("id")Integer id,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<Hotel>(adminService.viewHotelById(id, key),HttpStatus.OK);
	}
	
	@DeleteMapping("/adminHotel/{id}")
	public ResponseEntity<String>DeleteHotelByIdHandler(@Valid @PathVariable("id")Integer id,@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<String>(adminService.deleteHotelById(id, key),HttpStatus.OK);
	}
	
	@GetMapping("/adminHotel")
	public ResponseEntity<List<Hotel>>GetAllHotelIdHandler(@RequestParam String key) throws HotelException, LoginException{
	return new ResponseEntity<List<Hotel>>(adminService.viewAllHotel(key),HttpStatus.OK);
	}

	
//	-----------------------Travels------------------------

	
	@PostMapping("/travel")
	public ResponseEntity<Travels> addTravelsHandler(@Valid @RequestBody Travels travel,@RequestParam String key){
		return new ResponseEntity<>(travelService.addTravels(travel),HttpStatus.CREATED);
	}
	
	@PutMapping("/travel")
	public ResponseEntity<Travels> updateTravelsHandler(@Valid @RequestBody Travels travel,@RequestParam String key){
		return new ResponseEntity<>(travelService.updateTravels(travel),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/travel/{id}")
	public ResponseEntity<Travels> deleteTravelsHandler(@Valid @PathVariable("id") Integer id,@RequestParam String key){
		return new ResponseEntity<>(travelService.removeTravels(id),HttpStatus.OK);
	}
	
	@GetMapping("/travel/{id}")
	public ResponseEntity<Travels> getTravelByIdHandler(@Valid @PathVariable("id") Integer id){
		return new ResponseEntity<>(travelService.getTravelsById(id),HttpStatus.OK);
	}
	
	@GetMapping("/travels")
	public ResponseEntity<List<Travels>> getAllTravelsHandler(){
		return new ResponseEntity<>(travelService.getAllTravels(),HttpStatus.OK);

	}
	
//	---------------------Package--------------------------------
	

	@PostMapping("/Apackage")
	public ResponseEntity<PackageModule> addPackageHandler(@RequestBody PackageModule pack,@RequestParam String key) throws PackageException,LoginException{
		return new ResponseEntity<PackageModule>(packageService.addPackage(pack,key), HttpStatus.CREATED);
	}

	@DeleteMapping("/Apackage/{id}")
	public ResponseEntity<PackageModule> deletePackageByIdHandler(@PathVariable("id")Integer id,@RequestParam String key) throws PackageException,LoginException{
		return new ResponseEntity<PackageModule>(packageService.deletePackage(id,key), HttpStatus.OK); 
	}

	@GetMapping("/Apackage/{id}")
	public ResponseEntity<PackageModule> searchPackageByIdHandler(@PathVariable("id") Integer id,@RequestParam String key) throws PackageException,LoginException{
		return new ResponseEntity<PackageModule>(packageService.searchPackage(id,key), HttpStatus.OK);
	}

	@GetMapping("/AviewListOfPackage")
	public ResponseEntity<List<PackageModule>> viewAllPackageHandler(@RequestParam String key) throws PackageException,LoginException{
		return new ResponseEntity<List<PackageModule>>(packageService.viewAllPackages(key), HttpStatus.OK);
	}
	
//	------------------------Booking--------------------------------
	
	
	@PostMapping("/Abooking/{otp}")
	public ResponseEntity<Ticket> makeBookingHandler(@RequestBody Booking booking,@RequestParam String key,@PathVariable("otp")Integer otp)throws BookingException,LoginException {
		return new ResponseEntity<Ticket>(bookingService.makeBooking(booking,key,otp), HttpStatus.CREATED);
	}

	@DeleteMapping("/Abooking/{id}")
	public ResponseEntity<Ticket> cancelBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<>(bookingService.cancelBooking(id,key), HttpStatus.OK);
	}

	@GetMapping("/Abooking/{id}")
	public ResponseEntity<Booking> viewBookingByIdHandler(@PathVariable("id") Integer id,@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<Booking>(bookingService.viewBooking(id,key), HttpStatus.OK);
	}
	
	@GetMapping("/listOfBooking")
	public ResponseEntity<List<Booking>> viewAllBookingHandler(@RequestParam String key)throws BookingException,LoginException {
		return new ResponseEntity<List<Booking>>(bookingService.viewAllBooking(key), HttpStatus.OK);
	}
	
}
