package pro.sky.petshelterbot.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.petshelterbot.entity.Report;
import pro.sky.petshelterbot.service.ReportService;

import java.util.List;

@RestController
@RequestMapping( "/reports-cat")
@Tag(name = "Cat Reports API", description = "Cat report info.")
public class ReportCatController {

    private final ReportService reportService;

    public ReportCatController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Report>> findAllByCatId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(reportService.findAllByCatId(id, pageNo, pageSize));
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<Report> update(
            @PathVariable Long id,
            @RequestParam(required = false) Boolean checked,
            @RequestParam(required = false) Boolean approved) {
        return ResponseEntity.ok(reportService.updateReportCat(id, checked, approved));
    }

}
