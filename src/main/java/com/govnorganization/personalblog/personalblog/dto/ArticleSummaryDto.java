package com.govnorganization.personalblog.personalblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleSummaryDto {
    private final Long id;
    private final String title;
    private final String date;

}
