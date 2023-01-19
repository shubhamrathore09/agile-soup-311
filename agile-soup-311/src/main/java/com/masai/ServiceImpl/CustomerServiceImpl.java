package com.masai.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CustomerException;
import com.masai.Repository.CustomerRepo;
import com.masai.Service.CustomerService;
import com.masai.model.Customer;



@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;
	

	@Override
	public Customer Ragistration(Customer customer) throws CustomerException {
		Customer customer2=customerRepo.findByCustomerMobile(customer.getCustomerMobile());
		if(customer2==null) {
			return customerRepo.save(customer);
		}
		else {
			throw new CustomerException("customer already exist by that number");
		}
	}


	@Override
	public Customer getCustomerDetailsById(Integer id) throws CustomerException {
		return customerRepo.findById(id).orElseThrow(()->new CustomerException("Customer not available by that id: "+id));
	}

}
