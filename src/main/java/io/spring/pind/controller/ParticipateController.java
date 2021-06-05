package io.spring.pind.controller;

import io.spring.pind.dto.ParticipateDTO;
import io.spring.pind.service.ParticipateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participate")
@Log4j2
@RequiredArgsConstructor
public class ParticipateController {

    private final ParticipateService participateService;

    @GetMapping("/list/{project_id}")
    public ResponseEntity<List<ParticipateDTO>> getListByProject(@PathVariable("project_id") Long projectId){
        List<ParticipateDTO> result = participateService.getParticipateList(projectId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/enroll")
    public ResponseEntity<ParticipateDTO> enroll(@RequestBody ParticipateDTO participateDTO){
        ParticipateDTO participate = participateService.enroll(participateDTO.getProject_id(), participateDTO.getMember_id());
        return new ResponseEntity<>(participate, HttpStatus.OK);
    }

    @PutMapping("/accept_member")
    public ResponseEntity<Boolean> accept(@RequestBody ParticipateDTO participateDTO){
        Boolean result = participateService.acceptMember(participateDTO.getProject_id(), participateDTO.getMember_id());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> delete(@RequestBody ParticipateDTO participateDTO){
        Boolean result = participateService.deleteMember(participateDTO.getProject_id(), participateDTO.getMember_id());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
