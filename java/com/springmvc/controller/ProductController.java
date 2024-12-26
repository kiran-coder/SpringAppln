package com.springmvc.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.entity.ProductEntity;
import com.springmvc.model.ProductModel;
import com.springmvc.service.ProductService;

import jakarta.validation.Valid;

	
@Controller
public class ProductController{
//@GetMapping("/Productform")
//public String getProductForm()
//{
	//return "addproduct";
//}
@Autowired
ProductService productService;
private String fieldError;
@GetMapping("/Productform")
public String getproductform(Model model)
{
	ProductModel product=new ProductModel();
	product.setMadein("india");
	product.setQuantity(5);
	product.setDiscount(10);
	
	model.addAttribute("product",product);
	return "addproduct";
	
}


//ProductService productService;
//@PostMapping("/savepoint")
//public String savepoint( ProductModel productModel)
//{
	//productService.saveproductdetails(productModel);
	//return "success";
//}


@PostMapping("/savepoint")
public String saveProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
    HashMap<String, String> validationErrors = new HashMap<>();

    if (bindingResult.hasErrors()) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        model.addAttribute("validationErrors", validationErrors);
        return "addproduct";
    }
    return "redirect:/productlist";
}







private String getField() {
	// TODO Auto-generated method stub
	return null;
}


@GetMapping("/getallProducts")
public String getallProducts(Model model)
{
	List<ProductEntity> products =productService.getAllproduct();
	model.addAttribute("products",products);
	return "product_list";
}


@Autowired
ProductService productServices;
@GetMapping("/getSearchform")
public String getSearchform() 
{
	return "search_product";
}

@PostMapping("/searchbyid")
public String searchById(@RequestParam("id") long id, Model model) {
    ProductEntity product = productService.searchById(id); 
    model.addAttribute("product", product); 
    return "search_product"; 
}

@GetMapping("/delete/{id}")
public String deleteProductById(@PathVariable("id") long id) {
   productService.DeleteproductById(id);
   return "redirect:/getallProducts";
   }




@GetMapping("/edit/{id}")
public String EditproductById(@PathVariable("id")long id,Model model) 
{
	ProductModel model2=new ProductModel();
	model2=productService.EditproductById(id);
	model.addAttribute("product",model2);
	model.addAttribute("id",id);
	return  "edit-form";
}

@PostMapping("/editsubmit/{id}")
public String showEditform(@PathVariable("id")long id, ProductModel model)
{
	productService.editsubmit(id,model);
return "redirect:/getallProducts";
}










}











