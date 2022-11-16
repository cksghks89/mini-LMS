package com.example.minilms.admin.dto;

import com.example.minilms.admin.entity.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private long id;
    String categoryName;
    int sortValue;
    boolean usingYn;

    // ADD COLUMNS
    int courseCount;

    public static List<CategoryDto> of(List<Category> categories){
        if(categories != null){
            List<CategoryDto> categoryList = new ArrayList<>();
            for(Category x : categories){
                categoryList.add(of(x));
            }
            return categoryList;
        }
        return null;
    }

    public static CategoryDto of(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .sortValue(category.getSortValue())
                .usingYn(category.isUsingYn())
                .build();
    }
}
