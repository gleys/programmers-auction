package com.example.programmers_auction.project.domain;

import com.example.programmers_auction.category.domain.Category;
import com.example.programmers_auction.common.BaseEntity;
import com.example.programmers_auction.project.exception.InvalidBiddingException;
import com.example.programmers_auction.user.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.programmers_auction.project.domain.ProjectStatus.PENDING;
import static com.example.programmers_auction.project.domain.ProjectType.BUY;
import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "projects")
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Project extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @OneToOne
    private Category category;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_projects_to_users"))
    private User user;

    @Enumerated(value = STRING)
    @Column(name = "project_type", nullable = false)
    private ProjectType projectType;

    @Enumerated(value = STRING)
    @Column(name = "project_status", nullable = false)
    private ProjectStatus projectStatus;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(name = "open_price", nullable = false)
    private Long openPrice;

    @Column(name = "current_price", nullable = false)
    private Long currentPrice;

    @Column(name = "close_price", nullable = true)
    private Long closePrice;

    @Column(name = "minimum_bidding_unit", nullable = false)
    private Long minimumBiddingUnit;

    @Column(name = "bidding_count")
    private int biddingCount;

    public Project(Category category, User user, ProjectType projectType,
                   String title, String description, Long openPrice, Long minimumBiddingUnit) {
        this.category = category;
        this.user = user;
        this.projectType = projectType;
        this.title = title;
        this.description = description;
        this.openPrice = openPrice;
        this.minimumBiddingUnit = minimumBiddingUnit;

        //경매 시작 기본값
        this.biddingCount = 0;
        this.projectStatus = PENDING;
        this.currentPrice = openPrice;
        this.closePrice = null;
    }

    public void changeTitle(final String title) {
        this.title = title;
    }

    public void changeStatus(final ProjectStatus status) {
        this.projectStatus = status;
    }

    public void participateBidding(final Long price) {
        if(this.projectType == BUY) {
            validateBuyBidding(price);
        }
        else {
            validateSellBidding(price);
        }
        this.currentPrice = price;
    }

    private void validateBuyBidding(final Long price) {
        checkBiddingAvailableStatus();
        verifyBuyPriceRange(price);
    }

    private void validateSellBidding(Long price) {
        checkBiddingAvailableStatus();
        verifySellPriceRange(price);
    }

    private void checkBiddingAvailableStatus() {
        if (this.projectStatus != PENDING) {
            throw new InvalidBiddingException("입찰 가능 상태가 아닙니다.");
        }
    }

    private void verifyBuyPriceRange(final Long price) {
        if(this.currentPrice.compareTo(price) <= 0 || !isUnitPrice(price))  {
            throw new InvalidBiddingException("구매글 입찰은 항상 현재가 보다 낮아야 하고 최소 단위로만 입찰 가능합니다.");
        }
    }

    private void verifySellPriceRange(final Long price) {
        if(this.currentPrice.compareTo(price) >= 0 || !isUnitPrice(price)) {
            throw new InvalidBiddingException("판매글 입찰은 항상 현재가 보다 높아야 하고 최소 단위로만 입찰 가능합니다.");
        }
    }

    private boolean isUnitPrice(final Long price) {
        long difference = this.currentPrice - price;
        return (difference % minimumBiddingUnit) == 0;
    }




}
