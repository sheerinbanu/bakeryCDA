package com.ecommerce.bakery.Service;

import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Repository.SelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SelectionService {
    @Autowired
    private SelectionRepository ss;

    public Optional<Selection> getSelection(final int id){
        return ss.findById(id);
    }

    public Iterable<Selection> getAllSelection() {
        return ss.findAll();

    }

    public void deleteSelection(final int id) {
        ss.deleteById(id);
    }

    public void insertSelection(Selection Selection) {
        ss.save(Selection);
    }
}
