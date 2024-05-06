package com.example.backend.service;

public interface UploadService {
    void uploadSoftware(String url, String softwareJson);

    void uploadCompetition(String url, String competitionJson);

    void uploadPaper(String url, String paperJson);

    void uploadPatent(String url, String patentJson);

    void uploadVolunteer(String url, String volunteerJson);

    void uploadMonograph(String url, String monographJson);

    void uploadExchangeActivity(String url, String exchangeActivityJson);

    void uploadOthers(String url, String exchangeActivityJson);

    void uploadIdeologyMorality(String url, String ideologyMoralityJson);

    void uploadSocialWork(String url, String socialWorkJson);

//    void uploadItem(String url, String itemJson, Class<?> itemType, JpaRepository repository);

}
