package com.example.minilms.admin.service.impl;

import com.example.minilms.admin.dto.CategoryDto;
import com.example.minilms.admin.entity.Category;
import com.example.minilms.admin.mapper.CategoryMapper;
import com.example.minilms.admin.model.CategoryInput;
import com.example.minilms.admin.repository.CategoryRepository;
import com.example.minilms.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public List<CategoryDto> list() {
        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
        return CategoryDto.of(categories);
    }

    @Override
    public boolean add(String categoryName) {
        Category category = Category.builder()
                .categoryName(categoryName)
                .usingYn(true)
                .sortValue(0)
                .build();

        categoryRepository.save(category);

        return false;
    }

    @Override
    public boolean update(CategoryInput parameter) {
        Optional<Category> categoryOptional = categoryRepository.findById(parameter.getId());
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setCategoryName(parameter.getCategoryName());
            category.setSortValue(parameter.getSortValue());
            category.setUsingYn(parameter.isUsingYn());
            categoryRepository.save(category);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CategoryDto> frontList(CategoryDto parameter) {
        return categoryMapper.selectList(parameter);
    }
}
