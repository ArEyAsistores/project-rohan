package com.yonduunversity.rohan.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class Pager {

    private List<?> data;
    private int page;
    private int size;
    public Pager(List<?> data, int page, int size){
        this.data = data;
        this.page = page;
        this.size = size;
    }
}
