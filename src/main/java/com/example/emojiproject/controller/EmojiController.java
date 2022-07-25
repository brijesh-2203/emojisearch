package com.example.emojiproject.controller;

import com.example.emojiproject.model.Emoji;
import com.example.emojiproject.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emoji")
public class EmojiController {

    @Autowired
    private QueryService service;

    @GetMapping("/{searchText}")
    public List<String> getEmojis(@PathVariable String searchText)
    {
        List<Emoji> emojis = service.getEmojis(searchText);
        List<String> emoji = new ArrayList<>();

        if(emojis.isEmpty())
        {
            emoji.add("Emoji not found!!");
        }
        else {
            for (Emoji emoticon : emojis) {
                emoji.add(emoticon.getEmoji());
            }
        }
        return emoji;
    }
}
