package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Status;

public record TaskReqDto(
        String title,
        String description,
        Status status

) {
}
