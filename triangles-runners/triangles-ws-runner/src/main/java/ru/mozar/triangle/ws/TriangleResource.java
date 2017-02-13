package ru.mozar.triangle.ws;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mozar.triangle.api.TriangleService;
import ru.mozar.triangle.api.TriangleTypingService;

@RestController
@Log4j
public class TriangleResource {

    @Autowired
    private TriangleService triangleService;

    @Autowired
    private TriangleTypingService triangleTypingService;

    @RequestMapping("/triangle")
    public ResponseEntity<Response<String>> greeting(@RequestParam(value = "a") Double a, @RequestParam(value = "b") Double b, @RequestParam(value = "c") Double c) {
        log.debug("/triangle: a: " + a + ", b : " + b + ", c: " + c);
        try {
            return Response.success(triangleTypingService.typeTriangle(triangleService.createAndValidate(a, b, c)).name());
        } catch (IllegalArgumentException e) {
            return Response.badRequest(e.getMessage());
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            return Response.internalError(e.getMessage());
        }
    }
}