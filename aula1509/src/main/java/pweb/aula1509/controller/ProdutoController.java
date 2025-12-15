package pweb.aula1509.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pweb.aula1509.model.entity.*;
import pweb.aula1509.model.repository.ClientePFRepository;
import pweb.aula1509.model.repository.ClientePJRepository;
import pweb.aula1509.model.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    private Venda venda;

    @Autowired
    private ClientePFRepository clientePFRepository;

    @Autowired
    private ClientePJRepository clientePJRepository;

    /**
     * Método que carrega a página de cadastro de produto
     * @return a página "produto/form"
     */
    @GetMapping("/form")
    public ModelAndView form(ModelMap model) {
        model.addAttribute("produto", new Produto());
        return new ModelAndView("produto/form");
    }


    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(required = false) String nome,
            ModelMap model) {

        List<Produto> produtos;

        if (nome != null && !nome.trim().isEmpty()) {
            produtos = produtoRepository.buscarPorNome(nome);
        } else {
            produtos = produtoRepository.produtos();
        }

        model.addAttribute("produtos", produtos);
        model.addAttribute("nome", nome);
        model.addAttribute("rota", "/produto/list");

        return new ModelAndView("produto/list");
    }

    @PostMapping("/save")
    public ModelAndView save(Produto produto){
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        produtoRepository.remove(id);
        return new ModelAndView("redirect:/produto/list");
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelMap model) {
        model.addAttribute("produto", produtoRepository.produto(id));
        return new ModelAndView("produto/form", model);
    }


}
