package com.example.demo.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class CartController
{
    /*
    @Autowired
    DataSource dataSource;

    @Autowired
    GameOfUserRepo gameOfUserRepo;

    // Страница корзины
    @GetMapping("/cart")
    public String loadCartPage(HttpSession session, Model model)
    {
        if(session.getAttribute("currentUser") == null)
            return "redirect:/";

        model.addAttribute("thisUserCart", session.getAttribute("cart") );
        return "cartPage";
    }

    @PostMapping("/cart")
    public String afterBying(HttpSession session, Model model) throws SQLException
    {
        List<Games> gamesToBuy = (List<Games>) session.getAttribute("cart");
        int curretnUserId = ( (Users) session.getAttribute("currentUser") ).getId();
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        for (Games game: gamesToBuy )
        {
            if( gameOfUserRepo.contains( curretnUserId ,game.getId()) ) continue;
            stm.executeUpdate("insert into Libraries (idofuser, idofgame) values ("+ curretnUserId + ", " + game.getId() + " )");
        }
        con.close();

        session.setAttribute("cart", new ArrayList<Games>() );
        model.addAttribute("thisUserCart", session.getAttribute("cart") );

        return "redirect:/";
    }

     */
}
