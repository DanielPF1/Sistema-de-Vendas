package pweb.aula1509.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pweb.aula1509.model.entity.*;
import pweb.aula1509.model.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@RequestMapping("venda")

public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClientePFRepository clientePFRepository;

    @Autowired
    private ClientePJRepository clientePJRepository;


    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(required = false) LocalDate data,
            String nome,
            ModelMap model) {

        List<Venda> vendas;

        if (nome != null && !nome.trim().isEmpty()) {
            vendas = vendaRepository.buscarPorNome(nome);

            if (data != null) {
                vendas = vendaRepository.buscarPorData(data);
            }
        } else if (data != null) {
            vendas = vendaRepository.buscarPorData(data);
        } else {
            vendas = vendaRepository.vendas();
        }

        model.addAttribute("vendas", vendas);
        model.addAttribute("data", data);

        return new ModelAndView("venda/list");
    }

    @GetMapping("/carrinho")
    public ModelAndView carrinho(HttpSession session, ModelMap model) {
        // Recupera a venda da sessão
        Venda venda = (Venda) session.getAttribute("venda");

        // Produtos
        model.addAttribute("produtos", produtoRepository.produtos());

        // Carrinho (venda da sessão)
        model.addAttribute("venda", venda);

        // Clientes PF e PJ
        List<ClientePF> clientesPF = clientePFRepository.listarTodos();
        List<ClientePj> clientesPJ = clientePJRepository.listarTodos();

        List<Object> clientes = new ArrayList<>();
        clientes.addAll(clientesPF);
        clientes.addAll(clientesPJ);
        model.addAttribute("clientes", clientes);

        return new ModelAndView("venda/carrinho");
    }

    @GetMapping("/menu")
    public ModelAndView menu(HttpSession session, ModelMap model) {

        // Recupera a venda da sessão
        Venda venda = (Venda) session.getAttribute("venda");

        // Se não existir, cria uma nova
        if (venda == null) {
            venda = new Venda();
            venda.setItems(new ArrayList<>()); // garante lista não nula
            session.setAttribute("venda", venda);
        }

        // Produtos
        model.addAttribute("produtos", produtoRepository.produtos());

        // Carrinho (venda da sessão)
        model.addAttribute("venda", venda);



        return new ModelAndView("venda/menu");
    }


    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id, ModelMap model) {
        Venda venda = vendaRepository.venda(id);

        model.addAttribute("venda", venda);
        model.addAttribute("items", venda.getItems());

        return new ModelAndView("venda/detail");
    }


    @GetMapping("/produtos")
    public ModelAndView produtos(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.produtos());
        return new ModelAndView("venda/produtos");
    }


    // adiciona produto ao carrinho
    @GetMapping("/add/{idProduto}")
    public ModelAndView addProduto(
            @PathVariable Long idProduto,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Venda venda = (Venda) session.getAttribute("venda");
        if (venda == null) {
            venda = new Venda();
            session.setAttribute("venda", venda);
        }

        Produto produto = produtoRepository.produto(idProduto);
        if (produto != null) {
            venda.adicionarProduto(produto);

            // mensagem de sucesso
            redirectAttributes.addFlashAttribute(
                    "mensagemSucesso",
                    "Produto \"" + produto.getDescricao() + "\" adicionado ao carrinho!"
            );
        }

        return new ModelAndView("redirect:/venda/menu");

    }



    @GetMapping("/remove/{index}")
    public ModelAndView removeItem(@PathVariable("index") int index, HttpSession session) {

        Venda venda = (Venda) session.getAttribute("venda");

        if (venda != null && index >= 0 && index < venda.getItems().size()) {
            venda.getItems().remove(index);
            session.setAttribute("venda", venda);
        }

        return new ModelAndView("redirect:/venda/menu");
    }


    @PostMapping("/finalizar")
    public ModelAndView finalizar(@RequestParam String nomeCliente, HttpSession session) {

        Venda venda = (Venda) session.getAttribute("venda");

        List<ClientePF> encontrados = clientePFRepository.buscarPorNome(nomeCliente);
        List<ClientePj> encontrados2 = clientePJRepository.buscarPorNome(nomeCliente);

        if (!encontrados.isEmpty()) {
            venda.setCliente(encontrados.get(0));
        } else if (!encontrados2.isEmpty()) {
            venda.setCliente(encontrados2.get(0));
        }

        venda.setDataVenda(LocalDate.now());
        vendaRepository.save(venda);

        session.removeAttribute("venda");

        return new ModelAndView("redirect:/venda/list");
    }









}
