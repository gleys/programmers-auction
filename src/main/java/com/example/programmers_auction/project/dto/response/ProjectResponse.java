package com.example.programmers_auction.project.dto.response;
import com.example.programmers_auction.project.domain.Project;
import lombok.Data;

@Data
public class ProjectResponse {

    private Long id;
    private String title;
    private String projectType;
    private String projectStatus;
    private String description;
    private Long openPrice;
    private Long currentPrice;
    private Long closePrice;
    private Long minimumBiddingUnit;

    ProjectResponse(final Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.projectType = project.getProjectType().toString();
        this.projectStatus = project.getProjectStatus().toString();
        this.description = project.getDescription();
        this.openPrice = project.getOpenPrice();
        this.currentPrice = project.getCurrentPrice();
        this.closePrice = project.getClosePrice();
        this.minimumBiddingUnit = project.getMinimumBiddingUnit();
    }

}
