package customers;

import customers.domain.Address;
import customers.domain.CreditCard;
import customers.domain.Customer;
import customers.domain.Student;
import customers.repository.CustomerRepository;
import customers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void addStudent() {
		Address address = new Address("1000 4th St", "FairField", "IA", "52557");

		Student student1 = new Student(1L,"Sambath Chan", "641-819-2221", "schan@miu.edu");
		Student student2 = new Student(2L, "Zaw ye Naing", "641-819-2222", "znaing@miu.edu");
		Student student3 = new Student(3L, "Nguyen Khanh an Tran", "641-819-2223", "n.tran@miu.edu");
		Student student4 = new Student(4L, "Abdullah Ragheb Abdraboh Abdelglil", "641-819-2224", "aabdelglil@miu.edu");
		Student student5 = new Student(5L, "Imededdine Ben Kalia", "641-819-2225", "Imededdine.Kalia@miu.edu");

		student1.setAddress(address);
		student2.setAddress(address);
		student3.setAddress(address);
		student4.setAddress(address);
		student5.setAddress(address);

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);
		studentRepository.save(student4);
		studentRepository.save(student5);
	}

	@Override
	public void run(String... args) throws Exception {
        // create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		//get customers
		System.out.println(customerRepository.findById(66).get());
		System.out.println(customerRepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());
		//update customer
		customer = customerRepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerRepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerRepository.findCustomerWithCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

		System.out.println("-----------find by name ----------------");
		System.out.println(customerRepository.findByName("John doe"));

		addStudent();

		List<Student> students = studentRepository.findAll();
		for (Student student : students){
			System.out.println(student);
		}

		Student student = studentRepository.getStudentByName("Sambath Chan");
		System.out.println(student);

		student = studentRepository.getStudentByPhone("641-819-2223");
		System.out.println(student);

		students = Collections.singletonList(studentRepository.getStudentByCity("FairField"));
		for (Student stud : students){
			System.out.println(stud);
		}





	}

}
