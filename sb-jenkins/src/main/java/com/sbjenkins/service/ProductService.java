package com.sbjenkins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbjenkins.model.Product;
import com.sbjenkins.repository.ProductRepository;



@Service
public class ProductService {
	@Autowired ProductRepository productRep;
	
	
	public List<Product> getAllProducts(){
		List<Product> pList = productRep.findAll();
        if(pList.size() > 0) {
            return pList;
        } else {
            return new ArrayList<Product>();
        }
    }

}
