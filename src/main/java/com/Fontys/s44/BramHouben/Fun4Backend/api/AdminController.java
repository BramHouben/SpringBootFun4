package com.Fontys.s44.BramHouben.Fun4Backend.api;


import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductDiscountWrapper;
import com.Fontys.s44.BramHouben.Fun4Backend.service.AdminService;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final IAdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PreAuthorize("hasAuthority('product:write')")
    @PostMapping(path = "/changeDiscount", consumes = "application/json")
    public ResponseEntity<?> changeDiscount(@Valid @NotNull @RequestBody ProductDiscountWrapper productDiscountWrapper) {
        if (productDiscountWrapper == null) {
            ResponseEntity.badRequest().body("No product");
        }

        assert productDiscountWrapper != null;
        adminService.addDiscount(productDiscountWrapper.getUuid(), productDiscountWrapper.getNewPrice());

        return ResponseEntity.status(HttpStatus.OK).body("Process done!");

    }

    @PreAuthorize("hasAuthority('product:write')")
    @GetMapping(path = "/startDiscount")
    public ResponseEntity<?> startDiscount(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.badRequest().body("No valid credentials");
        }

        adminService.startDiscount();

        return ResponseEntity.status(HttpStatus.OK).body("Process done!");
    }
}
