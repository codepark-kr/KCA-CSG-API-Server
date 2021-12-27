package com.kca.csg.util;

import com.kca.csg.exception.ResourceNotFoundException;
import com.kca.csg.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.kca.csg.util.Constants.*;

public class GlobalUtils {

    private static TodoRepository todoRepository;
    private static CategoryRepository categoryRepository;
    private static TagRepository tagRepository;
    private static TwinsRepository twinsRepository;
    private static UserRepository userRepository;
    private static ShortiesRepository shortiesRepository;
    private static AlbumRepository albumRepository;

    public GlobalUtils(TodoRepository todoRepository, CategoryRepository categoryRepository, TagRepository tagRepository,
                       TwinsRepository twinsRepository, UserRepository userRepository, ShortiesRepository shortiesRepository, AlbumRepository albumRepository) {
        GlobalUtils.todoRepository = todoRepository;
        GlobalUtils.categoryRepository = categoryRepository;
        GlobalUtils.tagRepository = tagRepository;
        GlobalUtils.twinsRepository = twinsRepository;
        GlobalUtils.userRepository = userRepository;
        GlobalUtils.shortiesRepository = shortiesRepository;
        GlobalUtils.albumRepository = albumRepository;
    }

    public static Pageable sortDescending(int page, int size){
        return PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
    }

    public static Object getResourceById(Long id, String resourceName, Object fieldValue){
        switch(resourceName){
            case TODO: return todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, fieldValue));
            case TWINS: return twinsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TWINS, ID, fieldValue));
            case CATEGORY: return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, fieldValue));
            case TAG: return tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TAG, ID, fieldValue));
            case USER: return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(USER, ID, fieldValue));
            case SHORTIES: return shortiesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(SHORTIES, ID, fieldValue));
            case ALBUM: return albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(SHORTIES, ID, fieldValue));
            default: return null;
        }
    }
}
