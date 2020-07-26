package com.toyproject.payrecord.work.domain;

import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.global.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "timeline")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timeline extends BaseEntity {
    private static final String NULL_NOT_ALLOW = "공백을 입력할 수 없습니다.";

    @Id
    @Column(name = "timeline_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event", length = 40, nullable = false)
    @ApiModelProperty(value = "발생 이벤트", required = false)
    private String event;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @JoinColumns({
            @JoinColumn(name = "date"),
            @JoinColumn(name = "employee_id")
    })
    private Day day;

    public Timeline(String event) {
        if (StringUtil.isBlank(event)) {
            throw new IllegalArgumentException(NULL_NOT_ALLOW);
        }
        this.event = event;
    }
}
