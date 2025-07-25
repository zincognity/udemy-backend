package com.udemy.backend.api.course.category.core.application.usecase;

import org.springframework.stereotype.Service;

import com.udemy.backend.api.course.category.core.adapter.out.repository.CategoryRepository;
import com.udemy.backend.api.course.category.core.application.port.CreateCategoryPort;
import com.udemy.backend.api.course.category.core.domain.model.Category;
import com.udemy.backend.api.course.category.core.domain.request.CreateCategoryRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public final class CreateCategoryUseCase implements CreateCategoryPort {
  private final CategoryRepository categoryRepository;
  private Long idGenerator = 0L;

  @Override
  public Category create(CreateCategoryRequest request) {
    idGenerator++;

    Category category = Category
        .builder()
        .id(idGenerator)
        .name(request.getName())
        .build();

    log.info("Creando categoría con ID: {}", category.getId());

    if (category.getId() == null) {
      throw new IllegalStateException("Intentando guardar una categoría sin ID: " + category.getName());
    }

    return categoryRepository.save(category);
  }
}
