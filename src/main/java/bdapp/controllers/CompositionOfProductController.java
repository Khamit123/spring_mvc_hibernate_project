package bdapp.controllers;

import bdapp.DAO.CompositionOfProductDAO;
import bdapp.model.CompositionOfProduct;
import bdapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comprod")
public class CompositionOfProductController {

    @Autowired

    private CompositionOfProductDAO compositionOfProductDAO;

    @GetMapping("/{id}")
    public String comprod(Model model, @PathVariable("id") int id) {
        Product product = compositionOfProductDAO.product(id);
        model.addAttribute("names",compositionOfProductDAO.getNames());
        List<CompositionOfProduct> compositionOfProducts= compositionOfProductDAO.ComProducts(id);
        model.addAttribute("color",compositionOfProductDAO.colorConv(product.getColor()));
        model.addAttribute("productname",product.getName());
        model.addAttribute("compositionOfProducts",compositionOfProducts);
        return "table/compro";
    }

}
