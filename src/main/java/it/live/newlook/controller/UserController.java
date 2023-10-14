package it.live.newlook.controller;

import it.live.newlook.entity.User;
import it.live.newlook.payload.ApiResponse;
import it.live.newlook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user) {
        if (userRepository.existsByPhoneNumberAndUsername(user.getPhoneNumber(), user.getUsername()))
            throw new RuntimeException("Bunday user avval qo'shilgan");
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.builder().message("User saqlandi").status(200).build());
    }

    @GetMapping("/getUserById/{userid}")
    public User getUser(@PathVariable Long userid) {
        Optional<User> optionalUser = userRepository.findById(userid);
        if (optionalUser.isEmpty())
            throw new RuntimeException("Bunday user topilmadi");

        return optionalUser.get();
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/getPageUser")
    public List<User> getPageUser(@RequestParam int page, @RequestParam int size) {
        return userRepository.findAll(PageRequest.of(page, size, Sort.by("id"))).stream().toList();
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        try {
            userRepository.deleteById(userId);
            return ResponseEntity.ok(ApiResponse.builder().message("O'chirildi").status(200).build());
        } catch (Exception e) {
             throw new RuntimeException("O'chirishda xatolik");
        }
    }

    @DeleteMapping("/deleteAllUser")
    public ResponseEntity<ApiResponse> deleteALlUser() {
        try {
            userRepository.deleteAll();
            return ResponseEntity.ok(ApiResponse.builder().message("O'chirildi").status(200).build());
        } catch (Exception e) {
            throw new RuntimeException("O'chirishda xatolik");
        }
    }
}
