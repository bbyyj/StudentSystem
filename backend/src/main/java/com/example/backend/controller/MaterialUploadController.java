package com.example.backend.controller;

import com.example.backend.service.UploadService;
import com.example.backend.utils.TencentCOSUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MaterialUploadController {
    private final TencentCOSUtil tencentCOSUtil;
    private final UploadService uploadService;

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        log.info("正在上传，文件名{}", file.getOriginalFilename());
        String url = tencentCOSUtil.upLoadFile(file);
        log.info("文件的Url：{}", url);
        return url;
    }

    @PostMapping("/uploadSoftware")
    public String uploadSoftware(@RequestParam("file") MultipartFile file, @RequestParam String softwareJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadSoftware(url,softwareJson);
        return url;
    }

    @PostMapping("/uploadCompetition")
    public String uploadCompetition(@RequestParam("file") MultipartFile file, @RequestParam String competitionJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadCompetition(url,competitionJson);
        return url;
    }

    @PostMapping("/uploadPaper")
    public String uploadPaper(@RequestParam("file") MultipartFile file, @RequestParam String paperJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadPaper(url,paperJson);
        return url;
    }

    @PostMapping("/uploadPatent")
    public String uploadPatent(@RequestParam("file") MultipartFile file, @RequestParam String patentJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadPatent(url,patentJson);
        return url;
    }

    @PostMapping("/uploadVolunteer")
    public String uploadVolunteer(@RequestParam("file") MultipartFile file, @RequestParam String volunteerJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadVolunteer(url,volunteerJson);
        return url;
    }

    @PostMapping("/uploadMonograph")
    public String uploadMonograph(@RequestParam("file") MultipartFile file, @RequestParam String monographJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadMonograph(url,monographJson);
        return url;
    }

    @PostMapping("/uploadExchangeActivity")
    public String uploadExchangeActivity(@RequestParam("file") MultipartFile file, @RequestParam String exchangeActivityJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadExchangeActivity(url,exchangeActivityJson);
        return url;
    }

}
