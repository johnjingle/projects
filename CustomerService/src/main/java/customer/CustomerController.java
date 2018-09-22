package customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/* 
 * For addCustomer tested with Postman raw, JSON eg.
 * {
 * "id":1,
 * "firstname":"John",
 * "surname":"Smith"
 * }
 * 
 * The same names are allowed as long as the id is different.
 * 
 */
@RestController
public class CustomerController {
    
    // ArrayList will allow duplicate names but with a different id.
    // This could be the case.
    private List<Customer> customers = new ArrayList<>();
    
    @RequestMapping(value="/addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody ResponseEntity<Customer> addCustomer(@RequestBody Customer newCustomer) {
   	 
     	Customer theCustomer= customers.stream()
  			  .filter(customer -> newCustomer.getId() == customer.getId())
  			  .findAny()
  			  .orElse(null);
     	
     	if (theCustomer == null) {
     		customers.add(newCustomer);
     	} else  {
     		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(newCustomer);
     	}
    	
    	return ResponseEntity.status(HttpStatus.OK).body(newCustomer);
    }
    
    @RequestMapping(value="/deleteCustomer", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomer(@RequestParam(value="id", defaultValue="0") int id) {
    	
    	System.out.println(id);
    	
    	Customer theCustomer= customers.stream()
    			  .filter(customer -> id == customer.getId())
    			  .findAny()
    			  .orElse(null);
    		
         if(theCustomer != null ) {
        	 customers.remove(customers.indexOf(theCustomer));
         } else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id " + id + " Not found");
    	}
     	
        return ResponseEntity.status(HttpStatus.OK).body("Id " + id +  " Deleted ok");
    }
    
    @RequestMapping(value="/allCustomers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> allCustomer() {
    	
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
    
}
