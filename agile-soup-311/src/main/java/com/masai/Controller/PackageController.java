package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.PackageService;
import com.masai.model.PackageModule;

@RestController
@RequestMapping("/packages")
public class PackageController {

	@Autowired
	private PackageService packageService;

	@PostMapping("/package")
	public ResponseEntity<PackageModule> addPackageHandler(@Valid @RequestBody PackageModule pack) {
		return new ResponseEntity<PackageModule>(packageService.addPackage(pack), HttpStatus.CREATED);
	}

	@DeleteMapping("/package/{id}")
	public ResponseEntity<PackageModule> deletePackageByIdHandler(@PathVariable("id") Integer id) {
		return new ResponseEntity<PackageModule>(packageService.deletePackage(id), HttpStatus.OK); 
	}

	@GetMapping("/package/{id}")
	public ResponseEntity<PackageModule> searchPackageByIdHandler(@PathVariable("id") Integer id) {
		return new ResponseEntity<PackageModule>(packageService.searchPackage(id), HttpStatus.OK);
	}

	@GetMapping("/viewListOfPackage")
	public ResponseEntity<List<PackageModule>> viewAllPackageHandler() {
		return new ResponseEntity<List<PackageModule>>(packageService.viewAllPackages(), HttpStatus.OK);
	}
}