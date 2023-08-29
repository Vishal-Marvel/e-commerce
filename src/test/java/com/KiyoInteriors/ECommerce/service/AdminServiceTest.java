package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.CategoryRequest;
import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class AdminServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private DiscountCouponRepository discountCouponRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ImageService imageService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MongoOperations mongoOperations;
    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory_CategoryDoesNotExist_SavesCategory() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategory("Test Category");

        when(categoryRepository.existsByCategoryName(categoryRequest.getCategory())).thenReturn(false);

        // Act
        adminService.addCategory(categoryRequest);

        // Assert
        verify(categoryRepository, times(1)).existsByCategoryName(categoryRequest.getCategory());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testAddCategory_CategoryExists_ThrowsConstraintException() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategory("Existing Category");

        when(categoryRepository.existsByCategoryName(categoryRequest.getCategory())).thenReturn(true);

        // Act and Assert
        assertThrows(ConstraintException.class, () -> adminService.addCategory(categoryRequest));
        verify(categoryRepository, times(1)).existsByCategoryName(categoryRequest.getCategory());
        verify(categoryRepository, never()).save(any(Category.class));
    }
}