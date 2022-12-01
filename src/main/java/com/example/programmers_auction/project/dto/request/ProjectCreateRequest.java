package com.example.programmers_auction.project.dto.request;

import com.example.programmers_auction.category.domain.Category;
import com.example.programmers_auction.project.domain.Project;
import com.example.programmers_auction.project.domain.ProjectType;
import com.example.programmers_auction.user.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProjectCreateRequest {

    @NotBlank
    private String title;

    @NotNull
    private ProjectType projectType;

    @NotBlank
    private String description;

    @NotNull
    private Long openPrice;

    @NotNull
    private Long minimumBiddingUnit;

    public Project toEntity(final Category category, final User user) {
        return new Project(category, user, projectType, title,
                            description, openPrice, minimumBiddingUnit);
    }

}
