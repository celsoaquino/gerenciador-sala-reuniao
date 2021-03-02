package com.digital.crud.saladereuniao.controller;

import com.digital.crud.saladereuniao.exception.ResourceNotFoundException;
import com.digital.crud.saladereuniao.model.Room;
import com.digital.crud.saladereuniao.repository.RoomRepository;
import jdk.dynalink.linker.LinkerServices;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + id));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rooms not found for this id:: " + id));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStarHour(roomDetails.getStarHour());
        room.setEndHour(roomDetails.getEndHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rooms not found for this id:: " + id));
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

}
