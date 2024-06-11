package com.springlessons.clinicadmin.controller;

import com.springlessons.clinicadmin.dto.ClinicDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

// POST https://domain.com/clinic
// https://domain.com/clinic/34
// https://domain.com/clinic/spb/58
// https://domain.com/doctor/clinic/spb/58
// @Controller
// @RestController: @Controller + @ResponseBody
@RestController
@RequestMapping("/clinic")
public class ClinicController {
    // "{"id": 34, "name": "Больница №234" }"
    // RequestBody - данные будут переданы в теле сообщения json строкой
    // (required = false) - тело сообщения м.б пустым
    @PostMapping
    public int createClinic(@RequestBody(required = false)
                                ClinicDto clinicDto){
        return 1;
    }

//    @PostMapping
//    public int createClinic01(@RequestBody(required = false)
//                            ClinicDto clinicDto){
//        if (clinicDto != null && clinicDto.getId() < 1)
//            throw new ResponseStatusException
//                    (HttpStatusCode.valueOf(400), "Invalid clinic");
//        return 1;
//    }
//
//    @PostMapping
//    public ResponseEntity<Integer> createClinic02(@RequestBody(required = false)
//                            ClinicDto clinicDto){
//        if (clinicDto != null && clinicDto.getId() < 1)
//            throw new ResponseStatusException
//                    (HttpStatusCode.valueOf(400), "Invalid clinic");
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("имя", "значение");
//        return new ResponseEntity<>(1, headers, HttpStatus.CREATED);

       /* return ResponseEntity.created() // 201
                .headers(headers)
                .body(clinicDto.getId());*/

//        return ResponseEntity.ok()
//                .header("имя", "значение")
//                .body(clinicDto.getId());
//    }




    // clinic_name: "Больница №234", id: 34,
    // RequestParam - данные передаются в паре ключ - значение
    // name = "clinic_name" - ассоциация ключа с именем переменной.
    // Имеет смысл использовать, если ключ отличается от имени аргумента (clinic_name - name)
    // required = false параметр не является обязательным
    // defaultValue = "false" - значение по умолчанию, если параметр не пердан
//    @PostMapping
//    public int createClinic(@RequestParam(name = "clinic_name") String name,
//                            @RequestParam(required = false) Integer id,
//                            @RequestParam(required = false, defaultValue = "false")
//                                boolean enable){
//
//    }

    // GET https://domain.com/clinic/name/Больница №54
    // GET https://domain.com/clinic/name/Больница №436
    @GetMapping("/name/{clinicName}")
    public ClinicDto getByName(@PathVariable
                                   String clinicName){
        return new ClinicDto();
    }

   // GET https://domain.com/clinic/34
   // GET https://domain.com/clinic/2
   // GET https://domain.com/clinic/456
    @GetMapping("/{clinicId}")
    public ClinicDto getById(@PathVariable int clinicId){
        return new ClinicDto();
    }



// GET https://domain.com/clinic/filter?clinic_id=3
// GET https://domain.com/clinic/filter?clinic_id=435
    @GetMapping("/filter")
    public ClinicDto getByParamId(@RequestParam(name = "clinic_id")
                                 int id){
        return new ClinicDto();
    }

}





