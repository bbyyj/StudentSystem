package com.example.backend.controller;

import com.example.backend.service.UploadService;
import com.example.backend.utils.TencentCOSUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// 上传材料
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class MaterialUploadController {
    private final TencentCOSUtil tencentCOSUtil;
    private final UploadService uploadService;

//    @PostMapping("/upload")
//    public String upload(MultipartFile file) {
//        log.info("正在上传，文件名{}", file.getOriginalFilename());
//        String url = tencentCOSUtil.upLoadFile(file);
//        log.info("文件的Url：{}", url);
//        return url;
//    }

    @PostMapping("/Software")
    public String uploadSoftware(@RequestParam("file") MultipartFile file, @RequestParam String softwareJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadSoftware(url,softwareJson);
        return url;
    }

    @PostMapping("/Competition")
    public String uploadCompetition(@RequestParam("file") MultipartFile file, @RequestParam String competitionJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadCompetition(url,competitionJson);
        return url;
    }

    @PostMapping("/Paper")
    public String uploadPaper(@RequestParam("file") MultipartFile file, @RequestParam String paperJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadPaper(url,paperJson);
        return url;
    }

    @PostMapping("/Patent")
    public String uploadPatent(@RequestParam("file") MultipartFile file, @RequestParam String patentJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadPatent(url,patentJson);
        return url;
    }

    @PostMapping("/Volunteer")
    public String uploadVolunteer(@RequestParam("file") MultipartFile file, @RequestParam String volunteerJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadVolunteer(url,volunteerJson);
        return url;
    }

    @PostMapping("/Monograph")
    public String uploadMonograph(@RequestParam("file") MultipartFile file, @RequestParam String monographJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadMonograph(url,monographJson);
        return url;
    }

    @PostMapping("/ExchangeActivity")
    public String uploadExchangeActivity(@RequestParam("file") MultipartFile file, @RequestParam String exchangeActivityJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadExchangeActivity(url,exchangeActivityJson);
        return url;
    }

    @PostMapping("/Others")
    public String uploadOthers(@RequestParam("file") MultipartFile file, @RequestParam String othersJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadOthers(url,othersJson);
        return url;
    }

    @PostMapping("/IdeologyMorality")
    public String uploadIdeologyMorality(@RequestParam("file") MultipartFile file, @RequestParam String ideologyMoralityJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadIdeologyMorality(url,ideologyMoralityJson);
        return url;
    }

    @PostMapping("/SocialWork")
    public String uploadSocialWork(@RequestParam("file") MultipartFile file, @RequestParam String socialWorkJson) {
        String url = tencentCOSUtil.upLoadFile(file);
        uploadService.uploadSocialWork(url,socialWorkJson);
        return url;
    }

}
