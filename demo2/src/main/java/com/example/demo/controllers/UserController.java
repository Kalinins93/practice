package com.example.demo.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class UserController
{
    /*
    @Autowired
    GamesRepo gamesRepo;

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/users")
    public String loadUserPage(Model model, @RequestParam int id)
    {
        Users userThatIsCurrent = usersRepo.findById(id).get();

        Libraries libra = new Libraries();
        libra.setGames ( gamesRepo.getAllGamesOfUser( id ) );

        model.addAttribute("thisUser", userThatIsCurrent);
        model.addAttribute("thisLibraryOfThisUser", libra.getGames());

        return "userPage";
    }

    @GetMapping("/editUser")
    public String loadEdituserPage(@RequestParam int id, HttpSession session, Model model)
    {
        Users currentUser = (Users) session.getAttribute("currentUser");
        Users pageUser = usersRepo.findById(id).get();

        if( currentUser == null || pageUser.getId() != id)
            return "redirect:/";

        model.addAttribute("oldUserdata", pageUser);

        return "editUserPage";
    }

    @PostMapping("/editUser")
    public String changeUserData(@RequestParam int id, @RequestParam String name, @RequestParam String email,
                                 @RequestParam String hashcode, @RequestParam MultipartFile icon, HttpSession session) throws IOException
    {
        Users user = (Users) session.getAttribute("currentUser");

        if( usersRepo.getByEmail(email) != null && usersRepo.getByEmail(email).getId() != user.getId())
            return "redirect:/editUser?id="+id;

        Users usr = new Users();
        usr.setId(id);
        usr.setName(name);
        usr.setEmail(email);
        usr.setHashcode(hashcode);

        if( icon.isEmpty() )
            usr.setIconname( usersRepo.findById(id).get().getIconname() );
        else
        {
            DemoApplication.CopyToFolderImage(icon, id + "_icon.jpg");
            usr.setIconname(id + "_icon.jpg");
        }
        usersRepo.save( usr );

        return "redirect:/users?id="+id;
    }

     */
}
