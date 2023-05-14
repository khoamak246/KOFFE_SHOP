package com.koffe.koffe.controller;


import com.koffe.koffe.model.Product;
import com.koffe.koffe.model.ProductDetail;
import com.koffe.koffe.model.User;
import com.koffe.koffe.service.IProductService;
import com.koffe.koffe.service.serviceIMPL.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:upload_file.properties")
public class ProductController {

    @Value("${file-upload}")
    private String fileUpload;
    IProductService productService = new ProductServiceIMPL();

    @PostMapping("/admin/product/addProduct")
    public String addProduct(@RequestParam("file") MultipartFile file,
                             @RequestParam("productName") String name,
                             @RequestParam("productDescription") String description,
                             @RequestParam("priceS") int priceS,
                             @RequestParam("priceM") int priceM,
                             @RequestParam("priceL") int priceL) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.save(new ProductDetail(name, description, fileName, priceS, priceM, priceL));

        return "redirect:/admin/productMNG";
    }


    @GetMapping("/admin/product/stopSale")
    public String stopSaleProduct(HttpServletRequest request, HttpSession session) {
        int productId = 0;
        String productIdParam = request.getParameter("productId");
        if (productIdParam != null) {
            productId = Integer.parseInt(productIdParam);
        }
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != 1) {
            return "redirect:/admin/signin";
        }
        productService.changeStatusProductById(productId, false);
        return "redirect:/admin/productMNG";
    }

    @GetMapping("/admin/product/sale")
    public String saleProduct(HttpServletRequest request, HttpSession session) {
        int productId = 0;
        String productIdParam = request.getParameter("productId");
        if (productIdParam != null) {
            productId = Integer.parseInt(productIdParam);
        }
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != 1) {
            return "redirect:/admin/signin";
        }
        productService.changeStatusProductById(productId, true);
        return "redirect:/admin/productMNG";
    }

    @GetMapping("/admin/product/edit")
    public String editProduct(HttpServletRequest request, Model model, HttpSession session) {
        int productId = 0;
        String productIdParam = request.getParameter("productId");
        if (productIdParam != null) {
            productId = Integer.parseInt(productIdParam);
        }
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != 1) {
            return "redirect:/admin/signin";
        }

        model.addAttribute("currentUser", user);
        List<Product> products = productService.findByIdProduct(productId);
        ProductDetail product = null;
        if (!products.isEmpty()){
        String name = products.get(0).getProductName();
        String description = products.get(0).getProductDescription();
        String avatar = products.get(0).getProductAvatar();
        int priceS = products.get(0).getPrice();
        int priceM = products.get(1).getPrice();
        int priceL = products.get(2).getPrice();
        product = new ProductDetail(name, description, avatar, priceS, priceM, priceL);
        }
        model.addAttribute("editProduct", product);
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "/admin/pages/productMNG";
    }

    @PostMapping("/admin/product/edit")
    public String editProduct(@RequestParam("productId") int productId,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("productName") String name,
                              @RequestParam("productDescription") String description,
                              @RequestParam("priceS") int priceS,
                              @RequestParam("priceM") int priceM,
                              @RequestParam("priceL") int priceL,
                              @RequestParam("currentImg") String currentImg) {
        String fileName = file.getOriginalFilename();
        if (fileName.length() == 0) {
            fileName = currentImg;
        } else {
            File currentImgFile = new File(fileUpload + currentImg);
            currentImgFile.delete();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productService.updateProduct(new ProductDetail(productId, name, description, fileName, priceS, priceM, priceL));
        return "redirect:/admin/productMNG";
    }
}
