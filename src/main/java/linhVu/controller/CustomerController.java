package linhVu.controller;

import linhVu.model.Customer;
import linhVu.model.Province;
import linhVu.service.CustomerService;
import linhVu.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping("/customers")
//    public ModelAndView listCustomer(@PageableDefault(value = 2)Pageable pageable) {
    public ModelAndView listCustomer(){
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("customer/list");
        ModelAndView modelAndView1 = modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "A new customer is created successfully!");
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public  ModelAndView showEditForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message","Customer is updated successfully!");
        return modelAndView;
    }

    @GetMapping("delete-customer/{id}")
    public ModelAndView showDeleteCustomerForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if(customer!=null){
            ModelAndView modelAndView = new ModelAndView("customer/delete");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:customers";
    }
}
