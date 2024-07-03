package gift.controller;

import gift.controller.dto.ProductRequestDto;
import gift.controller.dto.ProductResponseDto;
import gift.controller.exception.ProductErrorCode;
import gift.controller.exception.ProductException;
import gift.controller.validator.ProductValidator;
import gift.model.Product;
import gift.model.ProductDao;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/list")
public class AdminController {

    private final ProductDao productDao;
    private final ProductValidator productValidator;

    public AdminController(ProductDao productDao, ProductValidator productValidator) {
        this.productDao = productDao;
        this.productValidator = productValidator;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<ProductResponseDto> productList = productDao.selectAllProduct()
            .stream()
            .map(ProductResponseDto::from)
            .collect(Collectors.toList());
        model.addAttribute("productList", productList);
        return "list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("productRequestDto", new ProductRequestDto());
        return "add-product-form";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute ProductRequestDto productRequestDto,
        BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-product-form";
        }
        if(productValidator.hasKakaoWord(productRequestDto)){
            throw new ProductException(ProductErrorCode.HAS_KAKAO_WORD);
        }
        productDao.insertProduct(productRequestDto.toEntity());
        return "redirect:/admin/list";
    }

    @GetMapping("/edit/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("productRequestDto", ProductRequestDto.from(productDao.selectProductById(id)));
        return "modify-product-form";
    }

    @PostMapping("edit/{id}")
    public String updateProduct(@PathVariable("id") Long id,
        @Valid @ModelAttribute ProductRequestDto productRequestDto,
        BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "modify-product-form";
        }
        if(productValidator.hasKakaoWord(productRequestDto)){
            throw new ProductException(ProductErrorCode.HAS_KAKAO_WORD);
        }
        productDao.updateProductById(id, productRequestDto.toEntity());
        return "redirect:/admin/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productDao.deleteProductById(id);
        return "redirect:/admin/list";
    }

    @ExceptionHandler(ProductException.class)
    public String handleProductException(ProductException productException, Model model){
        model.addAttribute("errorMessage", productException.getMessage());
        return "error";
    }
}
