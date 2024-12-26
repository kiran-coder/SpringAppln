package com.springmvc.model;

import org.aspectj.weaver.ast.Not;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	@NotBlank(message="product name cannot be blank")
private String name;
	@NotBlank(message="brand cannot be blank")
private String brand;
	@NotBlank(message="madein cannot be blank")
private String madein;
	@Positive(message="price must be greater than zero ")
private double price;
	@Min(value=1,message="quantity atleast must be 1")
private int quantity;
	@DecimalMax(value="100.0",message="discount cannot be exceed 100")
private int discount;
public  double taxrate;
public  double finalprice;
public  double stockvalue;



	// TODO Auto-generated method stub
	
}

