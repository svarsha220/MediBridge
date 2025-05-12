package net.organ.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("currentUser")  // Store currentUser in session
public class LoginController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private OrganRequestRepository requestRepository;

    // Show the login page
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    // Allow the user to select their role (admin/user)
    @PostMapping("/selectRole")
    public String selectRole(@RequestParam("role") String role, Model model) {
        model.addAttribute("role", role);
        if ("admin".equals(role)) {
            return "adminlogin";
        } else if ("user".equals(role)) {
            return "userlogin";
        }
        return "login";
    }

    // Admin login handler
    @PostMapping("/adminlogin")
    public String handleAdminLogin(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   Model model) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            model.addAttribute("message", "Welcome, Admin");
            return "redirect:/admindashboard";
        } else {
            model.addAttribute("error", "Invalid Admin credentials");
            return "adminlogin";
        }
    }

    // User login handler
    @PostMapping("/userlogin")
    public String handleUserLogin(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  Model model) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getPassword().equals(password)) {
                model.addAttribute("error", "Incorrect password");
                return "userlogin";
            } else {
                model.addAttribute("message", "Welcome back, " + username);
                model.addAttribute("currentUser", String.valueOf(user.getId()));  // ✅ Store user ID
            }
        } else {
            // Register new user
            User newUser = new User(username, password);
            userRepository.save(newUser);
            model.addAttribute("message", "Welcome, new user: " + username);
            model.addAttribute("currentUser", String.valueOf(newUser.getId()));  // ✅ Store user ID
        }

        return "redirect:/userdashboard";
    }

    // Show the user dashboard
    @GetMapping("/userdashboard")
    public String showUserDashboard(@ModelAttribute("currentUser") String currentUser, Model model) {
        try {
            Long userId = Long.parseLong(currentUser);
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                List<Donation> donationList = donationRepository.findByUserId(userId);
                List<OrganRequest> requestList = requestRepository.findByUserId(userId);

                model.addAttribute("donationList", donationList);
                model.addAttribute("requestList", requestList);
                model.addAttribute("username", user.getUsername());
            }
        } catch (NumberFormatException e) {
            return "redirect:/";  // Invalid session data
        }

        return "userdashboard";
    }

    // ✅ Show the admin dashboard with ALL donations and requests
    @GetMapping("/admindashboard")
    public String showAdminDashboard(Model model) {
        List<Donation> donationList = donationRepository.findAll();
        List<OrganRequest> requestList = requestRepository.findAll();

        model.addAttribute("donationList", donationList);
        model.addAttribute("requestList", requestList);

        return "admindashboard";
    }

    // Show the donation form
    @GetMapping("/donateorgan")
    public String showDonationForm(@SessionAttribute("currentUser") String currentUser, Model model) {
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        model.addAttribute("currentUser", currentUser);
        return "donateform";
    }

    // Submit the donation
    @PostMapping("/donateorgan")
    public String submitDonation(@ModelAttribute Donation donation, @SessionAttribute("currentUser") String currentUser) {
        try {
            Long userId = Long.parseLong(currentUser);
            donation.setUserId(userId);
        } catch (NumberFormatException e) {
            return "redirect:/error";  // Optional: create an error page
        }
        donationRepository.save(donation);
        return "redirect:/userdashboard";
    }

    // Show the organ request form
    @GetMapping("/requestorgan")
    public String showRequestForm() {
        return "requestform";
    }

    // Submit the organ request
    @PostMapping("/requestorgan")
    public String submitRequest(@RequestParam String organName,
                                @RequestParam String requesterName,
                                @RequestParam String bloodGroup,
                                @RequestParam int requesterAge,
                                @ModelAttribute("currentUser") String currentUser,
                                Model model) {
        try {
            Long userId = Long.parseLong(currentUser);
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                OrganRequest request = new OrganRequest(organName, requesterName, bloodGroup, requesterAge, userId);
                requestRepository.save(request);
            }
        } catch (NumberFormatException e) {
            return "redirect:/error";  // Optional error redirect
        }
        return "redirect:/userdashboard";
    }

    // API to get current user info
    @ResponseBody
    @GetMapping("/current-user")
    public User getCurrentUser(@ModelAttribute("currentUser") String currentUser) {
        try {
            Long userId = Long.parseLong(currentUser);
            return userRepository.findById(userId).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}


