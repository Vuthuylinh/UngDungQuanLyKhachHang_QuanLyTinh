package linhVu.service.impl;

import linhVu.model.Customer;
import linhVu.model.Province;
import linhVu.repository.CustomerRepository;
import linhVu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer customer) {
    customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
    customerRepository.delete(id);
    }

    @Override
    public Page<Customer> findAllByFirstNameContaining(String firstName, Pageable pageable) {
        return customerRepository.findAllByFirstNameContaining(firstName,pageable);
    }
}
