/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.domain.support;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Address;
import edu.mum.ea.bookstore.domain.Permission;
import edu.mum.ea.bookstore.domain.Role;



/**
 * Builds {@link Account} domain objects
 * 
 * @author Nazanin
 * 
 * 
 */
@Component
public class AccountBuilder extends EntityBuilder<Account> {

    @Override
    void initProduct() {
        this.product = new Account();
    }

    public AccountBuilder credentials(String username, String password) {
        this.product.setUsername(username);
        //DigestUtils: 
        //Operations to simplify common MessageDigest tasks. This class is immutable and thread-safe.
        //Message digests are secure one-way hash functions that 
        //take arbitrary-sized data and output a fixed-length hash value.
        this.product.setPassword(DigestUtils.sha256Hex(password + "{" + username + "}"));
        return this;
    }

    public AccountBuilder address(String city, String postalCode, String street, String houseNumber, String boxNumber,
            String country) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setHouseNumber(houseNumber);
        address.setPostalCode(postalCode);
        address.setBoxNumber(boxNumber);
        address.setCountry(country);

        this.product.setAddress(address);
        return this;
    }

    public AccountBuilder roleWithPermissions(Role role, Permission... permissions) {
        this.product.getRoles().add(role);

        for (Permission permission : permissions) {
            role.getPermissions().add(permission);
        }
        return this;
    }

    public AccountBuilder email(String email) {
        this.product.setEmailAddress(email);
        return this;
    }

    public AccountBuilder name(String firstName, String lastName) {
        this.product.setFirstName(firstName);
        this.product.setLastName(lastName);
        return this;
    }

    @Override
    Account assembleProduct() {
        return this.product;
    }
}
