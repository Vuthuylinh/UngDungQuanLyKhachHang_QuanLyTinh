package linhVu.service;

import linhVu.model.Customer;
import linhVu.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
        Iterable<Customer> findAll();

        Iterable<Customer> findAllByProvince(Province province);

        Customer findById(Long id);

        void save(Customer customer);

        void remove(Long id);
        Page<Customer>findAllByFirstNameContaining(String firstName, Pageable pageable);

}
