package com.springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springmvc.entity.ProductEntity;
import com.springmvc.model.ProductModel;
import com.springmvc.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	public ProductService(ProductRepository productRepository)
	{
		this.productRepository=productRepository;
	}
	public ProductEntity saveproductdetails(ProductModel productModel)
	{
	    ProductEntity productEntity=convert(productModel);
		return productRepository.save(productEntity);
	}
	public ProductEntity convert(ProductModel productModel)
	{
		ProductEntity productEntity=new ProductEntity();
		productEntity.setName(productModel.getName());
		productEntity.setBrand(productModel.getBrand());
		productEntity.setMadein(productModel.getMadein());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setQuantity(productModel.getQuantity());
		productEntity.setDiscount(productModel.getDiscount());
		productEntity.setDiscountamount(((productEntity.getPrice())*(productEntity.getDiscount()))/100);
		productEntity.setOfferprice(productEntity.getPrice()-productEntity.getDiscountamount());
		productEntity.setTaxrate(18.00);
		productEntity.setFinalprice(productEntity.getOfferprice()+((productEntity.getTaxrate()*productEntity.getOfferprice())/100));
		productEntity.setStockvalue(productEntity.getPrice()*productEntity.getQuantity());
		return productEntity;
	}

public List<ProductEntity> getAllproduct() {
	List<ProductEntity>products=productRepository.findAll();
	return products;
}

public ProductEntity searchById(Long id) {
    Optional<ProductEntity> optionalData = productRepository.findById(id);
    if (optionalData.isPresent()) {
        return optionalData.get(); 
    } else {
        return null;
    }
}




//ProductRepository productrepository;
public  void DeleteproductById(long id) {
	productRepository.deleteById(id);
 
}




public ProductModel EditproductById(long id) {
	
		Optional<ProductEntity> optionalData = productRepository.findById(id);
		if(optionalData.isPresent()) 
		{
			ProductModel model=new ProductModel();
			ProductEntity pr=new ProductEntity();
			pr=optionalData.get();
			model.setName(pr.getName());
			model.setBrand(pr.getBrand());
			model.setMadein(pr.getMadein());
			model.setPrice(pr.getPrice());
			model.setQuantity(pr.getQuantity());
			return model;
			}
		else
		{
			return null;
		}
	}
public void editsubmit(long id, ProductModel model) {
	 Optional<ProductEntity> optionalData=productRepository.findById(id);
	 if (optionalData.isPresent())
	 {
		 ProductEntity entity= optionalData.get();
		 entity.setName(model.getName());
			entity.setBrand( model.getBrand());
			entity.setMadein(model.getMadein());
			entity.setPrice(model.getPrice());
			entity.setQuantity(model.getQuantity());
			entity.setDiscount(model.getDiscount());
			entity.setDiscountamount(((entity.getPrice())*(entity.getDiscount()))/100);
			entity.setOfferprice(entity.getPrice()-entity.getDiscountamount());
			entity.setTaxrate(18.00);
			entity.setFinalprice(entity.getOfferprice()+((entity.getTaxrate()*entity.getOfferprice())/100));
			entity.setStockvalue(entity.getPrice()*entity.getQuantity());
			productRepository.save(entity);
	 }
	
}
}


















