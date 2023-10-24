package com.switchfully.eurder.security;

import com.switchfully.eurder.admin.AdminRepository;
import com.switchfully.eurder.customers.CustomerRepository;
import com.switchfully.eurder.exceptions.IncorrectPasswordException;
import com.switchfully.eurder.exceptions.InvalidAdminEmailException;
import com.switchfully.eurder.exceptions.InvalidCustomerEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    public SecurityService(CustomerRepository customerRepository, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }

    public void validateAuthorization(String authorization, Role role) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);
        if(role == Role.ADMIN){
            if(!adminRepository.getAdmins().containsKey(usernamePassword.getUsername())){
                logger.warn("Someone without valid Admin email tried to access an Admin feature\nemail: " + usernamePassword.getUsername());
                throw new InvalidAdminEmailException();
            }
            if(!usernamePassword.getPassword().equals(adminRepository.getAdmins().get(usernamePassword.getUsername()).getPassword())){
                logger.warn("Wrong password was given for Admin account: " + usernamePassword.getUsername());
                throw new IncorrectPasswordException(usernamePassword.getUsername());
            }
        }
        if(role == Role.CUSTOMER){
            if(!customerRepository.getCustomers().containsKey(usernamePassword.getUsername())){
                logger.warn("Someone without valid Customer email tried to access a Customer feature\nemail: " + usernamePassword.getUsername());
                throw new InvalidCustomerEmailException();
            }
            if(!usernamePassword.getPassword().equals(customerRepository.getCustomers().get(usernamePassword.getUsername()).getPassword())){
                logger.warn("Wrong password was given for Customer account: " + usernamePassword.getUsername());
                throw new IncorrectPasswordException(usernamePassword.getUsername());
            }
        }
    }
    public UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }
}
