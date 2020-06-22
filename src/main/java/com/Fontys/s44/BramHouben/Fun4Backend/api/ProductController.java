package com.Fontys.s44.BramHouben.Fun4Backend.api;

import com.Fontys.s44.BramHouben.Fun4Backend.model.Order;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductViewModel;
import com.Fontys.s44.BramHouben.Fun4Backend.service.ProductService;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/product")
@RestController
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;

    }

//    @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
    @PreAuthorize("hasAuthority('product:write')")
    @PostMapping()
    public ResponseEntity<?> addProduct(@Valid @NotNull @RequestBody Product product) {
        if (product==null){
            ResponseEntity.badRequest().body("No product to insert");
        }


        assert product != null;
        if(productService.insertProduct(product)){
            return ResponseEntity.status(HttpStatus.CREATED).body(product+" has been created!");
        };
       return ResponseEntity.badRequest().body("Something went wrong in the request!");
    }


//    @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
    @GetMapping(value = "/getProducts")
    public ResponseEntity<?> getAllProducts() {
        List<ProductViewModel> products= productService.getAllProducts();
        if (products!=null){
            return ResponseEntity.ok(products);
        }
        else {
            return ResponseEntity.badRequest().body("Cant get products");
        }
    }

//    @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
    @PreAuthorize("hasAuthority('product:write')")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") UUID uuid) {
        try {
            if (uuid==null){
                return ResponseEntity.badRequest().body("No valid uuid");
            }

            if(productService.deleteProductById(uuid)){
                return ResponseEntity.ok("Product deleted!");
            }else {
                return ResponseEntity.badRequest().body("Product doesn't exist!");
            }
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("Bad request");
        }

    }


    @GetMapping(path = "/getProduct/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") UUID uuid) {
        if (uuid!=null){
            Product product = productService.getProductById(uuid);
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.badRequest().body("Cant get product");
        }
    }

//    @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
    @PreAuthorize("hasAuthority('product:write')")
    @PutMapping(path = "/edit")
    public ResponseEntity<?> editProductById(@Valid @NotNull @RequestBody Product product) {
        productService.editProductById(product);
        return new ResponseEntity<>(HttpStatus.OK);

    }



}
