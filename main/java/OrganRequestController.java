package net.organ.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class OrganRequestController {

    @Autowired
    private OrganRequestService organRequestService;

    // CREATE
    @PostMapping
    public OrganRequest createRequest(@RequestBody OrganRequest request) {
        return organRequestService.saveRequest(request);
    }

    // READ ALL
    @GetMapping("/getAll")
    public List<OrganRequest> getAllRequests() {
        return organRequestService.getAllRequests();
    }

    // READ ONE
    @GetMapping("/{id}")
    public OrganRequest getRequestById(@PathVariable Long id) {
        return organRequestService.getRequestById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public OrganRequest updateRequest(@PathVariable Long id, @RequestBody OrganRequest request) {
        return organRequestService.updateRequest(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        organRequestService.deleteRequest(id);
    }
}
