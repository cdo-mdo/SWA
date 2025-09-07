package app;

import java.util.List;

import domain.Address;
import domain.CreditCard;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.CustomerRepository;
import domain.Customer;
import repositories.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "domain")
public class CustomerApplication implements CommandLineRunner{
	@Autowired
	StudentRepository studentrepository;

	@Autowired
	CustomerRepository customerrepository;

	public void addStudents() {
		Address address = new Address("1000 4th St", "FairField", "IA", "52557");

		Student student1 = new Student("Alfred Benefo Boahene", "Alfred.Boahene@miu.edu", "641-819-1111");
		Student student2 = new Student("Bassel Bakr Abdelhamid Ahmed", "Bassel.Ahmed@miu.edu", "641-819-1112");
		Student student3 = new Student("Khangai Dulamsuren", "kdulamsuren@miu.edu", "641-819-1113");
		Student student4 = new Student("Kalab Dereje Deneke", "kdeneke@miu.edu", "641-819-1114");
		Student student5 = new Student("Emmanuel Chilaka", "echilaka@miu.edu", "641-819-1115");

		student1.setAddress(address);
		student2.setAddress(address);
		student3.setAddress(address);
		student4.setAddress(address);
		student5.setAddress(address);

		studentrepository.save(student1);
		studentrepository.save(student2);
		studentrepository.save(student3);
		studentrepository.save(student4);
		studentrepository.save(student5);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);

//get customers
		System.out.println(customerrepository.findById(66).get());
		System.out.println(customerrepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerrepository.findAll());
		//update customer
		customer = customerrepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerrepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerrepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerrepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerrepository.findCustomerWithCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

		System.out.println("-----------find by name ----------------");
		System.out.println(customerrepository.findByName("John doe"));

		addStudents();

		List<Student> students = studentrepository.findAll();
		System.out.println("List Students :");
		for (Student student : students){
			System.out.println(student);
		}

		Student studentByName = studentrepository.getStudentByName("Emmanuel Chilaka");
		System.out.println(studentByName);

	}

}
