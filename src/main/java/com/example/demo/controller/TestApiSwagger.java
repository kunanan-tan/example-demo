package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author kunanan.t
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TestApiSwagger {

    @ApiOperation(value = "", notes = "Test Swagger")
    @GetMapping(value = "/testSwagger")
    public ResponseEntity<Object>  getTestSwagger(
            @RequestHeader(value = "Authorization", required = true) String authorization,
            @RequestParam(value = "testId", required = true) Long testId)  {
        try {
            return ResponseEntity.ok(testId);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
