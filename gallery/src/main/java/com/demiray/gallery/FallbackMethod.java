package com.demiray.gallery;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class FallbackMethod {

    public Gallery fallbackZartZort(int id) {
        return new Gallery(id);
    }
}
